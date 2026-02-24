import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ContactViewer {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/simple_contact_management";
        String user = "root";
        String password = "polo**AQ2396"; // your password

        try {
            //  Connect to database
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to Database");

            //  Create SQL query
            String query = "SELECT * FROM contactTable";

            //  Create a Statement object
            Statement stmt = con.createStatement();

            // 4️ Execute query and store results
            ResultSet rs = stmt.executeQuery(query);

            // 5️Print data in nice format
            System.out.println("\n---- Contact List ----");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Phone: " + rs.getString("phone"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Address: " + rs.getString("address"));
                System.out.println("-----------------------");
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

