/*
 * Class: CalculateBill
 * Author: Dhruv Patel
 * Description: This class creates a GUI-based Java Swing application for calculating and displaying
 *              an electricity bill based on the customer's meter number and units consumed.
 *              The interface allows users to input the units consumed, select a month,
 *              and then calculates the total bill based on predefined tax values.
 * Dependencies: Requires a database connection to fetch customer information
 *               and tax details, and to store bill data.
 *
 * Components:
 * - JPanel: Divides the frame into sections for layout organization.
 * - Labels, Buttons, TextFields, Choices: Provide GUI elements for user interaction.
 * - ImageIcon: Adds an image to the UI.
 */

// Import necessary Java libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

// Main class extending JFrame and implementing ActionListener
public class CalculateBill extends JFrame implements ActionListener {

    // UI Components
    JTextField tfUnits;            // Input field for units consumed
    JButton cancel, next;          // Buttons for submitting or canceling the operation
    JLabel lblName, customerAddressField; // Labels for displaying customer name and address
    Choice meterNumber , cmonth;    // Dropdowns for meter number and month selection

    // Constructor
    CalculateBill() {
        // Set up the frame properties
        setSize(700,500);
        setLocation(400,200);

        // Main panel for holding components
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(Color.LIGHT_GRAY);
        add(p);

        // Header Label
        JLabel heading = new JLabel("Calculate Electricity Bill");
        heading.setBounds(150,10,400,25);
        heading.setFont(new Font("Times new Roman", Font.PLAIN, 24));
        p.add(heading);

        // Meter Number Label and Dropdown
        JLabel lblmeterNumber = new JLabel("Meter Number");
        lblmeterNumber.setBounds(100,80,100,20);
        p.add(lblmeterNumber);

        meterNumber = new Choice();

        // Load meter numbers from the database into the dropdown
        try {
            connection c = new connection();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while (rs.next()) {
                meterNumber.add(rs.getString("meter_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        meterNumber.setBounds(240, 80,200,20);
        p.add(meterNumber);

        // Name Label and Display Field
        JLabel name = new JLabel("Name");
        name.setBounds(100,120,100,20);
        p.add(name);

        lblName = new JLabel(""); // Auto-populated with customer name
        lblName.setBounds(240,120,100,20);
        p.add(lblName);

        // Address Label and Display Field
        JLabel customerAddress = new JLabel("Address");
        customerAddress.setBounds(100,160,100,20);
        p.add(customerAddress);

        customerAddressField = new JLabel(); // Auto-populated with customer address
        customerAddressField.setBounds(240, 160, 200, 20);
        p.add(customerAddressField);

        // Load initial customer data based on selected meter number
        try {
            connection c = new connection();
            ResultSet rs = c.s.executeQuery("select * from customer Where meter_no = '" +meterNumber.getSelectedItem() + "'");
            while (rs.next()) {
                lblName.setText(rs.getString("name"));
                customerAddressField.setText(rs.getString("address"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Update customer data when meter number selection changes
        meterNumber.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    connection c = new connection();
                    ResultSet rs = c.s.executeQuery("select * from customer Where meter_no = '" +meterNumber.getSelectedItem() + "'");
                    while (rs.next()) {
                        lblName.setText(rs.getString("name"));
                        customerAddressField.setText(rs.getString("address"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Units Consumed Label and Input Field
        JLabel Units = new JLabel("Unit Consumed");
        Units.setBounds(100,200,100,20);
        p.add(Units);

        tfUnits = new JTextField();
        tfUnits.setBounds(240, 200, 200, 20);
        p.add(tfUnits);

        // Month Label and Dropdown
        JLabel customerState = new JLabel("Month");
        customerState.setBounds(100,240,100,20);
        p.add(customerState);

        cmonth = new Choice();
        cmonth.setBounds(240, 240, 200, 20);
        cmonth.add("January"); cmonth.add("February"); // Add all months
        cmonth.add("March"); cmonth.add("April");
        cmonth.add("May"); cmonth.add("June");
        cmonth.add("July"); cmonth.add("August");
        cmonth.add("September"); cmonth.add("October");
        cmonth.add("November"); cmonth.add("December");
        p.add(cmonth);

        // Submit and Cancel Buttons
        next = new JButton("Submit");
        next.setBounds(120, 350, 100, 25);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        p.add(next);

        cancel = new JButton("Cancel");
        cancel.setBounds(250, 350, 100, 25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        p.add(cancel);

        // Add image to the layout
        setLayout(new BorderLayout());
        add(p, "Center");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/hicon2.jpg"));
        Image i2 = i1.getImage().getScaledInstance(150,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image, "West");

        getContentPane().setBackground(Color.white);
        setVisible(true);
    }

    // Action events for buttons
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next) {  // Submit button action
            String meter = meterNumber.getSelectedItem();
            String month = cmonth.getSelectedItem();
            String units = tfUnits.getText();

            int totalbill = 0;
            int unit_consumed = Integer.parseInt(units);

            String query = "select * from tax";

            try {
                connection c = new connection();
                ResultSet rs = c.s.executeQuery(query);

                while (rs.next()) {  // Calculate bill with each tax component
                    totalbill += unit_consumed * Integer.parseInt(rs.getString("cost_per_unit"));
                    totalbill += Integer.parseInt(rs.getString("meter_rent"));
                    totalbill += Integer.parseInt(rs.getString("service_charge"));
                    totalbill += Integer.parseInt(rs.getString("service_tax"));
                    totalbill += Integer.parseInt(rs.getString("swacch_bharat_cess"));
                    totalbill += Integer.parseInt(rs.getString("fixed_tax"));
                }

            } catch (Exception ae) {
                ae.printStackTrace();
            }

            // Insert calculated bill into database
            String query2 = "insert into bill values('"+meter+"', '"+month+"','"+units+"','"+totalbill+"', 'Not Paid')";
            try {
                connection c = new connection();
                c.s.executeUpdate(query2);
                JOptionPane.showMessageDialog(null, "Customer Bill Added Successfully");
                setVisible(false);
            } catch (Exception ae) {
                ae.printStackTrace();
            }

        } else {  // Cancel button action
            setVisible(false);
        }
    }

    // Main method
    public static void main(String[] args) {
        new CalculateBill();
    }
}
