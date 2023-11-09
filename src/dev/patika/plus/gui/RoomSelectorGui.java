package dev.patika.plus.gui;

import dev.patika.plus.entity.Hotel;
import dev.patika.plus.entity.Room;
import dev.patika.plus.essential.Config;
import dev.patika.plus.operation.HotelOperation;
import dev.patika.plus.operation.PricingOperation;
import dev.patika.plus.operation.PropertyOperation;
import dev.patika.plus.operation.RoomOperation;
import dev.patika.plus.util.Dialog;
import dev.patika.plus.util.Html;
import dev.patika.plus.entity.Reservation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

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
    private JPanel roomOptionsJp;
    private JComboBox boardTypeJcb;
    private JLabel boardTypeJl;

    private int hotelId;
    private DefaultTableModel roomsTableModel;
    private String startDate;
    private String endDate;
    private Reservation reservation;
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


    public RoomSelectorGui(Reservation reservation) {
        this.reservation = reservation;
        this.hotelId = reservation.getHotelId();
        this.startDate = reservation.getStartDate();
        this.endDate = reservation.getEndDate();

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
        roomsJt.setDefaultEditor(Object.class, null);

        // board type
        HashMap<Integer, String> boardTypesMap = HotelOperation.retrieveBoardTypes(hotelId);
        boardTypesMap.values().forEach(boardTypeJcb::addItem);
        boardTypeJcb.setSelectedIndex(-1);
    }

    private void initActions() {
        // board type combobox
        boardTypeJcb.addActionListener(e -> {
            HashMap<String, Integer> boardTypesMapReversed = HotelOperation.retrieveBoardTypesReversed(hotelId);
            String selectedBoardType = boardTypeJcb.getSelectedItem().toString();
            int boardTypeId = boardTypesMapReversed.get(selectedBoardType);
            reservation.setBoardTypeId(boardTypeId);

            // load table
            roomsTableModel.setRowCount(0);
            for (Room room : RoomOperation.retrieveAvailables(hotelId, startDate, endDate)) {
                reservation.setRoomId(room.getId());
                Integer price = PricingOperation.calculatePrice(reservation);
                if (price == null) continue;

                roomsTableModel.addRow(new Object[]{
                        room.getId(),
                        room.getOfType(),
                        room.getBeds(),
                        room.getSize(),
                });
            }
        });

        // reserve
        proceedJb.addActionListener(e -> {
            int row = roomsJt.getSelectedRow();
            if (row == -1) {
                Dialog.getPremades().displayError("Please select a room.");
                return;
            }

            int roomId = (int) roomsJt.getValueAt(row, 0);
            int totalPrice = Integer.parseInt(priceInNumberJl.getText());

            reservation.setRoomId(roomId);
            reservation.setTotalPrice(totalPrice);
            new ReservationSummaryGui(reservation);
        });

        // table
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
                reservation.setRoomId(roomId);
                int price = PricingOperation.calculatePrice(reservation);
                priceInNumberJl.setText(String.valueOf(price));
            }
        });
    }
}
