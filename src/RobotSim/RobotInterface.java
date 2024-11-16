package RobotSim;

import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

/**
 * Class providing the user interface for the robot simulation.
 * Allows the user to interact with the arena and control the robots.
 */
public class RobotInterface {
    private Scanner s;
    private RobotArena myArena;

    /**
     * Constructor to set up the interface.
     * Initialises the scanner and creates a default arena.
     */
    public RobotInterface() {
        s = new Scanner(System.in);
        myArena = new RobotArena(20, 6);

        char ch;
        do {
            printMenu();
            ch = s.next().charAt(0);
            s.nextLine();

            handleUserInput(ch);
        } while (ch != 'X');

        s.close();
    }

    /**
     * Method to handle user input based on menu options.
     * @param ch - The character representing the user's menu choice.
     */
    private void handleUserInput(char ch) {
        switch (Character.toLowerCase(ch)) {
            case 'a': addRobot(); break;
            case 'i': GetInformation(); break;
            case 'd': doDisplay(); break;
            case 'r': runAnimation(); break;
            case 'n': newArena(); break;
            case 's': saveToFile(); break;
            case 'l': loadFromFile(); break;
            case 'm': moveRobots(); break;
            case 'x': exitSimulation(); break;
            default: System.out.println("Invalid option. Please try again."); break;
        }
    }

    /**
     * Method to print the menu options horizontally.
     */
    private void printMenu() {
        System.out.println("Menu: Add robot (a), Get information (i), Display (d), Run animation (r), New arena (n), Save (s), Load (l), Move (m), Exit (x)");
        System.out.print("Enter your choice: ");
    }

    /**
     * Method to add a new robot to the arena.
     * Adds a robot at a random available position in the arena.
     */
    private void addRobot() {
        myArena.addRobot();
        System.out.println("Robot added.");
    }

    /**
     * Method to display information about the arena.
     * Prints the current details of the arena and its robots.
     */
    private void GetInformation() {
        System.out.print(myArena.toString());
    }

    /**
     * Method to display the arena on the console.
     * Shows the current state of the arena including all robots.
     */
    public void doDisplay() {
        ConsoleCanvas c = new ConsoleCanvas(myArena.getWidth(), myArena.getHeight(), "32025021");
        myArena.showRobots(c);
        System.out.print(c.toString());
    }
    
    /**
     * Method to move all robots in the arena.
     * Moves each robot according to its direction and displays the updated arena.
     */
    private void moveRobots() {
        myArena.moveAllRobots();
        doDisplay();
    }

    /**
     * Method to run animation of robots moving.
     * Continuously moves robots until user stops the animation by pressing 'x'.
     */
    public void runAnimation() {
        Thread animationThread = new Thread(() -> {
            System.out.println("Press 'x' and Enter to stop the animation.");
            while (!Thread.currentThread().isInterrupted()) {
                myArena.moveAllRobots();
                doDisplay();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        animationThread.start();

        while (true) {
            try {
                char stop = (char) System.in.read();
                if (stop == 'x' || stop == 'X') {
                    animationThread.interrupt();
                    System.out.println("Animation stopped.");
                    break;
                }
            } catch (IOException e) {
                System.out.println("An error occurred while reading input to stop the animation.");
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Method to create a new arena with user-specified size.
     * Prompts the user for the width and height of the new arena.
     */
    public void newArena() {
        System.out.print("Enter width of the new arena: ");
        int width = s.nextInt();
        System.out.print("Enter height of the new arena: ");
        int height = s.nextInt();
        s.nextLine();
        myArena = new RobotArena(width, height);
        System.out.println("New arena created: " + width + " by " + height);
    }

    /**
     * Method to save the arena and robots to a file.
     * Saves the current state of the arena to a specified file.
     */
    public void saveToFile() {
        System.out.print("Enter filename to save the arena: ");
        String filename = s.nextLine();

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(myArena);
            System.out.println("Arena saved successfully to " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Method to load the arena and robots from a file.
     * Loads the state of the arena from a specified file.
     */
    public void loadFromFile() {
        System.out.print("Enter filename to load the arena: ");
        String filename = s.nextLine();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            myArena = (RobotArena) in.readObject();
            System.out.println("Arena loaded successfully from " + filename);
            doDisplay();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred while loading the file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Method to exit the simulation.
     * Closes the console and terminates the program.
     */
    private void exitSimulation() {
        System.out.println("Exiting simulation...");
        System.exit(0);
    }

    /**
     * Main method to start the interface.
     * @param args - Command line arguments.
     */
    public static void main(String[] args) {
        new RobotInterface();
    }
}
