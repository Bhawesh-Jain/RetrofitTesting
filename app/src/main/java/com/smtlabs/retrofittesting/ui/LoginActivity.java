package com.smtlabs.retrofittesting.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.smtlabs.retrofittesting.databinding.ActivityLoginBinding;
import com.smtlabs.retrofittesting.model.body.PostLoginCredentialModel;
import com.smtlabs.retrofittesting.model.ResponseModel;
import com.smtlabs.retrofittesting.retrofitservice.swiggySwingBoot;
import com.smtlabs.retrofittesting.utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    public String url = Constant.swiggyBaseUrl;

    public String MyPREFERENCES = Constant.MyPREFERENCES;
    public String _userName = Constant.userNameKey;
    public String _password = Constant.passwordKey;
    public String _token = Constant._tokenKey;
    public String _number = Constant.numberKey;
    public String _userEmail = Constant.userEmailKey;
    public String _userId = Constant.userIdKey;

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        binding.buttonLogin.setOnClickListener(view -> loginAuthenticate());

        String tempToken = sharedpreferences.getString(_token, "");
        if (!tempToken.equals("")){
            Toast.makeText(this, "Session Continued!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
        }

    }

    private void loginAuthenticate() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        swiggySwingBoot api = retrofit.create(swiggySwingBoot.class);

        PostLoginCredentialModel model = new PostLoginCredentialModel(binding.editTextUsername.getText().toString().trim(),
                binding.editTextPassword.getText().toString().trim());

        Call<ResponseModel> call = api.getAuthResult(model);

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel model = response.body();

                int statusCode = response.code();

                if (statusCode == 200) {
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString(_token, model.getToken());
                    editor.putString(_userEmail, model.getData().getUserEmail());
                    editor.putString(_userName, model.getData().getUserName());
                    editor.putString(_password, model.getData().getPassword());
                    editor.putString(_number, model.getData().getNumber());
                    editor.putInt(_userId, model.getData().getId());
                    editor.commit();

                    binding.editTextPassword.setText("");
                    binding.editTextUsername.setText("");

                    startActivity(new Intent(LoginActivity.this, DashboardActivity.class));

                    Toast.makeText(LoginActivity.this, model.getMessage() + "\n" + model.getToken(), Toast.LENGTH_SHORT).show();
                } else if (statusCode == 400) {
                    Toast.makeText(LoginActivity.this, "Login Failed!\n"+"Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Error Occurred on Response! Please check " +
                            "Internet Connection and Try Again", Toast.LENGTH_SHORT).show();
                }
                }


            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error Occurred! Please check " +
                        "Internet Connection and Try Again\n"+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
//    enqueue(new Callback<List<model>>() {
//        @Override
//        public void onResponse(Call<List<model>> call, Response<List<model>> response) {
//            binding.tv.setText("");
//            List<model> data = response.body();
//            for (int i = 0; i < data.size(); i++)
//                binding.tv.append("ID: " + data.get(i).getId() + "\nName: " + data.get(i).getName() +
//                        "\n\nEmail: " + data.get(i).getEmail() + "\n\nBody: " + data.get(i).getBody() + "\n\n\n");
//        }
//
//        @Override
//        public void onFailure(Call<List<model>> call, Throwable t) {
//            binding.tv.setText(R.string.error);
//        }
//    });
