package edu.gatech.cs2340.lab3newcomponents.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents a single Student in the database. It is an information holder.
 */

public class Student implements Serializable {
    /** a demonstration of using something other than an enum for holding choices
     *  IRL I would always use an enumeration for this
     */
    public static List<String> legalMajors = Arrays.asList("CS", "CM", "ISYE", "MATH", "EE", "CMPE", "NA");

    enum ClassStanding {
        FR, SO, JR, SR;
    }
    /** an attribute used to hold ClassStanding enum*/
    private ClassStanding classStand;

    /** a globally unique number for this object */
    private int id;

    /** this students name */
    private String name;

    /** this students major */
    private String major;

    /**
     * Lookup a major based on its code.  Returns the postion of that
     * major in the array, would not have to do this for an enum
     *
     * @param code the major to find
     *
     * @return the index of the array that corresponds to the submitted major
     */
    public static int findPosition(String code) {
        int i = 0;
        while (i < legalMajors.size()) {
            if (code.equals(legalMajors.get(i))) return i;
            ++i;
        }
        return 0;
    }

    /**
     * Constructor required for making the  new object
     *
     * @param nam     name of the student
     * @param maj    major of the student
     */
    public Student(String nam, String maj) {
        name = nam;
        major = maj;
    }

    //Getters are required for accessing the fields from the database
    public ClassStanding getClassStand() {
        return classStand;
    }

    public void setClassStand(ClassStanding c) {
        classStand = c;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public void setName(String nam) { name = nam; }

    public void setMajor(String maj) {major = maj; }

    //Need a way to set the id from the database
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Student: %s, major: %s, id: %d", name, major, id);
    }

}
