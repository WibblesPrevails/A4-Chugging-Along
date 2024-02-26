import java.util.Scanner;
import java.util.Random;

public class Engine {

    public FuelType fuelType;
    public double capacity;
    public double fuelLevel;
    public int usedFuel;
    public static Random randy = new Random();

    public Engine(){       //I don't understand why I need this. I was getting the error "Error: no non-private zero argument constructor found in class Engine (next line) remove private from existing constructor or define as: (next line) public Engine()"  ...  I added this and it fixed the error
    }

    public String refuel() {
        fuelLevel = capacity;
        return "Train refueled.";
    }

    public int go() {
        usedFuel = randy.nextInt((int)capacity - 1);
        if (usedFuel < fuelLevel) {
            fuelLevel = fuelLevel - usedFuel;
        }
        else {
            usedFuel = 0;
        }
        return usedFuel;
    }

    public Engine(FuelType testFuel, int capacity) {
        this.fuelType = testFuel;
        this.capacity = capacity;
        this.fuelLevel = capacity;
    }

    public static Engine newEngine(String fuelType, int capacity) {                        //This is the constructor for new engines - there must be a try/catch in Train.java to handle if/when somebody puts in a value that doesn't work for the enum!    
        FuelType testFuel = FuelType.valueOf(fuelType.toUpperCase());
        return new Engine(testFuel, capacity);
    }





    
    public static Scanner testScanner = new Scanner(System.in);                         //This scanner was used in the enclosed test function below to test before being connected to Train.java
     public void main(String[] args) {                                                   //This section is used to test components of the file before being connected to Train.java
         Engine testEngine;
         testEngine = newEngine("Steam", 5000);
         System.out.println(testEngine.refuel());
         int travel = testEngine.go();
         if (travel != 0) {
             System.out.println("The train has arrived at the next station having used " + Integer.toString(travel) + " worth of fuel. There is " + Integer.toString((int)testEngine.fuelLevel) + " left of fuel.");
         }
         else {
             System.out.println("There isn't enough fuel to travel to the next station.");
         }
     }
}
//  Stores information about the engine and fuel load
//  Stores a list of all the car names on the train
//  Stores a maximum fuel capacity for the engine

//  A function to create a new engine
//      Intakes the name of the engine
//      Intakes the fuel type of the engine
//      Sets that engine's fuel to maximum
//      Intakes the number of cars
//          Creates a list of the car names - calls "car"
//  A function to refuel
//      Sets fuel level to maximum