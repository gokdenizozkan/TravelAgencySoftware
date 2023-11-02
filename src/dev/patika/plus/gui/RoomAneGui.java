package dev.patika.plus.gui;

import dev.patika.plus.entity.Room;
import dev.patika.plus.entity.Season;
import dev.patika.plus.essential.Config;
import dev.patika.plus.operation.PropertyOperation;
import dev.patika.plus.operation.RoomOperation;
import dev.patika.plus.operation.SeasonOperation;

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
        setSize(Config.Gui.WIDTH, Config.Gui.HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void initFields() {
        // type
        for (String type : PropertyOperation.retrieveNames("room_type")) {
            ofTypeJcb.addItem(type);
        }

        // season
        seasonJcb.addItem("Select a season");
        ArrayList<Season> seasons = SeasonOperation.retrieveAll(hotelId);
        for (Season season : seasons) {
            seasonJcb.addItem(season.getName());
        }
        initSeasonActionListener(seasons);

        // table
        Object[] headers = {"Age", "Price"};
        pricingTableModel = new DefaultTableModel(headers, 0);
        pricingJt.setModel(pricingTableModel);
    }

    private void initSeasonActionListener(ArrayList<Season> seasons) {
        seasonJcb.addItemListener(e -> {
            Season season = seasons.get(seasonJcb.getSelectedIndex() - 1);
            seasonJcb.putClientProperty("selectedItemId", season.getId());
        });

    }

    private void initActions() {
        facilitiesJb.addActionListener(e -> {
            new PropertyAneGui(facilitiesJtf, "room_facility");
        });

        submitJb.addActionListener(e -> {
            String ofType = (String) ofTypeJcb.getSelectedItem();
            int beds = (int) bedsJs.getValue();
            int stock = (int) stockJs.getValue();
            int size = Integer.parseInt(sizeJtf.getText());
            String facilities = facilitiesJtf.getClientProperty("representativeIds").toString();
            HashMap<String, Integer> pricing = new HashMap<>();
            for (int i = 0; i < pricingTableModel.getRowCount(); i++) {
                pricing.put(pricingTableModel.getValueAt(i, 0).toString(),
                        Integer.parseInt(pricingTableModel.getValueAt(i, 1).toString()));
            }
            int seasonId = (int) seasonJcb.getClientProperty("selectedItemId");

            Room room = new Room(hotelId, ofType, beds, stock, size, facilities, seasonId, pricing.get("Adult"),
                    pricing.get("Child"));
            RoomOperation.add(room);
        });
    }

    private void loadPricing() {
        pricingTableModel.setRowCount(0);
        for (String ageClassifier : PropertyOperation.retrieveNames("age_classifier")) {
            pricingTableModel.addRow(new Object[]{ageClassifier, 0});
        }
    }
}
