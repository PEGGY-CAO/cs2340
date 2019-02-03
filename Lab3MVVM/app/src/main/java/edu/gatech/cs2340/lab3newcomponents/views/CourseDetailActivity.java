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
import android.widget.Toast;

import edu.gatech.cs2340.lab3newcomponents.R;
import edu.gatech.cs2340.lab3newcomponents.entity.Course;
import edu.gatech.cs2340.lab3newcomponents.entity.Student;
import edu.gatech.cs2340.lab3newcomponents.viewmodels.StudentListingViewModel;

/**
 * This view displays the list of all students in a specific course
 * <p>
 * The course is whatever was selected in the MainActivity with the click listener
 */

public class CourseDetailActivity extends AppCompatActivity {

    /** this contains a key for passing the student */
    public static final String STUDENT_DATA = "STUDENT_DATA";

    /** need an int defined since activity returns a result */
    private static final int EDIT_REQUEST = 5;

    /** key for passing course */
    public static final String EXTRA_COURSE = "edu.gatech.cs2340.lab3newcomponents.views.EXTRA_COURSE";

    /** reference to the course whose details this is */
    private Course course;

    /** our view model */
    private StudentListingViewModel viewModel;

    /** the adapter for translating List of student into display in recycler view */
    private StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        //check if a course was passed in, correct behavior is that a course always is passed
        if (getIntent().hasExtra(MainActivity.EXTRA_COURSE)) {
            course = (Course) getIntent().getSerializableExtra(MainActivity.EXTRA_COURSE);
        } else {
            //no course is an internal error, this should not happen
            Log.d("APP", "INTERNAL ERROR< NO COURSE PASSED");
        }

        //set up the button to register students - NOT IMPLEMENTED YET!!!
        FloatingActionButton add =  findViewById(R.id.button_add_student);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CourseDetailActivity.this, "Not implemented.", Toast.LENGTH_LONG).show();
            }
        });

        setTitle(String.format("Students Registered for: %s %s", course.getSchool(),course.getNumber()));

        /*
           Setup our recycler view by grabbing widget reference, setting layout and then setting
           the adapter.
         */
        RecyclerView recyclerView = findViewById(R.id.student_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new StudentAdapter();
        recyclerView.setAdapter(adapter);

        //grab our view model
        viewModel = ViewModelProviders.of(this).get(StudentListingViewModel.class);
        viewModel.setCurrentCourse(course);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.setStudentList(viewModel.getRegisteredStudents());

        adapter.setOnStudentClickListener(new StudentAdapter.OnStudentClickListener() {
            @Override
            public void onStudentClicked(Student student) {
                Intent intent = new Intent(CourseDetailActivity.this, EditStudentActivity.class);
                intent.putExtra(STUDENT_DATA, student);
                startActivityForResult(intent, EDIT_REQUEST);
            }
        });
    }
}
