package dev.patika.plus.gui;

import dev.patika.plus.entity.Hotel;
import dev.patika.plus.entity.Room;
import dev.patika.plus.essential.Config;
import dev.patika.plus.operation.*;
import dev.patika.plus.util.Html;
import dev.patika.plus.entity.Reservation;

import javax.swing.*;

public class ReservationSummaryGui extends JFrame {
    private JPanel wrapper;
    private JPanel contactJp;
    private JLabel contactNameJl;
    private JTextField contactNameJtf;
    private JLabel contactPhoneNumberJl;
    private JTextField contactPhoneNumberJtf;
    private JLabel contactEmailJl;
    private JTextField contactEmailJtf;
    private JButton reserveJb;
    private JEditorPane summaryJep;
    private JPanel reserveJp;


    private Reservation reservation;
    private Room room;
    private Hotel hotel;
    private String html;


    public ReservationSummaryGui(Reservation reservation) {
        this.reservation = reservation;
        this.room = RoomOperation.retrieve(reservation.getRoomId());
        this.hotel = HotelOperation.retrieve(room.getHotelId());


        init();
        initHtml();
        initFields();
        initActions();
    }

    private void init() {
        add(wrapper);
        setTitle("Reservation Panel - " + Config.Gui.TITLE);
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initHtml() {
        html = Html.form()
                .addHeading("Summary", 1)
                .addHeading("Hotel", 2)
                .startParagraph()
                .add("Name: " + hotel.getName()).newline()
                .add("Address: " + hotel.getAddress()).newline()
                .add("Phone Number: " + hotel.getPhoneNumber()).newline()
                .add("Email: " + hotel.getEmail()).newline()
                .add("Facilities: " + PropertyOperation.retrieveNames(hotel.getFacilities()))
                .endParagraph()
                .addHeading("Room", 2)
                .startParagraph()
                .add("Type: " + room.getOfType()).newline()
                .add("Beds: " + room.getBeds()).newline()
                .add("Size: " + room.getSize()).newline()
                .add("Facilities: " + PropertyOperation.retrieveNames(room.getFacilities()))
                .endParagraph()
                .addHeading("Reservation", 2)
                .startParagraph()
                .add("Board Type: " + PropertyOperation.retrieve(reservation.getBoardTypeId()).getName()).newline()
                .endParagraph()
                .addHeading("Pricing", 2)
                .startParagraph()
                .add("Check in date: " + reservation.getStartDate()).newline()
                .add("Check out date: " + reservation.getEndDate()).newline()
                .add("Adult Guest Count: " + reservation.getAdultGuestCount()).newline()
                .add("Child Guest Count: " + reservation.getChildGuestCount()).newline()
                .add("Total Price: " + reservation.getTotalPrice()).newline()
                .endParagraph()
                .toString();
    }

    private void initFields() {
        summaryJep.setContentType("text/html");
        summaryJep.setText(html);
    }

    private void initActions() {
        reserveJb.addActionListener(e -> {
            reservation.setContactName(contactNameJtf.getText());
            reservation.setContactPhoneNumber(contactPhoneNumberJtf.getText());
            reservation.setContactEmail(contactEmailJtf.getText());
            ReservationOperation.add(reservation);
            dispose();
        });
    }

    public static void main(String[] args) {
        new ReservationSummaryGui(new Reservation()
                .withHotelId(1)
                .withRoomId(3)
                .withBoardTypeId(1)
                .withAdultGuestCount(2)
                .withChildGuestCount(1)
                .withTotalPrice(500)
        );
    }
}
