
package com.example.padel_android.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CrearReservaResponse
{

    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("data")
    @Expose
    private CrearReservaData data;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = 3855407390951195828L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CrearReservaResponse() {
    }

    /**
     * 
     * @param data
     * @param success
     * @param message
     */
    public CrearReservaResponse(boolean success, CrearReservaData data, String message) {
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

    public CrearReservaResponse withSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public CrearReservaData getData() {
        return data;
    }

    public void setData(CrearReservaData data) {
        this.data = data;
    }

    public CrearReservaResponse withData(CrearReservaData data) {
        this.data = data;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CrearReservaResponse withMessage(String message) {
        this.message = message;
        return this;
    }


}
