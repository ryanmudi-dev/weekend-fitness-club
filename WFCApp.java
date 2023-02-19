import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class WFCApp implements Serializable {
    Scanner scanner = new Scanner(System.in);
    CalenderManager calenderManager = new CalenderManager();
    BookingManager bookingManager = new BookingManager();
    CustomersManager customersManager = new CustomersManager();
    private Customer currentCustomer = null;

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }

    public WFCApp() throws IOException {
    }

    public void newUserRegister(){
        System.out.println("Enter First Name");
        String firstName = scanner.next();
        System.out.println("Enter Last Name");
        String lastName = scanner.next();
        boolean registrationSuccessful = customersManager.registerNewCustomer(firstName, lastName);
        if (registrationSuccessful){
            System.out.println("Customer data created successfully");
            this.setCurrentCustomer(customersManager.getSpecificCustomer(firstName, lastName));
        } else{
            System.out.println("User already exist");
        }


    }

    public void manageBooking(Booking booking){
        System.out.println("Choose Option:\n[1] Attend Booking\n[2] Modify Booking\n[3] Exit");
        int response = scanner.nextInt();
        if (response == 1){
            System.out.println("Thank You for attending this lesson. Please kindly drop a review:");
            System.out.println("Choose an Option\n1: Very dissatisfied\n2: Dissatisfied\n3: Ok\n4: Satisfied\n5: Very Satisfied");
            int rating = scanner.nextInt();
            System.out.println("Please enter your review:\n");
            Scanner stringScanner = new Scanner(System.in);
            String review = stringScanner.nextLine();
            Rating customerRating = new Rating(rating, review);
            bookingManager.attendBookedLesson(this.currentCustomer, booking, customerRating);
        } else if(response == 2){
            System.out.println("Choose Option:\n[1] Change Booking\n[2] Cancel Booking\n[3] Exit");
            int responseMod = scanner.nextInt();
            if (responseMod == 1)
        }
    }

    public void exitApp(){
        System.exit(0);
    }


    public static void main(String[] args) throws IOException {

        Scanner mainScanner = new Scanner(System.in);

        //________________________________________________Start_____________________________________________________//
        System.out.println("Are you a new user or a returning user? Enter '1' for new user or '2' for return user: ");
        if (mainScanner.nextInt() == 1){

        }





    }
}
