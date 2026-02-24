import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/ simple_contact_management"; // replace with your DB name
        String user = "root";       // your MySQL username
        String password = "polo**AQ2396";  // your MySQL password

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Database Connected Successfully!");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
