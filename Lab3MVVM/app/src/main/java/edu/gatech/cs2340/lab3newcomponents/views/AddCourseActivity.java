package edu.gatech.cs2340.lab3newcomponents.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;

import edu.gatech.cs2340.lab3newcomponents.R;
import edu.gatech.cs2340.lab3newcomponents.entity.SchoolCode;

/**
 * This class adds a new course to the system
 */
public class AddCourseActivity extends AppCompatActivity {

    public static final String EXTRA_NAME =
            "edu.gatech.cs2340.lab3newcomponents.views.EXTRA_NAME";
    public static final String EXTRA_NUMBER =
            "edu.gatech.cs2340.lab3newcomponents.views.EXTRA_NUMBER";
    public static final String EXTRA_SCHOOLCODE =
            "edu.gatech.cs2340.lab3newcomponents.views.EXTRA_SCHOOLCODE";

    private EditText editCourseName;
    private EditText editCourseNumber;
    private Spinner schoolSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        editCourseName = findViewById(R.id.course_name_input);
        editCourseNumber = findViewById(R.id.course_number_input);
        schoolSpinner = findViewById(R.id.school_code_spinner);

        ArrayAdapter<SchoolCode> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                Arrays.asList(SchoolCode.values()));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        schoolSpinner.setAdapter(adapter);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        setTitle("Add New Course");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.add_course_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.save_course:
                saveCourse();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }

    private void saveCourse() {
        String number = editCourseNumber.getText().toString();
        String name   = editCourseName.getText().toString();
        SchoolCode code = (SchoolCode) schoolSpinner.getSelectedItem();

        if (number.trim().isEmpty() || name.trim().isEmpty()) {
            Toast.makeText(this, "Fields cannot be blank", Toast.LENGTH_LONG).show();
            return;
        }

        Log.d("APP", "Making intent");
        Log.d("APP", "Data - " + name + " " + number + " " + code.toString());
        Intent intent = new Intent();
        intent.putExtra(EXTRA_NAME, name);
        intent.putExtra(EXTRA_NUMBER, number);
        intent.putExtra(EXTRA_SCHOOLCODE, code);

        setResult(RESULT_OK, intent);

        finish();

    }
}
