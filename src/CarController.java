import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
 * This class represents the Controller part in the MVC pattern.
 * Its responsibilities are to listen to the View and responds in a appropriate manner by
 * modifying the model state and the updating the view.
 */

public class CarController {
    // The delay (ms) corresponds to 20 updates/second (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // TODO A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();

    public void startTimer() {
        this.timer.start();
    }

    /* Each step, the TimerListener moves all cars in the list and tells the
     * view to update its images. TODO Change this method to your needs.
     */
    private void checkBoundary(Car car) {
        double x = car.getPosition().getX();
        double y = car.getPosition().getY();
        if (x < 0 || x > 760 || y < 0 || y > 500) {
            car.stopEngine();
            car.setPosition(
                    Math.max(0, Math.min(x, 700)),
                    Math.max(0, Math.min(y, 500))
            );
            car.turnRight();
            car.turnRight();
            car.startEngine();
        }
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            for (int i = 0; i < cars.size(); i++) {
                Car car = cars.get(i);
                car.move();
                checkBoundary(car);
                int x = (int) Math.round(car.getPosition().getX());
                int y = (int) Math.round(car.getPosition().getY());
                frame.drawPanel.moveit(i, x, y);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.revalidate();
                frame.drawPanel.repaint();
            }
        }
    }

    void gas(int amount) {
        /* TODO adapt to your needs */
        double gas = ((double) amount) / 100;
        for (Car car : cars) {
            car.gas(gas);
        }
    }
    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Car car : cars) {
            car.brake(brake);
        }
    }
    void turnLeft() {
        for (Car car : cars) {
            car.turnLeft();
        }
    }
    void turnRight() {
        for (Car car : cars) {
            car.turnRight();
        }
    }
    void startEngine(){
        for (Car car : cars) {
            car.startEngine();
        }
    }
    void stopEngine(){
        for (Car car : cars) {
            car.stopEngine();
        }
    }
    void setTurboOn(){
        for (Car car: cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOn();
            }
        }
    }
    void setTurboOff(){
        for (Car car:cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOff();
            }
        }
    }
}
