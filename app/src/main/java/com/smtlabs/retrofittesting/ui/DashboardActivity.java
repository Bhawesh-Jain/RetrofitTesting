package com.smtlabs.retrofittesting.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.smtlabs.retrofittesting.databinding.ActivityDashboardBinding;
import com.smtlabs.retrofittesting.utils.Constant;

public class DashboardActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;

    private ActivityDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedpreferences = getSharedPreferences(Constant.MyPREFERENCES, Context.MODE_PRIVATE);

        Constant._token = sharedpreferences.getString(Constant._tokenKey, "");
        Constant.userId = sharedpreferences.getInt(Constant.userIdKey, 0);

        binding.textViewNumber.setText(sharedpreferences.getString(Constant.numberKey, ""));
        binding.textViewUseremail.setText(sharedpreferences.getString(Constant.userEmailKey, ""));
        binding.textViewUsername.setText(sharedpreferences.getString(Constant.userNameKey, ""));
        binding.textViewToken.setText(sharedpreferences.getString(Constant._tokenKey, ""));

        binding.buttonLogout.setOnClickListener(view -> {
            sharedpreferences.edit().clear().apply();
            Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        binding.buttonDashboardNext.setOnClickListener(view -> {
            Intent intent = new Intent(DashboardActivity.this, RestaurantListActivity.class);
            startActivity(intent);
        });
    }
}