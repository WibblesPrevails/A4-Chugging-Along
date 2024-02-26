import java.lang.Math;
import java.util.ArrayList;

public class Train {
    String fuelTypeInput = "Steam";         //the default fuel type for the engine
    int fuelCapacityInput = 5000;           //the default capacity of fuel in the engine
    int carCap = 5;                         //The default capacity of new cars added to the train
    public ArrayList<Car> carsList;
    Engine trainEngine;
    
    public Train(){
    }

    public Train(String fuelType, int capacity, int carsNum, int carCap) {          //This function puts together trains for the constructor
        this.trainEngine = Engine.newEngine(fuelType, capacity);
        this.carsList = new ArrayList<Car>(1);
        for (int i = 0; i < carsNum; i++) {
            carsList.add(Car.newCar(i + 1, carCap));
        }
        System.out.println("New train created. Train is powered by " + fuelType + ", with a capacity of " + Integer.toString((int)trainEngine.capacity) + " units of fuel. The train has " + Integer.toString(carsList.size()) + " cars, each capable of carrying " + Integer.toString(carsList.get(0).getCapacity()) + " passengers.");
    }

    public void modifyCarNum(int change) {                                          //This function adds/removes cars based on whether you give it a positive or negative number
        if (change < 0) {
            for (int i = 0; i < Math.abs(change); i++) {
                Car chosenCar = carsList.get(carsList.size() - 1);
                chosenCar.removePassenger(chosenCar.passengerList.size());
                carsList.remove(chosenCar);
            }
            System.out.println(Integer.toString(change) + " cars removed.");
        }
        else {
            for (int i = 0; i < change; i++) {
                carsList.add(Car.newCar(carsList.size() + 1, carCap));
            }
        }
    }

    public void modifyPassengerNum(int specificCar, int change) {                    //This function allows you to modify the number of passengers on a given car, or on all cars, by an amount (negative to remove passengers, positive to add them). To do all cars, the first argument must be 0. To do a specific car, that argument should be the number car you want to change.
        if (specificCar == 0) {
            if (change < 0) {
                for (int i = 0; i < carsList.size(); i++) {
                    carsList.get(i).removePassenger(Math.abs(change));
                }
            }
            else {
                for (int i = 0; i < carsList.size(); i++) {
                    carsList.get(i).addPassenger(Math.abs(change));
                }
            }
        }
        else {
            if (change < 0) {
                carsList.get(specificCar).removePassenger(Math.abs(change));
            }
            else {
                carsList.get(specificCar).addPassenger(Math.abs(change));
            }
        }
    }

    public void printEngine() {                                                 //This is the getengine accessor described in the readme
        System.out.println("This train has an engine that uses " + trainEngine.fuelType + " fuel. It can carry " + Integer.toString((int)trainEngine.capacity) + " units of fuel, of which " + Integer.toString((int)trainEngine.fuelLevel) + " are remaining.");
    }

    public void printCars(int specificCar) {                                    //This is the GetCar accessor described in the readme. It works similarly as the modifyPassengerNum command above, so directions there apply here too.
        if (specificCar == 0) {
            for (int i = 0; i < carsList.size(); i++) {
                System.out.println("For car " + Integer.toString(i + 1) + ":" + carsList.get(i).printManifest());
            }
        }
        else {
            System.out.println("For car " + Integer.toString(specificCar) + ":" + carsList.get(specificCar).printManifest());
        }
    }

    public void getMaxCapacity() {
        System.out.println("Across the whole train, there is capacity for " + Integer.toString(carsList.size() * carsList.get(0).capacity) + " people.");
    }

    public void seatsRemaining() {
        int seats = 0;
        for (int i = 0; i < carsList.size(); i++) {
            seats = seats + carsList.get(i).passengerList.size();
        }
        System.out.println("There are currently " + Integer.toString(carsList.size() * carsList.get(0).capacity - seats) + " seats left on this train");
    }


    public void refuel () {
        System.out.println(trainEngine.refuel());
    }

    public void go() {
        System.out.println("The train has traveled to another station. There is " + Integer.toString(trainEngine.go()) + " fuel remaining.");
    }

    public static Train newTrain(String fuelType, int capacity, int carsNum, int carCap) {
        return new Train(fuelType, capacity, carsNum, carCap);
    }

    public static void main(String[] args) {
        Train train = newTrain("Steam", 5000, 4, 5);
        train.printEngine();
        train.getMaxCapacity();
        train.modifyPassengerNum(0, 4);
        train.modifyPassengerNum(1, -4);
        train.printCars(0);
        train.seatsRemaining();
        train.go();
        train.refuel();
        train.modifyPassengerNum(0, -4);
        train.printCars(0);
    }
}


// This is the running file. All interactions will occur here and will call the other files for information.
// Calls the "Engine", "Car", and "Passenger" files



// Imports random                                                                                                       //This is all my pseudocode. As I got to know the assignment better, some of this has become out-of-date.

//  A function to print available functions as strings in a list
//  A function to create a new train
//      Requests the name of the train
//      Calls "Engine" and requests a name for the engine as well as its fuel type
//      Calls "Car" and requests a number of cars to be created
//  A function to request information on the train (name, name of engine, fuel type, number of cars, number of passengers, fuel level)
//  A function to ready to leave station
//      Creates a random distance for the next station
//      Checks if the current fuel level, based on a function of the number of cars and the type of fuel used, will be sufficient to reach the destination - calls "engine"
//          Prints fuel level, fuel required, and whether or not the train can safely leave the station
//  A function to move to the next station, called "go"
//      Sets the "disembarked" variable to false
//      Calls the "ready to leave station" function
//          If false, prints that the train ran out of fuel (fuel level/required fuel)% to the next station
//          If true, prints that the train has arrived at the next station
//              Prints the new fuel level
//      Waits for the user to request "disembark" and/or "refuel"
//          "Disembark" is required unless the train has no passengers
//  A function to refuel the train
//      Brings the fuel level to full and prints as such
//  A function to disembark
//      Checks if train has passengers, fails if it does not - calls "cars"
//      Checks if train has already disembarked, fails it it already has
//      Removes a random number from the total number of passengers on the train, with a fraction greater than 90% simply removing everybody, and less than 10% removing nobody
//          Prints how many passengers left the train, checking if nobody or everybody left the train and printing accordingly.
//          Prints the new number of passengers on the train
//  A function to board
//      Checks if train has cars, fails if it does not - calls "cars"
//      Checks if the train is full, fails if it is - calls "cars"
//      Adds a random number of passengers to the train - calls "cars" and "passenger"
//          Subtracts the new number of passengers from the maximum - calls "cars"
//              If positive, subtracts that number from the total passengers and prints "___ passengers could not board the train because it is full."
//  A function to change number of cars
//      Requests an integer from the user (can be negative)
//      If the number is positive:
//          Creates that number of new car class individuals - calls "cars"
//          Prints the new number of cars and the new passenger capacity - calls "cars"
//      If the number is negative:
//          Checks if the number of cars is greater than the absolute value of the provided integer, fails if false - calls "cars"
//          Checks how many people are on the number of cars at the end of the list - calls "cars"
//              Prints how many people were removed from the train, prints nothing if there were none
//              Removes the people from the number of cars at the end of the list - calls "cars" and "passenger"
//          Removes the number of cars from the end of the list of cars - calls "cars"