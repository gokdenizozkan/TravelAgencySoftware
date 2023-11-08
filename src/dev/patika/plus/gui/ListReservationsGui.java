package dev.patika.plus.gui;

import dev.patika.plus.essential.Config;
import dev.patika.plus.operation.HotelOperation;
import dev.patika.plus.operation.ReservationOperation;
import dev.patika.plus.operation.RoomOperation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;

public class ListReservationsGui extends JFrame {
    private JPanel wrapper;
    private JPanel reservationsJp;
    private JScrollPane reservationsJsp;
    private JTable reservationsJt;
    private DefaultTableModel reservationsTableModel;
    private JPopupMenu reservationsPopupMenu;
    private JMenuItem reservationsDeleteMenuItem;

    public ListReservationsGui() {
        init();
        initFields();
        initActions();
        loadReservations();
    }

    private void init() {
        add(wrapper);
        setTitle("Reservation Listing Panel - " + Config.Gui.TITLE);
        setSize(Config.Gui.WIDTH, Config.Gui.HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initFields() {
        // table
        Object[] headers = {"Id", "Contact Name", "Contact Phone Number", "Contact Email",
                "Hotel Name", "Hotel Address", "Hotel Phone Number", "Room Id", "Room Type",
                "Beds", "Total Price", "Check In Date", "Check Out Date"};
        reservationsTableModel = new DefaultTableModel(headers, 0);
        reservationsJt.setModel(reservationsTableModel);

        // popup
        reservationsPopupMenu = new JPopupMenu();
        reservationsDeleteMenuItem = new JMenuItem("Delete");
        reservationsPopupMenu.add(reservationsDeleteMenuItem);
        reservationsJt.setComponentPopupMenu(reservationsPopupMenu);
    }

    private void initActions() {
        reservationsDeleteMenuItem.addActionListener(e -> {
            int selectedRow = reservationsJt.getSelectedRow();
            if (selectedRow == -1) return;
            int id = Integer.parseInt(reservationsTableModel.getValueAt(selectedRow, 0).toString());
            ReservationOperation.delete(id)
                    .handleResponse();
            loadReservations();
        });
    }

    private void loadReservations() {
        reservationsTableModel.setRowCount(0);
        ReservationOperation.retrieveAll().forEach(reservation -> reservationsTableModel.addRow(new Object[]{
                reservation.getId(),
                reservation.getContactName(),
                reservation.getContactPhoneNumber(),
                reservation.getContactEmail(),
                HotelOperation.retrieve(reservation.getHotelId()).getName(),
                HotelOperation.retrieve(reservation.getHotelId()).getAddress(),
                HotelOperation.retrieve(reservation.getHotelId()).getPhoneNumber(),
                reservation.getRoomId(),
                RoomOperation.retrieve(reservation.getRoomId()).getOfType(),
                RoomOperation.retrieve(reservation.getRoomId()).getBeds(),
                reservation.getTotalPrice(),
                reservation.getStartDate(),
                reservation.getEndDate()
        }));
    }
}
