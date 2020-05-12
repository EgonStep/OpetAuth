package com.example.opetauth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class DashActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private TextView textWelcome, textResult;
    private EditText editSalary, editChildren, editPet, editName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

        mAuth = FirebaseAuth.getInstance();
        textWelcome = findViewById(R.id.textWelcome);
        textResult = findViewById(R.id.textResult);
        editChildren = findViewById(R.id.editChild);
        editSalary = findViewById(R.id.editSalary);
        editName = findViewById(R.id.editName);
        editPet = findViewById(R.id.editPets);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        textWelcome.setText("Welcome, " + user.getEmail());
        db = FirebaseFirestore.getInstance();
    }

    public void leave(View view) {
        mAuth.signOut();
        Intent init = new Intent(DashActivity.this, MainActivity.class);
        startActivity(init);
        finish();
    }

    public void createUserData(View view) {
        Intent cadOneActivity = new Intent(DashActivity.this, CadOneActivity.class);
        startActivity(cadOneActivity);
        finish();
    }

    public void createSalesdata(View view) {
        Intent cadTwoActivity = new Intent(DashActivity.this, CadTwoActivity.class);
        startActivity(cadTwoActivity);
        finish();
    }

    public void generateFirebaseData(View view) {
        List<Person> persons = PopulateUtil.loadPersons();
        persons.forEach(
                p -> db.collection("exemplo").add(p)
        );
    }

    public void loadData(View view) {
        CollectionReference collectionReference = db.collection("exemplo");
        collectionReference.whereEqualTo("active", false)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String result = "";
                        List<Person> persons = new ArrayList<>();
                        for(QueryDocumentSnapshot documentSnapshot: task.getResult()) {
                            result += "ID: " + documentSnapshot.getId() + "\n" +
                                    documentSnapshot.getData().toString() + "\n";
                            persons.add(documentSnapshot.toObject(Person.class));
                        }

                        result = "";
                        for (Person p : persons) {
                            result += p.toString() + "\n";
                        }
                        textResult.setText(result);
                    }
                });
    }

    public void searchBySalaryAndSons(View view) {
        CollectionReference collectionReference = db.collection("exemplo");
        collectionReference.whereGreaterThan("salary", Double.valueOf(
                editSalary.getText().toString()));
        Query query = collectionReference.whereGreaterThan("child",
                Integer.valueOf(editChildren.getText().toString()));
        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                String result = "";
                for(QueryDocumentSnapshot documentSnapshot: task.getResult()) {
                    result += documentSnapshot.getData().toString() + "\n";
                }
                textResult.setText(result);
            }
        });

    }

    public void searchByPets(View view) {
        CollectionReference collectionReference = db.collection("exemplo");
        Query query = collectionReference.whereArrayContains("pets", editPet.getText().toString());
        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                String result = "";
                for(QueryDocumentSnapshot documentSnapshot: task.getResult()) {
                    result += documentSnapshot.getData().toString() + "\n";
                }
                textResult.setText(result);
            }
        });
    }

    public void searchBySalary(View view) {
        CollectionReference collectionReference = db.collection("exemplo");
        Query query = collectionReference.whereEqualTo("salary", Double.valueOf(
                editSalary.getText().toString()));
        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                String result = "";
                for(QueryDocumentSnapshot documentSnapshot: task.getResult()) {
                    result += documentSnapshot.getData().toString() + "\n";
                }
                textResult.setText(result);
            }
        });
    }

    public void searchByName(View view) {
        CollectionReference collectionReference = db.collection("exemplo");
        Query query = collectionReference.whereEqualTo("name", editName.getText().toString());
        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                String result = "";
                for(QueryDocumentSnapshot documentSnapshot: task.getResult()) {
                    result += documentSnapshot.getData().toString() + "\n";
                }
                textResult.setText(result);
            }
        });
    }
}
