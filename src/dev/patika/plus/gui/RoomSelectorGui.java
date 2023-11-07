package dev.patika.plus.gui;

import dev.patika.plus.entity.Room;
import dev.patika.plus.essential.Config;
import dev.patika.plus.operation.PricingOperation;
import dev.patika.plus.operation.PropertyOperation;
import dev.patika.plus.operation.RoomOperation;
import dev.patika.plus.util.Html;
import dev.patika.plus.entity.Reservation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoomSelectorGui extends JFrame {
    private JPanel wrapper;
    private JPanel roomsJp;
    private JScrollPane roomsJsp;
    private JTable roomsJt;
    private JPanel pricingJp;
    private JLabel priceJl;
    private JButton proceedJb;
    private JPanel roomJp;
    private JEditorPane roomJep;
    private JLabel priceInNumberJl;

    private int hotelId;
    private DefaultTableModel roomsTableModel;
    private String startDate;
    private String endDate;
    private Reservation reservationIn;
    private String html =
            Html.form()
            .addHeading("Room Details", 1)
            .startParagraph()
            .addBold("Room Type:").add(" %s").newline()
            .addBold("Beds:").add(" %s").newline()
            .addBold("Size:").add(" %s").newline()
            .addBold("Facilities:").add(" %s")
            .endParagraph()
            .toString();


    public RoomSelectorGui(Reservation reservationIn) {
        this.reservationIn = reservationIn;
        this.hotelId = reservationIn.getHotelId();
        this.startDate = reservationIn.getStartDate();
        this.endDate = reservationIn.getEndDate();

        init();
        initFields();
        initActions();
    }

    private void init() {
        add(wrapper);
        setTitle("Room Selector - " + Config.Gui.TITLE);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initFields() {
        // table
        Object[] headers = {"Id", "Room Type", "Beds", "Size"};
        roomsTableModel = new DefaultTableModel(headers, 0);
        roomsJt.setModel(roomsTableModel);
        roomsJt.removeColumn(roomsJt.getColumnModel().getColumn(0));
        for (Room room : RoomOperation.retrieveAvailables(hotelId, startDate, endDate)) {
            roomsTableModel.addRow(new Object[]{
                    room.getId(),
                    room.getOfType(),
                    room.getBeds(),
                    room.getSize(),
            });
        }
    }

    private void initActions() {
        // reserve
        proceedJb.addActionListener(e -> {
            int row = roomsJt.getSelectedRow();
            int roomId = (int) roomsJt.getModel().getValueAt(row, 0);
            int totalPrice = Integer.parseInt(priceInNumberJl.getText());

            Reservation reservation = reservationIn.withRoomId(roomId);
            reservation = reservation.withTotalPrice(totalPrice);
            new ReservationGui(reservation);
        });

        roomsJt.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = roomsJt.getSelectedRow();
                int roomId = (int) roomsJt.getModel().getValueAt(row, 0);


                // Room JEditorPane
                roomJep.setEditable(false);
                Room room = RoomOperation.retrieve(roomId);
                roomJep.setContentType("text/html");
                roomJep.setText(String.format(html,
                        room.getOfType(),
                        room.getBeds(),
                        room.getSize(),
                        PropertyOperation.retrieveNames(room.getFacilitiesAsList())
                ));

                // pricing
                Reservation reservation = reservationIn.withRoomId(roomId);
                int price = PricingOperation.calculatePrice(reservation);
                priceInNumberJl.setText(String.valueOf(price));
            }
        });
    }

    public static void main(String[] args) {
        new RoomSelectorGui(Reservation.reserve()
                .withHotelId(1)
                .withStartDate("2024-01-01")
                .withEndDate("2024-01-02")
                .withAdultGuestCount(1)
                .withChildGuestCount(1)
        );
    }
}
