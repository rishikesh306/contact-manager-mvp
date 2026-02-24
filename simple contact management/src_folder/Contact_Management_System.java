import java.sql.*;
import java.util.Scanner;
public class Contact_Management_System {
    
    static final String url = "jdbc:mysql://localhost:3306/simple_contact_management";
    static final String user = "root";            
    static final String password = "polo**AQ2396";    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=====  Contact Management Menu =====");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Update Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();  // clear newline

            switch (choice) {
                case 1 -> addContact(sc);
                case 2 -> viewContacts();
                case 3 -> updateContact(sc);
                case 4 -> deleteContact(sc);
                case 5 -> {
                    System.out.println(" Thanks for using ours Contact Management System!");
                    sc.close();
                    return;
                }
                default -> System.out.println(" Invalid choice, try again!");
            }
        }
    }

    // ------------------ CREATE ------------------
    static void addContact(Scanner sc) {
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to Database");

            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Phone: ");
            String phone = sc.nextLine();
            System.out.print("Enter Email: ");
            String email = sc.nextLine();
            System.out.print("Enter Address: ");
            String address = sc.nextLine();

            String query = "INSERT INTO contactTable(name, phone, email, address) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, phone);
            pst.setString(3, email);
            pst.setString(4, address);

            pst.executeUpdate();
            System.out.println(" Contact Added Successfully!");
        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }

    // ------------------ READ ------------------
    static void viewContacts() {
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM contactTable");

            System.out.println("\n---- Contact List ----");
            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Phone: " + rs.getString("phone"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Address: " + rs.getString("address"));
                System.out.println("-----------------------");
            }
            if (!hasData) System.out.println("(no records)");
        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }

    // ------------------ UPDATE ------------------
    static void updateContact(Scanner sc) {
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.print("Enter Contact ID to Update: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter New Name: ");
            String name = sc.nextLine();
            System.out.print("Enter New Phone: ");
            String phone = sc.nextLine();
            System.out.print("Enter New Email: ");
            String email = sc.nextLine();
            System.out.print("Enter New Address: ");
            String address = sc.nextLine();

            String query = "UPDATE contactTable SET name=?, phone=?, email=?, address=? WHERE id=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, phone);
            pst.setString(3, email);
            pst.setString(4, address);
            pst.setInt(5, id);

            int rows = pst.executeUpdate();
            if (rows > 0)
                System.out.println(" Contact Updated Successfully!");
            else
                System.out.println(" No Contact Found with that ID.");
        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }

    // ------------------ DELETE ------------------
    static void deleteContact(Scanner sc) {
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.print("Enter Contact ID to Delete: ");
            int id = sc.nextInt();

            String query = "DELETE FROM contactTable WHERE id=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);

            int rows = pst.executeUpdate();
            if (rows > 0)
                System.out.println(" Contact Deleted Successfully!");
            else
                System.out.println(" No Contact Found with that ID.");
        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }
}



























// file compile command
//javac -cp "mysql_jarconnector/mysql-connector-j-8.1.0.jar;src_folder" src_folder/Contact_Management_System.java

// file run command
//java -cp "mysql_jarconnector/mysql-connector-j-8.1.0.jar;src_folder" Contact_Management_System
