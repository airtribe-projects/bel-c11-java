package org.airtribe.instructors;

public class Instructor {
  public String instructorId;
  public String instructorName;
  public String instructorEmail;
  public String instructorPhone;
  public String instructorAddress;

  public Instructor(String instructorId, String instructorName, String instructorEmail, String instructorPhone, String instructorAddress) {
    this.instructorId = instructorId;
    this.instructorName = instructorName;
    this.instructorEmail = instructorEmail;
    this.instructorPhone = instructorPhone;
    this.instructorAddress = instructorAddress;
  }

  public void displayInstructorDetails() {
    System.out.println("Instructor Id: " + instructorId);
    System.out.println("Instructor Name: " + instructorName);
    System.out.println("Instructor Email: " + instructorEmail);
    System.out.println("Instructor Phone: " + instructorPhone);
    System.out.println("Instructor Address: " + instructorAddress);
  }
}
