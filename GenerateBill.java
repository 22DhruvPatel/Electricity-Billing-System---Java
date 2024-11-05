import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class GenerateBill extends JFrame implements ActionListener {

    JTextArea area;
    String meter;
    JButton generateBill;
    Choice cmonth;
    GenerateBill(String meter) {
        this.meter = meter;
        setSize(500,800);
        setLocation(550,30);

        setLayout(new BorderLayout());

        Panel  panel = new Panel();

        JLabel heading = new JLabel("Generate Bill");

        JLabel meterNumber = new JLabel(meter);

        cmonth = new Choice();
        cmonth.add("Janurary");
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

        area = new JTextArea(50,15);
        area.setText("\n\n\t---------Click on the----------\n\t Generate Bill Button to get\n\tthe bill of the selected month.");


        JScrollPane pane = new JScrollPane(area);


        generateBill = new JButton("Generate Bill");
        generateBill.addActionListener(this);


        panel.add(heading);
        panel.add(meterNumber);
        panel.add(cmonth);
        add(panel,"North");


        add(pane, "Center");
        add(generateBill, "South");



        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            connection c = new connection();


            String month = cmonth.getSelectedItem();
            area.setText("\tReliance Power Limited\nELECTRICITY BILL GENERATED FOR THE MONTH OF "+month+",2022\n\n\n");

            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meter+"'");

            if (rs.next()) {
                area.append("\n   Customer Name : " +rs.getString("name"));
                area.append("\n   Meter Number  : " +rs.getString("meter_no"));
                area.append("\n   Address       : " +rs.getString("address"));
                area.append("\n   City          : " +rs.getString("city"));
                area.append("\n   State         : " +rs.getString("state"));
                area.append("\n   Email         : " +rs.getString("email"));
                area.append("\n   Phone Number  : " +rs.getString("phone"));
                area.append("\n--------------------------------------------------------------");
                area.append("\n");
            }


             rs = c.s.executeQuery("select * from meter_info where meter_no = '"+meter+"'");

            if (rs.next()) {
                area.append("\n   Meter Location : " +rs.getString("meter_location"));
                area.append("\n   Meter Type     : " +rs.getString("meter_type"));
                area.append("\n   Phase Code     : " +rs.getString("phase_code"));
                area.append("\n   Bill Type      : " +rs.getString("bill_type"));
                area.append("\n   Days           : " +rs.getString("days"));
                area.append("\n--------------------------------------------------------------");
                area.append("\n");
            }



            rs = c.s.executeQuery("select * from tax");

            if (rs.next()) {
                area.append("\n   Cost Per Unit     : " +rs.getString("cost_per_unit"));
                area.append("\n   Meter Rent        : " +rs.getString("meter_rent"));
                area.append("\n   Service Charge    : " +rs.getString("service_charge"));
                area.append("\n   Service Tax       : " +rs.getString("service_tax"));
                area.append("\n   Swacch Bharat Tax : " +rs.getString("swacch_bharat_cess"));
                area.append("\n   Fixed Tax         : " +rs.getString("fixed_tax"));
                area.append("\n--------------------------------------------------------------");
                area.append("\n");
            }



            rs = c.s.executeQuery("select * from bill where meter = '"+meter+"' and month = '"+month+"'");

            if (rs.next()) {
                area.append("\n   Current Month     : " +rs.getString("month"));
                area.append("\n   Units Consumed       : " +rs.getString("units"));
                area.append("\n   Total Charges    : " +rs.getString("totalbill"));
                area.append("\n-----------------------------------------------------------------------------------");
                area.append("\n   Total Payable : " +rs.getString("totalbill"));
                area.append("\n   Fixed Tax         : " +rs.getString("fixed_tax"));
                area.append("\n--------------------------------------------------------------");
                area.append("\n");
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }






    public static void main(String[] args) {
        new GenerateBill("");
    }
}
