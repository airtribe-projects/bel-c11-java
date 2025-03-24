package org.airtribe.course;

public class OnlineCourse extends Course {

  private String zoomUrl;
  public OnlineCourse(String courseId, String courseName, String courseDescription, String courseLanguage, String zoomUrl) {
    super(courseId, courseName, courseDescription, courseLanguage);
    this.zoomUrl = zoomUrl;
  }

  @Override
  public void displayCourseDetails() {
    System.out.println("Course Id: " + courseId);
    System.out.println("Course Name: " + courseName);
    System.out.println("Course Description: " + courseDescription);
    System.out.println("Zoom Url: " + zoomUrl);
    for (int i=0;i<_cohortList.size();i++) {
      _cohortList.get(i).displayCohortDetails();
    }
  }
}
