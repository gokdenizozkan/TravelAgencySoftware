package dev.patika.plus.gui;

import dev.patika.plus.essential.Config;

import javax.swing.*;

public class ManagementGui extends JFrame {
    private JPanel wrapper;
    private JButton aneJb;
    private JPanel buttonsJp;
    private JPanel aneJp;
    private JButton reservationJb;
    private JPanel reservationJpanel;

    public ManagementGui() {
        init();
        initActions();
    }

    private void init() {
        add(wrapper);
        setSize(400, 200);
        setTitle("Management" + Config.Gui.TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initActions() {
        aneJb.addActionListener(e -> new AneGui());

        reservationJb.addActionListener(e -> new ReservationGui());
    }

    public static void main(String[] args) {
        new ManagementGui();
    }
}

