package dev.patika.plus.gui;

import dev.patika.plus.operation.UserOperation;
import dev.patika.plus.util.Dialog;

import javax.swing.*;
import java.awt.*;

public class RegisterGui extends JFrame {
    private JPanel wrapper;
    private JTextField usernameJtf;
    private JPasswordField passwordJpf;
    private JComboBox ofTypeJcb;
    private JButton registerJb;
    private JLabel usernameJl;
    private JLabel passwordJl;
    private JLabel ofTypeJl;

    public RegisterGui(Component component, boolean firstUser, boolean adminAccess) {
        init(component);
        initFields(firstUser, adminAccess);
        initActions();

        if (firstUser) Dialog.of(Dialog.Type.MESSAGE)
                .withMessage("You are the first user. You will be registered as an admin.")
                .withTitle("First User")
                .display();
    }

    private void init(Component component) {
        add(wrapper);
        setTitle("Register Panel - " + dev.patika.plus.essential.Config.Gui.TITLE);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(component);
        setVisible(true);
    }

    private void initFields(boolean firstUser, boolean adminAccess) {
        for (String ofType : UserOperation.retrieveTypes()) ofTypeJcb.addItem(ofType);
        ofTypeJcb.setEnabled(false);

        if (firstUser) ofTypeJcb.setSelectedItem("admin");
        if (adminAccess) ofTypeJcb.setEnabled(true);
    }

    private void initActions() {
        registerJb.addActionListener(e -> {
            String username = usernameJtf.getText();
            String password = new String(passwordJpf.getPassword());
            String ofType = ofTypeJcb.getSelectedItem().toString();
            UserOperation.add(username, password, ofType);
            dispose();
        });
    }
}
