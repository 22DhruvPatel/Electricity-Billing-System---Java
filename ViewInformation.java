import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

/**
 * ViewInformation class is a Swing application that displays customer information
 * retrieved from a database based on the provided meter number.
 *
 * Author: Dhruv Patel
 * Date: November 5, 2024
 */
public class ViewInformation extends JFrame implements ActionListener {

    private JButton cancel; // Button to cancel and close the window

    /**
     * Constructor for the ViewInformation class.
     * Initializes the GUI components and retrieves customer information from the database.
     *
     * @param meter The meter number used to fetch customer details from the database.
     */
    ViewInformation(String meter) {
        // Set the properties of the JFrame
        setBounds(350, 150, 850, 650);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Heading label
        JLabel heading = new JLabel("VIEW CUSTOMER INFORMATION");
        heading.setBounds(250, 0, 500, 40);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(heading);

        // Customer information labels
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(70, 80, 100, 20);
        add(nameLabel);

        JLabel name = new JLabel("");
        name.setBounds(250, 80, 100, 20);
        add(name);

        JLabel meterNumberLabel = new JLabel("Meter number");
        meterNumberLabel.setBounds(70, 140, 100, 20);
        add(meterNumberLabel);

        JLabel meterNumber = new JLabel("");
        meterNumber.setBounds(250, 140, 100, 20);
        add(meterNumber);

        JLabel addressLabel = new JLabel("Address");
        addressLabel.setBounds(70, 200, 100, 20);
        add(addressLabel);

        JLabel address = new JLabel("");
        address.setBounds(250, 200, 100, 20);
        add(address);

        JLabel cityLabel = new JLabel("City");
        cityLabel.setBounds(70, 260, 100, 20);
        add(cityLabel);

        JLabel city = new JLabel("");
        city.setBounds(250, 260, 100, 20);
        add(city);

        JLabel stateLabel = new JLabel("State");
        stateLabel.setBounds(600, 80, 100, 20);
        add(stateLabel);

        JLabel state = new JLabel("");
        state.setBounds(600, 80, 100, 20);
        add(state);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(600, 140, 100, 20);
        add(emailLabel);

        JLabel email = new JLabel("");
        email.setBounds(600, 140, 100, 20);
        add(email);

        JLabel phoneLabel = new JLabel("Phone");
        phoneLabel.setBounds(600, 200, 100, 20);
        add(phoneLabel);

        JLabel phone = new JLabel("");
        phone.setBounds(600, 200, 100, 20);
        add(phone);

        // Retrieve and display customer information from the database
        try {
            connection c = new connection();
            String query = "SELECT * FROM customer WHERE meter_no = '" + meter + "'";
            ResultSet rs = c.s.executeQuery(query);
            if (rs.next()) { // Check if any record exists
                name.setText(rs.getString("name"));
                address.setText(rs.getString("address"));
                city.setText(rs.getString("city"));
                state.setText(rs.getString("state"));
                email.setText(rs.getString("email"));
                phone.setText(rs.getString("phone"));
                meterNumber.setText(rs.getString("meter_no"));
            } else {
                JOptionPane.showMessageDialog(null, "No data found for meter number: " + meter);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace for debugging
        }

        // Cancel button to close the window
        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(350, 340, 100, 25);
        add(cancel);
        cancel.addActionListener(this);

        // Image label
        ImageIcon i = new ImageIcon(ClassLoader.getSystemResource("Icons/viewcustomer.jpg"));
        Image i2 = i.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(20, 350, 600, 300);
        add(image);

        // Make the window visible
        setVisible(true);
    }

    /**
     * Action listener for the buttons in the GUI.
     *
     * @param ae The ActionEvent triggered by button clicks.
     */
    public void actionPerformed(ActionEvent ae) {
        setVisible(false); // Close the window
    }

    /**
     * Main method to run the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new ViewInformation("valid_meter_number"); // Replace with an actual meter number
    }
}
