package edu.gatech.cs2340.lab3newcomponents.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.lab3newcomponents.R;
import edu.gatech.cs2340.lab3newcomponents.entity.Course;

/**
 * This class is an adapter, that is it translates the list of courses held in some data structure
 * into a list of displayed courses on the screen
 */
public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    /** a holder for the list of courses in the model */
    private List<Course> courseList = new ArrayList<>();

    /** reference to a listener in case someone clicks on a course */
    private OnCourseClickListener listener;

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        //Tell the adapter what layout to use for each course in the list
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_item, parent, false);

        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {

        /* When the list needs to display a course, it will call this method and pass the
           position in the list it needs and a view holder for that position
        */

        //get the course located at this position
        Course course = courseList.get(position);

        //now use the view holder to set the appropriate information
        holder.courseNumber.setText(course.getNumber());
        holder.courseName.setText(course.getName());
        holder.courseSchool.setText(course.getSchool().toString());


    }

    @Override
    public int getItemCount() {
        if (courseList == null) return 0;
        return courseList.size();
    }

    public void setCourseList(List<Course> courses) {
        courseList = courses;
        notifyDataSetChanged();
    }

    public Course getCourseAt(int position) {
        return courseList.get(position);
    }


    class CourseViewHolder extends RecyclerView.ViewHolder {
        //View holder needs reference to each widget in the individual item in the list
        private TextView courseSchool;
        private TextView courseName;
        private TextView courseNumber;


        /**
         * Construct a new view holder, grab all the widget references and setup the
         * listener to detect a click on this item.
         *
         * @param itemView
         */
        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            courseSchool = itemView.findViewById(R.id.text_school);
            courseName = itemView.findViewById(R.id.text_course_name);
            courseNumber = itemView.findViewById(R.id.text_course_number);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onCourseClicked(courseList.get(position));
                    }
                }
            });

        }
    }

    public interface OnCourseClickListener {
        void onCourseClicked(Course course);
    }

    public void setOnCourseClickListener(OnCourseClickListener listener) {
        this.listener = listener;
    }

}
