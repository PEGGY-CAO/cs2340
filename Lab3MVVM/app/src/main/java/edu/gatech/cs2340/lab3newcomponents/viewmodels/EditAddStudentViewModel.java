package edu.gatech.cs2340.lab3newcomponents.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import edu.gatech.cs2340.lab3newcomponents.entity.Student;
import edu.gatech.cs2340.lab3newcomponents.model.Model;
import edu.gatech.cs2340.lab3newcomponents.model.StudentInteractor;

/**
 * View model supporting adding and updating an individual student
 */
public class EditAddStudentViewModel  extends AndroidViewModel {
    private StudentInteractor interactor;

    public EditAddStudentViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getStudentInteractor();
    }

    public void updateStudent(Student student ) {
        interactor.updateStudent(student);
    }

    public void addStudent(Student student) {
        interactor.addStudent(student);
    }
}
