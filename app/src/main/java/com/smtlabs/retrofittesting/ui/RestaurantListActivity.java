package com.smtlabs.retrofittesting.ui;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smtlabs.retrofittesting.R;
import com.smtlabs.retrofittesting.databinding.ActivityRestaurantListBinding;
import com.smtlabs.retrofittesting.model.ResponseModel;
import com.smtlabs.retrofittesting.model.RestaurantList;
import com.smtlabs.retrofittesting.model.RestaurantResponseModel;
import com.smtlabs.retrofittesting.repositry.RestaurantMethods;
import com.smtlabs.retrofittesting.retrofitservice.swiggySwingBoot;
import com.smtlabs.retrofittesting.ui.adapters.RestaurantListAdapter;
import com.smtlabs.retrofittesting.utils.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestaurantListActivity extends AppCompatActivity implements RestaurantMethods {

    private ActivityRestaurantListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRestaurantListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.swiggyBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FloatingActionButton wishlist = findViewById(R.id.fab_wishlist);
        swiggySwingBoot api = retrofit.create(swiggySwingBoot.class);


        Log.i(TAG, "onCreate: TOken"+Constant._token);
        Call<RestaurantResponseModel> call = api.getRestaurants(Constant._token);


        call.enqueue(new Callback<RestaurantResponseModel>() {
            @Override
            public void onResponse(Call<RestaurantResponseModel> call, Response<RestaurantResponseModel> response) {
                int statusCode = response.code();

                if (statusCode == 200){
                    RestaurantResponseModel model = response.body();
                    if (model != null){
                        List<RestaurantList> data = model.getData();

                        setAdapter(data, wishlist);

                    }

                }
                else {
                    Toast.makeText(RestaurantListActivity.this, "Failed! "+statusCode, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RestaurantResponseModel> call, Throwable t) {
                Toast.makeText(RestaurantListActivity.this, "Call Failed!", Toast.LENGTH_SHORT).show();
            }
        });



    }

    public void setAdapter(List<RestaurantList> list, FloatingActionButton wishlist) {

        RestaurantListAdapter adapter = new RestaurantListAdapter(list, getApplicationContext(), wishlist, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        binding.recyclerviewRestaurantList.setAdapter(adapter);
        binding.recyclerviewRestaurantList.setLayoutManager(layoutManager);

    }

    @Override
    public void onWishListClicked(List<Integer> selectedIds) {
        int userId = Constant.userId;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.swiggyBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        swiggySwingBoot api = retrofit.create(swiggySwingBoot.class);
        if (selectedIds!=null) {
            for (int i = 0; i < selectedIds.size(); i++) {
                int resId = selectedIds.get(i);
                Call<ResponseModel> call = api.addToWishlistId(Constant._token, resId, userId);
                call.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        Log.i(TAG, "ResponseCode = "+response.code());
                        if (response.code() == 200) {
                            Log.i(TAG, "onWishListClicked: Added = " + resId + " For = " + userId);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {

                    }
                });
            }
            Toast.makeText(this, "WishList Updated", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, WishlistActivity.class));
        }
    }
}