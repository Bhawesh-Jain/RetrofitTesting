package com.smtlabs.retrofittesting.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WishListResponse {

    @SerializedName("wishlistId")
    @Expose
    private int wishlistId;
    @SerializedName("userId")
    @Expose
    private int userId;
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
    @SerializedName("added")
    @Expose
    private boolean added;
    @SerializedName("swiggyOne")
    @Expose
    private boolean swiggyOne;

    public int getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public boolean isAdded() {
        return added;
    }

    public void setAdded(boolean added) {
        this.added = added;
    }

    public boolean isSwiggyOne() {
        return swiggyOne;
    }

    public void setSwiggyOne(boolean swiggyOne) {
        this.swiggyOne = swiggyOne;
    }

}

