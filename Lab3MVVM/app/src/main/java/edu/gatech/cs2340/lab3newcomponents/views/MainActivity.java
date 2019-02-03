package edu.gatech.cs2340.lab3newcomponents.views;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import edu.gatech.cs2340.lab3newcomponents.R;
import edu.gatech.cs2340.lab3newcomponents.entity.Course;
import edu.gatech.cs2340.lab3newcomponents.entity.SchoolCode;
import edu.gatech.cs2340.lab3newcomponents.model.Model;
import edu.gatech.cs2340.lab3newcomponents.viewmodels.CourseViewModel;

/**
 * This is the startup activity.   It should probably be called CourseListingActivity
 * instead.
 */
public class MainActivity extends AppCompatActivity {

    /** a reference to our view model */
    private CourseViewModel courseViewModel;

    /** make an adapter for the list of courses */
    private final CourseAdapter adapter = new CourseAdapter();

    /** an integer used because add a course returns something */
    public static final int ADD_COURSE_REQUEST_ID = 1;

    /** a string key for passing the course in a map */
    public static final String EXTRA_COURSE = "edu.gatech.cs2340.lab3newcomponents.views.EXTRA_COURSE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         //This code sets up our button at bottom of screen to add a new course
        FloatingActionButton add =  findViewById(R.id.button_add_course);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddCourseActivity.class);
                startActivityForResult(intent, ADD_COURSE_REQUEST_ID);
            }
        });

        /*
          Now we need to set up the view for holding the list of courses
         */
        //first grab a reference to the widget
        RecyclerView recyclerView = findViewById(R.id.course_list);
        //Set the layout manager for the list to just be a vertical list of stuff
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        //Setup the adapter for the view
        recyclerView.setAdapter(adapter);

        //grab the reference to our view model
        courseViewModel = ViewModelProviders.of(this).get(CourseViewModel.class);


        /**
         * This code sets up the swipe gesture to delete courses from the list
         */
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                //From the passed in view holder, we can get the adapter position that it is at
                //We call the adapter to ask which Course is at that position
                courseViewModel.deleteCourse(adapter.getCourseAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Course deleted", Toast.LENGTH_SHORT).show();
            }

        }).attachToRecyclerView(recyclerView);


        //This code sets up a listener so that if you click on a course, we launch the detail screen
        adapter.setOnCourseClickListener(new CourseAdapter.OnCourseClickListener() {
            @Override
            public void onCourseClicked(Course course) {
                Intent intent = new Intent(MainActivity.this, CourseDetailActivity.class);
                intent.putExtra(EXTRA_COURSE, course);
                startActivity(intent);
            }
        });

        //set the list of courses in teh view by using the adapter
        adapter.setCourseList(courseViewModel.getCourses());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Log.d("APP", "resultCode = " + String.valueOf(resultCode) "  r");

        if (requestCode == ADD_COURSE_REQUEST_ID && resultCode == RESULT_OK) {
            String name = data.getStringExtra(AddCourseActivity.EXTRA_NAME);
            String number = data.getStringExtra(AddCourseActivity.EXTRA_NUMBER);
            SchoolCode code = (SchoolCode) data.getSerializableExtra(AddCourseActivity.EXTRA_SCHOOLCODE);

            courseViewModel.addCourse(name, number, code);

            adapter.setCourseList(courseViewModel.getCourses());


        } else {
            Toast.makeText(this, "Course not saved!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.view_all_students) {
            Intent intent = new Intent(this, ViewAllStudentsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
