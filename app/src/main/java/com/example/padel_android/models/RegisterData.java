
package com.example.padel_android.models;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RegisterData
{

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("name")
    @Expose
    private String name;
    private final static long serialVersionUID = 5040279380943122456L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RegisterData() {
    }

    /**
     * 
     * @param name
     * @param token
     */
    public RegisterData(String token, String name) {
        super();
        this.token = token;
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RegisterData withToken(String token) {
        this.token = token;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RegisterData withName(String name) {
        this.name = name;
        return this;
    }

}
