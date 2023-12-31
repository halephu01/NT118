package com.example.lab4_3;


import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder>{
    private Context mcontext;
    private List<Student> mListStudent;
    private OnItemClickListener Listener;
    private DatabaseHandler db;

    public StudentAdapter(Context mcontext) {
        this.mcontext = mcontext;
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.Listener = listener;
    }
    public void setDB(DatabaseHandler db) {
        this.db = db;
    }
    public void setData(List<Student> mList) {
        this.mListStudent = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentAdapter.StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student,parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.StudentViewHolder holder, int position) {
        Student student = mListStudent.get(position);
        if (student == null) return;
        holder.tVName.setText(student.getName());
        holder.tVEmail.setText(student.getEmail());
        holder.tVPhone.setText(student.getPhoneNumber());
        holder.tvScore.setText(student.getScore());
        holder.tvAddress.setText(student.getAddress());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(mcontext);
                builder.setTitle("Edit student");
                View view = LayoutInflater.from(mcontext).inflate(R.layout.dialog_edit_student, null);

                EditText editTextName = view.findViewById(R.id.eTName);
                EditText editTextEmail = view.findViewById(R.id.eTEmail);
                EditText editTextPhone = view.findViewById(R.id.editTextPhone);
                EditText editTextScore = view.findViewById(R.id.editTextScore);
                EditText editTextAddress = view.findViewById(R.id.editTextAddress);

                editTextName.setText(student.getName());
                editTextEmail.setText(student.getEmail());
                editTextPhone.setText(student.getPhoneNumber());
                editTextScore.setText(student.getScore());
                editTextAddress.setText((student.getAddress()));
                //Toast.makeText(this, "show", Toast.LENGTH_LONG);
                int index = mListStudent.indexOf(student);
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        student.update(editTextName.getText().toString(),
                                editTextEmail.getText().toString(),
                                editTextPhone.getText().toString(),
                                editTextScore.getText().toString(),
                                editTextAddress.getText().toString());
                        db.updateStudent(student);
                        if (index != -1) {
                            mListStudent.set(index, student);
                            notifyItemChanged(index);
                        }
                    }
                });
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.deleteStudent(student.getId());
                        if (index != -1) {
                            mListStudent.remove(index);
                            notifyItemRemoved(index);
                        }
                    }
                });
                builder.setView(view);
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mListStudent != null)
        {
            return mListStudent.size();
        }
        return 0;
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        private TextView tVName;
        private TextView tVEmail;
        private TextView tVPhone;
        private TextView tvScore;
        private TextView tvAddress;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            tVName = itemView.findViewById(R.id.tVName);
            tVEmail = itemView.findViewById(R.id.tVEmail);
            tVPhone = itemView.findViewById(R.id.tVPhoneNubmer);
            tvScore = itemView.findViewById(R.id.tvScore);
            tvAddress = itemView.findViewById(R.id.tvAddress);
        }
    }
}