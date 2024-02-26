import java.util.ArrayList;

public class Car {

    public int capacity;
    public int name;
    public ArrayList<Passenger> passengerList;
    private String passengerNames;

    public Car(){       //I don't understand why I need this. I was getting the error "Error: no non-private zero argument constructor found in class Car (next line) remove private from existing constructor or define as: (next line) public Car()"  ...  I added this and it fixed the error
    }

    public Car(int name, int capacity) {
        this.capacity = capacity;
        this.name = name;
        this.passengerList = new ArrayList<Passenger>(1);
    }

    public int getCarName() {                           //This is a query for the name of the car (they are numerical integers, with 1 being the closest to the engine)
        return this.name;
    }

    public int getCapacity() {                            //This is a query for the capacity of the car
        return this.capacity;
    }

    public static Car newCar(int name, int capacity) {  //This is the constructor for new cars
        return new Car(name, capacity);
    }

    public String addPassenger(int reps) {                    //Adds passengers to a car, checks if there is enough room
        if (passengerList.size() + reps <= capacity) {
            for (int i = 0; i < reps; i++) {
                passengerList.add(Passenger.newPas(name));
            }
            return "Passengers added.";
        }
        else {
            return "Passengers not added. There is not enough room in the car to fit all of them.";
        }
    }

    public String removePassenger(int reps) {                 //Removes passengers from the car, checks if there are enough to remove
        if (passengerList.size() >= reps) {
            for (int i = 0; i < reps; i++) {
                passengerList.remove(0);
            }
            return "Passengers removed.";
        }
        else {
            return "Passengers not removed. There are not that many passengers on the car.";
        }
    }

    public String seatsRemaining() {                           //Shows how many seats are still available on a given car
        return "There are " + Integer.toString(capacity - passengerList.size()) + " seats left in this car.";
    }
    
    public String printManifest() {                            //Shows how many people are on this car and all of their names, gramatically correct
        if (passengerList.size() == 0) {
            passengerNames = "There are no people on this car.";
        }
        else if (passengerList.size() == 1){
            passengerNames = passengerList.get(0).getPasName() + " is on this car.";
        }
        else {
            passengerNames = "There are " + Integer.toString(passengerList.size()) + " people on board this car. Their names are ";
            for (int i = 0; i < passengerList.size() - 1; i++) {
                passengerNames = passengerNames + passengerList.get(i).getPasName() + ", ";
            }
            passengerNames = passengerNames + "and " + passengerList.get(passengerList.size() - 1).getPasName() + ".";
        }
        return passengerNames;
    }

    public void main(String[] args) {                           //This section was used to test each command before linking to Train.java
        Car newCarr = newCar(3,5);
        System.out.println(newCarr + "\n" + Integer.toString(newCarr.getCarName()));
        System.out.println(newCarr.addPassenger(5));
        System.out.println(newCarr.removePassenger(0));
        System.out.println(newCarr.printManifest());
        System.out.println(newCarr.removePassenger(4));
        System.out.println(newCarr.seatsRemaining());
    }

}

//  Stores information on each of the cars and the names of the passengers within them

//  A function to add cars
//      Creates new class individuals of cars with their own passenger lists and names
//  A function to remove cars
//      Outputs a list of the names of people on board each removed car - calls "engine"
//  A function to pass up the names of the passengers onboard the car
//      Writes it out as a string that is grammatically correct
//  A function to add passengers based on an input number
//      Checks if there is enough room
//  A function to remove passengers based on an input number
//      Checks if there are enough passengers
//  A function to check the remaining seats on the car
//      Checks if there are any