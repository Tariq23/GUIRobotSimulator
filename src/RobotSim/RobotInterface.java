package RobotSim;

import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import javax.swing.JFileChooser;
import java.io.IOException;

/**
 * Class providing the user interface for the robot simulation.
 * Allows the user to interact with the arena and control the robots.
 */
public class RobotInterface {
    private Scanner s; // Scanner for user input
    private RobotArena myArena; // Arena for robots

    /**
     * Constructor to set up the interface.
     * Initializes the scanner and creates a default arena.
     */
    public RobotInterface() {
        s = new Scanner(System.in); // Initialize scanner
        myArena = new RobotArena(20, 6); // Create an arena of size 20x6

        char ch;
        do {
            printMenu(); // Print the menu options
            ch = s.next().charAt(0); // Get user input
            s.nextLine(); // Consume newline

            switch (Character.toLowerCase(ch)) { // Convert input to lowercase for consistency
                case 'a':
                    myArena.addRobot(); // Add a new robot
                    System.out.println("Robot added.");
                    break;
                case 'i':
                    System.out.print(myArena.toString()); // Print arena and robot details
                    break;
                case 'd':
                    doDisplay(); // Display the arena
                    break;
                case 'n':
                    newArena(); // Create a new arena
                    break;
                case 's':
                    saveToFile(); // Save the arena to a file
                    break;
                case 'l':
                    loadFromFile(); // Load the arena from a file
                    break;
                case 'm':
                    myArena.moveAllRobots(); // Move all robots
                    doDisplay(); // Redraw the arena
                    break;
                case 'x':
                    System.out.println("Exiting simulation...");
                    System.exit(0); // Close the console window and exit the program
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (ch != 'X'); // Repeat until user exits

        s.close(); // Close the scanner
    }

    /**
     * Method to print the menu options horizontally.
     */
    private void printMenu() {
        System.out.println("Menu: Add robot (a), Get information (i), Display (d), New arena (n), Save (s), Load (l), Move (m), Exit (x)");
        System.out.print("Enter your choice: ");
    }

    /**
     * Method to create a new arena with user-specified size.
     */
    public void newArena() {
        System.out.print("Enter width of the new arena: ");
        int width = s.nextInt(); // Get width from user
        System.out.print("Enter height of the new arena: ");
        int height = s.nextInt(); // Get height from user
        s.nextLine(); // Consume newline
        myArena = new RobotArena(width, height); // Create a new arena
        System.out.println("New arena created: " + width + " by " + height);
    }

    /**
     * Method to save the arena and robots to a file.
     */
    public void saveToFile() {
        System.out.print("Enter filename to save the arena: ");
        String filename = s.nextLine(); // Get filename from user
        
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(myArena); // Save the entire arena object
            System.out.println("Arena saved successfully to " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Method to load the arena and robots from a file.
     */
    public void loadFromFile() {
        System.out.print("Enter filename to load the arena: ");
        String filename = s.nextLine(); // Get filename from user

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            myArena = (RobotArena) in.readObject(); // Load the entire arena object
            System.out.println("Arena loaded successfully from " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred while loading the file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Method to display the arena on the console.
     */
    public void doDisplay() {
        ConsoleCanvas c = new ConsoleCanvas(myArena.getWidth(), myArena.getHeight(), "32025021");
        myArena.showRobots(c); // Show all robots on the canvas
        System.out.print(c.toString()); // Print the canvas
    }

    /**
     * Main method to start the interface.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new RobotInterface(); // Create and run the interface
    }
}
