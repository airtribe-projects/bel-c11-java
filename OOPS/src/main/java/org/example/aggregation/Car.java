package org.example.aggregation;

public class Car {

  private String model;


  // has an engine
  private Engine _engine;

  Car(String model, Engine engine) {
    this.model = model;
    this._engine = engine;
  }

  public void showCar() {
    System.out.println("Car model: " + model);
    _engine.showEngine();
  }
}
