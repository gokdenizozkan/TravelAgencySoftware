package dev.patika.plus.gui;

import dev.patika.plus.entity.Hotel;
import dev.patika.plus.essential.Config;
import dev.patika.plus.operation.HotelOperation;
import dev.patika.plus.util.Dialog;
import dev.patika.plus.util.Util;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.JButton;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class HotelAddAndEditGui extends JFrame {
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
    private Hotel hotel;
    private ArrayList<JCheckBox> roomsPanelCheckBoxes;
    private ArrayList<JSpinner> roomsPanelSpinners;

    public HotelAddAndEditGui() {
        init();
        initActions();
    }

    public HotelAddAndEditGui(int hotelId) {
        if (hotelId == -1) {
            Dialog.getPremades().displayError();
            return;
        }
        init();
        initRoomsPanelComponents();
        initActions();

        this.hotel = HotelOperation.retrieve(hotelId);
        fillFields(hotel);
    }

    private void init() {
        add(wrapper);
        setTitle("Hotel Configuration Panel - " + Config.Gui.TITLE);
        setSize(Config.Gui.WIDTH, Config.Gui.HEIGHT);
        setDefaultCloseOperation(Config.Gui.DEFAULT_CLOSE_OPERATION);

        int[] location = Util.center(getSize());
        setLocation(location[0], location[1]);

        setResizable(true);
        setVisible(true);
    }

    private void initRoomsPanelComponents() {
        roomsPanelCheckBoxes = new ArrayList<>();
        roomsPanelSpinners = new ArrayList<>();

        for (Component component : roomsPanel.getComponents()) {
            if (component instanceof JCheckBox) {
                roomsPanelCheckBoxes.add((JCheckBox) component);
            }
            else if (component instanceof JSpinner) {
                roomsPanelSpinners.add((JSpinner) component);
            }
        }


    }

    private void initActions() {
        submitButton.addActionListener(e -> {
            Hotel hotel = constructHotelFromFields();
            HotelOperation.add(hotel);
        });

        for (JCheckBox checkBox : roomsPanelCheckBoxes) {
            checkBox.addActionListener(e -> {
                int id = (int) checkBox.getClientProperty("correspondingEnumId");
                JSpinner spinner = roomsPanelSpinners.get(id);

                if (checkBox.isSelected()) {
                    spinner.setEnabled(true);
                }
                else {
                    spinner.setEnabled(false);
                }
            });
        }
    }

    private void fillFields(Hotel hotel) {
        nameTextField.setText(hotel.getName());
        provinceTextField.setText(hotel.getProvince());
        stateTextField.setText(hotel.getState());
        addressTextField.setText(hotel.getAddress());
        emailTextField.setText(hotel.getEmail());
        phoneNumberTextField.setText(hotel.getPhoneNumber());
        starsSlider.setValue(hotel.getStars());

        // .Facilities panel
        String facilities = hotel.getFacilities();
        if (!facilities.isEmpty()) {
            ArrayList<Integer> facilityIds = Util.parseIds(facilities);
            Util.setAsSelectedAccordingTo(facilityIds, facilitiesPanel);
        }

        // .Board types panel
        String boardTypes = hotel.getBoardTypes();
        if (!boardTypes.isEmpty()) {
            ArrayList<Integer> boardTypeIds = Util.parseIds(boardTypes);
            Util.setAsSelectedAccordingTo(boardTypeIds, boardTypesPanel);
        }

        // .Rooms panel
        String rooms = hotel.getAllRooms();
        if (!rooms.isEmpty()) {
            HashMap<Integer, Integer> roomsMap = Util.map(hotel.getAllRooms());
            ArrayList<Integer> roomIds = new ArrayList<>(roomsMap.keySet());
            Util.setAsSelectedAccordingTo(roomIds, roomsPanel);

            for (JSpinner spinner : roomsPanelSpinners) {
                int id = (Integer) spinner.getClientProperty("boundCheckBoxId");
                System.out.println(Arrays.toString(roomIds.toArray()));
                if (roomIds.contains(id)) {
                    spinner.setValue(roomsMap.get(id));
                }
            }
        }
        else roomsPanelSpinners.forEach(spinner -> spinner.setEnabled(false));
    }

    private Hotel constructHotelFromFields() {
        ArrayList<Object> hotelData = new ArrayList<>();

        if (hotel != null) hotelData.add(hotel.getId());
        else hotelData.add(-1);

        // Gets data from basic info
        hotelData.add(nameTextField.getText());
        hotelData.add(provinceTextField.getText());
        hotelData.add(stateTextField.getText());
        hotelData.add(addressTextField.getText());
        hotelData.add(emailTextField.getText());
        hotelData.add(phoneNumberTextField.getText());
        hotelData.add(starsSlider.getValue());

        // Gets data from current details
        // .Facilities panel
        Component[] facilitiesPanelComponents = facilitiesPanel.getComponents();
        ArrayList<Integer> facilityIds = Util.retrieveSelectedCheckboxIds(facilitiesPanelComponents);
        String facilities = Util.stringify(facilityIds);
        hotelData.add(facilities);

        // .Board types panel
        Component[] boardTypesPanelComponents = boardTypesPanel.getComponents();
        ArrayList<Integer> boardTypeIds = Util.retrieveSelectedCheckboxIds(boardTypesPanelComponents);
        String boardTypes = Util.stringify(boardTypeIds);
        hotelData.add(boardTypes);

        // .Rooms panel
        Component[] roomsPanelComponents = roomsPanel.getComponents();
        ArrayList<Integer> roomIds = Util.retrieveSelectedCheckboxIds(roomsPanelComponents);

        ArrayList<Integer> roomValues = new ArrayList<>();
        for (JSpinner spinner : roomsPanelSpinners) {
            if (spinner.isEnabled()) roomValues.add((Integer) spinner.getValue());
        }

        String rooms = Util.stringify(roomIds, roomValues);
        hotelData.add(rooms);

        return new Hotel(hotelData);
    }
}
