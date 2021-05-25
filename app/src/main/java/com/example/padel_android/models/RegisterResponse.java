
package com.example.padel_android.models;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RegisterResponse
{

    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("data")
    @Expose
    private RegisterData data;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = -369300024155021040L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RegisterResponse() {
    }

    /**
     * 
     * @param data
     * @param success
     * @param message
     */
    public RegisterResponse(boolean success, RegisterData data, String message) {
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

    public RegisterResponse withSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public RegisterData getData() {
        return data;
    }

    public void setData(RegisterData data) {
        this.data = data;
    }

    public RegisterResponse withData(RegisterData data) {
        this.data = data;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RegisterResponse withMessage(String message) {
        this.message = message;
        return this;
    }

}
