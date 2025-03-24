package org.airtribe.course;

public class OfflineCourse extends Course {

  private String location;

  public OfflineCourse(String courseId, String courseName, String courseDescription, String courseLanguage, String location) {
    super(courseId, courseName, courseDescription, courseLanguage);
    this.location = location;
  }

  @Override
  public void displayCourseDetails() {
    System.out.println("Course Id: " + courseId);
    System.out.println("Course Name: " + courseName);
    System.out.println("Course Description: " + courseDescription);
    System.out.println("Location: " + location);
    for (int i=0;i<_cohortList.size();i++) {
      _cohortList.get(i).displayCohortDetails();
    }
  }
}
