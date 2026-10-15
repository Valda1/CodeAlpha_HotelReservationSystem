import java.sql.PreparedStatement;
import java.util.Scanner;

public class DB {

    //final String dbURL = "jdbc:mysql://localhost:3306/java";
    //final String user = "root";
    //final String password = "";

    private int roomNumber;

    public void makeReservation() {
        Reservation newReservation = new Reservation();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please choose the number of the room you would like to book: ");
        roomNumber = scanner.nextInt();

        String sql = "SELECT available FROM rooms WHERE room_id = ?";

        //PreparedStatement preparedStatement = conn.prepareStatement(sql);
       
        //preparedStatement.setInt(1, roomNumber);
       
        //ResultSet resultSet = preparedStatement.executeQuery();
       
        //Boolean available = resultSet.getBoolean("available");
       
        /*if (resultSet.next()) {
            if (available = true) {
                System.out.println("Room is available");
            } else {
                System.out.println("This room is not available. Please choose another room!");
            }
        }*/


    }
   
}
