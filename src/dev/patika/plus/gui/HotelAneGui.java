package dev.patika.plus.gui;

import dev.patika.plus.entity.Hotel;
import dev.patika.plus.essential.Config;
import dev.patika.plus.operation.HotelOperation;
import dev.patika.plus.operation.PropertyOperation;
import dev.patika.plus.quality.Parsable;
import dev.patika.plus.util.Dialog;
import dev.patika.plus.util.Util;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JButton;

import java.util.ArrayList;

public class HotelAneGui extends JFrame {
    private JPanel wrapper;
    private JTextField nameJtf;
    private JLabel nameJl;
    private JLabel provinceJl;
    private JTextField provinceJtf;
    private JLabel stateJl;
    private JTextField stateJtf;
    private JLabel addressJl;
    private JTextField addressJtf;
    private JLabel emailJl;
    private JTextField emailJtf;
    private JLabel phoneNumberJl;
    private JTextField phoneNumberJtf;
    private JSlider starsJs;
    private JLabel starsJl;
    private JPanel basicInfoJp;
    private JLabel basicInfoJl;
    private JPanel currentDetailsJp;
    private JLabel currentDetailsJl;
    private JPanel roomsPanel;
    private JButton submitJb;
    private JPanel aneJp;
    private JLabel facilitiesJl;
    private JTextField facilitiesJtf;
    private JButton facilitiesJb;
    private JLabel boardTypesJl;
    private JTextField boardTypesJtf;
    private JButton boardTypesJb;
    private Hotel hotel;

    public HotelAneGui() {
        init();
        initFields();
        initActions();
    }

    public HotelAneGui(int hotelId) {
        if (hotelId == -1) {
            Dialog.getPremades().displayError();
            return;
        }

        init();
        initFields();
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

    private void initFields() {
        if (facilitiesJtf.getClientProperty("representativeIds") == null)
            facilitiesJtf.putClientProperty("representativeIds", "");
        if (boardTypesJtf.getClientProperty("representativeIds") == null)
            boardTypesJtf.putClientProperty("representativeIds", "");
    }

    private void initActions() {
        // details
        facilitiesJb.addActionListener(e -> {
            if (facilitiesJtf.getText().isEmpty())
                new PropertyAneGui(facilitiesJtf, "hotel_facility");
            else {
                String representativeIds = facilitiesJtf.getClientProperty("representativeIds").toString();
                ArrayList<Integer> ids = Parsable.parseIntegerListStatic(representativeIds, ",");
                new PropertyAneGui(facilitiesJtf, "hotel_facility", ids);
            }
        });

        boardTypesJb.addActionListener(e -> {
            if (boardTypesJtf.getText().isEmpty())
                new PropertyAneGui(boardTypesJtf, "board_type");
            else {
                String representativeIds = boardTypesJtf.getClientProperty("representativeIds").toString();
                ArrayList<Integer> ids = Parsable.parseIntegerListStatic(representativeIds, ",");
                new PropertyAneGui(boardTypesJtf, "board_type", ids);
            }
        });

        submitJb.addActionListener(e -> {
            Hotel hotel = constructHotelFromFields();
            HotelOperation.add(hotel)
                    .handleResponse();
        });
    }

    private void fillFields(Hotel hotel) {
        nameJtf.setText(hotel.getName());
        provinceJtf.setText(hotel.getProvince());
        stateJtf.setText(hotel.getState());
        addressJtf.setText(hotel.getAddress());
        emailJtf.setText(hotel.getEmail());
        phoneNumberJtf.setText(hotel.getPhoneNumber());
        starsJs.setValue(hotel.getStars());
        fillTextField(facilitiesJtf, hotel.getFacilities());
        fillTextField(boardTypesJtf, hotel.getBoardTypes());
    }

    private Hotel constructHotelFromFields() {
        ArrayList<Object> hotelData = new ArrayList<>();

        if (hotel != null) hotelData.add(hotel.getId());
        else hotelData.add(-1);

        // Gets data from basic info
        hotelData.add(nameJtf.getText());
        hotelData.add(provinceJtf.getText());
        hotelData.add(stateJtf.getText());
        hotelData.add(addressJtf.getText());
        hotelData.add(emailJtf.getText());
        hotelData.add(phoneNumberJtf.getText());
        hotelData.add(starsJs.getValue());
        hotelData.add(facilitiesJtf.getClientProperty("representativeIds").toString());
        hotelData.add(boardTypesJtf.getClientProperty("representativeIds").toString());

        return new Hotel(hotelData);
    }

    /**
     * Fills the text field and its custom client property
     * with the given representative ids and their corresponding names.
     *
     * @param textField         The text field to be filled.
     * @param representativeIds The string that holds representative ids.
     */
    private void fillTextField(JTextField textField, String representativeIds) {
        if (representativeIds.isEmpty()) return;
        textField.putClientProperty("representativeIds", representativeIds);

        ArrayList<Integer> ids = Parsable.parseIntegerListStatic(representativeIds, ",");
        textField.setText(PropertyOperation.retrieveNames(ids).toString());
        textField.putClientProperty("representativeIds", representativeIds);
    }
}
