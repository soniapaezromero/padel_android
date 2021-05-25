
package com.example.padel_android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Usuario {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("email_verified_at")
    @Expose
    private String emailVerifiedAt;
    @SerializedName("current_team_id")
    @Expose
    private Object currentTeamId;
    @SerializedName("profile_photo_path")
    @Expose
    private Object profilePhotoPath;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("profile_photo_url")
    @Expose
    private String profilePhotoUrl;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Usuario() {
    }

    /**
     * 
     * @param currentTeamId
     * @param createdAt
     * @param profilePhotoPath
     * @param emailVerifiedAt
     * @param name
     * @param id
     * @param email
     * @param updatedAt
     * @param profilePhotoUrl
     */
    public Usuario(int id, String name, String email, String emailVerifiedAt, Object currentTeamId, Object profilePhotoPath, String createdAt, String updatedAt, String profilePhotoUrl) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.emailVerifiedAt = emailVerifiedAt;
        this.currentTeamId = currentTeamId;
        this.profilePhotoPath = profilePhotoPath;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario withId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Usuario withName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Usuario withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getEmailVerifiedAt() {
        return emailVerifiedAt;
    }

    public void setEmailVerifiedAt(String emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
    }

    public Usuario withEmailVerifiedAt(String emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
        return this;
    }

    public Object getCurrentTeamId() {
        return currentTeamId;
    }

    public void setCurrentTeamId(Object currentTeamId) {
        this.currentTeamId = currentTeamId;
    }

    public Usuario withCurrentTeamId(Object currentTeamId) {
        this.currentTeamId = currentTeamId;
        return this;
    }

    public Object getProfilePhotoPath() {
        return profilePhotoPath;
    }

    public void setProfilePhotoPath(Object profilePhotoPath) {
        this.profilePhotoPath = profilePhotoPath;
    }

    public Usuario withProfilePhotoPath(Object profilePhotoPath) {
        this.profilePhotoPath = profilePhotoPath;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Usuario withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Usuario withUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public Usuario withProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
        return this;
    }

}
