package dev.patika.plus.util;

public class Reservation {
    private int id;
    private int hotelId;
    private int roomId;
    private String startDate;
    private String endDate;
    private int adultGuestCount;
    private int childGuestCount;
    private int totalPrice;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getAdultGuestCount() {
        return adultGuestCount;
    }

    public void setAdultGuestCount(int adultGuestCount) {
        this.adultGuestCount = adultGuestCount;
    }

    public int getChildGuestCount() {
        return childGuestCount;
    }

    public void setChildGuestCount(int childGuestCount) {
        this.childGuestCount = childGuestCount;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
