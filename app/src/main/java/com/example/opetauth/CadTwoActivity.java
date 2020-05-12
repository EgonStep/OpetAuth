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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class CadTwoActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private EditText editTitle, editDescriptoin, editValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_two);

        editTitle = findViewById(R.id.editTitle);
        editDescriptoin = findViewById(R.id.editDescription);
        editValue = findViewById(R.id.editValue);
    }

    @Override
    protected void onStart() {
        super.onStart();
        db = FirebaseFirestore.getInstance();
    }

    public void createSaleData(View view) {
        String title = editTitle.getText().toString();
        String description = editDescriptoin.getText().toString();
        Double value = Double.parseDouble(editValue.getText().toString());

        Sales sales = new Sales(title, description, value);

        db.collection("sales").add(sales)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                String message = "Sales Created!";
                Toast.makeText(CadTwoActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String message = "Failed to create Sales!";
                Toast.makeText(CadTwoActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void returnToMainPage(View view) {
        Intent mainPage = new Intent(CadTwoActivity.this, DashActivity.class);
        startActivity(mainPage);
        finish();
    }
}
