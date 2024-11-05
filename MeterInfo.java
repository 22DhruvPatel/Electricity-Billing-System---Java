import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MeterInfo extends JFrame implements ActionListener {

    JTextField newCustomerField, customerAddressField, customerCityField, customerStateField, customerEmailField, customerPhoneField;
    JButton cancel, next;
    JLabel meterNumberField;
    Choice meterLocation, meterType, phaseCode, billType1;
    String meterNumber;
    MeterInfo(String meterNumber) {
        this.meterNumber = meterNumber;
        setSize(700,500);
        setLocation(400,200);

        // we need to divide this frame into two parts
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(Color.LIGHT_GRAY);
        add(p);

        // Add heading
        JLabel heading = new JLabel("Meter Information");
        heading.setBounds(180,10,200,25);
        heading.setFont(new Font("Times new Roman", Font.PLAIN, 24));
        p.add(heading);

        // Add Customer name
        JLabel customerName = new JLabel("Meter Number");
        customerName.setBounds(100,80,100,20);
        //heading.setFont(new Font("Times new Roman", Font.PLAIN, 24));
        p.add(customerName);

        JLabel meter_Number = new JLabel(meterNumber);
        meter_Number.setBounds(240,80,100,20);
        //heading.setFont(new Font("Times new Roman", Font.PLAIN, 24));
        p.add(meter_Number);


        // Meter Number
        JLabel meterNumber1 = new JLabel("Meter Location");
        meterNumber1.setBounds(100,120,100,20);
        p.add(meterNumber1);

        meterLocation = new Choice();
        meterLocation.add("Outside");
        meterLocation.add("Inside");
        meterLocation.setBounds(240,120,200,20);
        p.add(meterLocation);




        // Address
        JLabel meterType1 = new JLabel("Meter Type");
        meterType1.setBounds(100, 160, 100, 20);
        p.add(meterType1);

        meterType = new Choice();
        meterType.add("Electric Meter");
        meterType.add("Solar Meter");
        meterType.add("Smart Meter");
        meterType.setBounds(240,160,200,20);
        p.add(meterType);

        // City
        JLabel customerCity = new JLabel("Phase Code");
        customerCity.setBounds(100,200,100,20);
        p.add(customerCity);

        phaseCode = new Choice();
        phaseCode.add("011");
        phaseCode.add("022");
        phaseCode.add("033");
        phaseCode.add("044");
        phaseCode.add("055");
        phaseCode.add("066");
        phaseCode.add("077");
        phaseCode.add("088");
        phaseCode.add("099");

        phaseCode.setBounds(240,200,200,20);
        p.add(phaseCode);


        JLabel billType = new JLabel("Bill Type");
        billType.setBounds(100, 240, 100, 20);
        p.add(billType);

        billType1 = new Choice();
        billType1.add("Normal");
        billType1.add("Industral");
        billType1.setBounds(240,240,200,20);
        p.add(billType1);



        JLabel days = new JLabel("Days");
        days.setBounds(100,280,100,20);
        p.add(days);

        JLabel totalDays = new JLabel("30 Days");
        totalDays.setBounds(240,280,100,20);
        p.add(totalDays);


        JLabel note = new JLabel("Note");
        note.setBounds(100,320,100,20);
        p.add(note);

        JLabel defaultBill = new JLabel("By default the bill is calculated for 30 days only");
        defaultBill.setBounds(240,320,500,20);
        p.add(defaultBill);



        //Make two buttons
        next = new JButton("Submit");
        next.setBounds(220, 390, 100, 25);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        p.add(next);




        // Add another layout of type border to add an image to the center of panel
        setLayout(new BorderLayout());
        add(p, "Center");


        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/update.png"));
        Image i2 = i1.getImage().getScaledInstance(150,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image, "West"); // Border layout accepts directions with capital first letter.


        getContentPane().setBackground(Color.white);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next) {   // What happens when next button is clicked
            String meter = meterNumber;
            String Location = meterLocation.getSelectedItem();
            String type = meterType.getSelectedItem();
            String code = phaseCode.getSelectedItem();
            String billtype = billType1.getSelectedItem();
            String days = "30";

            // Create one table in database with name of customer and insert all the details in sequence. And 2nd query to insert name and meter to login table.
            // String query1 = "insert into customer values('"+name+"', '"+meter+"','"+address+"','"+city+"','"+state+"','"+email+"', '"+phone+"')";
            // String query2 = "insert into login values('"+meter+"','','"+name+"','',''))"; // Keep empty for extra values in login table.

            String query1 = "insert into meter_info values('"+meter+"', '"+Location+"','"+type+"','"+code+"','"+billtype+"','"+days+"')";

            try {
                connection c = new connection();
                c.s.executeUpdate(query1);
                // Create one table in database with name of customer and insert all the details in sequence. And 2nd query to insert name and meter to login table.
//                String query1 = "insert into meter_info values('"+meter+"', '"+Location+"','"+type+"','"+code+"','"+billtype+"','"+days+"')";



                JOptionPane.showMessageDialog(null,"Meter Information Added Successfully");
                setVisible(false);


                //new Frame
                new MeterInfo(meter);
            }catch (Exception ae ){
                ae.printStackTrace();;
            }

        }else{   // Frame is closed if clear button is pressed
            setVisible(false);
        }
    }
    public static void main(String[] args) {
        new MeterInfo("");
    }
}
