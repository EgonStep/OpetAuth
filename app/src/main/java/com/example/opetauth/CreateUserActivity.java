package com.example.opetauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateUserActivity extends AppCompatActivity {

    private EditText editLogin, editPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);


        editLogin = findViewById(R.id.editLogin);
        editPassword = findViewById(R.id.editPassword);

        mAuth = FirebaseAuth.getInstance();
    }


    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent dashboard = new Intent(CreateUserActivity.this, DashActivity.class);
            startActivity(dashboard);
            finish();
        }
    }

    public void createUser(View view) {
        String login = editLogin.getText().toString();
        String password = editPassword.getText().toString();
        mAuth.createUserWithEmailAndPassword(login, password).addOnCompleteListener(
                this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Toast.makeText(CreateUserActivity.this, "Fail to Create new user",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }
}
