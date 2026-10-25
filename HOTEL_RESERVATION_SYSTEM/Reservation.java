package HOTEL_RESERVATION_SYSTEM;
import java.time.LocalDate;

public class Reservation {
    private int roomNumber;
    private LocalDate date;
    private int userID;
    private int reservationID;
    private String fullName;

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    } 

    public LocalDate getDate() {
        return date;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }
    
    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public int getReservationID() {
        return reservationID;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
   

}
