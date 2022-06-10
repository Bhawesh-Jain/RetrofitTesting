package com.smtlabs.retrofittesting.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantList {

    @SerializedName("restaurantId")
    @Expose
    private int restaurantId;
    @SerializedName("restaurantName")
    @Expose
    private String restaurantName;
    @SerializedName("rating")
    @Expose
    private double rating;
    @SerializedName("deliveryTime")
    @Expose
    private String deliveryTime;
    @SerializedName("discount")
    @Expose
    private double discount;
    @SerializedName("swiggyOne")
    @Expose
    private boolean swiggyOne;

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public boolean isSwiggyOne() {
        return swiggyOne;
    }

    public void setSwiggyOne(boolean swiggyOne) {
        this.swiggyOne = swiggyOne;
    }

}