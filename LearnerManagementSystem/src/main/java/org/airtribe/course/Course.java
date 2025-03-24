package org.airtribe.course;

import java.util.ArrayList;
import java.util.List;
import org.airtribe.cohort.Cohort;


public abstract class Course {
  protected String courseId;

  protected String courseName;

  public String courseDescription;

  public String courseLanguage; // Node, java

  public List<Cohort> _cohortList;

  public Course(String courseId, String courseName, String courseDescription, String courseLanguage) {
    this.courseId = courseId;
    this.courseName = courseName;
    this.courseDescription = courseDescription;
    this.courseLanguage = courseLanguage;
    _cohortList = new ArrayList<>();
  }

  public abstract void displayCourseDetails();

  public Course addCohortToCourse(Cohort cohort) {
    _cohortList.add(cohort);
    return this;
  }

}
