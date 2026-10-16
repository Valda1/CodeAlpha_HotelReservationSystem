import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DB {

    final String dbURL = "jdbc:mysql://localhost:3306/java";
    final String user = "";
    final String password = "";

    private int roomNumber;
    private String fullName;

    public void makeReservation() {
        Reservation newReservation = new Reservation();
        Scanner scanner = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(dbURL, user, password)) {
            System.out.println("Please choose the number of the room you would like to book: ");
            roomNumber = scanner.nextInt();

            String sql = "SELECT available FROM rooms WHERE room_id = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
       
            preparedStatement.setInt(1, roomNumber);
       
            ResultSet resultSet = preparedStatement.executeQuery();
       
            boolean isAvailable = resultSet.getBoolean("available");
           
            boolean x = true;
           
            while (x) {
                if (resultSet.next()) {
                    if (isAvailable = true) {
                        System.out.println("Room is available");
                        x = false;
                       
                        System.out.println("Please enter your full name: ");
                        fullName = scanner.nextLine();

                        Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
                        Matcher matcher = pattern.matcher(fullName);

                    } else {
                        System.out.println("This room is not available. Please choose another room!");
                        System.out.println(" ");
                    }
                }
            }
       

        } catch (SQLException e){
            System.out.println("Something went wrong! " + e);
        }


    }
   
}
