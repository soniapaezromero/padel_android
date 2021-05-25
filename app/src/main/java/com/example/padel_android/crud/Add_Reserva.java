package com.example.padel_android.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.padel_android.R;
import com.example.padel_android.databinding.ActivityAddReservaBinding;
import com.example.padel_android.models.CrearReservaResponse;
import com.example.padel_android.models.Reserva;
import com.example.padel_android.network.ApiTokenRestClient;
import com.example.padel_android.util.SharedPreferencesManager;

import java.io.IOException;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add_Reserva extends AppCompatActivity implements View.OnClickListener, Callback<CrearReservaResponse> {
    private ActivityAddReservaBinding addReservaBinding;
    public static final int OK = 1;
    ProgressDialog progreso;
    SharedPreferencesManager preferences;
    public final Calendar c = Calendar.getInstance();
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);
    final int hora = c.get(Calendar.HOUR_OF_DAY);
    final int minuto = c.get(Calendar.MINUTE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reserva);
        addReservaBinding = ActivityAddReservaBinding.inflate(getLayoutInflater());
        View view = addReservaBinding.getRoot();
        setContentView(view);
        addReservaBinding.botonAdd.setOnClickListener(this);
        addReservaBinding.botonBorrar.setOnClickListener(this);
        addReservaBinding.addfecha.setOnClickListener(this);
        addReservaBinding.addHoraComienzo.setOnClickListener(this);
        addReservaBinding.addHoraFinal.setOnClickListener(this);
        preferences = new SharedPreferencesManager(this);
    }


    @Override
    public void onClick(View v) {
        String fecha;
        String hora_comienzo;
        String hora_fin;
        String email_company;
        Reserva reserva;
        if (v == addReservaBinding.botonAdd) {
            fecha = addReservaBinding.addfecha.getText().toString();
            Log.e("Dato caja de texto", fecha);
            hora_comienzo = addReservaBinding.addHoraComienzo.getText().toString();
            hora_fin  = addReservaBinding.addHoraFinal.getText().toString();
            email_company = addReservaBinding.addEmail.getText().toString();

            if (fecha.isEmpty()) {
                Toast.makeText(this, "Rellena fecha", Toast.LENGTH_SHORT).show();
                addReservaBinding.addfecha.requestFocus();
            } else if (hora_comienzo.isEmpty()) {
                Toast.makeText(this, "Rellena hora de hora_comienzo", Toast.LENGTH_SHORT).show();
                addReservaBinding.addHoraComienzo.requestFocus();

            } else if (hora_fin.isEmpty()) {
                Toast.makeText(this, "Rellena hora de hora_fin", Toast.LENGTH_SHORT).show();
                addReservaBinding.addHoraFinal.requestFocus();
            } else if (email_company.isEmpty()) {
                Toast.makeText(this, "Rellena email", Toast.LENGTH_SHORT).show();
                addReservaBinding.addEmail.requestFocus();
            }else if (email_company.isEmpty()) {
            } else {
                reserva = new Reserva();
                reserva.setFecha(fecha);
                Log.e("Dato cargo reseva fecha", reserva.getFecha().toString());
                reserva.setHoraComienzo(hora_comienzo);
                reserva.setHoraFin(hora_fin);
                reserva.setEmailCompany(email_company);
                connection(reserva);
            }
        } else if (v == addReservaBinding.botonBorrar) {
            finish();
        }

        if (v == addReservaBinding.addfecha) {
            mostrarCalendario();
        }
        if (v == addReservaBinding.addHoraComienzo) {
            obtenerHora();
        }
        if (v == addReservaBinding.addHoraFinal) {
            obtenerHorafin();
        }
    }




    // CReamos la conesion y le pasamos la reserva a añadir
    private void connection(Reserva reserva) {
        progreso = new ProgressDialog(this);
        progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progreso.setMessage("Connecting . . .");
        progreso.setCancelable(false);
        progreso.show();

        Call<CrearReservaResponse> call = ApiTokenRestClient.getInstance(preferences.getToken()).createReserva(reserva);
        call.enqueue(this);
    }
    @Override
    public void onResponse(Call<CrearReservaResponse> call, Response<CrearReservaResponse> response) {
        progreso.dismiss();
        if (response.isSuccessful()) {
            CrearReservaResponse addResponse = response.body();
            if (addResponse.isSuccess()) {
                Intent i = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("id", addResponse.getData().getId());
                bundle.putString("fecha", addResponse.getData().getFecha());
                bundle.putString("hora_comienzo", addResponse.getData().getHoraComienzo());
                bundle.putString("hora_fin", addResponse.getData().getHoraFin());
                bundle.putString("email_company", addResponse.getData().getEmailCompany());
                bundle.putString("createdAt", addResponse.getData().getCreatedAt());
                i.putExtras(bundle);
                setResult(OK, i);
                finish();
                showMessage("Reserva Creada");
            } else {
                String message = "Error realiznado la reserva";
                if (!addResponse.getMessage().isEmpty()) {
                    message += ": " + addResponse.getMessage();
                }
                showMessage(message);

            }
        } else {
            StringBuilder message = new StringBuilder();
            message.append("Download error: ");
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
    public void onFailure(Call<CrearReservaResponse> call, Throwable t) {
        progreso.dismiss();
        if (t != null)
            showMessage("Failure in the communication\n" + t.getMessage());
    }

    private void showMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }



    //Muestra el Datepicker y lo pasa  a una editText
    private void mostrarCalendario() {
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            private static final String SEPARADOR = "-";

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = "";
                if (dayOfMonth < 10) {
                    diaFormateado = "0" + String.valueOf(dayOfMonth);
                } else {
                    diaFormateado = String.valueOf(dayOfMonth);
                }
                String mesFormateado = "";
                if (mesActual < 10) {
                    mesFormateado = "0" + String.valueOf(mesActual);
                } else {
                    mesFormateado = String.valueOf(mesActual);
                }
                //Muestro la fecha con el formato deseado
                addReservaBinding.addfecha.setText(diaFormateado + SEPARADOR + mesFormateado + SEPARADOR + year);


            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        }, anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();

    }
    //Muestra el TimerPicker, filtra las horas  lo pasa  a una editText
    private void obtenerHora() {

        TimePickerDialog recogerHora = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                view.setIs24HourView(true);
                String horaFormateada = "";
                if((hourOfDay<9)||(hourOfDay>22)) {
                    Toast.makeText(Add_Reserva.this,"La pista esta abierta de 9:00 a 23.00",Toast.LENGTH_SHORT).show();
                    horaFormateada= "09";
                }else{
                    horaFormateada=String.valueOf(hourOfDay);
                }
                //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                String minutoFormateado = "";
                if(minute != 0){
                    Toast.makeText(Add_Reserva.this,"Los reservas empiezan en las horas en punto, se pondra en punto .",Toast.LENGTH_SHORT).show();
                    minutoFormateado="0";
                }else{
                    minutoFormateado=String.valueOf(minute);
                }


                //Muestro la hora con el formato deseado
                addReservaBinding.addHoraComienzo.setText(horaFormateada + ":0" + minutoFormateado );

            }
            //Estos valores deben ir en ese orden
            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
            //Pero el sistema devuelve la hora en formato 24 horas
        }, hora, minuto, true);
        recogerHora.show();
    }
    private void obtenerHorafin() {
        String horaComienzo=addReservaBinding.addHoraComienzo.getText().toString();
        String[]partes= horaComienzo.split(":");
        String horas= partes[0];
        String minutos=partes[1];
        int start= Integer.parseInt(horas);
        TimePickerDialog recogerHora = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                view.setIs24HourView(true);
                String horaFormateada = "";
                if((hourOfDay<9)||(hourOfDay>22)) {
                    Toast.makeText(Add_Reserva.this,"La pista esta abierta de 9:00 a 23.00",Toast.LENGTH_SHORT).show();
                    horaFormateada= "09";
                }else{
                    if(hourOfDay <= start){
                        Toast.makeText(Add_Reserva.this,"La hora de fin tien quese superior a la de comienzo",Toast.LENGTH_SHORT).show();
                        addReservaBinding.addHoraFinal.requestFocus();
                    }else {
                        horaFormateada = String.valueOf(hourOfDay);
                    }

                }
                //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                String minutoFormateado = "";
                if(minute != 0){
                    Toast.makeText(Add_Reserva.this,"Los reservas empiezan en las horas en punto, se pondra en punto .",Toast.LENGTH_SHORT).show();
                    minutoFormateado="0";
                }else{
                    minutoFormateado=String.valueOf(minute);
                }


                //Muestro la hora con el formato deseado
                addReservaBinding.addHoraFinal.setText(horaFormateada + ":0" + minutoFormateado );

            }
            //Estos valores deben ir en ese orden
            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
            //Pero el sistema devuelve la hora en formato 24 horas
        }, hora, minuto, true);
        recogerHora.show();
    }
    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

}
