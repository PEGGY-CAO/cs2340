package edu.gatech.cs2340.lab3newcomponents.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import edu.gatech.cs2340.lab3newcomponents.entity.Course;
import edu.gatech.cs2340.lab3newcomponents.entity.Student;
import edu.gatech.cs2340.lab3newcomponents.model.Model;
import edu.gatech.cs2340.lab3newcomponents.model.StudentInteractor;

/**
 * View model supporting viewing a list of students either for a single course or
 * every student in the application.
 */
public class StudentListingViewModel extends AndroidViewModel {

    private StudentInteractor interactor;
    private Course currentCourse;

    public StudentListingViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getStudentInteractor();
    }

    public void setCurrentCourse(Course course) {
        currentCourse = course;
    }

    public List<Student> getRegisteredStudents() {
        Log.d("APP", "Current Course " + currentCourse);
        return interactor.getStudentsForCourse(currentCourse);
    }

    public List<Student> getStudents() {
        return interactor.getAllStudents();
    }

}
