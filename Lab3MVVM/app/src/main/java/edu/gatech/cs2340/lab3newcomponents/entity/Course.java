package edu.gatech.cs2340.lab3newcomponents.entity;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a single course in the application.  It is an information holder.
 *
 */


public class Course implements Serializable {

    /** unique course identifying number, can be used as a key */
    private int id;

    /** the course name */
    private String name;

    /** the course number */
    private String number;

    /** the school code for the course */
    private SchoolCode school;

    /** the students registered for this course */
    private List<Integer> registeredStudents;

    public Course(String nam, String numb, SchoolCode sch) {
        name = nam;
        number = numb;
        school = sch;
        registeredStudents = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public SchoolCode getSchool() {
        return school;
    }

    public int getId() {
        return id;
    }

    public void setId(int pid) {
       id = pid;
    }

    public void registerStudent(Student s) {
        registeredStudents.add(s.getId());
    }

    public List<Integer> getRegisteredStudents() {
        Log.d("APP","Registered Students: " + registeredStudents);
        return registeredStudents;
    }

    @Override
    public String toString() {
        return "Course: " + name + "students: " + registeredStudents;
    }
}
