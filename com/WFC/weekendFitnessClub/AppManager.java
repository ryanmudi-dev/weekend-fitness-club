package com.WFC.weekendFitnessClub;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**

 The AppManager class is responsible for managing the overall application.
 It interacts with other manager classes such as CalendarManager, BookingManager,
 CustomersManager and ReportManager to perform various tasks.
 It also manages the current user/customer information and provides user interaction for
 registering new users, signing in returning users, registering new booking, managing current bookings and generating reports.
 The class manages all User Interactions
 */

public class AppManager implements Serializable {
    transient Scanner scanner = new Scanner(System.in);
    CalendarManager calendarManager = new CalendarManager();
    BookingManager bookingManager = new BookingManager();
    CustomersManager customersManager = new CustomersManager();
    ReportManager reportManager = new ReportManager(calendarManager);
    private Customer currentCustomer = null;
    private final static String filePath = "com/WFC/weekendFitnessClub/Serialization/appState.dat";


    public AppManager() throws IOException {
    }


    /**

     Setter method to set the scanner object
     @param scanner Scanner object to set
     */
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    /**

     Getter method to get the current customer
     @return Customer object representing the current user
     */
    public Customer getCurrentCustomer() {
        return this.currentCustomer;
    }
    /**

     Setter method to set the current customer
     @param currentCustomer Customer object representing the current user to set
     */
    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }


    /**

     Method to register a new user. It prompts the user for their first name, last name, and email address.
     It validates the email address and creates a new customer account if the email is valid and the user
     does not already have an account. Otherwise, it informs the user that they already have an account registered.
     It sets the current customer to the newly registered customer.
     */
    public void newUserRegister(){
        System.out.println("Enter First Name");
        String firstName = scanner.next();
        System.out.println("Enter Last Name");
        String lastName = scanner.next();
        boolean invalidEmail = true;
        String emailAddress;
        do{
            System.out.println("Enter Email Address");
            emailAddress = scanner.next();
            if (emailAddress.contains("@") && emailAddress.contains(".")){
                invalidEmail = false;
            } else {
                System.out.println("The Email Address you entered is invalid, Please  enter a valid Email Address");
            }

        } while(invalidEmail);

        boolean registrationSuccessful = this.customersManager.registerNewCustomer(firstName, lastName, emailAddress);
        if (registrationSuccessful){
            System.out.println("Customer data created successfully");
        } else{
            System.out.println("You already have an account registered");
        }
        this.setCurrentCustomer(this.customersManager.getSpecificCustomer(emailAddress));
        System.out.println("Welcome " + this.currentCustomer.getCustomerName());


    }



    /**

     This method allows the user to manage a specific booking by attending, modifying, or canceling it.
     @param booking the Booking object to be managed
     @throws IOException If an I/O error occurs
     */
    public void manageBooking(Booking booking) throws IOException {
        System.out.println("Enter An Option:\n[1] Attend Booking\n[2] Modify Booking\n[0] Exit App");
        int response = 100;
        try {
            response = scanner.nextInt();
            if(response == 0){
                this.exitApp();
            }else if (response == 1){
                System.out.println("Thank You for attending this lesson. Please kindly drop a review:");
                int rating = 100;
                do{
                    System.out.println("Please Rate out of 5:\n[1] Very dissatisfied\n[2] Dissatisfied\n[3] Ok\n[4] Satisfied\n[5] Very Satisfied:");
                    try{
                        rating = scanner.nextInt();
                    } catch (Exception e){
                        scanner.next();
                    }


                    if(rating < 0 || rating > 5){
                        System.out.println("Invalid Input, Please enter a number between 1 and 5\n");
                        scanner.next();
                    }
                } while(rating < 0 || rating > 5);

                System.out.println("Please enter your review:");
                Scanner stringScanner = new Scanner(System.in);
                String review = stringScanner.nextLine();
                Rating customerRating = new Rating(rating, review);
                this.bookingManager.attendBookedLesson(booking, customerRating);
                System.out.println("Thank You for sharing your feedback\n");
            } else if(response == 2){
                boolean continueManage = true;
                do{

                    int responseMod = 100;
                    do{
                        System.out.println("Enter an Option:\n[1] Change Booking\n[2] Cancel Booking\n[-1] Go Back\n[0] Exit App");
                        try{
                            responseMod = scanner.nextInt();
                        } catch (Exception e) {
                            scanner.next();
                        }
                        if(responseMod < -1 || responseMod > 2){
                            System.out.println("Invalid Input. Please Enter a valid number from the option listed\n");
                        }
                    } while (responseMod < -1 || responseMod > 2);




                    if(responseMod == 0){
                        this.exitApp();
                    }else if(responseMod == -1){
                        continueManage = false;
                        this.manageBooking(booking);

                    }else if (responseMod == 1){

                        int responseView = 100;
                        do{
                            System.out.println("Please select the lesson you want to change the booking to.");
                            System.out.println("Please select how you want to view the available lessons.\nEnter an Option\n[1] View by Day of the Week\n[2] View by Fitness Activity\n[-1] Go Back\n[0] Exit App");
                            try{
                                responseView = scanner.nextInt();
                            } catch (Exception e){
                                scanner.next();
                            }
                            if(responseView < -1 || responseView > 2){
                                System.out.println("Invalid Input. Please Enter a valid number from the option listed\n");
                            }
                        } while (responseView < -1 || responseView > 2);

                        if(responseView == 0){
                            this.exitApp();
                        }else if (responseView != -1){
                            continueManage = false;
                            this.bookingManager.changeBookedLesson(this.currentCustomer, booking, booking.getLesson(), chooseLesson(responseView, false));
                            System.out.println("Your Booking has been changed successfully.\n");
                        }

                    } else {
                        this.bookingManager.cancelBooking(booking);
                        System.out.println("Your Booking has been cancelled successfully!\n");
                        continueManage = false;
                    }
                } while(continueManage);

            } else{
                System.out.println("You entered a number that is not on the options\nPlease enter a valid option.");
                this.manageBooking(booking);
            }
        } catch (InputMismatchException e){
            System.out.println("Invalid Input. Please Enter a valid number from the option listed\n");
            scanner.next();
            this.manageBooking(booking);
        }

    }



    /**

     Lists all available lessons and returns the selected lesson based on the user's input.
     @param responseView the user's response to the view options (1 for view by day of the week, 2 for view by fitness activity)
     @param calledFromWithin a boolean indicating whether the method was called from within itself
     @return the selected lesson
     @throws IOException if an I/O error occurs
     */
    public Lesson chooseLesson(int responseView, boolean calledFromWithin) throws IOException {
        // If calledFromWithin is true, prompt the user to select a view option
        if (calledFromWithin){
            System.out.println("Please select how you want to view the available lessons.\nEnter an Option\n[1] View by Day of the Week\n[2] View by Fitness Activity\n[-1] Go Back");
            responseView = scanner.nextInt();
        }


        int temp = responseView + 0;
        ArrayList<Lesson> availableLessons = null;
        if (responseView == 1) {

            responseView = 100;
            do{
                System.out.println("Enter an Option:\n[1] Saturday\n[2] Sunday\n[-1] Go Back\n[0] Exit App");
                try{
                    responseView = scanner.nextInt();
                } catch (Exception e){
                    scanner.next();
                }
                if(responseView < -1 || responseView > 2){
                    System.out.println("Invalid Input. Please Enter a valid number from the option listed\n");
                }
            } while (responseView < -1 || responseView > 2);


            if(responseView == -1){
                return this.chooseLesson(temp, true);
            }else if(responseView == 0){
                this.exitApp();
            } else if(responseView == 1){
                availableLessons = this.calendarManager.getAvailableLessons("Saturday");
            } else if (responseView == 2){
                availableLessons = this.calendarManager.getAvailableLessons("Sunday");
            }

        } else if (responseView == 2){

            responseView = 100;
            do{
                System.out.println("Enter an Option:\n[1] Yoga\n[2] Spin\n[3] Zumba\n[4] Aquacise\n[-1] Go Back\n[0] Exit App");
                try{
                    responseView = scanner.nextInt();
                } catch (Exception e){
                    scanner.next();
                }
                if(responseView < -1 || responseView > 4){
                    System.out.println("Invalid Input. Please Enter a valid number from the option listed\n");
                }
            } while (responseView < -1 || responseView > 4);


            if(responseView == -1){
                return this.chooseLesson(temp, true);
            }else if (responseView == 1){
                availableLessons = this.calendarManager.getAvailableLessons(this.calendarManager.getSpecificActivity("Yoga"));
            } else if (responseView == 2){
                availableLessons = this.calendarManager.getAvailableLessons(this.calendarManager.getSpecificActivity("Spin"));
            } else if (responseView == 3){
                availableLessons = this.calendarManager.getAvailableLessons(this.calendarManager.getSpecificActivity("Zumba"));
            } else if (responseView == 4) {
                availableLessons = this.calendarManager.getAvailableLessons(this.calendarManager.getSpecificActivity("Aquacise"));
            } else if(responseView == 0){
                this.exitApp();
            }

        }
        assert availableLessons != null;
        ArrayList<String> printableLessons = this.calendarManager.convertLessonArrayToDateStringsArray(availableLessons);
        do {

            responseView = 100;
            int LessonsLength = printableLessons.size();
            do{
                System.out.println("Please select a Lesson");
                for (int i = 0; i < printableLessons.size(); i++){
                    int num = i + 1;
                    if(num < 10){
                        System.out.println("Enter [" + num + "]  for: " + printableLessons.get(i));
                    } else{
                        System.out.println("Enter [" + num + "] for: " + printableLessons.get(i));
                    }

                }
                try{
                    responseView = scanner.nextInt();
                } catch (Exception e){
                    scanner.next();
                }
                if(responseView < 1 || responseView > LessonsLength){
                    System.out.println("Invalid Input. Please Enter a valid number from the option listed\n");
                }
            } while (responseView < 1 || responseView > LessonsLength);

            if(this.currentCustomer.getCurrentBookedLessons().contains(availableLessons.get(responseView-1))){
                System.out.println("You already have this lesson booked");
            }
        } while(this.currentCustomer.getCurrentBookedLessons().contains(availableLessons.get(responseView-1)));


        return availableLessons.get(responseView-1);
    }


    /**

     Allows the current customer to book a lesson by registering a new booking in the booking manager.
     Prints out the new booking ID once the lesson is successfully booked.
     @param customerResponse the response received from the customer in choosing a lesson.
     @throws IOException if an I/O error occurs.
     */

    public void bookALesson(int customerResponse) throws IOException {
        String newBookingID = this.bookingManager.registerBooking(this.currentCustomer, this.chooseLesson(customerResponse, false));
        System.out.println("Lesson Booked Successfully.\nYour New Booking ID is '" + newBookingID + "'\n");
        newBookingID = null;
    }


    /**

     Saves the current state of the WFC app to a file specified by the filePath field.
     Uses object serialization to save the current state.
     @throws IOException If an I/O error occurs while writing to the file.
     */
    public void saveWfcAppState() throws IOException{
        try{
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }


    /**

     Prompts the user to enter their email address and signs in the corresponding registered user.
     If the user enters an invalid email address, they will be prompted to enter a valid one.
     If the user types "Exit", the app will exit.
     If the email address entered is not associated with any registered user, the user will be prompted to enter a valid email address.
     Upon successful sign-in, sets the current customer to the returning customer and prints a welcome message.
     @throws IOException If an I/O error occurs while getting customer information or exiting the app.
     */
    public void signInRegisteredUser() throws IOException {
        Customer returningCustomer;
        do {
            boolean invalidEmail = true;
            String emailAddress;
            do{
                System.out.println("Enter Email Address or Type 'Exit' to exit the App.");
                emailAddress = scanner.next();
                if ((emailAddress.contains("@") && emailAddress.contains(".")) || emailAddress.equalsIgnoreCase("exit")){
                    invalidEmail = false;
                } else {
                    System.out.println("The Email Address you entered is invalid, Please  enter a valid Email Address");
                }

            } while(invalidEmail);
            returningCustomer = this.customersManager.getSpecificCustomer(emailAddress);
            if(emailAddress.equalsIgnoreCase("exit")){
                this.exitApp();
            } else if(returningCustomer == null){
                System.out.println("The Email you entered is Incorrect, Please enter the correct email or Type 'Exit' to exit the App");
            }
        } while(returningCustomer == null);

        this.setCurrentCustomer(returningCustomer);
        System.out.println("Welcome back " + this.getCurrentCustomer().getCustomerName());

    }


    /**

     Signs out the current user by setting the current customer to null.
     */
    public void signOutCurrentUser() {
        this.setCurrentCustomer(null);
    }


    /**
     * Exits the application after saving the current state.
     *
     * @throws IOException if there is an error while saving the state
     */
    public void exitApp() throws IOException {
        if (this.getCurrentCustomer() != null) {
            System.out.println("Thank you " + this.getCurrentCustomer().getCustomerName() + ", and hope you will be back soon");
            this.signOutCurrentUser();
        } else {
            System.out.println("Thank you, and hope you will be back soon");
        }
        this.saveWfcAppState();
        System.exit(0);
    }



    /**

     This method represents the full application logic of the Weekend Fitness Club application.
     It displays a menu to the user with options to sign up, sign in, generate monthly lesson report, generate monthly champion fitness type report, book new lesson, manage current booking, sign out or exit the app.
     It takes user input from the console and calls the appropriate methods based on the user's selection.
     If the user inputs invalid input, the method will prompt the user to input a valid input.
     If the user selects to generate a report, the method will prompt the user to enter the month they want a report for and generate the report using the report manager.
     If the user selects to book a new lesson or manage their current booking, the method will call the appLogicExtension method to handle the booking.
     If the user selects to sign out, the method sets the current customer to null and calls the fullApp method to display the menu again.
     If the user selects to exit the app, the method calls the exitApp method.
     @throws IOException If there is an error with the input/output of the program.
     */
    public void startWFCApp() throws IOException {
        Scanner mainScanner = new Scanner(System.in);

        System.out.println("-----------------Weekend Fitness Club-----------------\n");


        int mainUserResponse = 100;
        do{
            System.out.println("Enter an Option:\n[1] Sign Up\n[2] Sign In\n[3] Generated Monthly Lesson Report\n[4] Generate Monthly Champion Fitness Type Report\n[0] Exit App");
            try{
                mainUserResponse = mainScanner.nextInt();
            } catch (Exception e){
                mainScanner.next();
            }
            if(mainUserResponse < 0 || mainUserResponse > 4){
                System.out.println("Invalid Input. Please Enter a valid number from the option listed\n");
            }
        } while (mainUserResponse < 0 || mainUserResponse > 4);

        if (mainUserResponse == 0) {
            this.exitApp();

        }
        if (mainUserResponse == 3 || mainUserResponse == 4){
            boolean tempcontinue = true;
            int tempSave = Integer.valueOf(mainUserResponse);
            do {


                mainUserResponse = 100;
                do{
                    if (tempSave == 3){
                        System.out.println("Please enter the month you want a report generated for (E.g. '3' for March) or enter [-1] to go back");
                    } else{
                        System.out.println("Please enter the month you want the Champion Report generated for (E.g. '3' for March) or enter [-1] to go back");
                    }
                    for (String month : calendarManager.lessonByMonthToString()) {
                        System.out.println("Enter " + month);
                    }

                    try{
                        mainUserResponse = mainScanner.nextInt();
                    } catch (Exception e){
                        mainScanner.next();
                    }
                    if((mainUserResponse < 1 || mainUserResponse > 12) && mainUserResponse != -1){
                        System.out.println("Invalid Input. Please Enter a valid number from the option listed\n");
                    }
                } while ((mainUserResponse < 1 || mainUserResponse > 12) && mainUserResponse != -1);

                if (mainUserResponse == -1) {
                    this.startWFCApp();
                }
                if (tempSave == 3) {
                    String report = reportManager.getMonthlyLessonReport(mainUserResponse);
                    if(report != null){
                        System.out.println("\n-----------------MONTHLY REPORT-----------------\n");
                        System.out.println(report);
                        tempcontinue = false;
                        report = null;
                    }

                } else {
                    String report = reportManager.getMonthlyChampionFitnessReport(mainUserResponse);
                    if (report != null){
                        System.out.println("\n--------------------------MONTHLY CHAMPION REPORT--------------------------\n");
                        System.out.println(report);
                        tempcontinue = false;
                        report = null;
                    }

                }
                if (tempcontinue){
                    System.out.println("The month you entered is not available on the calendar, please enter one of the months listed.");
                }

            } while (tempcontinue);
            System.out.println();
            startWFCApp();

        } else {
            if (mainUserResponse == 1) {
            this.newUserRegister();
            } else if (mainUserResponse == 2) {
                this.signInRegisteredUser();
            }
            while (true) {
                int temp = 2;

            int customerResponse = 100;

                do{
                    if (!this.getCurrentCustomer().hasBooking()){
                        System.out.println("Enter an Option:\n[1] Book New Lesson\n[-1] Sign Out\n[0] Exit App");
                        temp = 1;
                    } else{
                        System.out.println("Enter an Option:\n[1] Book New Lesson\n[2] Manage current Booking\n[-1] Sign Out\n[0] Exit App");
                    }

                    try{
                        customerResponse = mainScanner.nextInt();
                    } catch (Exception e){
                        mainScanner.next();
                    }
                    if(customerResponse < -1 || customerResponse > temp){
                        System.out.println("Invalid Input. Please Enter a valid number from the option listed\n");
                    }
                } while (customerResponse < -1 || customerResponse > temp);
            if (customerResponse == 0) {
                this.exitApp();

            } else if (customerResponse == -1) {
                this.setCurrentCustomer(null);
                this.startWFCApp();
            }

            this.appLogicExtension(customerResponse);
        }
    }
    }

    public void appLogicExtension(int customerResponse) throws IOException {

            if (customerResponse == 1) {
                int responseView = 100;

                do{
                    System.out.println("Please select how you want to view the available lessons.\nEnter an Option\n[1] View by Day of the Week\n[2] View by Fitness Activity\n[-1] Go Back\n[0] Exit App");
                    try{
                        responseView = scanner.nextInt();
                    } catch (Exception e){

                        scanner.next();
                    }
                    if(responseView < -1 || responseView > 2){
                        System.out.println("Invalid Input. Please Enter a valid number from the option listed\n");
                    }
                } while (responseView < -1 || responseView > 2);

                if(responseView == 0){
                    this.exitApp();
                } else if (responseView == -1){
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
                    currentBookingsString = null;
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
