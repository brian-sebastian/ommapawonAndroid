package com.example.otp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.otp.R;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SigningActivity extends AppCompatActivity {

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signing);

        final EditText inputMobile = findViewById(R.id.inputMobile);
        Button buttonGetOTP = findViewById(R.id.buttonGetOTP);

        final ProgressBar progressBarSignin = findViewById(R.id.progressBarSigning);

        buttonGetOTP.setOnClickListener(view -> {
            if(inputMobile.getText().toString().trim().isEmpty()){
                Toast.makeText(SigningActivity.this, "Masukkan Nomor Telepon", Toast.LENGTH_SHORT).show();
                return;
            }
            progressBarSignin.setVisibility(View.VISIBLE);
            buttonGetOTP.setVisibility(View.INVISIBLE);

            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    "+62" + inputMobile.getText().toString(),
                    10,
                    TimeUnit.SECONDS,
                    SigningActivity.this,
                    new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                        @Override
                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                            progressBarSignin.setVisibility(View.GONE);
                            buttonGetOTP.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onVerificationFailed(@NonNull FirebaseException e) {
                            progressBarSignin.setVisibility(View.GONE);
                            buttonGetOTP.setVisibility(View.VISIBLE);
                            Toast.makeText(SigningActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                            progressBarSignin.setVisibility(View.GONE);
                            buttonGetOTP.setVisibility(View.VISIBLE);
                            Intent intent = new Intent(getApplicationContext(),VerifyActivity.class);
                            intent.putExtra("mobile", inputMobile.getText().toString());
                            intent.putExtra("verificationId", verificationId);
                            startActivity(intent);
                        }
                    }
            );

        });


    }
}