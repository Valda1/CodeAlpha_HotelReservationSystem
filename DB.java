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

    
    public void selectRoom() {
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
    }

    public void collectUserInfo(){
        Scanner scanner = new Scanner(System.in);
        boolean matches = false;

        while(!matches){
            System.out.println("Please enter your full name:");
            fullName = scanner.nextLine().trim();
            Pattern pattern = Pattern.compile("[a-zA-Z]+(?:[ '-][a-zA-Z]+)*");
            Matcher matcher = pattern.matcher(fullName);
            matches = matcher.matches();

            if(matches){
                System.out.println("Name accepted!");
                System.out.println("You have succesfully made reservation! Thank you!");
            }else{
                System.out.println("The name entered in not valid. Try again!");
            }
        }
    }

    public void saveReservation(Connection conn) throws SQLException {
        String updateSQL = "UPDATE rooms SET isAvailable = FALSE WHERE room_id = ? AND isAvailable =TRUE";

        String insertSQL = "INSERT INTO reservations (room_id, date, full_name) VALUES (?, ?, ?)";

        //conn.setAutoCommit(false);

        //PreparedStatement preparedStatement = conn.prepareStatement(updateSQL);

        try{
            try(PreparedStatement updateStatement = conn.prepareStatement(updateSQL)){
                updateStatement.setInt(1, roomNumber);

                int rowsUpdated = updateStatement.executeUpdate();

                if(rowsUpdated == 0){
                    conn.rollback();
                    System.out.println("Sorry, room is no longer available or does not exist.");
                    return;
                }else if(rowsUpdated > 0){
                    System.out.println("Success!");
                }
            }

            try(PreparedStatement inseStatement = conn.prepareStatement(insertSQL)){
                inseStatement.setInt(1, roomNumber);
                //inseStatement.setDate(2, java.sql.Date.valueOf(date));
                inseStatement.setString(3, fullName);

                //int rowsInserted = inseStatement.executeUpdate();
                conn.commit();
                System.out.println("You have succefully made a reservation!");
            }catch (SQLException e){
                conn.rollback();
                throw e;
            }

        }finally{
            conn.setAutoCommit(true);
        }
    }

    

   
}
