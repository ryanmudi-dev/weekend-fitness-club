import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WFCApp implements Serializable {
    transient Scanner scanner = new Scanner(System.in);
    CalenderManager calenderManager = new CalenderManager();
    BookingManager bookingManager = new BookingManager();
    CustomersManager customersManager = new CustomersManager();
    private Customer currentCustomer = null;
    private final static String filePath = "Serialization/appState.dat";

    public Customer getCurrentCustomer() {
        return this.currentCustomer;
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
        System.out.println("Enter Email Address");
        String emailAddress = scanner.next();
        boolean registrationSuccessful = this.customersManager.registerNewCustomer(firstName, lastName, emailAddress);
        if (registrationSuccessful){
            System.out.println("Customer data created successfully");
        } else{
            System.out.println("You already have an account registered");
        }
        this.setCurrentCustomer(this.customersManager.getSpecificCustomer(emailAddress));
        System.out.println("Welcome " + this.currentCustomer.getCustomerName());


    }

    public void manageBooking(Booking booking) throws IOException {
        System.out.println("Enter An Option:\n[1] Attend Booking\n[2] Modify Booking\n[0] Exit App");
        int response = scanner.nextInt();
        if(response == 0){
            this.exitApp();
        }else if (response == 1){
            System.out.println("Thank You for attending this lesson. Please kindly drop a review:");
            System.out.println("Please Rate out of 5:\n[1] Very dissatisfied\n[2] Dissatisfied\n[3] Ok\n[4] Satisfied\n[5] Very Satisfied:");
            int rating = scanner.nextInt();
            System.out.println("Please enter your review:");
            Scanner stringScanner = new Scanner(System.in);
            String review = stringScanner.nextLine();
            Rating customerRating = new Rating(rating, review);
            this.bookingManager.attendBookedLesson(this.currentCustomer, booking, customerRating);
            System.out.println("Thank You for sharing your feedback\n");
        } else if(response == 2){
            System.out.println("Enter an Option:\n[1] Change Booking\n[2] Cancel Booking\n[0] Exit App");
            int responseMod = scanner.nextInt();
            if(responseMod == 0){
                this.exitApp();
            }else if (responseMod == 1){
                System.out.println("Please select the lesson you want to change the booking to.");
                this.bookingManager.changeBookedLesson(this.currentCustomer, booking, booking.getLesson(), chooseLesson());
                System.out.println("Your Booking has been changed successfully.\n");
            } else if(responseMod == 2){
                this.bookingManager.cancelBooking(booking);
            }
        }
    }


    public Lesson chooseLesson() throws IOException {
        /** Lists all available lessons and return selected lesson */

        System.out.println("Please select how you want to view the available lessons.\nEnter an Option\n[1] View by Day of the Week\n[2] View by Fitness Activity");
        int responseView = scanner.nextInt();
        ArrayList<Lesson> availableLessons = null;
        if (responseView == 1) {
            System.out.println("Enter an Option:\n[1] Saturday\n[2] Sunday");
            responseView = scanner.nextInt();
            if(responseView == 1){
                availableLessons = this.calenderManager.getAvailableLessons("Saturday");
            } else if (responseView == 2){
                availableLessons = this.calenderManager.getAvailableLessons("Sunday");
            }

        } else if (responseView == 2){
            System.out.println("Enter an Option:\n[1] Yoga\n[2] Spin\n[3] Zumba\n[4] Aquacise\n[0] Exit App");
            responseView = scanner.nextInt();
            if (responseView == 1){
                availableLessons = this.calenderManager.getAvailableLessons(this.calenderManager.getSpecificActivity("Yoga"));
            } else if (responseView == 2){
                availableLessons = this.calenderManager.getAvailableLessons(this.calenderManager.getSpecificActivity("Spin"));
        } else if (responseView == 3){
                availableLessons = this.calenderManager.getAvailableLessons(this.calenderManager.getSpecificActivity("Zumba"));
        } else if (responseView == 4) {
                availableLessons = this.calenderManager.getAvailableLessons(this.calenderManager.getSpecificActivity("Aquacise"));
            } else if(responseView == 0){
                this.exitApp();
            }

        }
        assert availableLessons != null;
        ArrayList<String> printableLessons = this.calenderManager.convertLessonArrayToDateStringsArray(availableLessons);
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

    public void bookALesson() throws IOException {
        String newBookingID = this.bookingManager.registerBooking(this.currentCustomer, chooseLesson());
        System.out.println("Your New Booking ID is '" + newBookingID + "', Please keep this as you would need it to manage your booking\n");
    }

    public void saveWfcAppState() throws IOException{
        try{
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public void signInRegisteredUser(){
        Customer returningCustomer;
        do {
            System.out.println("Enter Your Email");
            String emailAddress = scanner.next();
            returningCustomer = this.customersManager.getSpecificCustomer(emailAddress);
        } while(returningCustomer == null);

        this.setCurrentCustomer(returningCustomer);
        System.out.println("Welcome back " + this.getCurrentCustomer().getCustomerName());

    }
//
    public void signOutCurrentUser(){
        this.setCurrentCustomer(null);
    }

    public void exitApp() throws IOException {
        if (this.getCurrentCustomer() != null){
            System.out.println("Thank you " + this.getCurrentCustomer().getCustomerName() + ", and hope you will be back soon");
            this.signOutCurrentUser();
        } else{
            System.out.println("Thank you, and hope you will be back soon");
        }
        this.saveWfcAppState();
        System.exit(0);
    }

    public static void main(String[] args) throws IOException {
        Scanner mainScanner = new Scanner(System.in);
        WFCApp wfcApp = new WFCApp();
        try{
             FileInputStream fis = new FileInputStream(filePath);
             ObjectInputStream ois = new ObjectInputStream(fis);

            wfcApp = (WFCApp) ois.readObject();
            ois.close();
            fis.close();
        } catch(FileNotFoundException | ClassNotFoundException e) {
            System.out.println(e);
        } finally {
            wfcApp.scanner = new Scanner(System.in);
        }

        //________________________________________________Start_____________________________________________________//
        System.out.println("Are you a new user or a registered user?");
        System.out.println("Enter an Option:\n[1] New User\n[2] Registered User\n[0] Exit App");
        int mainUserResponse = mainScanner.nextInt();
        if (mainUserResponse == 0){
            wfcApp.exitApp();
        } else if (mainUserResponse == 1){
            wfcApp.newUserRegister();
        } else if(mainUserResponse == 2){
            wfcApp.signInRegisteredUser();
        }

        while(true){
        System.out.println("Would you like to book a new lesson or manage an already booked lesson?");
        System.out.println("Enter an Option:\n[1] Book New Lesson\n[2] Manage current Booking\n[0] Exit App");
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
                } else if (!wfcApp.bookingManager.verifyBookingId(wfcApp.getCurrentCustomer(), bookingID.toLowerCase())){
                    System.out.println("The Booking ID you entered is incorrect or This booking does not belong to this user.\nPlease enter a correct ID or type 'Exit' to exit the App:");
                }
            } while (!wfcApp.bookingManager.verifyBookingId(wfcApp.getCurrentCustomer(), bookingID));

            Booking currentBooking = wfcApp.bookingManager.getSpecificBooking(bookingID);
            wfcApp.manageBooking(currentBooking);
        } else if(customerResponse == 0){
            wfcApp.exitApp();
        }





    }

    }




}
