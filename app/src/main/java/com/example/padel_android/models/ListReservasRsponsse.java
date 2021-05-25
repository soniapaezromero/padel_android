
package com.example.padel_android.models;


import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ListReservasRsponsse
{

    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("data")
    @Expose
    private List<Reserva> data = null;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = 3743926642346452857L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ListReservasRsponsse() {
    }

    /**
     * 
     * @param data
     * @param success
     * @param message
     */
    public ListReservasRsponsse(boolean success, List<Reserva> data, String message) {
        super();
        this.success = success;
        this.data = data;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ListReservasRsponsse withSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public List<Reserva> getData() {
        return data;
    }

    public void setData(List<Reserva> data) {
        this.data = data;
    }

    public ListReservasRsponsse withData(List<Reserva> data) {
        this.data = data;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ListReservasRsponsse withMessage(String message) {
        this.message = message;
        return this;
    }

}
