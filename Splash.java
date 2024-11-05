/*
 * @author Dhruv Patel
 */

import javax.swing.*;
import java.awt.*;

/**
 * The Splash class displays a splash screen with a background image
 * that gradually increases in size before transitioning to the login screen.
 */
public class Splash extends JFrame implements Runnable {
    Thread t;  // Thread for running the splash screen

    /**
     * Constructor for the Splash class.
     * Initializes the splash screen with an image, sets its properties,
     * and starts the animation for increasing its size.
     */
    Splash() {
        // Add image to the background of the splash screen.
        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("Icons/elect.jpg"));
        Image img2 = img1.getImage().getScaledInstance(730, 550, Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);
        JLabel jimage = new JLabel(img3);
        add(jimage);

        // Start the splash screen display
        setVisible(true);

        // Increase the size of the splash screen over time using a for loop.
        int x = 1;
        for (int i = 2; i < 600; i += 4, x += 1) {
            setSize(i + x, i);
            setLocation(700 - ((i + x) / 2), 400 - (i / 2));
            try {
                Thread.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Initialize and start the thread to handle the splash screen duration.
        t = new Thread(this);
        t.start();  // Start method internally calls the run method.

        setVisible(true);
    }

    /**
     * The run method defines the behavior of the thread.
     * It sleeps for 4000 milliseconds, then hides the splash screen
     * and opens the login screen.
     */
    public void run() {  // Runnable has run method so need to override run method from Runnable.
        try {
            // After 4000 milliseconds, close the splash window and open the login page.
            Thread.sleep(4000);
            setVisible(false);

            // Start the Login Method
            new Login();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The main method to launch the Splash screen application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Splash();
    }
}
