package com.example.padel_android.crud;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.padel_android.MainActivity;
import com.example.padel_android.R;
import com.example.padel_android.adapter.ClickListener;
import com.example.padel_android.adapter.RecyclerTouchListener;
import com.example.padel_android.adapter.ReservasAdapter;
import com.example.padel_android.databinding.ActivityAgendaDeReservasBinding;
import com.example.padel_android.models.BorrarResponse;
import com.example.padel_android.models.ListReservasRsponsse;
import com.example.padel_android.models.LogOutResponse;
import com.example.padel_android.models.Reserva;
import com.example.padel_android.network.ApiTokenRestClient;
import com.example.padel_android.util.SharedPreferencesManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgendaDeReservas extends AppCompatActivity implements View.OnClickListener {
    public static final int ADD_CODE = 100;
    public static final int UPDATE_CODE = 200;
    public static final int OK = 1;
    ReservasAdapter adapter;
    ProgressDialog progreso;
    int positionClicked;
    SharedPreferencesManager preferences;
    private ActivityAgendaDeReservasBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_de_reservas);
        binding = ActivityAgendaDeReservasBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.floatingActionButton.setOnClickListener(this);
        preferences = new SharedPreferencesManager(this);
        adapter = new ReservasAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);

        binding.recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, binding.recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                showMessage("Single Click on reserva with id: " + adapter.getAt(position).getId());
                modify(adapter.getAt(position));
                positionClicked = position;
            }

            @Override
            public void onLongClick(View view, int position) {
                showMessage("Long press on position :" + position);
                confirm(adapter.getAt(position).getId(), adapter.getAt(position).getFecha(),adapter.getAt(position).getHoraComienzo(),adapter.getAt(position).getHoraFin(),adapter.getAt(position).getEmailCompany(), position);
            }
        }));

        //Destruir la instancia de Retrofit para que se cree una con el nuevo token
        ApiTokenRestClient.deleteInstance();

        downloadTasks();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_barra, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.refresh:
                //petición al servidor para descargar de nuevo los sitios
                downloadTasks();
                break;

            case R.id.email:
                //send an email
                Intent i = new Intent(this, EmailCRud.class);
                startActivity(i);
                break;

            case R.id.exit:
                //petición al servidor para anular el token (a la ruta /api/logout)
                Call<LogOutResponse> call = ApiTokenRestClient.getInstance(preferences.getToken()).logout();
                call.enqueue(new Callback<LogOutResponse>() {
                    @Override
                    public void onResponse(Call<LogOutResponse> call, Response<LogOutResponse> response) {
                        if (response.isSuccessful()) {
                            LogOutResponse logoutResponse = response.body();
                            if (logoutResponse.isSuccess()) {
                                showMessage("Logout OK");
                            } else
                                showMessage("Error in logout");
                        } else {
                            StringBuilder message = new StringBuilder();
                            message.append("Download error: " + response.code());
                            if (response.body() != null)
                                message.append("\n" + response.body());
                            if (response.errorBody() != null)
                                try {
                                    message.append("\n" + response.errorBody().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            showMessage(message.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<LogOutResponse> call, Throwable t) {
                        String message = "Failure in the communication\n";
                        if (t != null)
                            message += t.getMessage();
                        showMessage(message);

                    }
                });
                preferences.saveToken(null, null);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {

        if (v == binding.floatingActionButton) {
            Intent i = new Intent(this, Add_Reserva.class);
            startActivityForResult(i, ADD_CODE);
        }
    }

    private void downloadTasks() {
        progreso = new ProgressDialog(this);
        progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progreso.setMessage("Connecting . . .");
        progreso.setCancelable(false);
        progreso.show();

        Call<ListReservasRsponsse> call = ApiTokenRestClient.getInstance(preferences.getToken()).getReservas();
        call.enqueue(new Callback<ListReservasRsponsse>() {
            @Override
            public void onResponse(Call<ListReservasRsponsse> call, Response<ListReservasRsponsse> response) {
                progreso.dismiss();
                if (response.isSuccessful()) {
                    ListReservasRsponsse getListaResponse = response.body();
                    if (getListaResponse.isSuccess()) {
                        adapter.setReservas(getListaResponse.getData());
                        showMessage("Reservas  downloaded ok");
                    } else {
                        showMessage("Error downloading the tasks: " + getListaResponse.getMessage());
                    }
                } else {
                    StringBuilder message = new StringBuilder();
                    message.append("Download error: " + response.code());
                    if (response.body() != null)
                        message.append("\n" + response.body());
                    if (response.errorBody() != null)
                        try {
                            message.append("\n" + response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    showMessage(message.toString());
                }
            }

            @Override
            public void onFailure(Call<ListReservasRsponsse> call, Throwable t) {
                progreso.dismiss();
                String message = "Failure in the communication\n";
                if (t != null)
                    message += t.getMessage();
                showMessage(message);
            }
        });
    }

    private void showMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Reserva reserva = new Reserva();

        if (requestCode == ADD_CODE)
            if (resultCode == OK) {
                reserva.setId(data.getIntExtra("id", 1));
                reserva.setFecha(data.getStringExtra("fecha"));
                reserva.setHoraComienzo(data.getStringExtra("hora_comienzo"));
                reserva.setHoraFin(data.getStringExtra("hora_fin"));
                reserva.setFecha(data.getStringExtra("email_company"));
                reserva.setCreatedAt(data.getStringExtra("createdAt"));
                adapter.add(reserva);
            }

        if (requestCode == UPDATE_CODE)
            if (resultCode == OK) {
                reserva.setId(data.getIntExtra("id", 1));
                reserva.setFecha(data.getStringExtra("fecha"));
                reserva.setHoraComienzo(data.getStringExtra("hora_comienzo"));
                reserva.setHoraFin(data.getStringExtra("hora_fin"));
                reserva.setFecha(data.getStringExtra("email_company"));
                reserva.setCreatedAt(data.getStringExtra("createdAt"));
                adapter.modifyAt(reserva, positionClicked);
            }
    }

    private void modify(Reserva reserva) {
        Intent i = new Intent(this, Edit_Reserva.class);
        String fecha= reserva.getFecha();
        Log.e("FEcha recicle", fecha);
        i.putExtra("reserva",reserva);
        startActivityForResult(i, UPDATE_CODE);
    }

    private void confirm(final int id, String fecha,String hora_comienzo,String hora_fin,String email, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(fecha + "\nDo you want to delete?")
                .setTitle("Delete")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        connection(position);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }

    private void connection(final int position) {
        //Call<ResponseBody> call = ApiRestClient.getInstance().deleteSite("Bearer " + preferences.getToken(), adapter.getId(position));
        Call<BorrarResponse> call = ApiTokenRestClient.getInstance(preferences.getToken()).deleteReserva((int) adapter.getItemId(position));
        progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progreso.setMessage("Connecting . . .");
        progreso.setCancelable(false);
        progreso.show();
        call.enqueue(new Callback<BorrarResponse>() {
            @Override
            public void onResponse(Call<BorrarResponse> call, Response<BorrarResponse> response) {
                progreso.dismiss();
                if (response.isSuccessful()) {
                    BorrarResponse deleteResponse = response.body();
                    if (deleteResponse.isSuccess()) {
                        adapter.removeAt(position);
                        showMessage("Reserva deleted OK");
                    } else
                        showMessage("Error deleting the reserva");
                } else {
                    StringBuilder message = new StringBuilder();
                    message.append("Error deleting a site: " + response.code());
                    if (response.body() != null)
                        message.append("\n" + response.body());
                    if (response.errorBody() != null)
                        try {
                            message.append("\n" + response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    showMessage(message.toString());
                }
            }

            @Override
            public void onFailure(Call<BorrarResponse> call, Throwable t) {
                progreso.dismiss();
                if (t != null)
                    showMessage("Failure in the communication\n" + t.getMessage());

            }
        });
    }
}