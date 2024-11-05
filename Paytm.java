/*
 * @author Dhruv Patel
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The class Paytm extends JFrame and implements ActionListener
 */
public class Paytm extends JFrame implements ActionListener {

    String meter;
    JButton back;

    /**
     * Constructor for Paytm class
     *
     * @param meter the meter number
     */
    public Paytm(String meter) {
        this.meter = meter;

        // Set up JFrame properties
        setTitle("Paytm Payment");
        setSize(800, 600);
        setLocation(400, 150);
        setLayout(null); // Set layout to null for absolute positioning
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close application on exit

        // Create JEditorPane to load Paytm webpage
        JEditorPane j = new JEditorPane();
        j.setEditable(false);

        try {
            j.setPage("https://paytm.com/online-payments");
        } catch (Exception e) {
            j.setContentType("text/html");
            j.setText("<html>Could not load Paytm page. Please check your internet connection.</html>");
        }

        // Add JScrollPane for JEditorPane
        JScrollPane jp = new JScrollPane(j);
        jp.setBounds(0, 0, 780, 540); // Set bounds for the JScrollPane
        add(jp);

        // Create and set up the Back button
        back = new JButton("Back");
        back.setBounds(640, 550, 80, 30);
        back.addActionListener(this);
        add(back); // Add button to the frame

        // Set the frame to be visible
        setVisible(true);
    }

    /**
     * Action performed for button events
     *
     * @param ae the ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
            new PayBill(meter); // Navigate back to PayBill screen
        }
    }

    /**
     * Main method to run the application
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Paytm("");
    }
}
