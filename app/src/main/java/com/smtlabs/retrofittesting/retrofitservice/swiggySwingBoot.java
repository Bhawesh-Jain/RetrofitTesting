package com.smtlabs.retrofittesting.retrofitservice;

import com.smtlabs.retrofittesting.model.ResponseModel;
import com.smtlabs.retrofittesting.model.RestaurantResponseModel;
import com.smtlabs.retrofittesting.model.WishListResponseModel;
import com.smtlabs.retrofittesting.model.body.PostLoginCredentialModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface swiggySwingBoot {
    @POST("authJWT")
    Call<ResponseModel> getAuthResult(@Body PostLoginCredentialModel dataModal);

    @FormUrlEncoded
    @POST("authJWT")
    Call<ResponseModel> getAuthResult(@Field("username") String username, @Field("password") String password);

    @POST("addtoWislist")
    Call<ResponseModel> addToWishlistId(@Header("Authorization") String authHeader, @Query("restaurantId") int restaurantId, @Query("userId") int userId);

    @GET("getRestaurantlist")
    Call<RestaurantResponseModel> getRestaurants(@Header("Authorization") String authHeader);

    @GET("getWishList")
    Call<WishListResponseModel> getWishList(@Header("Authorization") String authHeader, @Query("id") int userId);



}
