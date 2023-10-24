package dev.patika.plus.gui;

import javax.swing.*;

public class HotelAddAndEditGui {
    private JPanel wrapper;
    private JTextField nameTextField;
    private JLabel nameLabel;
    private JLabel provinceLabel;
    private JTextField provinceTextField;
    private JLabel stateLabel;
    private JTextField stateTextField;
    private JLabel addressLabel;
    private JTextField addressTextField;
    private JLabel emailLabel;
    private JTextField emailTextField;
    private JLabel phoneNumberLabel;
    private JTextField phoneNumberTextField;
    private JSlider starsSlider;
    private JLabel starsLabel;
    private JPanel basicInfoPanel;
    private JLabel basicInfoLabel;
    private JPanel currentDetailsPanel;
    private JLabel currentDetailsLabel;
    private JPanel facilitiesPanel;

    public HotelAddAndEditGui() {
        // TODO add hotel
    }

    public HotelAddAndEditGui(int hotelId) {
        if (hotelId == -1) {
            // Show error dialogue
        }
        // TODO edit hotel
    }
}
