package dev.patika.plus.gui;

import dev.patika.plus.entity.User;
import dev.patika.plus.operation.UserOperation;
import dev.patika.plus.util.Dialog;

import javax.swing.*;

public class LoginGui extends JFrame {
    private JPanel wrapper;
    private JTextField usernameJtf;
    private JPasswordField passwordJpf;
    private JButton loginJb;
    private JLabel usernameJl;
    private JLabel passwordJl;

    public LoginGui() {
        init();
        initActions();

        User[] admins = UserOperation.retrieveAll("admin");
        if (admins.length == 0) new RegisterGui(loginJb, true, false);
    }

    private void init() {
        add(wrapper);
        setTitle("Login Panel - " + dev.patika.plus.essential.Config.Gui.TITLE);
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initActions() {
        // değerlendirme formu 8
        loginJb.addActionListener(e -> {
            String username = usernameJtf.getText();
            String password = new String(passwordJpf.getPassword());
            User user = UserOperation.retrieve(username);
            if (user == null) Dialog.of(Dialog.Type.MESSAGE)
                    .withTitle("No user found!")
                    .withMessage("Please check the username you typed in.\nIt is not found.")
                    .withBehaviour(Dialog.BehaviourType.ERROR)
                    .display();

            else if (!user.getPassword().equals(password)) Dialog.of(Dialog.Type.MESSAGE)
                    .withTitle("Wrong password!")
                    .withMessage("The password you typed in seems invalid! Please try again.")
                    .withBehaviour(Dialog.BehaviourType.ERROR)
                    .display();

            else {
                dispose();
                if (user.getOfType().equals("admin")) {
                    new UserAneGui();
                } else {
                    new ManagementGui();
                }
            }
        });
    }
}
