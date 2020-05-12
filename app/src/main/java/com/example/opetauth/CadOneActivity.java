package com.example.opetauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CadOneActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private EditText editName, editAddress, editPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_one);

        editName = findViewById(R.id.editName);
        editAddress = findViewById(R.id.editAddress);
        editPhone = findViewById(R.id.editPhone);
    }

    @Override
    protected void onStart() {
        super.onStart();
        db = FirebaseFirestore.getInstance();
    }

    public void saveOnFirebase(View view) {
        String name = editName.getText().toString();
        String address = editAddress.getText().toString();
        String phone = editPhone.getText().toString();

        Map<String, Object> userData = new HashMap<>();
        userData.put("name", name);
        userData.put("address", address);
        userData.put("phone", phone);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;

        db.collection("users").document(user.getUid()).set(userData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        String message = "User Created!";
                        Toast.makeText(CadOneActivity.this, message, Toast.LENGTH_LONG)
                                .show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String message = "Failed to Create New User!";
                        Toast.makeText(CadOneActivity.this, message, Toast.LENGTH_LONG)
                                .show();
                    }
                });
    }

    public void returnToMainPage(View view) {
        Intent mainPage = new Intent(CadOneActivity.this, DashActivity.class);
        startActivity(mainPage);
        finish();
    }
}
