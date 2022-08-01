package com.example.otp.response;

import com.example.otp.models.Menu;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseMenu {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("rumah_makan_menu")
    @Expose
    private List<Menu> rumah_makan_menu;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Menu> getRumah_makan_menu() {
        return rumah_makan_menu;
    }

    public void setRumah_makan_menu(List<Menu> rumah_makan_menu) {
        this.rumah_makan_menu = rumah_makan_menu;
    }
}
