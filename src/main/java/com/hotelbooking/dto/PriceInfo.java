package com.hotelbooking.dto;

public class PriceInfo {

    private double pricePerNight;
    private int nights;
    private double totalPrice;
    private String currency;

    public PriceInfo() {}

    public PriceInfo(double pricePerNight, int nights, double totalPrice, String currency) {
        this.pricePerNight = pricePerNight;
        this.nights = nights;
        this.totalPrice = totalPrice;
        this.currency = currency;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public int getNights() {
        return nights;
    }

    public void setNights(int nights) {
        this.nights = nights;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
