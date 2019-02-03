package edu.gatech.cs2340.lab3newcomponents.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import edu.gatech.cs2340.lab3newcomponents.entity.Course;
import edu.gatech.cs2340.lab3newcomponents.entity.SchoolCode;
import edu.gatech.cs2340.lab3newcomponents.model.CourseInteractor;
import edu.gatech.cs2340.lab3newcomponents.model.Model;

/**
 * This viewmodel would support activities that need to interact with course objects
 *
 */
public class CourseViewModel extends AndroidViewModel {
    private CourseInteractor model;
    private List<Course> courses;

    public CourseViewModel(@NonNull Application application) {
        super(application);
        model = Model.getInstance().getCourseInteractor();
        courses = model.getAllCourses();
    }

    public void addCourse(String courseName, String courseNumber, SchoolCode courseCode) {
        Course course = new Course(courseName, courseNumber, courseCode);
        model.addCourse(course);
        courses = model.getAllCourses();
    }

    public void deleteCourse(Course course) {
        model.deleteCourse(course);
    }

    public List<Course> getCourses() {
        return courses;
    }
}
