
package com.example.padel_android.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ModificarReservaResponse
{

    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("data")
    @Expose
    private ModificarData data;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = 3540902187254537297L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ModificarReservaResponse() {
    }

    /**
     * 
     * @param data
     * @param success
     * @param message
     */
    public ModificarReservaResponse(boolean success, ModificarData data, String message) {
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

    public ModificarReservaResponse withSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public ModificarData getData() {
        return data;
    }

    public void setData(ModificarData data) {
        this.data = data;
    }

    public ModificarReservaResponse withData(ModificarData data) {
        this.data = data;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ModificarReservaResponse withMessage(String message) {
        this.message = message;
        return this;
    }

}
