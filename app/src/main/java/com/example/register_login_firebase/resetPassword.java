package com.example.register_login_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class resetPassword extends AppCompatActivity {

    EditText emailEditText;
    Button resetBtn;
    FirebaseAuth Auth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        progressDialog = new ProgressDialog(this);
        emailEditText = (EditText) findViewById(R.id.email);
        resetBtn = (Button) findViewById(R.id.resetbtn);

        Auth = FirebaseAuth.getInstance();

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordReset();
            }
        });
    }

    private void passwordReset() {
        String email = emailEditText.getText().toString().trim();

        if(email.isEmpty()){
            emailEditText.setError("Email is required!");
            emailEditText.requestFocus();
            return;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Please provide a valid email");
            emailEditText.requestFocus();
            return;
        }else{
            progressDialog.setMessage("Sending you reset link...");
            progressDialog.setTitle("Reset Password");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            Auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        Toast.makeText(resetPassword.this, "Reset Link sent Successfully", Toast.LENGTH_SHORT).show();
                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(resetPassword.this, "Something went wrong, Try Again!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}