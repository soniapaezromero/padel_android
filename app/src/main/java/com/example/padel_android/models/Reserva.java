
package com.example.padel_android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Reserva implements Serializable
{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("fecha")
    @Expose
    private String fecha;
    @SerializedName("hora_comienzo")
    @Expose
    private String horaComienzo;
    @SerializedName("hora_fin")
    @Expose
    private String horaFin;
    @SerializedName("email_company")
    @Expose
    private String emailCompany;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    private final static long serialVersionUID = -1843193964161504340L;

    /**
     * No args constructor for use in serialization
     *
     * @param fecha
     * @param comienzo
     * @param fin
     * @param email
     */
    public Reserva(String fecha, String comienzo, String fin, String email) {
        this.fecha = fecha;
        this.horaComienzo = horaComienzo;
        this.horaFin = horaFin;
        this.emailCompany = emailCompany;
    }

    /**
     * 
     * @param horaFin
     * @param fecha
     * @param createdAt
     * @param id
     * @param emailCompany
     * @param horaComienzo
     * @param updatedAt
     */
    public Reserva(int id, String fecha, String horaComienzo, String horaFin, String emailCompany, String createdAt, String updatedAt) {
        super();
        this.id = id;
        this.fecha = fecha;
        this.horaComienzo = horaComienzo;
        this.horaFin = horaFin;
        this.emailCompany = emailCompany;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Reserva() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Reserva withId(int id) {
        this.id = id;
        return this;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Reserva withFecha(String fecha) {
        this.fecha = fecha;
        return this;
    }

    public String getHoraComienzo() {
        return horaComienzo;
    }

    public void setHoraComienzo(String horaComienzo) {
        this.horaComienzo = horaComienzo;
    }

    public Reserva withHoraComienzo(String horaComienzo) {
        this.horaComienzo = horaComienzo;
        return this;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public Reserva withHoraFin(String horaFin) {
        this.horaFin = horaFin;
        return this;
    }

    public String getEmailCompany() {
        return emailCompany;
    }

    public void setEmailCompany(String emailCompany) {
        this.emailCompany = emailCompany;
    }

    public Reserva withEmailCompany(String emailCompany) {
        this.emailCompany = emailCompany;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Reserva withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Reserva withUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

}
