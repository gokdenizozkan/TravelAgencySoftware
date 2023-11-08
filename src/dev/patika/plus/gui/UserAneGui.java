package dev.patika.plus.gui;

import dev.patika.plus.entity.User;
import dev.patika.plus.operation.UserOperation;
import dev.patika.plus.util.Dialog;
import dev.patika.plus.util.Util;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserAneGui extends JFrame {
    private JPanel wrapper;
    private JPanel usersJp;
    private JScrollPane usersJsp;
    private JTable usersJt;
    private JPanel userOperationsJp;
    private JPanel fieldsJp;
    private JLabel usernameJl;
    private JTextField usernameJtf;
    private JLabel passwordJl;
    private JPasswordField passwordJpf;
    private JLabel typeJl;
    private JComboBox typeJcb;
    private JButton submitJb;
    private DefaultTableModel usersTableModel;

    public UserAneGui() {
        init();
        initFields();
        initActions();
    }

    private void init() {
        add(wrapper);
        setTitle("Admin Panel - " + dev.patika.plus.essential.Config.Gui.TITLE);
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initFields() {
        // table
        Object[] headers = {"Id", "Username", "Password", "Type"};
        usersTableModel = new DefaultTableModel(headers, 0);
        usersJt.setModel(usersTableModel);
        loadUsers();

        // combo box
        for (String type : UserOperation.retrieveTypes()) typeJcb.addItem(type);
    }

    private void initActions() {
        // table
        usersJt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = usersJt.getSelectedRow();
                if (selectedRow == -1) return;
                usernameJtf.setText(usersTableModel.getValueAt(selectedRow, 1).toString());
                passwordJpf.setText(usersTableModel.getValueAt(selectedRow, 2).toString());
                typeJcb.setSelectedItem(usersTableModel.getValueAt(selectedRow, 3).toString());
            }
        });

        // submit
        submitJb.addActionListener(e -> {
            boolean filled = Util.isAllComponentsFilled(usernameJtf, passwordJpf, typeJcb);
            if (!filled) {
                Dialog.getPremades().displayError("Please fill all fields.");
                return;
            }
            String username = usernameJtf.getText();
            String password = new String(passwordJpf.getPassword());
            String type = typeJcb.getSelectedItem().toString();
            User user = new User(username, password, type);

            UserOperation.add(user)
                    .handleResponse();
            loadUsers();
        });
    }

    private void loadUsers() {
        usersTableModel.setRowCount(0);
        for (User user : UserOperation.retrieveAll()) {
            usersTableModel.addRow(new Object[]{
                    user.getId(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getOfType()
            });
        }
    }
}
