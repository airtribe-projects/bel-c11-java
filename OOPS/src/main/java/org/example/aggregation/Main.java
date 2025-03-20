package org.example.aggregation;

public class Main {
  public static void main() {
    Engine engine = new Engine("V8");
    Car car = new Car("V8", engine);
    car.showCar();
  }
}
