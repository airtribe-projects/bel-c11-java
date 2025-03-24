package org.airtribe.learners;

public abstract class Learner {
  public String learnerId;
  public String learnerName;
  public String learnerEmail;
  public String learnerPhone;
  public String learnerAddress;

  public Learner(String learnerId, String learnerName, String learnerEmail, String learnerPhone, String learnerAddress) {
    this.learnerId = learnerId;
    this.learnerName = learnerName;
    this.learnerEmail = learnerEmail;
    this.learnerPhone = learnerPhone;
    this.learnerAddress = learnerAddress;
  }

  public abstract void displayLearnerDetails();
}
