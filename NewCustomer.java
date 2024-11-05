/*
 * @author Dhruv Patel
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * The NewCustomer class extends JFrame and implements ActionListener
 * for managing the new customer registration process.
 */
public class NewCustomer extends JFrame implements ActionListener {

    private JTextField newCustomerField, customerAddressField, customerCityField, customerStateField, customerEmailField, customerPhoneField;
    private JButton cancel, next;
    private JLabel meterNumberField;

    /**
     * Constructor for NewCustomer class that initializes the frame and components.
     */
    public NewCustomer() {
        setTitle("New Customer Registration");
        setSize(700, 500);
        setLocation(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close application on exit
        setLayout(new BorderLayout());

        // Create panel for form components
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(Color.LIGHT_GRAY);
        add(p, BorderLayout.CENTER);

        // Add heading
        JLabel heading = new JLabel("New Customer");
        heading.setBounds(180, 10, 200, 25);
        heading.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        p.add(heading);

        // Customer Name
        JLabel customerName = new JLabel("Customer Name");
        customerName.setBounds(100, 80, 100, 20);
        p.add(customerName);
        newCustomerField = new JTextField();
        newCustomerField.setBounds(240, 80, 200, 20);
        p.add(newCustomerField);

        // Meter Number
        JLabel meterNumber = new JLabel("Meter Number");
        meterNumber.setBounds(100, 120, 100, 20);
        p.add(meterNumber);
        meterNumberField = new JLabel(generateMeterNumber());
        meterNumberField.setBounds(240, 120, 100, 20);
        p.add(meterNumberField);

        // Address
        JLabel customerAddress = new JLabel("Address");
        customerAddress.setBounds(100, 160, 100, 20);
        p.add(customerAddress);
        customerAddressField = new JTextField();
        customerAddressField.setBounds(240, 160, 200, 20);
        p.add(customerAddressField);

        // City
        JLabel customerCity = new JLabel("City");
        customerCity.setBounds(100, 200, 100, 20);
        p.add(customerCity);
        customerCityField = new JTextField();
        customerCityField.setBounds(240, 200, 200, 20);
        p.add(customerCityField);

        // State
        JLabel customerState = new JLabel("State");
        customerState.setBounds(100, 240, 100, 20);
        p.add(customerState);
        customerStateField = new JTextField();
        customerStateField.setBounds(240, 240, 200, 20);
        p.add(customerStateField);

        // Customer Email
        JLabel customerEmail = new JLabel("Email");
        customerEmail.setBounds(100, 280, 100, 20);
        p.add(customerEmail);
        customerEmailField = new JTextField();
        customerEmailField.setBounds(240, 280, 200, 20);
        p.add(customerEmailField);

        // Customer Phone Number
        JLabel customerPhone = new JLabel("Phone");
        customerPhone.setBounds(100, 320, 100, 20);
        p.add(customerPhone);
        customerPhoneField = new JTextField();
        customerPhoneField.setBounds(240, 320, 200, 20);
        p.add(customerPhoneField);

        // Buttons
        next = createButton("Next", 120, 390);
        cancel = createButton("Cancel", 250, 390);

        // Add image to the West of the layout
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/update.png"));
        Image i2 = i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image, BorderLayout.WEST);

        getContentPane().setBackground(Color.white);
        setVisible(true);
    }

    /**
     * Generate a random meter number.
     *
     * @return A string representation of a random meter number.
     */
    private String generateMeterNumber() {
        Random rand = new Random();
        long number = Math.abs(rand.nextLong() % 1000000);
        return String.valueOf(number);
    }

    /**
     * Create a button with specified label and position.
     *
     * @param label the label for the button
     * @param x     the x-coordinate for the button
     * @param y     the y-coordinate for the button
     * @return the created JButton
     */
    private JButton createButton(String label, int x, int y) {
        JButton button = new JButton(label);
        button.setBounds(x, y, 100, 25);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.addActionListener(this);
        add(button);
        return button;
    }

    /**
     * Handle button actions.
     *
     * @param e the ActionEvent triggered by button clicks
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next) {
            handleNextAction();
        } else { // Close the frame if cancel button is pressed
            setVisible(false);
        }
    }

    /**
     * Handle actions for the Next button.
     */
    private void handleNextAction() {
        String name = newCustomerField.getText();
        String meter = meterNumberField.getText();
        String address = customerAddressField.getText();
        String city = customerCityField.getText();
        String state = customerStateField.getText();
        String email = customerEmailField.getText();
        String phone = customerPhoneField.getText();

        String query1 = "INSERT INTO customer VALUES('" + name + "', '" + meter + "','" + address + "','" + city + "','" + state + "','" + email + "', '" + phone + "')";
        String query2 = "INSERT INTO login VALUES('" + meter + "','','" + name + "','','')"; // Keep empty for extra values in login table.

        try {
            connection c = new connection();
            c.s.executeUpdate(query1);
            c.s.executeUpdate(query2);
            JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");
            setVisible(false);
            new MeterInfo(meter);
        } catch (Exception ae) {
            ae.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ae.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Main method to run the NewCustomer application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        new NewCustomer();
    }
}
