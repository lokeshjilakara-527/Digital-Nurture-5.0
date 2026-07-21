package springcore;

public class Car {

    // engine object will be given by spring, we are not creating it with new keyword
    private Engine engine;
    private String carColor;

    // constructor injection - spring will pass the engine object here
    public Car(Engine engine) {
        this.engine = engine;
    }

    // setter injection - spring will call this method to set the color
    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public void driveCar() {
        System.out.println("Car color is: " + carColor);
        System.out.println(engine.startEngine());
        System.out.println("Car is moving now...");
    }

}
