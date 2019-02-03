package edu.gatech.cs2340.lab3newcomponents.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.lab3newcomponents.R;
import edu.gatech.cs2340.lab3newcomponents.entity.Student;

/**
 * Adapts the list of students in the model to be a list of graphical elements in view
 */
public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    /** a copy of the list of students in the model */
    private List<Student> studentList = new ArrayList<>();

    /** a listener for a touch event on the student */
    private OnStudentClickListener listener;

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        // hook up to the view for a single student in the system
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_item, parent, false);

        return new StudentViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {

        //bind the student data for one student
        Student student = studentList.get(position);

        Log.d("APP", "Binding: " + position + " " + studentList.get(position));

        holder.studentMajor.setText(student.getMajor());
        holder.studentName.setText(student.getName());

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public void setStudentList(List<Student> students) {
        studentList = students;
        notifyDataSetChanged();
    }


    /**
     * This is a holder for the widgets associated with a single entry in the list of students
     */
    class StudentViewHolder extends RecyclerView.ViewHolder {
        private TextView studentName;
        private TextView studentMajor;



        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.text_student_name);
            studentMajor = itemView.findViewById(R.id.text_student_major);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onStudentClicked(studentList.get(position));
                    }
                }
            });

        }
    }

    public interface OnStudentClickListener {
        void onStudentClicked(Student student);
    }

    public void setOnStudentClickListener(OnStudentClickListener listener) {
        this.listener = listener;
    }
}
