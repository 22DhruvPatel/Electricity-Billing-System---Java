import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

/**
 * PayBill class for the Billing Management System application.
 * This class provides a graphical user interface (GUI) for users to view and pay their electricity bills.
 * It retrieves and displays bill details based on the selected month and allows users to update the status of their bill to 'paid'.
 *
 * Author: Dhruv Patel
 */
public class PayBill extends JFrame {

    Choice cmonth; // Dropdown menu for selecting the billing month
    JButton pay, back; // Buttons for paying and going back
    String meter; // Meter number associated with the user's bill

    /**
     * Constructor for the PayBill class.
     * Initializes the GUI components and retrieves bill information from the database.
     *
     * @param meter the meter number associated with the user's bill
     */
    PayBill(String meter) {
        this.meter = meter;
        setLayout(null);
        setBounds(300, 150, 900, 600);

        JLabel heading = new JLabel("Electricity Bill");
        heading.setFont((new Font("Tahoma", Font.BOLD, 24)));
        heading.setBounds(120, 5, 400, 30);
        add(heading);

        JLabel meterNumberLabel = new JLabel("Meter Number");
        meterNumberLabel.setBounds(35, 80, 200, 20);
        add(meterNumberLabel);

        JLabel meterNumber = new JLabel("");
        meterNumber.setBounds(300, 80, 200, 20);
        add(meterNumber);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(35, 140, 200, 20);
        add(nameLabel);

        JLabel name = new JLabel("");
        name.setBounds(300, 140, 200, 20);
        add(name);

        JLabel monthLabel = new JLabel("Month");
        monthLabel.setBounds(35, 200, 200, 20);
        add(monthLabel);

        cmonth = new Choice();
        cmonth.setBounds(300, 200, 200, 20);
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
        add(cmonth);

        JLabel unitsLabel = new JLabel("Units");
        unitsLabel.setBounds(35, 260, 200, 20);
        add(unitsLabel);

        JLabel units = new JLabel("");
        units.setBounds(300, 260, 200, 20);
        add(units);

        JLabel totalBillLabel = new JLabel("Total Bill");
        totalBillLabel.setBounds(35, 320, 200, 20);
        add(totalBillLabel);

        JLabel totalBill = new JLabel("");
        totalBill.setBounds(300, 320, 200, 20);
        add(totalBill);

        JLabel statusLabel = new JLabel("Status");
        statusLabel.setBounds(35, 380, 200, 20);
        add(statusLabel);

        JLabel status = new JLabel("");
        status.setBounds(300, 380, 200, 20);
        status.setForeground(Color.RED);
        add(status);

        // Retrieve customer information and bill details from the database
        try {
            connection c = new connection();
            ResultSet rs = c.s.executeQuery("Select * from customer where meter_no = '" + meter + "'");

            while (rs.next()) {
                meterNumber.setText(meter);
                name.setText(rs.getString("name"));
            }

            rs = c.s.executeQuery("Select * from bill where meter= '" + meter + "' AND month= '" + cmonth.getSelectedItem() + "'");

            while (rs.next()) {
                units.setText(rs.getString("units"));
                totalBill.setText(rs.getString("totalbill"));
                status.setText(rs.getString("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Update bill details based on the selected month
        cmonth.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    connection c = new connection();

                    ResultSet rs = c.s.executeQuery("Select * from bill where meter= '" + meter + "' AND month= '" + cmonth.getSelectedItem() + "'");

                    while (rs.next()) {
                        units.setText(rs.getString("units"));
                        totalBill.setText(rs.getString("totalbill"));
                        status.setText(rs.getString("status"));
                    }
                } catch (Exception ae) {
                    ae.printStackTrace();
                }
            }
        });

        pay = new JButton("Pay");
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.white);
        pay.setBounds(100, 460, 100, 25);
        add(pay);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.white);
        back.setBounds(230, 460, 100, 25);
        add(back);

        getContentPane().setBackground(Color.WHITE);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/bill.png"));
        Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 120, 600, 3000);
        add(image);

        setVisible(true);
    }

    /**
     * Handles action events triggered by button clicks.
     * Updates the bill status to 'paid' if the Pay button is clicked
     * and navigates back to the previous screen if the Back button is clicked.
     *
     * @param ae the ActionEvent triggered by the user interaction
     */
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == pay) {
            try {
                connection c = new connection();
                c.s.executeUpdate("update bill set status = 'paid' where meter = '" + meter + "' AND month = '" + cmonth.getSelectedItem() + "'");
            } catch (Exception e) {
                e.printStackTrace();
            }
            setVisible(false);
            new Paytm(meter);
        } else {
            setVisible(false);
        }
    }

    /**
     * Main method to launch the PayBill application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new PayBill("");
    }
}
