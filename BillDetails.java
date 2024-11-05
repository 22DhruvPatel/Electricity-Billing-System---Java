/*
 * @author Dhruv Patel
 */

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

/**
 * The BillDetails class displays the billing information associated with a given meter number
 * in a JTable within a JFrame.
 */
public class BillDetails extends JFrame {

    /**
     * Constructor for the BillDetails class.
     * Initializes the JFrame and populates it with billing information for the specified meter.
     *
     * @param meter The meter number for which to display the billing details.
     */
    BillDetails(String meter) {
        setSize(700, 650);
        setLocation(400, 150);
        getContentPane().setBackground(Color.WHITE);

        // Create a JTable to display billing information
        JTable table = new JTable();

        try {
            connection c = new connection();
            String query = "Select * from bill where meter = '" + meter + "'";
            ResultSet rs = c.s.executeQuery(query);

            // Set the table model to the result set
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create a JScrollPane to hold the table
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0, 0, 700, 650);
        add(sp);

        setVisible(true);
    }

    /**
     * Main method to run the BillDetails application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new BillDetails("");
    }
}
