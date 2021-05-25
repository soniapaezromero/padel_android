package com.example.padel_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.padel_android.crud.AgendaDeReservas;
import com.example.padel_android.crud.Register;
import com.example.padel_android.databinding.ActivityMainBinding;
import com.example.padel_android.models.LoginResponse;
import com.example.padel_android.network.ApiRestClient;
import com.example.padel_android.util.SharedPreferencesManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String APP = "Padel App";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String TOKEN = "token";
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_REGISTER = 1;
    SharedPreferencesManager preferences;
    private ProgressDialog progressDialog;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        preferences = new SharedPreferencesManager(this);
        binding.email.setText(preferences.getEmail());
        binding.password.setText(preferences.getPassword());

        binding.login.setOnClickListener(this);
        binding.register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        hideSoftKeyboard();

        if (v == binding.login) {
            if (validate() == false) {
                showMessage("Error al validar los datos");
            } else {
                loginByServer();
            }

        } else if (v == binding.register) {
            // Start the Register activity
            Intent intent = new Intent(this, Register.class);
            startActivityForResult(intent, REQUEST_REGISTER);

        }
    }

    private void loginByServer() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Login ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        binding.login.setEnabled(false);

        String email = binding.email.getText().toString();
        String password = binding.password.getText().toString();

        Call<LoginResponse> call = ApiRestClient.getInstance().login(email, password);
        //User user = new User(name, email, password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                StringBuilder message = new StringBuilder();
                progressDialog.dismiss();
                binding.login.setEnabled(true);

                //onRegisterSuccess();
                LoginResponse loginResponse = response.body();
                if (response.isSuccessful()) {
                    if (loginResponse.isSuccess()) {
                        Log.d("onResponse", "" + response.body());
                        preferences.save(binding.email.getText().toString(), binding.password.getText().toString(), loginResponse.getData().getToken());
                        startActivity(new Intent(getApplicationContext(), AgendaDeReservas.class));
                        finish();
                    } else {
                        showMessage("Error in login: " + loginResponse.getMessage());
                    }
                } else {
                    message.append("Error in login: ");
                    if (response.body() != null)
                        message.append("\nBody\n" + response.body());
                    if (response.errorBody() != null)
                        try {
                            message.append("\nError\n" + response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    showMessage(message.toString());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressDialog.dismiss();
                binding.login.setEnabled(true);
                String message = "Failure in the communication\n";
                if (t != null) {
                    Log.d("onFailure", t.getMessage());
                    message += t.getMessage();
                }
                showMessage(message);
                binding.register.setEnabled(true);
            }
        });
    }

    private void showMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_REGISTER) {
            if (resultCode == RESULT_OK) {

                preferences.save(
                        data.getExtras().getString("email"),
                        data.getExtras().getString("password"),
                        data.getExtras().getString("token"));
                startActivity(new Intent(this, AgendaDeReservas.class));
                finish();
            } else if (requestCode == RESULT_CANCELED) {

                showMessage("Registro cancelado");
            }
        }
    }

    public boolean validate() {
        boolean valid = true;

        String email = binding.email.getText().toString();
        String password = binding.password.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.email.setError("enter a valid email address");
            requestFocus(binding.email);
            valid = false;
        } else {
            binding.email.setError(null);
        }

        if (password.isEmpty()) {
            binding.password.setError("Password is empty");
            requestFocus(binding.password);
            valid = false;
        } else {
            binding.password.setError(null);
        }

        return valid;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
}