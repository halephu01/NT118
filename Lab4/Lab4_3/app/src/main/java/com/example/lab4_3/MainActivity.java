package com.example.lab4_3;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {
    private EditText editTextStudentID;
    private EditText editTextName;
    private EditText editTextAge;
    private EditText editAVGScore;
    private EditText editAddress;
    private StudentAdapter studentAdapter;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextStudentID = findViewById(R.id.editTextStudentID);
        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editAVGScore = findViewById(R.id.editAVGScore);
        editAddress = findViewById(R.id.editAddress);
        Button buttonAdd = findViewById(R.id.buttonAdd);
        RecyclerView recyclerViewStudents = findViewById(R.id.recyclerViewStudents);

        studentAdapter = new StudentAdapter();
        studentAdapter.setOnItemClickListener(this);
        recyclerViewStudents.setAdapter(studentAdapter);
        recyclerViewStudents.setLayoutManager(new LinearLayoutManager(this));

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM students", null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String studentID = cursor.getString(cursor.getColumnIndex("studentid"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int age = cursor.getInt(cursor.getColumnIndex("age"));
                float score = cursor.getFloat(cursor.getColumnIndex("score"));
                String address = cursor.getString(cursor.getColumnIndex("address"));
                Student student = new Student(id, studentID, name, age, score, address);
                studentAdapter.addStudent(student);
            } while (cursor.moveToNext());
        }
        cursor.close();

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentid = editTextStudentID.getText().toString();
                String name = editTextName.getText().toString();
                int age = Integer.parseInt(editTextAge.getText().toString());
                float score = Float.parseFloat(editAVGScore.getText().toString());
                String address = editAddress.getText().toString();
                ContentValues values = new ContentValues();
                values.put("studentid", studentid);
                values.put("name", name);
                values.put("age", age);
                values.put("score", score);
                values.put("address", address);
                long id = database.insert("students", null, values);
                Student student = new Student((int) id, studentid, name, age, score, address);
                studentAdapter.addStudent(student);
                editTextStudentID.setText("");
                editTextName.setText("");
                editTextAge.setText("");
                editAVGScore.setText("");
                editAddress.setText("");
            }
        });
    }

    @Override
    public void onItemClick(final Student student) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit student");
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_edit_student, null);
        final EditText editTextStudentID = view.findViewById(R.id.edit1TextStudentID);
        final EditText editTextName = view.findViewById(R.id.edit1TextName);
        final EditText editTextAge = view.findViewById(R.id.edit1TextAge);
        final EditText editTextAVGScore = view.findViewById(R.id.edit1TextAVGScore);
        final EditText editTextAddress = view.findViewById(R.id.edit1TextAddress);
        editTextStudentID.setText(student.getStudentid());
        editTextName.setText(student.getName());
        editTextAge.setText(String.valueOf(student.getAge()));
        editTextAVGScore.setText(String.valueOf(student.getScore()));
        editTextAddress.setText(student.getAddress());
        builder.setView(view);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String studentid = editTextStudentID.getText().toString();
                String name = editTextName.getText().toString();
                int age = Integer.parseInt(editTextAge.getText().toString());
                float score = Float.parseFloat(editTextAVGScore.getText().toString());
                String address = editTextAddress.getText().toString();
                ContentValues values = new ContentValues();
                values.put("studentid", studentid);
                values.put("name", name);
                values.put("age", age);
                values.put("score", score);
                values.put("address", address);
                database.update("students", values, "id = ?", new String[]{String.valueOf(student.getId())});
                studentAdapter.updateStudent(new Student(student.getId(), studentid, name, age, score, address));
                editTextStudentID.setText(studentid);
                editTextName.setText(name);
                editTextAge.setText(String.valueOf(age));
                editTextAVGScore.setText(String.valueOf(score));
                editTextAddress.setText(address);
            }
        });
        builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                database.delete("students", "id = ?", new String[]{String.valueOf(student.getId())});
                studentAdapter.deleteStudent(student);
            }
        });
        builder.show();
    }
}