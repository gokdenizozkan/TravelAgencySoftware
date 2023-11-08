package dev.patika.plus.gui;

import dev.patika.plus.entity.Property;
import dev.patika.plus.essential.Database;
import dev.patika.plus.util.Util;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

public class PropertyAneGui extends JFrame {
    private JPanel wrapper;
    private JPanel availablesJp;
    private JLabel availablesJl;
    private JScrollPane availablesJsp;
    private JTable availablesJt;
    private JPanel actionsJp;
    private JButton addJb;
    private JButton removeJb;
    private JPanel selectedJp;
    private JLabel selectedJl;
    private JScrollPane selectedJsp;
    private JTable selectedJt;
    private JButton submitJb;
    private DefaultTableModel availablesTableModel;
    private DefaultTableModel selectedTableModel;
    private final JTextField originJtf;

    public PropertyAneGui(JTextField originJtf, String propertyType) {
        this.originJtf = originJtf;

        init();
        initTables();
        initialTableLoad(propertyType);
        initActions();
    }

    public PropertyAneGui(JTextField originJtf, String propertyType, ArrayList<Integer> initialSelectedPropertyIds) {
        this.originJtf = originJtf;

        init();
        initTables();
        initialTableLoad(propertyType, initialSelectedPropertyIds);
        initActions();
    }

    private void init() {
        setTitle("Property Management Panel");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(wrapper);
        setVisible(true);
    }

    private void initTables() {
        Object[] rows = {"ID", "NAME"};
        availablesTableModel = new DefaultTableModel(rows, 0);
        selectedTableModel = new DefaultTableModel(rows, 0);

        availablesJt.setModel(availablesTableModel);
        selectedJt.setModel(selectedTableModel);
    }

    /**
     * Loads all properties of given type to availables table.
     * Selected properties are not loaded as they are meant to be selected by the user.
     * @param propertyType Type of the properties to be loaded.
     *                     Either "hotel_facility", "room_facility" or "board_type".
     */
    private void initialTableLoad(String propertyType) {
        String query = "SELECT * FROM property WHERE of_type = ?";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Property property;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setString(1, propertyType);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                property = new Property(resultSet);
                availablesTableModel.addRow(property.constructRow());
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            Util.close(preparedStatement, resultSet);
        }
    }

    /**
     * Loads all properties of given type to availables table and selectedTable if their id is in initialSelectedPropertyIds.
     * @param propertyType Type of the properties to be loaded.
     * @param initialSelectedPropertyIds Ids of the properties to be loaded to selectedTable.
     */
    private void initialTableLoad(String propertyType, ArrayList<Integer> initialSelectedPropertyIds) {
        String query = "SELECT * FROM property WHERE of_type = ?";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Property property = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setString(1, propertyType);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                property = new Property(resultSet);
                if (!initialSelectedPropertyIds.contains(property.getId()))
                    availablesTableModel.addRow(property.constructRow());
                else
                    selectedTableModel.addRow(property.constructRow());
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            Util.close(preparedStatement, resultSet);
        }
    }

    private void initActions() {
        addJb.addActionListener(e -> {
            int selectedRow = availablesJt.getSelectedRow();
            if (selectedRow == -1) return;

            int id = (int) availablesTableModel.getValueAt(selectedRow, 0);
            String name = (String) availablesTableModel.getValueAt(selectedRow, 1);
            selectedTableModel.addRow(new Object[]{id, name});
            availablesTableModel.removeRow(selectedRow);
        });

        removeJb.addActionListener(e -> {
            int selectedRow = selectedJt.getSelectedRow();
            if (selectedRow != -1) return;

            int id = (int) selectedTableModel.getValueAt(selectedRow, 0);
            String name = (String) selectedTableModel.getValueAt(selectedRow, 1);
            availablesTableModel.addRow(new Object[]{id, name});
            selectedTableModel.removeRow(selectedRow);
        });

        submitJb.addActionListener(e -> {
            StringBuilder ids = new StringBuilder();
            StringBuilder names = new StringBuilder();

            for (int i = 0; i < selectedTableModel.getRowCount(); i++) {
                int id = (int) selectedTableModel.getValueAt(i, 0);
                String name = (String) selectedTableModel.getValueAt(i, 1);

                ids.append(id).append(",");
                names.append(name).append(", ");
            }

            originJtf.setText(names.toString());
            originJtf.putClientProperty("representativeIds", ids.toString());
            dispose();
        });
    }
}
