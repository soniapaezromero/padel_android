
package com.example.padel_android.models;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ModificarData
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
    private final static long serialVersionUID = 1998868152527149428L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ModificarData() {
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
    public ModificarData(int id, String fecha, String horaComienzo, String horaFin, String emailCompany, String createdAt, String updatedAt) {
        super();
        this.id = id;
        this.fecha = fecha;
        this.horaComienzo = horaComienzo;
        this.horaFin = horaFin;
        this.emailCompany = emailCompany;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ModificarData withId(int id) {
        this.id = id;
        return this;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public ModificarData withFecha(String fecha) {
        this.fecha = fecha;
        return this;
    }

    public String getHoraComienzo() {
        return horaComienzo;
    }

    public void setHoraComienzo(String horaComienzo) {
        this.horaComienzo = horaComienzo;
    }

    public ModificarData withHoraComienzo(String horaComienzo) {
        this.horaComienzo = horaComienzo;
        return this;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public ModificarData withHoraFin(String horaFin) {
        this.horaFin = horaFin;
        return this;
    }

    public String getEmailCompany() {
        return emailCompany;
    }

    public void setEmailCompany(String emailCompany) {
        this.emailCompany = emailCompany;
    }

    public ModificarData withEmailCompany(String emailCompany) {
        this.emailCompany = emailCompany;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public ModificarData withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public ModificarData withUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

}
