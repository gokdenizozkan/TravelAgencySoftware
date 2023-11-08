package dev.patika.plus.gui;

import dev.patika.plus.entity.Season;
import dev.patika.plus.essential.Config;
import dev.patika.plus.essential.Database;
import dev.patika.plus.operation.SeasonOperation;
import dev.patika.plus.util.Date;
import dev.patika.plus.util.Util;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SeasonAneGui extends JFrame {
    private JPanel wrapper;
    private JPanel fieldsJp;
    private JLabel aneingJl;
    private JTextField nameJtf;
    private JLabel nameJl;
    private JComboBox startYearJcb;
    private JComboBox startMonthJcb;
    private JComboBox startDayJcb;
    private JPanel nameJp;
    private JPanel startDateJp;
    private JLabel startDateJl;
    private JPanel endDateJp;
    private JLabel endDateJl;
    private JComboBox endYearJcb;
    private JComboBox endMonthJcb;
    private JComboBox endDayJcb;
    private JTable priceJt;
    private JPanel startJp;
    private JPanel endJp;
    private JPanel seasonsJp;
    private JScrollPane seasonsJsp;
    private JTable seasonsJt;
    private JPanel seasonJp;
    private JButton updateJb;
    private JButton addJb;
    private JButton deleteJb;
    private DefaultTableModel seasonsTableModel;
    private int hotelId;

    public SeasonAneGui(int hotelId) {
        this.hotelId = hotelId;

        init();
        initFields();
        initActions();

        loadSeasons();
    }

    private void init() {
        add(wrapper);
        setTitle("Season Add and Edit Panel - " + Config.Gui.TITLE);
        setSize(Config.Gui.WIDTH, Config.Gui.HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void initFields() {
        // table
        Object[] headers = {"Id", "Name", "Start", "End"};
        seasonsTableModel = new DefaultTableModel(headers, 0);
        seasonsJt.setModel(seasonsTableModel);
        seasonsJt.removeColumn(seasonsJt.getColumnModel().getColumn(0));

        // date comboboxes
        readyDatePicker(startYearJcb, startMonthJcb, startDayJcb);
        readyDatePicker(endYearJcb, endMonthJcb, endDayJcb);
    }

    private void readyDatePicker(JComboBox yearJcb, JComboBox monthJcb, JComboBox dayJcb) {
        yearJcb.addItem("");
        monthJcb.addItem("");
        dayJcb.addItem("");
        for (String year : Date.getYears(2023, 2033)) yearJcb.addItem(year);
        for (String month : Date.getMonths()) monthJcb.addItem(month);
        for (String day : Date.getDays()) dayJcb.addItem(day);
    }

    private void initActions() {
        seasonsJt.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = seasonsJt.getSelectedRow();
                if (selectedRow == -1) return;

                int id = (int) seasonsJt.getModel().getValueAt(selectedRow, 0);
                String name = (String) seasonsJt.getModel().getValueAt(selectedRow, 1);
                String startDate = (String) seasonsJt.getModel().getValueAt(selectedRow, 2);
                String endDate = (String) seasonsJt.getModel().getValueAt(selectedRow, 3);

                setFieldsOnPress(id, name, startDate, endDate);
            }
        });

        addJb.addActionListener(e -> {
            String name = nameJtf.getText();
            String startDate = Date.ify(startYearJcb, startMonthJcb, startDayJcb);
            String endDate = Date.ify(endYearJcb, endMonthJcb, endDayJcb);

            SeasonOperation.add(hotelId, name, startDate, endDate)
                    .handleResponse();
            loadSeasons();
        });

        updateJb.addActionListener(e -> {
            int seasonId = (int) updateJb.getClientProperty("id");
            String name = nameJtf.getText();
            String startDate = Date.ify(startYearJcb, startMonthJcb, startDayJcb);
            String endDate = Date.ify(endYearJcb, endMonthJcb, endDayJcb);
            SeasonOperation.update(seasonId, name, startDate, endDate)
                    .handleResponse();
            loadSeasons();
        });

        deleteJb.addActionListener(e -> {
            int seasonId = (int) updateJb.getClientProperty("id");
            SeasonOperation.delete(seasonId)
                    .handleResponse();
            loadSeasons();
        });
    }

    private void loadSeasons() {
        seasonsTableModel.setRowCount(0);
        String query = "SELECT * FROM season WHERE hotel_id = ?";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, hotelId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Season season = new Season(resultSet);
                Object[] row = {season.getId(), season.getName(), season.getStart(), season.getEnd()};
                seasonsTableModel.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(preparedStatement, resultSet);
        }
    }

    private void setFieldsOnPress(int id, String name, String startDate, String endDate) {
        // id and name
        nameJtf.setText(name);
        updateJb.putClientProperty("id", id);

        // dates
        // .start
        String[] startDateParts = Date.split(startDate);
        startYearJcb.setSelectedItem(startDateParts[0]);
        startMonthJcb.setSelectedItem(startDateParts[1]);
        startDayJcb.setSelectedItem(startDateParts[2]);
        // .end
        String[] endDateParts = Date.split(endDate);
        endYearJcb.setSelectedItem(endDateParts[0]);
        endMonthJcb.setSelectedItem(endDateParts[1]);
        endDayJcb.setSelectedItem(endDateParts[2]);
    }

    public static void main(String[] args) {
        new SeasonAneGui(1);
    }
}
