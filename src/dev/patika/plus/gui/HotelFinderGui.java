package dev.patika.plus.gui;

import dev.patika.plus.entity.Hotel;
import dev.patika.plus.essential.Config;
import dev.patika.plus.operation.HotelOperation;
import dev.patika.plus.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        Object[] headers = {"ID", "Name", "Province", "State", "Address", "Phone"};
        hotelsTableModel = new DefaultTableModel(headers, 0);
        hotelsJt.setModel(hotelsTableModel);
    }

    private void readyDatePicker(JComboBox yearJcb, JComboBox monthJcb, JComboBox dayJcb) {
        for (String year : Date.getYears(2023, 2033)) {
            yearJcb.addItem(year);
        }
        for (String month : Date.getMonths()) {
            monthJcb.addItem(month);
        }
        monthJcb.addActionListener(e -> {
            dayJcb.removeAllItems();
            String year = yearJcb.getSelectedItem().toString();
            String month = monthJcb.getSelectedItem().toString();
            for (String day : Date.getDays(year, month)) {
                dayJcb.addItem(day);
            }
        });
    }

    private void initActions() {
        findJb.addActionListener(e -> {
            String province = provinceJcb.getSelectedItem().toString();
            String state = stateJcb.getSelectedItem().toString();
            String checkInDate = Date.ify(checkInYearJcb, checkInMonthJcb, checkInDayJcb);
            String checkOutDate = Date.ify(checkOutYearJcb, checkOutMonthJcb, checkOutDayJcb);
            hotelsTableModel.setRowCount(0);
            for (Hotel hotel : HotelOperation.retrieveBy(province, state)) {
                // TODO stock check by RoomOpearion.isRoomAvailable :: after reservation table is configured
                // if available, add to table
                hotelsTableModel.addRow(new Object[]{
                        hotel.getId(),
                        hotel.getName(),
                        hotel.getProvince(),
                        hotel.getState(),
                        hotel.getAddress(),
                        hotel.getPhoneNumber()
                });
            }
        });
    }

    public static void main(String[] args) {
        new HotelFinderGui();
    }
}
