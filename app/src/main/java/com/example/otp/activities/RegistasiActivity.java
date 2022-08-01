package com.example.otp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.otp.R;
import com.example.otp.config.ServerConfig;
import com.example.otp.response.ResponseValue;
import com.example.otp.rest.ApiService;
import com.example.otp.utils.SharedPrefManager;
import com.google.android.material.snackbar.Snackbar;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistasiActivity extends AppCompatActivity {

    private Context mContext;
    private ProgressDialog progressDialog;
    private static final String TAG = "RegistrasiActivity";
    ApiService mApiService;

    //   public Button buttonDaftar,buttonMasuk;
//    EditText kolomNama, kolomNomor, kolomEmail;

    @BindView(R.id.kolomNama)
    EditText etNama;
    @BindView(R.id.kolomNomor)
    EditText etPhone;
    @BindView(R.id.kolomEmail)
    EditText etEmail;
    @BindView(R.id.buttonDaftar)
    Button btnSignUp;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    @OnClick(R.id.buttonDaftar)
    void signup() {
        //hilangkan keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(coordinatorLayout.getWindowToken(), 0);

        //create progres dialog
        progressDialog = ProgressDialog.show(mContext, null, getString(R.string.memuat), true, false);


        //mengambil nilai inputan ke string
        String strNama = etNama.getText().toString();
        String strPhone = clearPhone(etPhone.getText().toString());
        String strEmail = etEmail.getText().toString();
        String token = SharedPrefManager.getInstance(mContext).getDeviceToken();


        if (strNama.isEmpty() || strNama.equals(null)) {
            progressDialog.dismiss();
            etNama.setError("Nama diperlukan");
            etNama.requestFocus();
            return;
        } else if (strPhone.equals("62")) {
            progressDialog.dismiss();
            etPhone.setError("Nomor telepon diperlukan");
            etPhone.requestFocus();
            return;
        } else if (strPhone.length() < 12) {
            progressDialog.dismiss();
            etPhone.setError("Nomor telepon tidak valid");
            etPhone.requestFocus();
            return;
        } else if (strPhone.isEmpty() || strPhone.equals(null)) {
            progressDialog.dismiss();
            etPhone.setError("Nomor telepon diperlukan");
            etPhone.requestFocus();
            return;
        } else if (strEmail.isEmpty() || strEmail.equals(null)) {
            progressDialog.dismiss();
            etEmail.setError("Email diperlukan");
            etEmail.requestFocus();
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(strEmail).matches()) {
            progressDialog.dismiss();
            etEmail.setError("Email tidak valid");
            etEmail.requestFocus();
            return;
        } else if (token.isEmpty() || token.equals(null)) {
            progressDialog.dismiss();
            etEmail.setError("Email diperlukan");
            etEmail.requestFocus();
            return;
        } else {

            mApiService.signupRequest(strNama, strPhone, strEmail, token).enqueue(new Callback<ResponseValue>() {
                @Override
                public void onResponse(Call<ResponseValue> call, Response<ResponseValue> response) {
                    progressDialog.dismiss();
                    String value, message;
                    if (response.isSuccessful()) {
                        value = response.body().getValue();
                        message = response.body().getMessage();
                        if (value.equals("1")) {
                            //snack bar success
                            Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show();

//                            Intent intent = new Intent(mContext,SigninActivity.class);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
//                            startActivity(intent);

                        } else {
                            Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(mContext, "Gagal Registrasi", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseValue> call, Throwable t) {
                    Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
                    progressDialog.dismiss();
                    Snackbar.make(coordinatorLayout, R.string.lostconnection, Snackbar.LENGTH_LONG).show();
                }
            });
        }

    }


    @OnClick(R.id.buttonMasuk)
    void toSignUp() {
        Intent intent = new Intent(mContext, SigningActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registasi);

        mContext = this;
        mApiService = ServerConfig.getAPIService();
        ButterKnife.bind(this);
//        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/MavenPro-Regular.ttf");
//        btnSignUp.setTypeface(type);

        //if get intent
        if (getIntent().hasExtra("phone")) {
            etPhone.setText(getIntent().getStringExtra("phone").toString());
        }
    }


    public String clearPhone(String phoneNumber) {
        String hp = phoneNumber.replaceAll("-", "");
        String clearPhone = hp.substring(1, hp.length());
        return clearPhone;
    }

    //        kolomNama = (EditText) findViewById(R.id.kolomNama);
//        kolomNomor = (EditText) findViewById(R.id.kolomNomor);
//        kolomEmail = (EditText) findViewById(R.id.kolomEmail);
//
//        buttonDaftar = (Button) findViewById(R.id.buttonDaftar);
//    buttonHome = (Button) findViewById(R.id.buttonHome);
//
//        buttonDaftar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (kolomNama.getText().toString().isEmpty()){
//                    Toast.makeText(RegistasiActivity.this, "Kolom nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
//                    return;
//                }else if(kolomNomor.getText().toString().isEmpty()){
//                    Toast.makeText(RegistasiActivity.this, "Kolom nomor tidak boleh kosong", Toast.LENGTH_SHORT).show();
//                    return;
//                }else if(kolomEmail.getText().toString().isEmpty()){
//                    Toast.makeText(RegistasiActivity.this, "Kolom email tidak boleh kosong", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                Intent buttonDaftar = new Intent(getApplicationContext(), SigningActivity.class);
//                startActivity(buttonDaftar);
//            }
//        });


//
//        buttonHome.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            ButtonHome();
//        }
//    });
//}

//    public void ButtonHome(){
//        Intent buttonHome = new Intent(getApplicationContext(), HalamanUtamaActivity.class);
//        startActivity(buttonHome);
//    }
}