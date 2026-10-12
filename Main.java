import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int choice = 0;
   
    System.out.println("Welcome to our hotel!");
    System.out.println("______________________");
    //System.out.println("How can we help you today?");
    System.out.println("What would you like to do?");
    //printOptions();
   
    //choice = scanner.nextInt();
    //scanner.nextLine();
   
    switch(choice) {
        case 0:
            printOptions();
            break;
        case 1:
            //makeReservation();
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
