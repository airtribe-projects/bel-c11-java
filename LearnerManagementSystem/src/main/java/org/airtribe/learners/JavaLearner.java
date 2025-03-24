package org.airtribe.learners;

public class JavaLearner extends Learner{
  private boolean doesKnowObjectOrientedPorgramming;

  public JavaLearner(String learnerId, String learnerName, String learnerEmail, String learnerPhone, String learnerAddress, boolean doesKnowObjectOrientedPorgramming) {
    super(learnerId, learnerName, learnerEmail, learnerPhone, learnerAddress);
    this.doesKnowObjectOrientedPorgramming = doesKnowObjectOrientedPorgramming;
  }

  @Override
  public void displayLearnerDetails() {
    System.out.println("Learner Id: " + learnerId);
    System.out.println("Learner Name: " + learnerName);
    System.out.println("Learner Email: " + learnerEmail);
    System.out.println("Learner Phone: " + learnerPhone);
    System.out.println("Learner Address: " + learnerAddress);
    System.out.println("Does know Object Oriented Programming: " + doesKnowObjectOrientedPorgramming);
  }
}
