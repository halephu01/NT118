package com.example.lab4_3;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;
    // Database Name
    private static final String DATABASE_NAME = "ManageStudent";
    // Contacts table name
    private static final String TABLE_STUDENTS = "Students";
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PH_NO = "phone_number";
    private static final String KEY_SCORE = "score";
    private static final String KEY_ADD = "address";
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENT_TABLE = "CREATE TABLE " +
                TABLE_STUDENTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY autoincrement,"
                + KEY_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_PH_NO + " TEXT,"
                + KEY_SCORE + "FLOAT,"
                + KEY_ADD + "TEXT" + ")";
        String CREATE = "CREATE TABLE Students (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "email TEXT," +
                "phone_number TEXT," +
                "score FLOAT," +
                "address TEXT" + ")";
        db.execSQL(CREATE);
    }
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        // Create tables again
        onCreate(db);
    }
    public void addStudent(com.example.lab4_3.Student new_student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id", new_student.getId());
        values.put("name", new_student.getName());
        values.put("email", new_student.getEmail());
        values.put("phone_number", new_student.getPhoneNumber());
        values.put("score", new_student.getScore());
        values.put("address", new_student.getAddress());

        db.insert(TABLE_STUDENTS, null, values);
        db.close();
    }
    public boolean deleteStudent(int rowId) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_STUDENTS, KEY_ID + "=" + rowId,
                null) > 0;
    }
    public Student getStudent(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_STUDENTS + " WHERE id = ? ";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            @SuppressLint("Range")
            Student student = new Student(cursor.getInt(cursor.getColumnIndex(KEY_ID)),
                    cursor.getString(cursor.getColumnIndex(KEY_NAME)),
                    cursor.getString(cursor.getColumnIndex(KEY_EMAIL)),
                    cursor.getString(cursor.getColumnIndex(KEY_PH_NO)),
                    cursor.getString(cursor.getColumnIndex(KEY_SCORE)),
                    cursor.getString(cursor.getColumnIndex(KEY_ADD)));
            cursor.close();
            return student;
        } else {
            cursor.close();
            return null;
        }
    }
    public void updateStudent(Student update_stu) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", update_stu.getId());
        values.put("name", update_stu.getName());
        values.put("email", update_stu.getEmail());
        values.put("phone_number", update_stu.getPhoneNumber());
        values.put("score",update_stu.getScore());
        values.put("address",update_stu.getAddress());

        db.update("Students", values, "id = ?", new String[]{String.valueOf(update_stu.getId())});
    }
    @SuppressLint("Range")
    public List<Student> getAllStudents()
    {
        List<Student> students_ = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_STUDENTS, new String[]{KEY_ID,KEY_NAME,KEY_EMAIL,KEY_PH_NO,KEY_SCORE,KEY_ADD},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            students_.add( new Student(cursor.getInt(cursor.getColumnIndex(KEY_ID)),
                    cursor.getString(cursor.getColumnIndex(KEY_NAME)),
                    cursor.getString(cursor.getColumnIndex(KEY_EMAIL)),
                    cursor.getString(cursor.getColumnIndex(KEY_PH_NO)),
                    cursor.getString(cursor.getColumnIndex(KEY_SCORE)),
                    cursor.getString(cursor.getColumnIndex(KEY_ADD))));
        }
        return students_;
    }
    public void deleteAllContacts()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENTS, null, null);
    }
}