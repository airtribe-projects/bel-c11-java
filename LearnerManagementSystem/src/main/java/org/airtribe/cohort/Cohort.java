package org.airtribe.cohort;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.airtribe.course.Course;
import org.airtribe.instructors.Instructor;
import org.airtribe.learners.Learner;


public class Cohort {
  public String cohortId;
  public String cohortName;
  public String cohortDescription;

  public Date startDate;

  public Date endDate;

  private List<Learner> _learnerList;

  private List<Instructor> _instructorList;

  public Cohort(String cohortId, String cohortName, String cohortDescription, Date startDate, Date endDate) {
    this.cohortId = cohortId;
    this.cohortName = cohortName;
    this.cohortDescription = cohortDescription;
    this.startDate = startDate;
    this.endDate = endDate;
    this._learnerList = new ArrayList<>();
    this._instructorList = new ArrayList<>();
  }

  public void displayCohortDetails() {
    System.out.println("Cohort Id: " + cohortId);
    System.out.println("Cohort Name: " + cohortName);
    System.out.println("Cohort Description: " + cohortDescription);
    System.out.println("Start Date: " + startDate);
    System.out.println("End Date: " + endDate);
    for (int i=0;i<_learnerList.size();i++) {
      _learnerList.get(i).displayLearnerDetails();
    }

    for (int i=0;i<_instructorList.size();i++) {
      _instructorList.get(i).displayInstructorDetails();
    }
  }

  public Cohort addLearnerToCohort(Learner learner) {
    _learnerList.add(learner);
    return this;
  }

  public Cohort addInstructorToCohort(Instructor instructor) {
    _instructorList.add(instructor);
    return this;
  }

}
