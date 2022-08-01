package com.example.otp.rest;

import com.example.otp.response.ResponseMenu;
import com.example.otp.response.ResponseValue;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @FormUrlEncoded
    @POST("konsumen")
    Call<ResponseValue> signupRequest(@Field("konsumen_nama") String nama,
                                      @Field("konsumen_phone") String phone,
                                      @Field("konsumen_email") String email,
                                      @Field("token") String token);

    @GET("daftar_menu")
    Call<ResponseMenu> getRumahMakanMenu(@Query("id") String rumah_makan_id,
                                         @Query("id_konsumen") String id_konsumen);
}
