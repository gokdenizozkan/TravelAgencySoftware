package dev.patika.plus.gui;

import dev.patika.plus.essential.Config;
import dev.patika.plus.operation.HotelOperation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AneGui extends JFrame {
    private JPanel wrapper;
    private JPanel hotelsJp;
    private JScrollPane hotelsJsp;
    private JTable hotelsJt;
    private JPanel hotelOptionsJp;
    private JButton addHotelJb;
    private JPanel hotelActionsJp;
    private JLabel selectedHotelNameJl;
    private JButton addRoomJb;
    private JButton seasonManagementJb;


    private DefaultTableModel hotelsTableModel;
    private JPopupMenu hotelsJpm;
    private JMenuItem addJmi;
    private JMenuItem editJmi;
    private JMenuItem deleteJmi;
    private JMenuItem reloadTableJmi;


    public AneGui() {
        init();
        initFields();
        initActions();
    }

    private void init() {
        add(wrapper);
        setTitle("Hotel Panel - " + Config.Gui.TITLE);
        setSize(Config.Gui.WIDTH, Config.Gui.HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initFields() {
        // table
        Object[] headers = {"ID", "Name", "Province", "State", "Email", "Phone Number"};
        hotelsTableModel = new DefaultTableModel(headers, 0);
        hotelsJt.setModel(hotelsTableModel);
        loadHotels();

        // popup menu
        hotelsJpm = new JPopupMenu();
        addJmi = new JMenuItem("Add");
        editJmi = new JMenuItem("Edit");
        deleteJmi = new JMenuItem("Delete");
        reloadTableJmi = new JMenuItem("Reload Hotels");

        hotelsJpm.add(addJmi);
        hotelsJpm.add(editJmi);
        hotelsJpm.add(deleteJmi);
        hotelsJpm.add(reloadTableJmi);
        hotelsJt.setComponentPopupMenu(hotelsJpm);

        // label
        selectedHotelNameJl.setText(null);
    }

    private void initActions() {
        // table
        hotelsJt.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = hotelsJt.getSelectedRow();
                if (selectedRow == -1) {
                    selectedHotelNameJl.setText(null);
                    return;
                }
                int id = (int) hotelsJt.getValueAt(selectedRow, 0);
                String name = (String) hotelsJt.getValueAt(selectedRow, 1);
                selectedHotelNameJl.setText(name);
            }
        });

        // button
        addHotelJb.addActionListener(e -> new HotelAneGui());
        addRoomJb.addActionListener(e -> {
            int selectedRow = hotelsJt.getSelectedRow();
            if (selectedRow == -1) return;
            int id = (int) hotelsJt.getValueAt(selectedRow, 0);
            new RoomAneGui(id);
        });
        seasonManagementJb.addActionListener(e -> {
            int selectedRow = hotelsJt.getSelectedRow();
            if (selectedRow == -1) return;
            int id = (int) hotelsJt.getValueAt(selectedRow, 0);
            new SeasonAneGui(id);
        });

        // popup menu
        addJmi.addActionListener(e -> new HotelAneGui());
        editJmi.addActionListener(e -> {
            int selectedRow = hotelsJt.getSelectedRow();
            if (selectedRow == -1) return;
            int id = (int) hotelsJt.getValueAt(selectedRow, 0);
            new HotelAneGui(id);
        });
        deleteJmi.addActionListener(e -> {
            int selectedRow = hotelsJt.getSelectedRow();
            if (selectedRow == -1) return;
            int id = (int) hotelsJt.getValueAt(selectedRow, 0);
            HotelOperation.delete(id)
                    .handleResponse();
        });
        reloadTableJmi.addActionListener(e -> loadHotels());
    }

    private void loadHotels() {
        hotelsTableModel.setRowCount(0);
        HotelOperation.retrieveAll().forEach(hotel -> {
            Object[] row = {
                    hotel.getId(),
                    hotel.getName(),
                    hotel.getProvince(),
                    hotel.getState(),
                    hotel.getEmail(),
                    hotel.getPhoneNumber()
            };
            hotelsTableModel.addRow(row);
        });
    }
}
