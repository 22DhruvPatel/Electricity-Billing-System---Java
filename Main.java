import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main class for the Billing Management System application.
 * This class creates the main graphical user interface (GUI) for the application,
 * including a menu bar with various options based on the user's role (Admin or regular user).
 * The application provides functionalities for managing customers,
 * updating information, generating bills, and launching external applications like Notepad and Calculator.
 *
 * Author: Dhruv Patel
 */
public class Main extends JFrame implements ActionListener {

    String userType; // Stores the type of user (e.g., Admin)
    String meter;    // Stores the meter identifier for customer-related actions

    /**
     * Constructor for the Main class.
     * Initializes the GUI components and sets up the menu bar based on user type.
     *
     * @param userType the type of user (e.g., Admin)
     * @param meter    the meter identifier associated with the user
     */
    Main(String userType, String meter) {
        this.userType = userType;
        this.meter = meter;
        // Set max width and height of JFrame
        setExtendedState(JFrame.MAXIMIZED_BOTH);  // Maximized_Both will extend the length and width to maximize of JFrame.
        // Add Image to the frame
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/elect1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1550, 850, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);

        // Create menu bar for the JFrame
        MenuBar mb = new MenuBar(); // Menubar create menu bar in the JFrame
        setMenuBar(mb);

        // Add menus to menu bar

        // 1st menu item
        Menu master = new Menu("Master");

        // Add items to master
        MenuItem newCustomer = new MenuItem("New Customer");
        newCustomer.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        newCustomer.addActionListener(this);
        master.add(newCustomer);

        MenuItem customerDetails = new MenuItem("Customer Details");
        customerDetails.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        customerDetails.addActionListener(this);
        master.add(customerDetails);

        MenuItem depositDetails = new MenuItem("Deposit Details");
        depositDetails.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        depositDetails.addActionListener(this);
        master.add(depositDetails);

        MenuItem calculateBill = new MenuItem("Calculate Bill");
        calculateBill.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        calculateBill.addActionListener(this);
        master.add(calculateBill);

        // 2nd menu item
        Menu information = new Menu("Information");

        MenuItem updateInformation = new MenuItem("Update Information");
        updateInformation.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        information.add(updateInformation);
        updateInformation.addActionListener(this);

        MenuItem viewInformation = new MenuItem("View Information");
        viewInformation.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        viewInformation.addActionListener(this);
        information.add(viewInformation);

        // 3rd menu item
        Menu user = new Menu("User");

        MenuItem payBill = new MenuItem("Pay Bill");
        payBill.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        payBill.addActionListener(this);
        user.add(payBill);

        MenuItem billDetails = new MenuItem("Bill Details");
        billDetails.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        billDetails.addActionListener(this);
        user.add(billDetails);

        // 5th menu item
        Menu utility = new Menu("Utility");

        MenuItem notepad = new MenuItem("Notepad");
        notepad.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        notepad.addActionListener(this);
        utility.add(notepad);

        MenuItem calculator = new MenuItem("Calculator");
        calculator.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        calculator.addActionListener(this);
        utility.add(calculator);

        // 4th item
        Menu report = new Menu("Reports");

        MenuItem generateBill = new MenuItem("Generate Bill");
        generateBill.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        generateBill.addActionListener(this);
        report.add(generateBill);

        // 6th menu item
        Menu menuExit = new Menu("Exit");
        menuExit.addActionListener(this);

        MenuItem exit = new MenuItem("Exit");
        exit.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        menuExit.add(exit);

        if (userType.equals("Admin")) {
            mb.add(master);
        } else {
            mb.add(report);
            mb.add(user);
            mb.add(information);
        }

        mb.add(utility);
        mb.add(menuExit);

        setLayout(new FlowLayout()); // Change layout to flow layout to show menu bar

        setVisible(true);
    }

    /**
     * Handles action events triggered by menu items.
     * Opens new windows or performs actions based on the selected menu item.
     *
     * @param ae the ActionEvent triggered by the user interaction
     */
    public void actionPerformed(ActionEvent ae) {
        String msg = ae.getActionCommand(); // We can find which command ( e.x. - customerDetails ) is called using getActionCommand.
        if (msg.equals("New Customer")) {
            new NewCustomer();
        } else if (msg.equals("Customer Details")) {
            new CustomerDetails();
        } else if (msg.equals("Deposit Details")) {
            new DepositDetails();
        } else if (msg.equals("Calculate Bill")) {
            new CalculateBill();
        } else if (msg.equals("View Information")) {
            new ViewInformation(meter);
        } else if (msg.equals("Update Information")) {
            new UpdateInformation(meter);
        } else if (msg.equals("Bill Details")) {
            new BillDetails(meter);
        } else if (msg.equals("Notepad")) {
            try {
                Runtime.getRuntime().exec("notepad.exe");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msg.equals("Calculator")) {
            try {
                Runtime.getRuntime().exec("calc.exe");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msg.equals("Exit")) {
            setVisible(false);
            new Login();
        } else if (msg.equals("Pay Bill")) {
            new PayBill(meter);
        } else if (msg.equals("Generate Bill")) {
            new GenerateBill("meter");
        }
    }

    /**
     * Main method to launch the application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Main("", ""); // Launches the main application window with default parameters
    }
}
