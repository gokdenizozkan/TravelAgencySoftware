package dev.patika.plus.util;

import javax.swing.*;

public class Dialog {
    private FormedDialog<?> formedDialog;
    private PremadeDialog premadeDialog;

    private Dialog(Type type) {
        if (type == null) {
            premadeDialog = new PremadeDialog();
            return;
        }

        switch (type) {
            case MESSAGE -> formedDialog = new FormedDialog<>(type);
            case INPUT -> formedDialog = new FormedDialog<String>(type);
            case CONFIRM -> formedDialog = new FormedDialog<Integer>(type);
        }
    }

    public static FormedDialog<?> of(Type type) {
        return new Dialog(type).formedDialog;
    }

    public static PremadeDialog getPremades() {
        return new Dialog(null).premadeDialog;
    }

    /**
     * FormedDialog should be accessed via {@link Dialog#of(Type)}.<br><br>
     * This class is not intended to be used directly.
     * It is only public because of the Java's inner class access rules.
     */
    public static class FormedDialog<ReturnType> {
        protected Type type;
        protected JComponent parentComponent;
        protected String message;
        protected String title;
        protected int behaviourType = BehaviourType.PLAIN.get();

        private FormedDialog(Type type) {
            this.type = type;
        }

        public FormedDialog<ReturnType> withParent(JComponent parentComponent) {
            this.parentComponent = parentComponent;
            return this;
        }

        public FormedDialog<ReturnType> withMessage(String message) {
            this.message = message;
            return this;
        }

        public FormedDialog<ReturnType> withTitle(String title) {
            this.title = title;
            return this;
        }

        public FormedDialog<ReturnType> withBehaviour(BehaviourType behaviourType) {
            this.behaviourType = behaviourType.get();
            return this;
        }

        /**
         * Displays the dialog.
         * @return <br>
         *     <b>String</b>, if type is {@link Type#INPUT}<br>
         *     <b>Integer</b>, if type is {@link Type#CONFIRM}<br>
         *     <b>null</b>, if type is {@link Type#MESSAGE}<br>
         */
        public ReturnType display() {
            ReturnType value = null;
            switch (type) {
                case MESSAGE -> JOptionPane.showMessageDialog(parentComponent, message, title, behaviourType);
                case INPUT -> {
                    String s = JOptionPane.showInputDialog(parentComponent, message, title, behaviourType);
                    value = (ReturnType) s;
                }
                case CONFIRM -> {
                    Integer i = JOptionPane.showConfirmDialog(parentComponent, message, title, behaviourType);
                    value = (ReturnType) i;
                }
            }
            return value;
        }
    }

    public static class PremadeDialog {
        public void displayError() {
            JOptionPane.showMessageDialog(null, "An error occured during the process.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        public void displayError(String process) {
            JOptionPane.showMessageDialog(null, "An error occured during the " + process + " process.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        public void displaySuccess() {
            JOptionPane.showMessageDialog(null, "The process has been completed successfully.", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public enum Type {
        MESSAGE, INPUT, CONFIRM
    }

    public enum BehaviourType {
        ERROR, INFORMATION, WARNING, QUESTION, PLAIN;

        public int get() {
            return switch (ordinal()) {
                case 0 -> JOptionPane.ERROR_MESSAGE;
                case 1 -> JOptionPane.INFORMATION_MESSAGE;
                case 2 -> JOptionPane.WARNING_MESSAGE;
                case 3 -> JOptionPane.QUESTION_MESSAGE;
                default -> JOptionPane.PLAIN_MESSAGE;
            };
        }
    }

    public enum OptionType {
        YES_NO, YES_NO_CANCEL, OK_CANCEL, DEFAULT;

        public int get() {
            return switch (ordinal()) {
                case 0 -> JOptionPane.YES_NO_OPTION;
                case 1 -> JOptionPane.YES_NO_CANCEL_OPTION;
                case 2 -> JOptionPane.OK_CANCEL_OPTION;
                default -> JOptionPane.DEFAULT_OPTION;
            };
        }
    }


}
