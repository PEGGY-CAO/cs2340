package edu.gatech.cs2340.lab3newcomponents.model;

import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import edu.gatech.cs2340.lab3newcomponents.entity.Course;

/**
 * Provide operations associated with Courses
 *
 * Basically provides an interface between the UI (view model) and the Model classes
 */
public class CourseInteractor extends Interactor {

    public CourseInteractor(Repository repo) {
        super(repo);

    }

    public void addCourse(Course course) {
        getRepository().addCourse(course);
    }

    public List<Course> getAllCourses() {
        return getRepository().getAllCourses();
    }

    public void deleteCourse(Course course) {
        getRepository().deleteCourse(course);
    }
}
