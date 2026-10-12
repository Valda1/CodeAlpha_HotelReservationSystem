public class Room {
    private int roomNumber;
    private Boolean available;
    private String category;
    private Float price;

    // The Constructor
    public Room(int roomNumber, Boolean available, String category, Float price) {
        this.roomNumber = roomNumber;
        this.available = available;
        this.category = category;
        this.price = price;
    }
   
    public void checkAvailability() {
        String sql = "SELECT isAvailable FROM rooms;";
    }
}
