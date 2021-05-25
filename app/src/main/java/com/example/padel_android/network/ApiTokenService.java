package com.example.padel_android.network;

import com.example.padel_android.models.BorrarResponse;
import com.example.padel_android.models.CrearReservaResponse;
import com.example.padel_android.models.Email;
import com.example.padel_android.models.EmailResponse;
import com.example.padel_android.models.ListReservasRsponsse;
import com.example.padel_android.models.LogOutResponse;
import com.example.padel_android.models.ModificarReservaResponse;
import com.example.padel_android.models.Reserva;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiTokenService {

    @POST("api/logout")
    Call<LogOutResponse> logout(
            //@Header("Authorization") String token
    );

    @GET("api/reservas")
    Call<ListReservasRsponsse> getReservas(
            //@Header("Authorization") String token
    );

    @POST("api/reservas")
    Call<CrearReservaResponse> createReserva(
            //@Header("Authorization") String token,
            @Body Reserva reserva);

    @PUT("api/reservas/{id}")
    Call<ModificarReservaResponse> updateReserva(
            //@Header("Authorization") String token,
            @Body Reserva reserva,
            @Path("id") int id);

    @DELETE("api/reservas/{id}")
    Call<BorrarResponse> deleteReserva(
            //@Header("Authorization") String token,
            @Path("id") int id);

    @POST("api/email")
    Call<EmailResponse> sendEmail(@Body Email email);
}

