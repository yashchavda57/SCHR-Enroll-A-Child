package com.example.enrollchild;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    EditText childName,fatherName,Address,mobile,description;
    Button submit;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        childName = findViewById(R.id.childName);
        fatherName = findViewById(R.id.fatherName);
        mobile = findViewById(R.id.mobile);
        Address = findViewById(R.id.address);
        description = findViewById(R.id.description);

        submit = findViewById(R.id.submit);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String childname = childName.getText().toString().trim();
                String fathername = fatherName.getText().toString().trim();
                String address = Address.getText().toString().trim();
                String Description = description.getText().toString().trim();
                String Mobile = mobile.getText().toString().trim();

                userId = "String";
                Map<String,Object> user = new HashMap<>();
                user.put("childName",childname);
                user.put("fatherName",fathername);
                user.put("address",address);
                user.put("mobile",Mobile);
                user.put("description",Description);
//                Task<DocumentReference> documentReference = fStore.collection("users")
                CollectionReference collectionReference = fStore.collection("users");

                collectionReference.add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }
                });




            }
        });


    }

    public void logout(View v){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }
}
