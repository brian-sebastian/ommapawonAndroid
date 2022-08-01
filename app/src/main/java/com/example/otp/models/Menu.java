package com.example.otp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Menu implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("rumah_makan_id")
    @Expose
    private Integer rumah_makan_id;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("nama_makanan")
    @Expose
    private String nama_makanan;
    @SerializedName("harga")
    @Expose
    private Integer harga;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRumah_makan_id() {
        return rumah_makan_id;
    }

    public void setRumah_makan_id(Integer rumah_makan_id) {
        this.rumah_makan_id = rumah_makan_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNama_makanan() {
        return nama_makanan;
    }

    public void setNama_makanan(String nama_makanan) {
        this.nama_makanan = nama_makanan;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
