package com.smtlabs.retrofittesting.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.smtlabs.retrofittesting.databinding.ActivityWishlistBinding;
import com.smtlabs.retrofittesting.model.WishListResponse;
import com.smtlabs.retrofittesting.model.WishListResponseModel;
import com.smtlabs.retrofittesting.retrofitservice.swiggySwingBoot;
import com.smtlabs.retrofittesting.ui.adapters.WishListAdapter;
import com.smtlabs.retrofittesting.utils.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WishlistActivity extends AppCompatActivity {

    private ActivityWishlistBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWishlistBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.swiggyBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        swiggySwingBoot api = retrofit.create(swiggySwingBoot.class);

        binding.textViewWishlist.setOnClickListener(view -> {
            Call<WishListResponseModel> call = api.getWishList(Constant._token, Constant.userId);

            call.enqueue(new Callback<WishListResponseModel>() {
                @Override
                public void onResponse(Call<WishListResponseModel> call, Response<WishListResponseModel> response) {
                    Toast.makeText(WishlistActivity.this, "Result = "+response.code(), Toast.LENGTH_SHORT).show();
                    if (response.code() == 200){
                        binding.textViewWishlist.setVisibility(View.GONE);
                        WishListResponseModel model = response.body();

                        if (model != null){
                            List<WishListResponse> data = model.getData();

                            setAdapter(data);
                        }

                    }
                }

                @Override
                public void onFailure(Call<WishListResponseModel> call, Throwable t) {
                    binding.textViewWishlist.setText("Error! Please Try Again");
                }
            });
        });

    }

    private void setAdapter(List<WishListResponse> data) {
        WishListAdapter adapter = new WishListAdapter(data, getApplicationContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        binding.recyclerviewWishList.setAdapter(adapter);
        binding.recyclerviewWishList.setLayoutManager(layoutManager);
    }
}