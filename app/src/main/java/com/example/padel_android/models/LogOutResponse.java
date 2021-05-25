
package com.example.padel_android.models;


import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LogOutResponse
{

    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("data")
    @Expose
    private List<Object> data = null;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = 3906476885040600186L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LogOutResponse() {
    }

    /**
     * 
     * @param data
     * @param success
     * @param message
     */
    public LogOutResponse(boolean success, List<Object> data, String message) {
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

    public LogOutResponse withSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public LogOutResponse withData(List<Object> data) {
        this.data = data;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LogOutResponse withMessage(String message) {
        this.message = message;
        return this;
    }

}
