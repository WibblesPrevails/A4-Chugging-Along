# CSC120-A5: Chugging Along

## Outline
In this assignment, we'll explore **Encapsulation** by building a virtual Train!

![A cartoon train with an engine and three passenger cars](https://jcrouser.github.io/CSC120/img/train.png)

As before, all the files necessary for this assignment are contained within this repository. When you submit, please remember to include:

 - all files necessary to compile your program
 - `reflection.md` containing your reflections and notes
 - `rubric.md` where you document which elements of the assignment you have attempted and/or completed.
 
##  Program files
For this assignment, you'll be writing four interrelated classes:

 - The `Passenger` class (`Passenger.java`) will store information about an individual passenger
 - The `Engine` class (`Engine.java`) will represent the locomotive engine, storing information about its fuel type, level, etc.
 - The `Car` class (`Car.java`) will be used as a container for `Passenger` objects
 - and the `Train` (`Train.java`) class will tie them all together
 
 You'll also notice a 5th file in the repository (`FuelType.java`), which contains something that looks like an extremely simple class:
 ```
public enum FuelType {
    STEAM, INTERNAL_COMBUSTION, ELECTRIC, OTHER;
}
```
In Java, we can use the keyword `enum` to establish simple type that must take as its value one of a set of predefined constant values. We'll use this in the `Engine` class instead of a `String` to keep track of what kind of fuel the `Engine` uses. You don't need to change this file, but you can use the values it contains like this:

```
FuelType f = FuelType.ELECTRIC;
```

## Relationships between the Classes
 
Let's pause a moment to think about the different kinds of relationships we'll want to establish:

 - The `Train` class will have a relationship with the `Engine` class, because the `Engine` is responsible for powering the `Train`.
 - The `Train` class also has a relationship with the `Car` class: the `Train` has a collection of `Cars` associated with it at any given time, and you can add / remove `Cars` as necessary (without destroying either the `Train` or the `Cars` themselves).
 - The `Passenger` class has relationships with both the `Car` and `Train` classes (`Passenger`s board `Car`s as their means of using the `Train` to move around more efficiently).
 
 ## Step 1: The `Engine` class
 We recommend you start by implementing the `Engine` class. Your `Engine` class will need:
 
  - a `FuelType` attribute to indicate what type of fuel it uses, and `double`s to store the current and maximum fuel levels (along with appropriate accessors for each).
  - a constructor, which takes in initial values for the attributes named above and sets them appropriately
  - a `refuel()` method which will reset the `Engine`'s current fuel level to the maximum, and which doesn't need to `return` anything
  - a `go()` which will decrease the current fuel level, print some useful information (e.g. remaining fuel level), and return `True` if the fuel level is above 0 and `False` otherwise.

Remember, OOP is all about deciding which classes are responsible for which parts of the end solution. As you program, consider which of these attributes/methods should be `public`, and which should be `private`. These questions may be helpful to ask yourself:
 - Does **another class** need to be able to **read** this value? (If so, it could either be marked `public` or have an `accessor`)
 - Does **another class** need to be able to **modify** this value? (If so, it could either be marked `public` or have a `manipulator`)

 
You can use the `main` method defined below as a starting point for testing:
 ```
    public static void main(String[] args) {
        Engine myEngine = new Engine(FuelType.ELECTRIC, 100.0);
        while (myEngine.go()) {
            System.out.println("Choo choo!");
        }
        System.out.println("Out of fuel.");
    }
```

## Step 2: the `Car` class
Next, we'll set to work on the `Car` class. The `Car` class will need:

 - an `ArrayList` where it will store the `Passenger`s currently onboard, and an `int` for the `Car`'s maximum capacity (since `ArrayList`s will expand as we add objects, we'll need to manually limit their size)
 - a constructor, which takes in an initial value for the `Car`'s maximum capacity and initializes an appropriately-sized `ArrayList`
 - accessor-like methods `public int getCapacity()` and `public int seatsRemaining()` that return the maximum capacity and remaining seats, respectively
 - `addPassenger(Passenger p)` and `removePassenger(Passenger p)` methods to add or remove a `Passenger` from the `Car` and return `True` if the operation was successful, and `False` otherwise. (_Hint: don't forget to check that there are seats available if someone wants to board, and to confirm that the `Passenger` is actually onboard before trying to remove them! If you encounter a problem, you should `return False`._)
 - and a final method `printManifest()` that prints out a list of all `Passenger`s aboard the car (or "This car is EMPTY." if there is no one on board).

As before, consider which of these should be `public` and which should be `private` (potentially with `accessor`s and/or `manipulator`s).

## Step 3: completing the `Passenger` class
Now that you've got a functional `Car` class, the `Passenger` class can be expanded to use the `Car`'s methods to implement some of its own:

 - `boardCar(Car c)` can call `c.addPassenger(this)` to board a given `Car` (_Hint: this method should check the value that gets `return`ed by `c.addPassenger(...)` in case the selected car is full._)
 - `getOffCar(Car c)` can call `c.removePassenger(this)` to get off a given `Car` (_Hint: this method should check the value that gets `return`ed by `c.removePassenger(...)` in case the `Passenger` wasn't actually onboard._)

## Step 4: the `Train` class
Now we're in the home stretch! To assemble your `Train`, you'll need (at minimum):

 -  an `Engine`
 -  an `ArrayList` to keep track of the `Car`s currently attached
 -  a constructor `Train(FuelType fuelType, double fuelCapacity, int nCars, int passengerCapacity)` which will initialize the `Engine` and `Car`s and store them
 -  a few accessors: 
     -  `public Engine getEngine()`
     -  `public Car getCar(int i)` to return the `i`th car
     -  `public int getMaxCapacity()` which will return the maximum total capacity across all `Car`s
     -  `public int seatsRemaining()` which will return the number of remaining open seats across all `Car`s
- and finally, its own `printManifest()` that prints a roster of all `Passenger`s onboard (_Hint: ask your `Car`s to help!_)
