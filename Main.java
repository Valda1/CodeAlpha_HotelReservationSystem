import java.util.Scanner;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int choice = 0;
   
    System.out.println("Welcome to our hotel!");
    System.out.println("______________________");
    //System.out.println("How can we help you today?");
    System.out.println("What would you like to do?");
    printOptions();
   
    choice = scanner.nextInt();
    //scanner.nextLine();
   
    switch(choice) {
        case 0:
            printOptions();
            break;
        case 1:
            DB myData = new DB();
            myData.makeReservation();
            break;
        case 2:
            //cancelReservation();
            break;
        case 3:
            //showReservation();
            break;
        case 4:
            //checkAvailability();
            break;
    }
}
   
    public static void printOptions () {
        System.out.println("\nPress");
        System.out.println("\t 0 - To print choice options");
        System.out.println("\t 1 - Make a reservation");
        System.out.println("\t 2 - Cancel my reservation");
        System.out.println("\t 3 - Check my reservation info");
        System.out.println("\t 4 - Check room's availability");
        //System.out.println("\t 7 - To log out");
    }
  
}

/*public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/JAVA_hotel_reservation_system";
        String user = "root";
        String password = "root";

        try (Connection connection =
                 DriverManager.getConnection(url, user, password)) {

            System.out.println("Connected to MySQL successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}*/
