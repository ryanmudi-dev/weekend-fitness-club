import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

interface executeFunction {
    boolean execute(String str);
}

public class AppManagerDemo implements Serializable {
    transient Scanner scanner = new Scanner(System.in);
    CalenderManager calenderManager = new CalenderManager();
    BookingManager bookingManager = new BookingManager();
    CustomersManager customersManager = new CustomersManager();
    ReportManager reportManager = new ReportManager(calenderManager);
    private Customer currentCustomer = null;
    private final static String filePath = "Serialization/appState.dat";

    public Customer getCurrentCustomer() {
        return this.currentCustomer;
    }

    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }

    public AppManagerDemo() throws IOException {
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
            boolean continueManage = true;
            do{
                System.out.println("Enter an Option:\n[1] Change Booking\n[2] Cancel Booking\n[-1] Go Back\n[0] Exit App");
                int responseMod = scanner.nextInt();
                if(responseMod == 0){
                    this.exitApp();
                }else if(responseMod == -1){
                    continueManage = false;
                    this.manageBooking(booking);

                }else if (responseMod == 1){
                    System.out.println("Please select the lesson you want to change the booking to.");
                    System.out.println("Please select how you want to view the available lessons.\nEnter an Option\n[1] View by Day of the Week\n[2] View by Fitness Activity\n[-1] Go Back");
                    int responseView = scanner.nextInt();
                    if (responseView != -1){
                        continueManage = false;
                        this.bookingManager.changeBookedLesson(this.currentCustomer, booking, booking.getLesson(), chooseLesson(responseView, false));
                        System.out.println("Your Booking has been changed successfully.\n");
                    }

                } else if(responseMod == 2){
                    this.bookingManager.cancelBooking(booking);
                    System.out.println("Your Booking has been cancelled successfully!\n");
                    continueManage = false;
                }
            } while(continueManage);

        }
    }


    public Lesson chooseLesson(int responseView, boolean calledFromWithin) throws IOException {
        /** Lists all available lessons and return selected lesson given a response*/
        if (calledFromWithin){
            System.out.println("Please select how you want to view the available lessons.\nEnter an Option\n[1] View by Day of the Week\n[2] View by Fitness Activity\n[-1] Go Back");
            responseView = scanner.nextInt();
        }


//        if (responseView == -1){
//
//        }
        int temp = responseView + 0;
        ArrayList<Lesson> availableLessons = null;
        if (responseView == 1) {
            System.out.println("Enter an Option:\n[1] Saturday\n[2] Sunday\n[-1] Go Back\n[0] Exit App");
            responseView = scanner.nextInt();

            if(responseView == -1){
                return this.chooseLesson(temp, true);
            }else if(responseView == 0){
                this.exitApp();
            } else if(responseView == 1){
                availableLessons = this.calenderManager.getAvailableLessons("Saturday");
            } else if (responseView == 2){
                availableLessons = this.calenderManager.getAvailableLessons("Sunday");
            }

        } else if (responseView == 2){
            System.out.println("Enter an Option:\n[1] Yoga\n[2] Spin\n[3] Zumba\n[4] Aquacise\n[-1] Go Back\n[0] Exit App");
            responseView = scanner.nextInt();
            if(responseView == -1){
                return this.chooseLesson(temp, true);
            }else if (responseView == 1){
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
                if(num < 10){
                    System.out.println("Enter [" + num + "]  for: " + printableLessons.get(i));
                } else{
                    System.out.println("Enter [" + num + "] for: " + printableLessons.get(i));
                }

            }
            responseView = scanner.nextInt();
            if(this.currentCustomer.currentBookedLessons().contains(availableLessons.get(responseView-1))){
                System.out.println("You already have this lesson booked");
            }
        } while(this.currentCustomer.currentBookedLessons().contains(availableLessons.get(responseView-1)));


        return availableLessons.get(responseView-1);
    }

    public void bookALesson(int customerResponse) throws IOException {
        String newBookingID = this.bookingManager.registerBooking(this.currentCustomer, this.chooseLesson(customerResponse, false));
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

    public void signInRegisteredUser() throws IOException {
        Customer returningCustomer;
        do {
            System.out.println("Enter Your Email");
            String emailAddress = scanner.next();
            returningCustomer = this.customersManager.getSpecificCustomer(emailAddress);
            if(emailAddress.toLowerCase().equals("exit")){
                this.exitApp();
            } else if(returningCustomer == null){
                System.out.println("The Email you entered in Incorrect, Please enter the correct email or Type 'Exit' to exit the App");
            }
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


    public void fullAppLogic() throws IOException {
        Scanner mainScanner = new Scanner(System.in);
        System.out.println("Enter an Option:\n[1] Sign Up\n[2] Sign In\n[3] Generated Monthly Lesson Report\n[4] Generate Monthly Champion Fitness Type Report\n[0] Exit App");
        int mainUserResponse = mainScanner.nextInt();
        if (mainUserResponse == 0) {
            this.exitApp();

        }
        if (mainUserResponse == 3 || mainUserResponse == 4){
            boolean tempcontinue = true;
            int tempSave = mainUserResponse + 0;
            do {
                if (tempSave == 3){
                    System.out.println("Please enter the month you want a report generated for or enter [-1] to go back");
                } else{
                    System.out.println("Please enter the month you want the Champion Report generated for or enter [-1] to go back");
                }
                for (String month : calenderManager.lessonByMonthToString()) {
                    System.out.println("Enter " + month);
                }
                mainUserResponse = mainScanner.nextInt();
                if (mainUserResponse == -1) {
                    this.fullAppLogic();
                }
                if (tempSave == 3) {
                    String report = reportManager.getMonthlyLessonReport(mainUserResponse);
                    if(report != null){
                        System.out.println("\n-----------------MONTHLY REPORT-----------------\n");
                        System.out.println(report);
                        tempcontinue = false;
                    }

                } else {
                    String report = reportManager.getMonthlyChampionFitnessReport(mainUserResponse);
                    if (report != null){
                        System.out.println("\n--------------------------MONTHLY CHAMPION REPORT--------------------------\n");
                        System.out.println(report);
                        tempcontinue = false;
                    }

                }
                if (tempcontinue){
                    System.out.println("The month you entered is not available on the calender, please enter one of the months listed.");
                }

            } while (tempcontinue);
            System.out.println();
            fullAppLogic();

        } else {
            if (mainUserResponse == 1) {
            this.newUserRegister();
            } else if (mainUserResponse == 2) {
            System.out.println("Enter an Option:\n[1] Sign In\n[-1] Go Back\n[0] Exit App");
            int userResponse = mainScanner.nextInt();
            if (userResponse == 0) {
                this.exitApp();
            } else if (userResponse == -1) {
                this.fullAppLogic();
            } else if (userResponse == 1) {
                this.signInRegisteredUser();
            }
            }
            while (true) {
            if (this.getCurrentCustomer().getCurrentBookedLessons().size() == 0){
                System.out.println("Enter an Option:\n[1] Book New Lesson\n[-1] Sign Out\n[0] Exit App");
            } else{
                System.out.println("Enter an Option:\n[1] Book New Lesson\n[2] Manage current Booking\n[-1] Sign Out\n[0] Exit App");
            }
            int customerResponse = mainScanner.nextInt();
            if (customerResponse == 0) {
                this.exitApp();

            } else if (customerResponse == -1) {
                this.setCurrentCustomer(null);
                this.fullAppLogic();
            }

            this.appLogicExtension(customerResponse);
        }
    }
    }

    public void appLogicExtension(int customerResponse) throws IOException {

            if (customerResponse == 1) {
                System.out.println("Please select how you want to view the available lessons.\nEnter an Option\n[1] View by Day of the Week\n[2] View by Fitness Activity\n[-1] Go Back");
                int responseView = scanner.nextInt();
                if (responseView == -1){
                    return;
                }

                this.bookALesson(responseView);
            } else if (customerResponse == 2) {
                Scanner mainStringScanner = new Scanner(System.in);
                String bookingID;
                do {
                    ArrayList<String> currentBookingsString = this.getCurrentCustomer().currentBookingsToString();
                    System.out.println("Here are your current bookings:");
                    for (String currentBooking : currentBookingsString){
                        System.out.println(currentBooking);
                    }
                    System.out.println();
                    System.out.println("Please Enter the Booking ID you want to manage or Type 'Exit' to Exit the App:");
                    bookingID = mainStringScanner.next();
                    if (bookingID.equalsIgnoreCase("exit")) {
                        this.exitApp();
                    } else if (!this.bookingManager.verifyBookingId(this.getCurrentCustomer(), bookingID.toLowerCase())) {
                        System.out.println("The Booking ID you entered is incorrect or This booking does not belong to this user.\nPlease enter a correct ID or type 'Exit' to exit the App:");
                    }
                } while (!this.bookingManager.verifyBookingId(this.getCurrentCustomer(), bookingID));

                Booking currentBooking = this.bookingManager.getSpecificBooking(bookingID);
                this.manageBooking(currentBooking);
            }

    }




}
