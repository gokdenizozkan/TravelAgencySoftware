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
    private JCheckBox facility0CheckBox;
    private JCheckBox facility1CheckBox;
    private JCheckBox facility2CheckBox;
    private JCheckBox facility3CheckBox;
    private JCheckBox facility4CheckBox;
    private JCheckBox facility5CheckBox;
    private JCheckBox facility6CheckBox;
    private JLabel facilitiesLabel;
    private JPanel boardTypesPanel;
    private JCheckBox boardType0CheckBox;
    private JCheckBox boardType1CheckBox;
    private JCheckBox boardType2CheckBox;
    private JCheckBox boardType3CheckBox;
    private JCheckBox boardType4CheckBox;
    private JCheckBox boardType5CheckBox;
    private JCheckBox boardType6CheckBox;
    private JLabel boardTypesLabel;
    private JPanel roomsPanel;
    private JCheckBox singleRoomCheckBox;
    private JCheckBox doubleRoomCheckBox;
    private JCheckBox suiteRoomCheckBox;
    private JSpinner singleRoomSpinner;
    private JLabel roomsLabel;
    private JSpinner doubleRoomSpinner;
    private JSpinner suiteRoomSpinner;
    private JButton submitButton;

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
