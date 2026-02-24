import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class ContactManager {
    public static void main(String[] args) {

        //  Database connection details
        String url = "jdbc:mysql://localhost:3306/simple_contact_management"; // your DB name
        String user = "root";        // your MySQL username
        String password = "polo**AQ2396"; // your MySQL password

        Scanner sc = new Scanner(System.in);

        try {
            // 🔌 Step 1: Connect to MySQL
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to Database");

            //  Step 2: Ask user for contact details
            System.out.print("Enter Contact Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Contact Phone: ");
            String phone = sc.nextLine();

            System.out.print("Enter Contact Email: ");
            String email = sc.nextLine();

            System.out.print("Enter Contact Address: ");
            String address = sc.nextLine();


            //  Step 3: SQL query to insert data
            String query = "INSERT INTO contacttable (name, phone, email, address) VALUES (?, ?, ?, ?)";

            //  Step 4: Prepare statement & set values
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, phone);
            pst.setString(3, email);
            pst.setString(4, address);
            //  Step 5: Execute
            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println(" Contact Added Successfully!");
            } else {
                System.out.println(" Something went wrong!");
            }

            //  Step 6: Close everything
            pst.close();
            con.close();
            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
