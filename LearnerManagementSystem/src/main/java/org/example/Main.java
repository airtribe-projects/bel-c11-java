package org.example;

import java.util.Date;
import org.airtribe.cohort.Cohort;
import org.airtribe.course.Course;
import org.airtribe.course.OnlineCourse;
import org.airtribe.instructors.Instructor;
import org.airtribe.learners.JavaLearner;
import org.airtribe.learners.Learner;
import org.airtribe.learners.NodeLearner;


public class Main {
  public static void main(String[] args) {
    Course javaOnlineCourse = new OnlineCourse("3", "Java Online Course", "Java Online Course Description", "Java", "https://zoom.us/123");
    Course nodeOnlineCourse = new OnlineCourse("4", "Node Online Course", "Node Online Course Description", "Node", "https://zoom.us/456");


    Cohort cohort1 = new Cohort("1", "Cohort 1", "Cohort 1 Description", new Date(), new Date());
    Cohort cohort2 = new Cohort("2", "Cohort 2", "Cohort 2 Description", new Date(), new Date());

    Learner javaLearner1 = new JavaLearner("1", "Java Learne 1", "test", "test", "test", true);
    Learner javaLearner2 = new JavaLearner("2", "Java Learne 2", "test", "test", "test", true);
    Learner nodeLearner1 = new NodeLearner("3", "Node Learne 1", "test", "test", "test", true);
    Learner nodeLearner2 = new NodeLearner("4", "Node Learne 2", "test", "test", "test", true);

    Instructor instructor1 = new Instructor("1", "Instructor 1", "test", "test", "test");

    cohort1.addLearnerToCohort(javaLearner1);
    cohort1.addLearnerToCohort(nodeLearner1);
    cohort1.addInstructorToCohort(instructor1);

    cohort2.addLearnerToCohort(javaLearner2);
    cohort2.addLearnerToCohort(nodeLearner2);
    cohort2.addInstructorToCohort(instructor1);


    javaOnlineCourse.addCohortToCourse(cohort1);
    javaOnlineCourse.addCohortToCourse(cohort2);

    nodeOnlineCourse.addCohortToCourse(cohort2);
    nodeOnlineCourse.addCohortToCourse(cohort1);

    javaOnlineCourse.displayCourseDetails();
    nodeOnlineCourse.displayCourseDetails();

  }
}