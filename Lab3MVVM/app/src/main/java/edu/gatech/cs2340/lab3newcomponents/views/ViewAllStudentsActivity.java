package edu.gatech.cs2340.lab3newcomponents.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import edu.gatech.cs2340.lab3newcomponents.R;
import edu.gatech.cs2340.lab3newcomponents.entity.Student;
import edu.gatech.cs2340.lab3newcomponents.viewmodels.StudentListingViewModel;

import static edu.gatech.cs2340.lab3newcomponents.views.CourseDetailActivity.STUDENT_DATA;

/**
 * This displays all students in the model, regardless of registration
 */
public class ViewAllStudentsActivity extends AppCompatActivity {

    /** a key for passing data */
    public static final String STUDENT_DATA = "STUDENT_DATA";
    /** an int for the request code */
    private static final int EDIT_REQUEST = 5;
    /** our data model */
    private StudentListingViewModel  viewModel;
    /** an adapter for the recycler view */
    private StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_students);

        //button to add a new student to the database of ALL students
        FloatingActionButton add =  findViewById(R.id.button_add_student);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewAllStudentsActivity.this, EditStudentActivity.class);
                startActivity(intent);
            }
        });

        /*
         Set up our recycler view by grabbing the layout for a single item
         */
        RecyclerView recyclerView = findViewById(R.id.student_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // Setup the adapter for this recycler view
        adapter = new StudentAdapter();
        recyclerView.setAdapter(adapter);

        //grab our view model instance
        viewModel = ViewModelProviders.of(this).get(StudentListingViewModel.class);

        Log.d("APP", viewModel.getStudents().toString());

    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.setStudentList(viewModel.getStudents());

        adapter.setOnStudentClickListener(new StudentAdapter.OnStudentClickListener() {
            @Override
            public void onStudentClicked(Student student) {
                Intent intent = new Intent(ViewAllStudentsActivity.this, EditStudentActivity.class);
                intent.putExtra(STUDENT_DATA, student);
                startActivityForResult(intent, EDIT_REQUEST);
            }
        });
    }
}
