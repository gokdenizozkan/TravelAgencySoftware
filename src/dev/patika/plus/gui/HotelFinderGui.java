package dev.patika.plus.gui;

import dev.patika.plus.entity.Hotel;
import dev.patika.plus.entity.Reservation;
import dev.patika.plus.entity.Season;
import dev.patika.plus.essential.Config;
import dev.patika.plus.operation.HotelOperation;
import dev.patika.plus.operation.RoomAvailabilityOperation;
import dev.patika.plus.operation.SeasonOperation;
import dev.patika.plus.util.Date;
import dev.patika.plus.util.Dialog;
import dev.patika.plus.util.Util;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;

public class HotelFinderGui extends JFrame {
    private JPanel wrapper;
    private JPanel searchOptionsJp;
    private JComboBox provinceJcb;
    private JComboBox stateJcb;
    private JLabel provinceJl;
    private JLabel stateJl;
    private JPanel checkInDateJp;
    private JComboBox checkInYearJcb;
    private JComboBox checkInMonthJcb;
    private JComboBox checkInDayJcb;
    private JPanel checkOutDateJp;
    private JComboBox checkOutYearJcb;
    private JComboBox checkOutMonthJcb;
    private JComboBox checkOutDayJcb;
    private JLabel checkInJl;
    private JPanel datesJp;
    private JPanel hotelsJp;
    private JScrollPane hotelsJsp;
    private JTable hotelsJt;
    private JButton findJb;
    private JLabel dateInJl;
    private JLabel dateOutJl;
    private JPanel guestsJp;
    private JLabel adultJl;
    private JSpinner adultJs;
    private JLabel childJl;
    private JSpinner childJs;
    private DefaultTableModel hotelsTableModel;

    public HotelFinderGui() {
        init();
        initFieldsAndFieldActions();
        initActions();
    }

    private void init() {
        add(wrapper);
        setTitle("Hotel Finder - " + Config.Gui.TITLE);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initFieldsAndFieldActions() {
        // provinceJcb
        for (String province : HotelOperation.retrieveProvinces()) {
            provinceJcb.addItem(province);
        }
        provinceJcb.setSelectedIndex(-1);

        // stateJcb
        provinceJcb.addActionListener(e -> {
            stateJcb.removeAllItems();
            for (String state : HotelOperation.retrieveStates(provinceJcb.getSelectedItem().toString())) {
                stateJcb.addItem(state);
            }
            stateJcb.setSelectedIndex(-1);
        });

        // checkInDate and checkOutDate
        readyDatePicker(checkInYearJcb, checkInMonthJcb, checkInDayJcb);
        readyDatePicker(checkOutYearJcb, checkOutMonthJcb, checkOutDayJcb);

        // hotels table
        Object[] headers = {"ID", "Name", "Province", "State", "Stars", "Address", "Phone"};
        hotelsTableModel = new DefaultTableModel(headers, 0);
        hotelsJt.setModel(hotelsTableModel);
        hotelsJt.setDefaultEditor(Object.class, null);
    }

    private void readyDatePicker(JComboBox yearJcb, JComboBox monthJcb, JComboBox dayJcb) {
        for (String year : Date.getYears(2023, 2033)) {
            yearJcb.addItem(year);
        }
        yearJcb.setSelectedIndex(-1);
        for (String month : Date.getMonths()) {
            monthJcb.addItem(month);
        }
        monthJcb.setSelectedIndex(-1);

        monthJcb.addActionListener(e -> {
            dayJcb.removeAllItems();
            String year = yearJcb.getSelectedItem().toString();
            String month = monthJcb.getSelectedItem().toString();
            for (String day : Date.getDays(year, month)) {
                dayJcb.addItem(day);
            }
            dayJcb.setSelectedIndex(-1);
        });

    }

    private void initActions() {
        // değerlendirme formu 13 - değerlendirme formu 14
        findJb.addActionListener(e -> {
            // fill
            boolean filled = Util.isAllComponentsFilled(provinceJcb, stateJcb, checkInYearJcb, checkInMonthJcb,
                    checkInDayJcb, checkOutYearJcb, checkOutMonthJcb, checkOutDayJcb, adultJs, childJs);
            if (!filled) {
                Dialog.of(Dialog.Type.MESSAGE)
                        .withMessage("Please fill all fields.")
                        .withTitle("Error")
                        .display();
                return;
            }

            String province = provinceJcb.getSelectedItem().toString();
            String state = stateJcb.getSelectedItem().toString();
            String checkInDate = Date.ify(checkInYearJcb, checkInMonthJcb, checkInDayJcb);
            String checkOutDate = Date.ify(checkOutYearJcb, checkOutMonthJcb, checkOutDayJcb);

            // date check
            if (checkInDate.compareTo(checkOutDate) > 0) {
                Dialog.of(Dialog.Type.MESSAGE)
                        .withMessage("Check-in date cannot be after check-out date.")
                        .withTitle("Error")
                        .display();
                return;
            }

            hotelsTableModel.setRowCount(0);
            for (Hotel hotel : HotelOperation.retrieveBy(province, state)) {
                // stock check by RoomOpearion.isRoomAvailable
                // if not available, continue
                // if available, add to table
                boolean available = RoomAvailabilityOperation.isAvailable(hotel.getId(), checkInDate, checkOutDate);
                if (!available) continue;

                // SEASON AVAILABILTY CHECK
                HashSet<Season> seasons = SeasonOperation.retrieveByPeriod(hotel.getId(), checkInDate, checkOutDate);
                if (seasons == null) continue;

                hotelsTableModel.addRow(new Object[]{
                        hotel.getId(),
                        hotel.getName(),
                        hotel.getProvince(),
                        hotel.getState(),
                        hotel.getStars(),
                        hotel.getAddress(),
                        hotel.getPhoneNumber()
                });
            }
        });
        hotelsJt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // double click
                if (e.getClickCount() == 2) {
                    int row = hotelsJt.getSelectedRow();
                    if (row == -1) return;
                    int hotelId = Integer.parseInt(hotelsJt.getValueAt(row, 0).toString());
                    Reservation reservation = Reservation.reserve()
                            .withHotelId(hotelId)
                            .withAdultGuestCount((int) adultJs.getValue())
                            .withChildGuestCount((int) childJs.getValue())
                            .withStartDate(Date.ify(checkInYearJcb, checkInMonthJcb, checkInDayJcb))
                            .withEndDate(Date.ify(checkOutYearJcb, checkOutMonthJcb, checkOutDayJcb));
                    new RoomSelectorGui(reservation);
                }
            }
        });
    }
}
