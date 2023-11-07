package dev.patika.plus.gui;

import dev.patika.plus.essential.Config;

import javax.swing.*;

public class ReservationGui extends JFrame {
    private JPanel wrapper;
    private JButton addReservationJb;
    private JButton listReservationsJb;

    public ReservationGui() {
        init();
        initActions();
    }

    private void init() {
        add(wrapper);
        setTitle("Reservation Panel - " + Config.Gui.TITLE);
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initActions() {
        addReservationJb.addActionListener(e -> new HotelFinderGui());
        listReservationsJb.addActionListener(e -> new ListReservationsGui());
    }

    public static void main(String[] args) {
        new ReservationGui();
    }
}
