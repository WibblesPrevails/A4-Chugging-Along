import java.util.Random;
//import java.util.ArrayList;

public class Passenger {
    
    public static String[] namesArray = {"Bill", "Jan", "Avery", "John", "Gabriel", "Jesus", "Arabel", "Onjolie", "Julian", "Ava", "Ella", "Emily", "Oscar", "Ethan", "Samantha", "Ariel"};
    public String name;
    public int carID;
    public static Random randy = new Random();
    //public static ArrayList<Passenger> testArrayList = new ArrayList<Passenger>();

    
    public Passenger(String name, int index, int car) {
        this.name = name + Integer.toString(index);
        this.carID = car;
    }

    public String getPasName() {
        return this.name;
    }

    public static Passenger newPas(int car) {
        String nameGen = namesArray[randy.nextInt(namesArray.length)];
        int indexGen = randy.nextInt(999);
        return new Passenger(nameGen, indexGen, car);
        //testArrayList.add(new Passenger(nameGen, indexGen, car));                                     //This section tested my Passenger objects when they were still on the TestArrayList before the list was outsourced to the Car.java file
        //System.out.println("My name is " + testArrayList.get(0).name);
        
        //Passenger create = new Passenger(nameGen, indexGen, car);                                     //This section tested my Passenger objects before they were integrated into an arraylist.
        //System.out.println("My name is " + create.name + "\n" + "I am aboard car " + create.carID);
    }

    public static void main(String[] args) {
        newPas(1);
    }
}

//  Stores information on individual passengers and controls their movements
//  Stores a list of common names

//  A function to create a new passenger
//      Generates a random name and three number index for the passenger from a list of names
//      Names a new instance of the passenger class the name with the index tacked on at the end
//      Assigns that passenger to an existing car
//      Sends the name + index to that car
//  A function to remove a passenger
//      Removes that passenger from its car
//      Deletes that passenger class