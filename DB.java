import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DB {

    final String dbURL = "jdbc:mysql://localhost:3306/JAVA_hotel_reservation_system";
    final String user = "root";
    final String password = "root";

    private int roomNumber;
    private String fullName;

    
    /*public void makeReservation() {
        Reservation newReservation = new Reservation();
        Scanner scanner = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(dbURL, user, password)) {

            boolean x = true;

            while (x) {
                System.out.println("Please choose the number of the room you would like to book:");
                roomNumber = scanner.nextInt();

                String sql = "SELECT isAvailable FROM rooms WHERE room_id = ?";

                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

                    preparedStatement.setInt(1, roomNumber);

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {

                        if (resultSet.next()) {
                            boolean isAvailable = resultSet.getBoolean("isAvailable");

                            if (isAvailable) {
                                System.out.println("Room is available");
                                x = false;
                            } else {
                                System.out.println("This room is not available. Please choose another room!");
                            }

                        } else {
                            System.out.println("This room does not exist. Please choose another room!");
                        }
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Something went wrong! " + e.getMessage());
        }
    }*/


    public void makeReservation() {
        Reservation newReservation = new Reservation();
        Scanner scanner = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(dbURL, user, password)) {
            System.out.println("Please choose the number of the room you would like to book: ");
            roomNumber = scanner.nextInt();

            String sql = "SELECT isAvailable FROM rooms WHERE room_id = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
       
            preparedStatement.setInt(1, roomNumber);
       
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
       
            boolean isAvailable = resultSet.getBoolean("isAvailable");
           
            boolean x = true;
           
            while (x) {
                if (resultSet.next()) {
                    if (isAvailable == true) {
                        System.out.println("Room is available");
                        x = false;
                       
                        System.out.println("Please enter your full name: ");
                        //fullName = scanner.nextLine();
                        fullName = scanner.next();

                        Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
                        Matcher matcher = pattern.matcher(fullName);
                        boolean matches = matcher.matches();

                        boolean y = true;

                        while(y){
                            if(matches){
                               newReservation.setFullName(fullName);
                               y = false; 
                            }else{
                                System.out.println("Please enter a valid name:");
                                fullName = scanner.next();
                            }

                        }
 
                        if(matches) {
                            newReservation.setFullName(fullName);
                        }else{
                            System.out.println("Please enter a valid name:");
                            fullName = scanner.next();
                        }

                    } else {
                        System.out.println("This room is not available. Please choose another room!");
                        //x = true;
                        //System.out.println(" ");
                        roomNumber = scanner.nextInt();
                    }
                }
            }
       

        } catch (SQLException e){
            System.out.println("Something went wrong! " + e);
        }


    }
   
}
