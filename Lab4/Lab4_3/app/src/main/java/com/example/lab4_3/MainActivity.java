package com.example.lab4_3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rView;
    private StudentAdapter adapter;
    private Button btnAdd;
    private EditText edName;
    private EditText edEmail;
    private EditText edPhone;
    private EditText edScore;
    private EditText edAddress;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler db = new DatabaseHandler(this);
        db.deleteAllContacts();

        rView = findViewById(R.id.recyclerView);
        adapter = new StudentAdapter(this);
        adapter.setDB(db);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rView.setLayoutManager(linearLayoutManager);

        List<Student> students = db.getAllStudents();

        adapter.setData(students);
        rView.setAdapter(adapter);

        btnAdd = findViewById(R.id.btnAdd);
        edName = findViewById(R.id.eTName);
        edEmail = findViewById(R.id.eTEmail);
        edPhone = findViewById(R.id.editTextPhone);
        edScore = findViewById(R.id.editTextScore);
        edAddress = findViewById(R.id.editTextAddress);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edName.getText().length() == 0 ||
                        edEmail.getText().length() == 0||
                        edPhone.getText().length() == 0||
                        edScore.getText().length() == 0||
                        edAddress.getText().length() == 0)
                {
                    Toast toast = Toast.makeText(MainActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_LONG);
                    toast.show();
                }
                else
                {
                    Student new_stu = new Student(edName.getText().toString(),
                            edEmail.getText().toString(),
                            edPhone.getText().toString(),
                            edScore.getText().toString(),
                            edAddress.getText().toString());


                    db.addStudent(new_stu);
                    students.add(new_stu);
                    adapter.setData(students);
                    adapter.notifyDataSetChanged();
                    edName.setText("");
                    edEmail.setText("");
                    edPhone.setText("");
                    edScore.setText("");
                    edAddress.setText("");
                }
            }
        });
    }
}