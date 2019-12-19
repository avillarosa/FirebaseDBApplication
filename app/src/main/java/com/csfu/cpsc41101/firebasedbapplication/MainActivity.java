package com.csfu.cpsc41101.firebasedbapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseReference mDB = FirebaseDatabase.getInstance().getReference();

        // insert a student object into Firebase Realtime DB
        /*
        Student sObj = new Student("Kiki", "James", "74838477");
        DatabaseReference mStudents = mDB.child("students");
        //String key = mStudents.push().getKey();
        String key = "person2";
        mStudents.child(key).setValue(sObj);
        */

        //Query query = mDB.child("students").orderByChild("lastName").equalTo("Shen");
        Query query = mDB.child("students").orderByKey().equalTo("person1");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                Object val = dataSnapshot.getValue();
                Log.d("Firebase Application ", "Key = " + key);
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    key = ds.getKey();
                    val = ds.getValue();
                    //
                    //Log.d("Firebase Application ", "Key = " + key);
                    Student sObj = ds.getValue(Student.class);
                    Log.d("Firebase Application ", "Key = " + sObj.lastName);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
