/**
 * Author: Dhruv Patel
 *
 *
 * Description: This Java program defines the CustomerDetails class, which is responsible for displaying customer information
 * from a database in a tabular format. It allows users to print the customer details by interacting with a GUI.
 * The program connects to a database using a connection object and executes SQL queries to retrieve customer data.
 */

import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class CustomerDetails extends JFrame implements ActionListener {

    // GUI components
    Choice meterNumber, cmonth;
    JTable table;
    JButton search, print;

    /**
     * Constructor for CustomerDetails.
     * Sets up the JFrame with size, location, and table for displaying customer information.
     */
    CustomerDetails() {
        super("Customer Details");

        // Set window size and position
        setSize(1200, 650);
        setLocation(200, 150);

        // Initialize table for displaying customer details
        table = new JTable();

        // Database connection and retrieval of customer data
        try {
            connection c = new connection();
            ResultSet rs = c.s.executeQuery("select * from customer");
            // Use DbUtils to populate the table with data from ResultSet
            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception ae) {
            ae.printStackTrace();
        }

        // Add JScrollPane to hold the table and enable scrolling
        JScrollPane sp = new JScrollPane(table);
        add(sp);

        // Initialize and add Print button to allow printing of table data
        print = new JButton("Print");
        print.addActionListener(this); // Attach action listener for button click
        add(print, "South"); // Place button at the bottom of the window

        setVisible(true); // Make JFrame visible
    }

    /**
     * Action event handler for button clicks.
     *
     * @param ae ActionEvent generated by button click
     */
    public void actionPerformed(ActionEvent ae) {
        try {
            // Trigger print functionality for the table
            table.print();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Main method to launch the CustomerDetails JFrame.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        new CustomerDetails();
    }
}
