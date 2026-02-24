import java.sql.*;
import java.util.Scanner;

public class ContactDeleter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3306/simple_contact_management";
        String user = "root";
        String password = "polo**AQ2396"; // your password

        try {
            // Step 1: Connect to database
            Connection con = DriverManager.getConnection(url,user,password);

            System.out.println("Connected to Database");

            // Step 2: Ask for which contact to delete
            System.out.print("Enter Contact ID to Delete: ");
            int id = sc.nextInt();

            // Step 3: Write delete query
            String query = "DELETE FROM contactTable WHERE id=?";

            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);

            // Step 4: Execute delete
            int rowsDeleted = pst.executeUpdate();

            // Step 5: Show result
            if (rowsDeleted > 0) {
                System.out.println("Contact Deleted Successfully!");
            } else {
                System.out.println("No Contact Found with that ID.");
            }

            con.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}

