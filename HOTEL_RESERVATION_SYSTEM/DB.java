package HOTEL_RESERVATION_SYSTEM;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DB {

    final String dbURL = "jdbc:mysql://localhost:3306/JAVA_hotel_reservation_system";
    final String user = "root";
    final String password = "root";


    public int selectRoom() throws SQLException {
    Scanner scanner = new Scanner(System.in);

    try (Connection conn = DriverManager.getConnection(dbURL, user, password)) {
        while (true) {
            System.out.println("Please choose the number of the room you would like to book:");
            int selectedRoom = scanner.nextInt();

            String sql = "SELECT isAvailable FROM rooms WHERE room_id = ?";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, selectedRoom);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        boolean isAvailable = rs.getBoolean("isAvailable");

                        if (isAvailable) {
                            System.out.println("Room is available!");
                            return selectedRoom;
                        } else {
                            System.out.println("This room is not available. Try again!");
                        }
                    } else {
                        System.out.println("This room does not exist. Try again!");
                    }
                }
            }
        }
    }
}

    
    /*public int selectRoom1() {
        Reservation newReservation = new Reservation();
        Scanner scanner = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(dbURL, user, password)) {

            boolean x = true;

            while (x) {
                System.out.println("Please choose the number of the room you would like to book:");
                int selectedRoom = scanner.nextInt();

                String sql = "SELECT isAvailable FROM rooms WHERE room_id = ?";

                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

                    preparedStatement.setInt(1, selectedRoom);

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {

                                    try (ResultSet rs = ps.executeQuery()) {
                                        if (!rs.next()) {
                                            System.out.println("This room does not exist. Try again!");
                                            continue;
                                        }

                                        if (rs.getBoolean("isAvailable")) {
                                            System.out.println("Room is available!");
                                            return selectedRoom;
                                        }

                        if (resultSet.next()) {
                            boolean isAvailable = resultSet.getBoolean("isAvailable");

                            if (isAvailable) {
                                System.out.println("Room is available");
                                x = false;
                                return selectedRoom;
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

    public String collectUserInfo(){
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("Please enter your full name:");
            String fullName = scanner.nextLine().trim();
            Pattern pattern = Pattern.compile("[a-zA-Z]+(?:[ '-][a-zA-Z]+)*");
            Matcher matcher = pattern.matcher(fullName);

            if(matcher.matches()){
                System.out.println("Name accepted!");
                return fullName;
            }else{
                System.out.println("The name entered in not valid. Try again!");
            }
        }
    }

    public void saveReservation(Connection conn, int roomNumber, String fullName) throws SQLException {
        LocalDate date = LocalDate.now();

        String updateSQL = "UPDATE rooms SET isAvailable = FALSE WHERE room_id = ? AND isAvailable =TRUE";

        String insertSQL = "INSERT INTO reservations (room_id, date, full_name) VALUES (?, ?, ?)";

        conn.setAutoCommit(false);

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

            try(PreparedStatement insertStatement = conn.prepareStatement(insertSQL)){
                insertStatement.setInt(1, roomNumber);
                insertStatement.setDate(2, java.sql.Date.valueOf(date));
                insertStatement.setString(3, fullName);

                int rowsInserted = insertStatement.executeUpdate();
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

        public void cancelReservation(){
            try(Connection conn = DriverManager.getConnection(dbURL, user, password)){
                Scanner scanner = new Scanner(System.in);

                System.out.println("Please enter your reservation ID:");
                int reservationID = scanner.nextInt();

                //conn.setAutoCommit(false);

                String selectSQL = "SELECT room_num FROM reservations WHERE reservation_id = ?";
                String deleteSQL = "DELETE FROM reservations WHERE reservation_id = ?";
                //String updateSQL = "UPDATE rooms JOIN reservations ON rooms.room_id = reservations.room_num SET rooms is_available = TRUE WHERE reservations.room_num = ? AND is_available = FALSE";
                String updateSQL = "UPDATE rooms SET is_available = TRUE WHERE room_id = ? AND is_available = FALSE";

                PreparedStatement ps1 = conn.prepareStatement(deleteSQL);
                ps1.setInt(1, reservationID);

                int rowDeleted = ps1.executeUpdate();

                if(rowDeleted > 0){
                    System.out.println("Your reservation has been cancelled!");

                    int roomNumber;

                    PreparedStatement ps2 = conn.prepareStatement(selectSQL);
                    ResultSet rs = ps2.executeQuery();

                    if(rs.next()){
                        
                    }

                    PreparedStatement ps3 = conn.prepareStatement(updateSQL);
                    ps3.setInt(1, roomNumber);
                }else{
                    System.out.println("Something went wrong!");
                }

            }catch(SQLException e){
                System.out.println("Something went wrong: " + e);
            }
    }

        public void showReservation(){
            try(Connection conn = DriverManager.getConnection(dbURL, user, password)){
                Scanner scanner = new Scanner(System.in);
                System.out.println("Please enter the ID number of your reservation:");
                int reservationID = scanner.nextInt();

                String selectSQL = "SELECT room_id, date, full_name from reservations WHERE reservation_id = ?";

                /*System.out.println("Please enter the room number:");
                int roomNumber = scanner.nextInt();
                System.out.println("Please enter your full name:");
                String fullName = scanner.nextLine().trim();
       
                String selectSQL = "SELECT from reservations WHERE room_id = ? AND full_name =?";*/

                PreparedStatement ps = conn.prepareStatement(selectSQL);
                ps.setInt(1, reservationID);
                ResultSet rs = ps.executeQuery();

                if(rs.next()){
                    int roomNumber = rs.getInt(1);
                    Date date = rs.getDate(2);
                    //String date = rs.getString(2);
                    String fullName = rs.getString(3);

                    String output = "Reservation info: \n\t reservation_id: %d \n\t room_id: %d \n\t date: %s \n\t full_name: %s";

                    System.out.println(String.format(output, reservationID, roomNumber, date, fullName));
                }else{
                    System.out.println("Reservation does not exist!");
                }

            }catch (SQLException e){
                System.out.println("Something went wrong: " + e);

            }      
    }

    public void makeReservation(){
        try(Connection conn = DriverManager.getConnection(dbURL, user, password)){

            int selectRoom = selectRoom();
            String customerName = collectUserInfo();

            saveReservation(conn, selectRoom, customerName);

        }catch(SQLException e){
            System.out.println("Reservation failed: " + e.getMessage());
        }
    }

    

   
}
