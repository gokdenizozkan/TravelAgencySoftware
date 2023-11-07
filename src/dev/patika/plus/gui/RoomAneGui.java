package dev.patika.plus.gui;

import dev.patika.plus.entity.Room;
import dev.patika.plus.entity.Season;
import dev.patika.plus.essential.Config;
import dev.patika.plus.operation.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.HashMap;

public class RoomAneGui extends JFrame {
    private JPanel wrapper;
    private JPanel basicsJp;
    private JLabel ofTypeJl;
    private JComboBox ofTypeJcb;
    private JSpinner bedsJs;
    private JLabel bedsJl;
    private JLabel stockJl;
    private JSpinner stockJs;
    private JTextField sizeJtf;
    private JLabel sizeJl;
    private JLabel facilitiesJl;
    private JButton facilitiesJb;
    private JTextField facilitiesJtf;
    private JPanel facilitiesJp;
    private JPanel pricingJp;
    private JScrollPane pricingJsp;
    private JTable pricingJt;
    private JButton submitJb;
    private JComboBox seasonJcb;
    private JLabel seasonJl;
    private JComboBox boardTypeJcb;
    private JLabel boardTypeJl;
    private DefaultTableModel pricingTableModel;
    private int hotelId;

    public RoomAneGui(int hotelId) {
        this.hotelId = hotelId;

        init();
        initFields();
        initActions();
        loadPricing();
    }

    private void init() {
        add(wrapper);
        setTitle("Room Add and Edit Panel - " + Config.Gui.TITLE);
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void initFields() {
        // type
        for (String type : PropertyOperation.retrieveAllNames("room_type")) {
            ofTypeJcb.addItem(type);
        }

        // season
        ArrayList<Season> seasons = SeasonOperation.retrieveAll(hotelId);
        for (Season season : seasons) {
            seasonJcb.addItem(season.getName());
        }
        seasonJcb.setSelectedIndex(-1);
        initSeasonActionListener(seasons);

        // board type
        HashMap<Integer, String> facilities = HotelOperation.retrieveFacilities(hotelId);
        for (String facility : facilities.values()) {
            boardTypeJcb.addItem(facility);
        }
        boardTypeJcb.setSelectedIndex(-1);

        // table
        Object[] headers = {"Age", "Price"};
        pricingTableModel = new DefaultTableModel(headers, 0);
        pricingJt.setModel(pricingTableModel);
    }

    private void initSeasonActionListener(ArrayList<Season> seasons) {
        seasonJcb.addItemListener(e -> {
            Season season = seasons.get(seasonJcb.getSelectedIndex());
            seasonJcb.putClientProperty("selectedItemId", season.getId());
        });

    }

    private void initActions() {
        facilitiesJb.addActionListener(e -> new PropertyAneGui(facilitiesJtf, "room_facility"));

        submitJb.addActionListener(e -> {
            // room add
            String ofType = (String) ofTypeJcb.getSelectedItem();
            int beds = (int) bedsJs.getValue();
            int stock = (int) stockJs.getValue();
            int size = Integer.parseInt(sizeJtf.getText());
            String facilities = facilitiesJtf.getClientProperty("representativeIds").toString();
            Room room = new Room(hotelId, ofType, beds, stock, size, facilities);
            RoomOperation.add(room);

            // pricing add
            HashMap<String, Integer> pricing = new HashMap<>();
            for (int i = 0; i < pricingTableModel.getRowCount(); i++) {
                String age = pricingTableModel.getValueAt(i, 0).toString();
                int price = Integer.parseInt(pricingTableModel.getValueAt(i, 1).toString());

                pricing.put(age, price);
            }

            int seasonId = (int) seasonJcb.getClientProperty("selectedItemId");

            String selectedBoardType = (String) boardTypeJcb.getSelectedItem();
            int boardTypeId = HotelOperation.retrieveFacilitiesReversed(hotelId).get(selectedBoardType);

            PricingOperation.add(room.getId(), seasonId, boardTypeId, pricing.get("Adult"), pricing.get("Child"));
        });
    }

    private void loadPricing() {
        pricingTableModel.setRowCount(0);
        for (String ageClassifier : PropertyOperation.retrieveAllNames("age_classifier")) {
            pricingTableModel.addRow(new Object[]{ageClassifier, 0});
        }
    }

    public static void main(String[] args) {
        new RoomAneGui(1);
    }
}
