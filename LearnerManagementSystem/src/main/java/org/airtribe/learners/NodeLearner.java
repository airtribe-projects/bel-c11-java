package org.airtribe.learners;

public class NodeLearner extends Learner {

  private boolean doesKnowV8Engine;

  public NodeLearner(String learnerId, String learnerName, String learnerEmail, String learnerPhone, String learnerAddress, boolean doesKnowV8Engine) {
    super(learnerId, learnerName, learnerEmail, learnerPhone, learnerAddress);
    this.doesKnowV8Engine = doesKnowV8Engine;
  }

  @Override
  public void displayLearnerDetails() {
    System.out.println("Learner Id: " + learnerId);
    System.out.println("Learner Name: " + learnerName);
    System.out.println("Learner Email: " + learnerEmail);
    System.out.println("Learner Phone: " + learnerPhone);
    System.out.println("Learner Address: " + learnerAddress);
    System.out.println("Does know V8 Engine: " + doesKnowV8Engine);
  }
}
