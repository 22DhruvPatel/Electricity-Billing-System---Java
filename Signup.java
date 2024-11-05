/*
 * @author Dhruv Patel
 */

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

/**
 * The Signup class extends JFrame and implements ActionListener
 * for user account creation functionality.
 */
public class Signup extends JFrame implements ActionListener {

    JButton createButton, backButton;  // Buttons for create and back actions
    Choice accType;                     // Dropdown for account type selection
    JTextField meterNumber, customerNameText, userNameText, customerPasswordText;  // Input fields

    /**
     * Constructor to initialize the Signup frame.
     */
    Signup() {
        // Create JFrame
        setBounds(450, 150, 700, 400);  // Set position and size of the frame
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Create a bordered panel for the form
        JPanel panel = new JPanel();
        panel.setBounds(30, 30, 650, 300);
        panel.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY, 5), "Create Account")); // Panel border
        panel.setLayout(null);
        panel.setForeground(Color.BLACK);
        add(panel);

        // Create a label and dropdown for account type
        JLabel heading = new JLabel("Create Account As");
        heading.setBounds(100, 50, 150, 20);
        heading.setForeground(Color.GRAY);
        heading.setFont(new Font("Times New Roman", Font.BOLD, 14));
        panel.add(heading);

        accType = new Choice();
        accType.add("Admin");
        accType.add("Customer");
        accType.setBounds(260, 50, 150, 20);
        panel.add(accType);

        // Create fields for meter number, username, customer name, and password
        createLabel(panel, "Meter Number", 90);
        meterNumber = new JTextField();
        meterNumber.setBounds(260, 90, 150, 20);
        meterNumber.setVisible(false);
        panel.add(meterNumber);

        createLabel(panel, "Username", 130);
        userNameText = new JTextField();
        userNameText.setBounds(260, 130, 150, 20);
        panel.add(userNameText);

        createLabel(panel, "Name", 170);
        customerNameText = new JTextField();
        customerNameText.setBounds(260, 170, 150, 20);
        panel.add(customerNameText);

        meterNumber.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent fe) {
                try {
                    connection c = new connection();
                    ResultSet rs = c.s.executeQuery("select * from login where meter_no = '" + meterNumber.getText() + "'");
                    while (rs.next()) {
                        customerNameText.setText(rs.getString("name"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        createLabel(panel, "Password", 210);
        customerPasswordText = new JTextField();
        customerPasswordText.setBounds(260, 210, 150, 20);
        panel.add(customerPasswordText);

        // Show/hide meter number field based on account type
        accType.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                String user = accType.getSelectedItem();
                boolean isCustomer = user.equals("Customer");
                meterNumber.setVisible(isCustomer);
                meterNumber.setVisible(isCustomer);
                customerNameText.setEditable(!isCustomer);
            }
        });

        // Create buttons
        createButton = new JButton("Create");
        createButton.setBackground(Color.BLACK);
        createButton.setForeground(Color.WHITE);
        createButton.setBounds(140, 260, 120, 25);
        createButton.addActionListener(this);
        panel.add(createButton);

        backButton = new JButton("Back");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setBounds(300, 260, 120, 25);
        backButton.addActionListener(this);
        panel.add(backButton);

        // Add image to frame
        ImageIcon image = new ImageIcon(ClassLoader.getSystemResource("Icons/signupImage.png"));
        Image image2 = image.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        JLabel imageLabel = new JLabel(new ImageIcon(image2));
        imageLabel.setBounds(415, 30, 250, 250);
        panel.add(imageLabel);

        setVisible(true);
    }

    /**
     * Creates a label and adds it to the specified panel.
     *
     * @param panel the panel to which the label will be added
     * @param text  the text of the label
     * @param y     the y-coordinate for the label
     */
    private void createLabel(JPanel panel, String text, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(100, y, 150, 20);
        label.setForeground(Color.GRAY);
        label.setFont(new Font("Times New Roman", Font.BOLD, 14));
        panel.add(label);
    }

    /**
     * Action performed method to handle button clicks.
     *
     * @param ae the ActionEvent to be handled
     */
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == createButton) {
            String aType = accType.getSelectedItem();
            String suserName = userNameText.getText();
            String sname = customerNameText.getText();
            String spassword = customerPasswordText.getText();
            String smeter = meterNumber.getText();

            try {
                connection c = new connection();
                String query;
                if (aType.equals("Admin")) {
                    query = "insert into login values('" + smeter + "', '" + suserName + "', '" + sname + "', '" + spassword + "', '" + aType + "')";
                } else {
                    query = "update login set username = '" + suserName + "', password = '" + spassword + "', user = '" + aType + "' Where meter_no = '" + smeter + "'";
                }
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Account Created Successfully");
                setVisible(false);
                new Login();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == backButton) {
            setVisible(false);
            new Login();
        }
    }

    /**
     * Main method to launch the Signup application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        new Signup();
    }
}
