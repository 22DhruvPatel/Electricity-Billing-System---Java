/**
 * DepositDetails.java
 *
 * A class that represents the Deposit Details page in a billing system application.
 * This class allows users to search for deposit details by meter number and month,
 * displays results in a table, and provides an option to print the data.
 *
 * Author: Dhruv Patel
 */

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class DepositDetails extends JFrame implements ActionListener {

    // Dropdown for selecting meter number and month
    Choice meterNumber, cmonth;
    // Table to display deposit details
    JTable table;
    // Buttons for search and print actions
    JButton search, print;

    /**
     * Constructor to initialize the DepositDetails JFrame.
     * Sets up the layout, initializes UI components, and fetches meter numbers
     * from the database to populate the dropdown menu.
     */
    public DepositDetails() {
        super("Deposit Details");

        // Setting up the frame size, location, layout, and background color
        setSize(700, 700);
        setLocation(400, 100);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Label and choice for selecting a meter number
        JLabel lblMeterNumber = new JLabel("Search By Meter Number");
        lblMeterNumber.setBounds(20, 20, 150, 20);
        add(lblMeterNumber);

        meterNumber = new Choice();
        meterNumber.setBounds(180, 20, 150, 20);
        add(meterNumber);

        // Fetches meter numbers from the Customer table and adds them to the dropdown
        try {
            connection c = new connection();
            ResultSet rs = c.s.executeQuery("SELECT * FROM Customer");
            while (rs.next()) {
                meterNumber.add(rs.getString("meter_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Label and dropdown for selecting a month
        JLabel lblMonth = new JLabel("Search By Month");
        lblMonth.setBounds(400, 20, 100, 20);
        add(lblMonth);

        cmonth = new Choice();
        cmonth.setBounds(520, 20, 150, 20);
        // Adding month options to the dropdown
        cmonth.add("January");
        cmonth.add("February");
        cmonth.add("March");
        cmonth.add("April");
        cmonth.add("May");
        cmonth.add("June");
        cmonth.add("July");
        cmonth.add("August");
        cmonth.add("September");
        cmonth.add("October");
        cmonth.add("November");
        cmonth.add("December");
        add(cmonth);

        // Initializes the JTable for displaying data
        table = new JTable();

        // Load initial data from the 'bill' table into the JTable
        try {
            connection c = new connection();
            ResultSet rs = c.s.executeQuery("SELECT * FROM bill");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception ae) {
            ae.printStackTrace();
        }

        // ScrollPane to enable scrolling for the table
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0, 100, 700, 600);
        add(sp);

        // Search button for finding details based on user selection
        search = new JButton("Search");
        search.setBounds(20, 70, 80, 20);
        search.addActionListener(this);
        add(search);

        // Print button for printing the displayed table data
        print = new JButton("Print");
        print.setBounds(120, 70, 80, 20);
        print.addActionListener(this);
        add(print);

        setVisible(true);
    }

    /**
     * Handles actions for search and print buttons.
     * - The search button fetches deposit details for the selected meter number and month.
     * - The print button sends the table's content to the printer.
     *
     * @param ae the ActionEvent triggered by button clicks.
     */
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            // SQL query to fetch records from the 'bill' table matching the selected meter and month
            String query = "SELECT * FROM bill WHERE meter = '" + meterNumber.getSelectedItem() +
                    "' AND month = '" + cmonth.getSelectedItem() + "'";
            try {
                connection c = new connection();
                ResultSet rs = c.s.executeQuery(query);
                // Update the table model with the search results
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == print) {
            // Print the table content
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Main method to run the DepositDetails JFrame.
     *
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        new DepositDetails();
    }
}
