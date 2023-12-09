package com.example.lab4_3;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class StudentViewHolder extends RecyclerView.ViewHolder {
    private final TextView textViewStudentID;
    private final TextView textViewName;
    private final TextView textViewAge;
    private final TextView textAVGScore;

    public StudentViewHolder(View itemView) {
        super(itemView);
        textViewStudentID = itemView.findViewById(R.id.textViewStudentID);
        textViewName = itemView.findViewById(R.id.textViewName);
        textViewAge = itemView.findViewById(R.id.textViewAge);
        textAVGScore = itemView.findViewById(R.id.textViewAVGScore);
    }

    public void bind(final Student student, final OnItemClickListener listener) {
        textViewStudentID.setText(student.getStudentid());
        textViewName.setText(student.getName());
        textViewAge.setText("Age: " + String.valueOf(student.getAge()) + " ");
        textAVGScore.setText("Score: " + String.valueOf(student.getScore()));
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(student);
            }
        });
    }
}