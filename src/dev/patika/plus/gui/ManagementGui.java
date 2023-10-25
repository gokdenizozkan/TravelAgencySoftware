package dev.patika.plus.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dev.patika.plus.essential.Config;
import dev.patika.plus.operation.HotelOperation;
import dev.patika.plus.util.Util;

public class ManagementGui extends JFrame {
    private JPanel wrapper;
    private JPanel wrapperTop;
    private JPanel wrapperBottom;
    private JPanel wrapperUpperMiddle;
    private JPanel wrapperLowerMiddle;
    private JPanel wrapperFour;
    private JPanel wrapperOne;
    private JPanel wrapperThree;
    private JPanel wrapperTwo;
    private JScrollPane hotelsScrollPane;
    private JTable hotelsTable;
    private JPanel hotelListingOptionsPanel;
    private JCheckBox optionShowAvailablesCheckbox;
    private JTextField optionSearchByNameTextField;

    public ManagementGui() {
        init();
    }

    private void init() {
        add(wrapper);
        setTitle("Management Panel " + Config.Gui.TITLE);
        setSize(Config.Gui.WIDTH, Config.Gui.HEIGHT);
        setDefaultCloseOperation(Config.Gui.DEFAULT_CLOSE_OPERATION);

        int[] location = Util.center(getSize());
        setLocation(location[0], location[1]);

        setResizable(true);
        setVisible(true);
    }

    private void setLocationToCenter() {
        int[] location = Util.center(getSize());
        setLocation(location[0], location[1]);
    }


    // Wrapper Four, Hotel Listing
    private DefaultTableModel hotelsTableModel;
    private Object[] hotelsTableRow;
    private JPopupMenu hotelsTablePopupMenu;
    private void initHotelListing() {
        Object[] hotelsTableColumns = {"Name", "Province", "State", "Available Rooms"};
        hotelsTableModel = new DefaultTableModel(hotelsTableColumns, 0);
        hotelsTable.setModel(hotelsTableModel);
    }

    private void initHotelsTableActions() {
        hotelsTablePopupMenu = new JPopupMenu();
        JMenuItem addNewHotelMenuItem = new JMenuItem("Add New Hotel");
        JMenuItem editHotelInfoMenuItem = new JMenuItem("Edit Hotel Info");
        JMenuItem deleteHotelMenuItem = new JMenuItem("Delete Hotel");

        addNewHotelMenuItem.addActionListener(e -> new HotelAddAndEditGui());
        editHotelInfoMenuItem.addActionListener(e -> {
            int selectedRow = hotelsTable.getSelectedRow();
            int hotelId = (int) hotelsTable.getValueAt(selectedRow, 0);
            new HotelAddAndEditGui(hotelId);
        });
        deleteHotelMenuItem.addActionListener(e -> {
            int selectedRow = hotelsTable.getSelectedRow();
            if (selectedRow != -1) {
                int hotelId = (int) hotelsTable.getValueAt(selectedRow, 0);
                HotelOperation.delete(hotelId);
            }
        });

        hotelsTablePopupMenu.add(addNewHotelMenuItem);
        hotelsTablePopupMenu.add(editHotelInfoMenuItem);
        hotelsTablePopupMenu.add(deleteHotelMenuItem);

        hotelsTable.setComponentPopupMenu(hotelsTablePopupMenu);
    }

    // Wrapper Three, Hotel Details
}
