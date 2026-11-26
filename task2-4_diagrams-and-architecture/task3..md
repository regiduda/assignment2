Analyse your classes with respect to *Separation of Concern* (SoC) and the *Single Responsibility Principle* (SRP).

* Which responsibilities does your classes have?  
* What reasons to change do your classes have?  
* Which classes should we split to make them follow SoC and SRP better? Are there any classes that should be fused?


## Classes in the assignment:

*Public abstract class Car*

* **Which responsibilities does your classes have?**  
  This class contains all the information regarding the object that is used through-out the whole project. It is a parent class and includes attributes to personalize each car depending on its attributes. It serves as the main connection class to connect different methods with each other, without duplicating the calls.  
    
- **Attributes:**  
  - private final int nrDoors ;  
  - private final double enginePower;  
  - private Color color;  
  - private final String modelName;  
  - private double currentSpeed;  
  - protected double x;  
  - protected double y;  
  - protected String direction= “North”;  
  -   
- **Methods:**  
  - **Getters:**  
  -     public int getNrDoors()  
  -     public double getEnginePower()  
  -     public Color getColor()  
  -     public String getModelName()   
  -     public double getCurrentSpeed()  
  -     public double getX()  
  -     public double getY()  
  -     public Point2D getPosition()  
  - **Setters:**  
  -     public void setColor(Color clr)  
  -     public void setPosition(double x, double y)   
  -     public void setCurrentSpeed(double speed)  
  - **Methods for moving:**  
  -     public void startEngine()  
  -     public void stopEngine()  
  -     protected abstract double speedFactor();  
  -     public void incrementSpeed(double amount)  
  -     public void decrementSpeed(double amount)  
  -     public void gas(double amount)  
  -     public void brake(double amount)  
  -     public void move()  
  -     public void turnLeft()  
  -     public void turnRight()

    

  **What reasons to change do your classes have?**

- Any new attributes of the car, or moving paths (car could move back, or diagonally).   
- String direction could use enum (North, East, South, West) to avoid repetition (adhere to the DRY)  
- x, y and direction  could be private and we could use getters to more safely access then (open-close principle)  
- The class have a long list  of attributes, methods for the engine and methods for moving/direction \- but it might make more sense to keep this parent class intact  
  


  

  *Public class Saab95*  
  **Which responsibilities does your classes have?**  
  It mostly inherits its attributes from its superclass (Car), except turboOn; and it mostly inherits its methods from Car, but overwrites speedFactor and defines setters for turboOn and turboOff.  
  * Attributes:  
    * private boolean turboOn;  
    * Inherits others attributes from Car (nDoors, enginePower, modelName)  
  * Methods:  
    * public void setTurboOn()  
    * public void setTurboOff()  
    * protected double speedFactor() \= overwrites speedFactor from Car  
      

	**What reasons to change do your classes have?**

	Not really, the subclass adheres to the Principle of Least Astonishment, and contains only what it needs. Main changes should happen in class Car, the only changes that could be attached are details of the car.  
Maybe Turbo could get a separate interface?

*Public class Volvo240*

**Which responsibilities does your classes have?**

It is another main object in the project that mainly inherits it’s attributes from the parent superclass (Car), but since it has a unique speedFactor it has this method in its body.

* Attributes:  
  * private final static double trimFactor;  
    * Inherits others attributes from Car(nrDoors, color, enginePower, modelName)  
  * Methods:  
    * public Volvo240()  
    * protected double speedFactor() \=  overwrites speedFactor from Car

	**What reasons to change do your classes have?**  
	Not really, the subclass adheres to the Principle of Least Astonishment. Main changes should happen in class Car, the only changes that could be attached are details of the car.

*Public class CarController*

**Which responsibilities does your classes have?**

The CarController is responsible for handling user interactions and coordinating between the View and Model, responding to user input, such as button clicks, and invokes the corresponding actions by modifying the model state.

* Attributes:  
  *    private final int delay \= 50;  
    *    private Timer timer \= new Timer(delay, new TimerListener());

		Methods:

* public void startTimer()  
* private class TimerListener implements ActionListener{}(separate class)  
* private void checkBoundary(Car car)  
* void gas(int amount)  
* void brake(int amount)  
* void turnLeft()  
* void turnRight()  
* void startEngine()  
* void stopEngine()  
* void setTurboOn()  
* void setTurboOff()

	  
**What reasons to change do your classes have?**

- Maybe only run revalidate() and repaint() once after the loop, not once per car.  
- Replace the 8 repeating loop with for-each loop for each car  
- Instead rame.drawPanel.moveit() add CarView simple methods,  like: updateCar(i, x, y) and refresh() to limit coupling and keeping in mind they should only talk to their neighbours (Demeter’s law)  
- There are interdependencies between this class and CarView. CarView frame (here) and CarController carC (in CarView) should be private and we should modify the main method, and maybe have a separate interface for this, like beside Movable.

  *Public interface Movable \- interface*

  **Which responsibilities does your classes have?**

* public void move();  
* public void turnLeft();  
* public void turnRight();  
  Well contained, nice interface. Right now CarController depends on Car and it should be depending on the interface, Movable. gas(), break() could be added to the interface.  
    
    
  *Private class TimerListner*  
  **Which responsibilities does your classes have?**  
  It implements the ActionListener interface, and uses a method called actionPerformed.  
  * Methods:  
    * public void actionPerformed(ActionEvent e)  
      

	**What reasons to change do your classes have?**

Moving out into the separate Java file, instead of staying in the CarController class.	

*Public class CarView*

**Which responsibilities does your classes have?**

This class represents the full view of the MVC pattern of your car simulator. It initializes with being centred on the screen and attaching its controller in its state. It communicates with the Controller by calling its methods when an action fires in its components.

* Attributes:  
  * public static final int X;  
    * public static final int Y

		Methods:

* public CarView(String framename, CarController cc)  
  * private void initComponents(String title)

	  
	**What reasons to change do your classes have?**

	Clean up to make code more DRY, but without removing something important.

- initComponents()is too big, split it into smaller methods  
- Maybe create an update method instead of the public drawPanel field

  *Public class DrawPanel*

  **Which responsibilities does your classes have?**

  This class represents the animated part of the view with the car images.

  * Methods:  
    * void moveit(int index, int x, int y)  
    * public DrawPanel(int x, int y)  
    * protected void paintComponent(Graphics g)

	**What reasons to change do your classes have?**

- Change it so that the two cars are not hard coded  
- Loading image could be separated from painting  
- For safer access we could make the Drawpanel private and give Carview a method that could be called

	

*CarApplication (main)*

**Which responsibilities does your classes have?**

Entry point for the program to run.

* Methods:   
  * void main()

	**What reasons to change do your classes have?**

	No changes are required.

*Public class CarTest*

**Which responsibilities does your classes have?**

Testing the existing methods from Car, Saab95 and Volvo240.

* Methods:  
  * reportResult  
    * testTurbo  
      * testStartEngine  
      * testVolvoGas  
      * testTurnRightThenMove  
      * testGasTwoThenBrakeOne  
      * testStopEngine  
      * testInvalidGas  
      * testInvalidBrake  
      * testSpeedLimit  
      * testMoveThenTurnLeft  
      * testGasNotDecreaseSpeed  
      * Void main (call all test methods)

	**What reasons to change do your classes have?**

Not architecturewise, but we could add more tests and clear up some of them. Also covering the new methods with tests too.  
Note: Use getter methods when the attributes are updated to private, and add test to cover the new methods from this assignment.

Potential new classes: Direction, Turbo, CarControls, ImageLoader (they shown in task 4)