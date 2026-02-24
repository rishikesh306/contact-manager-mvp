import java.sql.*;
import java.util.Scanner;

public class ContactUpdater {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String url = "jdbc:mysql://localhost:3306/simple_contact_management";
        String user = "root";
        String password = "polo**AQ2396"; // your password

        try {
            // Step 1: Connect to database
            Connection con = DriverManager.getConnection(url,user,password);
            System.out.println("Connected to Database");

            // Step 2: Ask user which contact to update
            System.out.print("Enter Contact ID to Update: ");
            int id = sc.nextInt();
            sc.nextLine();  // to clear buffer

            // Step 3: Ask what new details you want
            System.out.print("Enter New Name: ");
            String name = sc.nextLine();

            System.out.print("Enter New Phone: ");
            String phone = sc.nextLine();

            System.out.print("Enter New Email: ");
            String email = sc.nextLine();

            System.out.print("Enter New Address: ");
            String address = sc.nextLine();

            // Step 4: Write SQL query to update
            String query = "UPDATE contactTable SET name=?, phone=?, email=?, address=? WHERE id=?";

            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, phone);
            pst.setString(3, email);
            pst.setString(4, address);
            pst.setInt(5, id);

            // Step 5: Execute update
            int rowsUpdated = pst.executeUpdate();

            // Step 6: Check result
            if (rowsUpdated > 0) {
                System.out.println(" Contact Updated Successfully!");
            } else {
                System.out.println(" No Contact Found with that ID.");
            }

            // Step 7: Close connection
            con.close();

        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
        }

        sc.close();
    }
}
