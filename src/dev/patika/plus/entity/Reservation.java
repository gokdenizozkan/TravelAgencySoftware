package dev.patika.plus.entity;

import dev.patika.plus.util.Util;

import java.sql.ResultSet;

public class Reservation {
    private int id;
    private int hotelId;
    private int roomId;
    private int boardTypeId;
    private String startDate;
    private String endDate;
    private int adultGuestCount;
    private int childGuestCount;
    private int totalPrice;
    private String contactName;
    private String contactPhoneNumber;
    private String contactEmail;

    private Reservation() {
    }

    public Reservation(ResultSet resultSet) {
        try {
            this.id = resultSet.getInt("id");
            this.hotelId = resultSet.getInt("hotel_id");
            this.roomId = resultSet.getInt("room_id");
            this.boardTypeId = resultSet.getInt("board_type_id");
            this.startDate = resultSet.getString("start_date");
            this.endDate = resultSet.getString("end_date");
            this.adultGuestCount = resultSet.getInt("adult_guest_count");
            this.childGuestCount = resultSet.getInt("child_guest_count");
            this.totalPrice = resultSet.getInt("total_price");
            this.contactName = resultSet.getString("contact_name");
            this.contactPhoneNumber = resultSet.getString("contact_phone_number");
            this.contactEmail = resultSet.getString("contact_email");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static Reservation reserve() {
        return new Reservation();
    }

    public Reservation withStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public Reservation withEndDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    public Reservation withHotelId(int hotelId) {
        this.hotelId = hotelId;
        return this;
    }

    public Reservation withRoomId(int roomId) {
        this.roomId = roomId;
        return this;
    }

    public Reservation withBoardTypeId(int boardTypeId) {
        this.boardTypeId = boardTypeId;
        return this;
    }

    public Reservation withAdultGuestCount(int adultGuestCount) {
        this.adultGuestCount = adultGuestCount;
        return this;
    }

    public Reservation withChildGuestCount(int childGuestCount) {
        this.childGuestCount = childGuestCount;
        return this;
    }

    public Reservation withTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public Reservation withContactName(String contactName) {
        this.contactName = contactName;
        return this;
    }

    public Reservation withContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
        return this;
    }

    public Reservation withContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
        return this;
    }

    public int getId() {
        return id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getBoardTypeId() {
        return boardTypeId;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getAdultGuestCount() {
        return adultGuestCount;
    }

    public int getChildGuestCount() {
        return childGuestCount;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setBoardTypeId(int boardTypeId) {
        this.boardTypeId = boardTypeId;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setAdultGuestCount(int adultGuestCount) {
        this.adultGuestCount = adultGuestCount;
    }

    public void setChildGuestCount(int childGuestCount) {
        this.childGuestCount = childGuestCount;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}
