/*
 * @author Dhruv Patel
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

/**
 * The UpdateInformation class provides a GUI for updating customer information
 * associated with a specific meter number. It extends JFrame and implements ActionListener.
 */
public class UpdateInformation extends JFrame implements ActionListener {

    // Text fields for customer information
    TextField addText, cityText, emailText, stateText, phoneText;
    JButton update, cancel;
    String meter;

    /**
     * Constructor for the UpdateInformation class.
     * Initializes the JFrame components and retrieves customer data for the specified meter.
     *
     * @param meter The meter number associated with the customer to update.
     */
    UpdateInformation(String meter) {
        this.meter = meter;

        setBounds(300, 150, 1050, 450);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Heading label
        JLabel heading = new JLabel("UPDATE CUSTOMER INFORMATION");
        heading.setBounds(110, 0, 400, 40);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(heading);

        // Labels and text fields for customer information
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(30, 70, 100, 20);
        add(nameLabel);

        JLabel nameText = new JLabel();
        nameText.setBounds(230, 70, 200, 20);
        add(nameText);

        JLabel meterNumberLabel = new JLabel("Meter number");
        meterNumberLabel.setBounds(30, 110, 100, 20);
        add(meterNumberLabel);

        JLabel meterNumber = new JLabel("");
        meterNumber.setBounds(230, 110, 200, 20);
        add(meterNumber);

        JLabel addressLabel = new JLabel("Address");
        addressLabel.setBounds(30, 150, 100, 20);
        add(addressLabel);

        addText = new TextField();
        addText.setBounds(230, 150, 200, 20);
        add(addText);

        JLabel cityLabel = new JLabel("City");
        cityLabel.setBounds(30, 190, 100, 20);
        add(cityLabel);

        cityText = new TextField();
        cityText.setBounds(230, 190, 200, 20);
        add(cityText);

        JLabel stateLabel = new JLabel("State");
        stateLabel.setBounds(30, 230, 100, 20);
        add(stateLabel);

        stateText = new TextField();
        stateText.setBounds(230, 230, 200, 20);
        add(stateText);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(30, 270, 100, 20);
        add(emailLabel);

        emailText = new TextField();
        emailText.setBounds(230, 270, 200, 20);
        add(emailText);

        JLabel phoneLabel = new JLabel("Phone");
        phoneLabel.setBounds(30, 310, 100, 20);
        add(phoneLabel);

        phoneText = new TextField();
        phoneText.setBounds(230, 310, 200, 20);
        add(phoneText);

        // Retrieve customer information from the database
        try {
            connection c = new connection();
            String query = "SELECT * FROM customer WHERE meter_no = '" + meter + "'";
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                nameText.setText(rs.getString("name"));
                addText.setText(rs.getString("address"));
                cityText.setText(rs.getString("city"));
                stateText.setText(rs.getString("state"));
                emailText.setText(rs.getString("email"));
                phoneText.setText(rs.getString("phone"));
                meterNumber.setText(rs.getString("meter_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Update button
        update = new JButton("Update");
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setBounds(70, 360, 100, 25);
        add(update);
        update.addActionListener(this);

        // Cancel button
        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(230, 360, 100, 25);
        add(cancel);
        cancel.addActionListener(this);

        // Image for the GUI
        ImageIcon i = new ImageIcon(ClassLoader.getSystemResource("Icons/update.jpg"));
        Image i2 = i.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(550, 50, 400, 300);
        add(image);

        setVisible(true);
    }

    /**
     * Handles button click events for updating information or cancelling the operation.
     *
     * @param e The ActionEvent triggered by button clicks.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == update) {
            String addressField = addText.getText();
            String emailField = emailText.getText();
            String cityField = cityText.getText();
            String stateField = stateText.getText();
            String phoneField = phoneText.getText();

            // Update customer information in the database
            try {
                connection c = new connection();
                c.s.executeUpdate("UPDATE customer SET address = '" + addressField + "', city = '" + cityField + "', state = '" + stateField + "', email = '" + emailField + "', phone = '" + phoneField + "' WHERE meter_no = '" + meter + "'");
                JOptionPane.showMessageDialog(null, "User Information Updated.");
                setVisible(false);
            } catch (Exception ae) {
                ae.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    /**
     * Main method to run the UpdateInformation application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new UpdateInformation("");
    }
}
