package edu.gatech.cs2340.lab3newcomponents.model;

import android.util.Log;

import java.util.List;

import edu.gatech.cs2340.lab3newcomponents.entity.Course;
import edu.gatech.cs2340.lab3newcomponents.entity.Student;

/**
 * Provide the operations associated with Student Entity
 *
 * Basically an interface between the Model and the UI (view model)
 */
public class StudentInteractor extends Interactor {

    public StudentInteractor(Repository repo) {
        super(repo);
    }

    public List<Student> getAllStudents() {
        return getRepository().getAllStudents();
    }
    public List<Student> getStudentsForCourse(Course course) {
        return getRepository().getStudentsForCourse(course);
    }

    public void addStudent (Student s) {
        getRepository().addStudent(s);
    }

    public void updateStudent(Student s) {
        getRepository().updateStudent(s);
        Log.d("APP", "Interactor: updating student: " + s);
    }


}
