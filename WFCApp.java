import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
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
        } else{
            System.out.println("You already have an account registered");
        }
        this.setCurrentCustomer(customersManager.getSpecificCustomer(firstName, lastName));


    }

    public void manageBooking(Booking booking){
        System.out.println("Enter An Option: [1] Attend Booking, [2] Modify Booking, [3] Exit App:");
        int response = scanner.nextInt();
        if(response == 3){
            this.exitApp();
        }else if (response == 1){
            System.out.println("Thank You for attending this lesson. Please kindly drop a review:");
            System.out.println("Please Rate out of 5:\n[1] Very dissatisfied\n[2] Dissatisfied\n[3] Ok\n[4] Satisfied\n[5] Very Satisfied:");
            int rating = scanner.nextInt();
            System.out.println("Please enter your review:");
            Scanner stringScanner = new Scanner(System.in);
            String review = stringScanner.nextLine();
            Rating customerRating = new Rating(rating, review);
            bookingManager.attendBookedLesson(this.currentCustomer, booking, customerRating);
            System.out.println("Thank You for sharing your feedback\n");
        } else if(response == 2){
            System.out.println("Enter an Option:\n[1] Change Booking, [2] Cancel Booking, [3] Exit App:");
            int responseMod = scanner.nextInt();
            if(responseMod == 3){
                this.exitApp();
            }else if (responseMod == 1){
                System.out.println("Please select the lesson you want to change the booking to.");
                bookingManager.changeBookedLesson(this.currentCustomer, booking, booking.getLesson(), chooseLesson());
                System.out.println("Your Booking has been changed successfully.\n");
            } else if(responseMod == 2){
                bookingManager.cancelBooking(booking);
            }
        }
    }

    public void exitApp(){
        System.exit(0);
    }

    public Lesson chooseLesson(){
        /** Lists all available lessons and return selected lesson */

        System.out.println("Please select how you wan to view the available lessons.\nEnter an Option:[1] View by Day of the Week, [2] View by Fitness Activity:");
        int responseView = scanner.nextInt();
        ArrayList<Lesson> availableLessons = null;
        if (responseView == 1) {
            System.out.println("Enter an Option: [1] Saturday, [2] Sunday\n");
            responseView = scanner.nextInt();
            if(responseView == 1){
                availableLessons = calenderManager.getAvailableLessons("Saturday");
            } else if (responseView == 2){
                availableLessons = calenderManager.getAvailableLessons("Sunday");
            }

        } else if (responseView == 2){
            System.out.println("Enter an Option: [1] Yoga, [2] Spin, [3] Zumba, [4] Aquacise, [5] Exit App:");
            responseView = scanner.nextInt();
            if (responseView == 1){
                availableLessons = calenderManager.getAvailableLessons(calenderManager.getSpecificActivity("Yoga"));
            } else if (responseView == 2){
                availableLessons = calenderManager.getAvailableLessons(calenderManager.getSpecificActivity("Spin"));
        } else if (responseView == 3){
                availableLessons = calenderManager.getAvailableLessons(calenderManager.getSpecificActivity("Zumba"));
        } else if (responseView == 4) {
                availableLessons = calenderManager.getAvailableLessons(calenderManager.getSpecificActivity("Aquacise"));
            } else if(responseView == 5){
                this.exitApp();
            }

        }
        assert availableLessons != null;
        ArrayList<String> printableLessons = calenderManager.convertLessonArrayToDateStringsArray(availableLessons);
        do {
            System.out.println("Please select a Lesson");
            for (int i = 0; i < printableLessons.size(); i++){
                int num = i + 1;
                System.out.println("Enter [" + num + "] for: " + printableLessons.get(i));
            }
            responseView = scanner.nextInt();
            if(this.currentCustomer.currentBookedLessons().contains(availableLessons.get(responseView-1))){
                System.out.println("You already have this lesson booked");
            }
        } while(this.currentCustomer.currentBookedLessons().contains(availableLessons.get(responseView-1)));


        return availableLessons.get(responseView-1);
    }

    public void bookALesson(){
        String newBookingID = bookingManager.registerBooking(this.currentCustomer, chooseLesson());
        System.out.println("Your New Booking ID is '" + newBookingID + "', Please keep this as you would need it to manage your booking\n");
    }

    public static void main(String[] args) throws IOException {

        Scanner mainScanner = new Scanner(System.in);
        WFCApp wfcApp = new WFCApp();

        //________________________________________________Start_____________________________________________________//
        System.out.println("Are you a new user or a registered user?");
        System.out.println("Enter an Option: [1] New User, [2] Registered User, [3] Exit App:");
        int mainUserResponse = mainScanner.nextInt();
        if (mainUserResponse == 3){
            wfcApp.exitApp();
        } else if (mainUserResponse == 1){
            wfcApp.newUserRegister();
        }

        while(true){
        System.out.println("Would you like to book a new lesson or manage an already booked lesson");
        System.out.println("Enter an Option: [1] Book New Lesson, [2] Manage current Booking, [3] Exit App:");
        int customerResponse = mainScanner.nextInt();
        if (customerResponse == 1){
            wfcApp.bookALesson();
        }else if(customerResponse == 2) {
            Scanner mainStringScanner = new Scanner(System.in);
            String bookingID;
            do {
                System.out.println("Pleas Enter Your Booking ID or Enter 'Exit' to Exit the App:");
                bookingID = mainStringScanner.next();
                if(bookingID.toLowerCase().equals("exit")){
                    wfcApp.exitApp();
                } else if (!wfcApp.bookingManager.verifyBookingId(wfcApp.currentCustomer, bookingID)){
                    System.out.println("The Booking ID you entered is incorrect or This booking does not belong to this user.\nPlease enter a correct ID or type 'Exit' to exit the App:");
                }
            } while (!wfcApp.bookingManager.verifyBookingId(wfcApp.currentCustomer, bookingID));

            Booking currentBooking = wfcApp.bookingManager.getSpecificBooking(bookingID);
            wfcApp.manageBooking(currentBooking);
        } else if(customerResponse == 3){
            wfcApp.exitApp();
        }





    }
    }
}
