import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;



// Steps of jdbc connectivity
// 1. Register the Driver
// 2. Make a database and connect it by giving the link and information.
// 3. Create a statement
// 4. Executing mysql queries




public class connection {
    Connection c;
    Statement s;
    connection() {
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebs", "root", "Dhruv@2201");
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
