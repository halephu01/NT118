package com.example.lab2_3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ArrayAdapter<Employee> adapter;
    ListView lvPerson;
    TextView tvPerson;
    Button btNhapNV;
    EditText edMaNV, edTenNV;
    RadioGroup rgLoaiNV;
    ArrayList<Employee> employees;

    Employee employee;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvPerson = findViewById(R.id.tv_person);
        lvPerson = findViewById(R.id.lv_person);
        btNhapNV = findViewById(R.id.bt_nhapNV);
        edMaNV = findViewById(R.id.ed_maNV);
        edTenNV = findViewById(R.id.ed_tenNV);
        rgLoaiNV = findViewById(R.id.rg_loaiNV);

        employees = new ArrayList<Employee>();
        adapter = new ArrayAdapter<Employee>(this, android.R.layout.simple_list_item_1,employees);
        lvPerson.setAdapter(adapter);
        btNhapNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radId = rgLoaiNV.getCheckedRadioButtonId();
                String id = edMaNV.getText().toString();
                String name = edTenNV.getText().toString();
                if (radId == R.id.rd_chinhthuc) {
                    employee = new EmployeeFulltime();
                } else {
                    employee = new EmployeeParttime();
                }
                employee.setId(id);
                employee.setName(name);
                employees.add(employee);
                adapter.notifyDataSetChanged();

                edMaNV.setText("");
                edTenNV.setText("");

                rgLoaiNV.clearCheck();
            }
        });
    }
    };
