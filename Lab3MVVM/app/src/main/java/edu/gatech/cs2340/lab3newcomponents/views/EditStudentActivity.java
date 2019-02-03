package edu.gatech.cs2340.lab3newcomponents.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import edu.gatech.cs2340.lab3newcomponents.R;
import edu.gatech.cs2340.lab3newcomponents.entity.Course;
import edu.gatech.cs2340.lab3newcomponents.entity.Student;
import edu.gatech.cs2340.lab3newcomponents.viewmodels.EditAddStudentViewModel;

/**
 * This class acts as the code behind for editing a student or creating a new student
 */
public class EditStudentActivity extends AppCompatActivity {

    /** reference to our view model */
    private EditAddStudentViewModel viewModel;

    /* ************************
        Widgets we will need for binding and getting information
     */
    private TextView idField;
    private EditText nameField;
    private Spinner majorSpinner;

    /* ***********************
       Data for student being edited.
     */
    private Student student;

    /* ***********************
       flag for whether this is a new student being added or an existing student being edited;
     */
    private boolean editing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        /*
         * Disables actionbar back button
         */
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(false);
        }

        /*
         * Grab the dialog widgets so we can get info for later
         */
        nameField = findViewById(R.id.student_name_input);
        majorSpinner = findViewById(R.id.major_spinner);
        idField = findViewById(R.id.student_id_field);
        Button button = findViewById(R.id.add_button);

        /*
          Set up the adapter to display the allowable majors in the spinner
         */
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Student.legalMajors);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        majorSpinner.setAdapter(adapter);

        /*
           If a student has been passed in, this was an edit, if not, this is a new add
         */
        if (getIntent().hasExtra(CourseDetailActivity.STUDENT_DATA)) {
            //Editing an existing student - set default data
            student = (Student) getIntent().getSerializableExtra(CourseDetailActivity.STUDENT_DATA);
            majorSpinner.setSelection(Student.findPosition(student.getMajor()));
            editing = true;
            button.setText("Update");
            setTitle("Editing Existing Student");
        } else {
            //Adding a new student
            student = new Student("Bob", "CS");
            editing = false;
            button.setText("Add");
            setTitle("Adding New Student");
        }

        nameField.setText(student.getName());
        idField.setText(String.format("Student ID: %d", student.getId()));

        viewModel = ViewModelProviders.of(this).get(EditAddStudentViewModel.class);
    }

    /**
     * Button handler for the add new student button
     *
     * @param view the button that was pressed
     */
    public void onAddPressed(View view) {
        Log.d("Edit", "Add/Update Student Pressed");

        student.setName(nameField.getText().toString());
        student.setMajor((String) majorSpinner.getSelectedItem());

        Log.d("Edit", "Got new student data: " + student);

        //do the right thing depending on whether this is a new student or an edit
        if (editing) {
            viewModel.updateStudent(student);
        } else {
            viewModel.addStudent(student);
        }

        finish();
    }

    /**
     * Button handler for cancel - just call back pressed
     *
     * @param view the button pressed
     */
    public void onCancelPressed(View view) {
        onBackPressed();
    }
}
