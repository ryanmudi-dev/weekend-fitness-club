package com.WFC.weekendFitnessClub;

import java.io.Serializable;
import java.util.ArrayList;

/**

 Customer class represents a customer in a fitness center who can book lessons and make bookings.
 A customer object has a name, email address, a list of booked lessons, and a list of all bookings.
 */

public class Customer implements Serializable {

    private String customerName;
    private String customerEmailAddress;
    private ArrayList<Lesson> bookedLessons;
    private final ArrayList<Booking> currentBookings;

    /**
     * Constructs a customer object with a given name and email address
     * @param name the name of the customer
     * @param emailAddress the email address of the customer
     */
    public Customer(String name, String emailAddress){
        this.customerName = name;
        this.customerEmailAddress = emailAddress;
        this.bookedLessons = new ArrayList<Lesson>();
        this.currentBookings = new ArrayList<Booking>();
    }

    /**
     * Returns the email address of the customer
     * @return the email address of the customer
     */
    public String getCustomerEmailAddress() {
        return customerEmailAddress;
    }

    /**
     * Sets the email address of the customer
     * @param customerEmailAddress the email address to set for the customer
     */
    public void setCustomerEmailAddress(String customerEmailAddress) {
        this.customerEmailAddress = customerEmailAddress;
    }

    /**
     * Returns the current bookings of the customer
     * @return an ArrayList of current bookings
     */
    public ArrayList<Booking> getCurrentBookings() {
        return currentBookings;
    }

    /**
     * Returns the email address of the customer
     * @return the email address of the customer
     */
    public String getEmailAddress() {
        return customerEmailAddress;
    }

    /**
     * Sets the email address of the customer
     * @param emailAddress the email address to set for the customer
     */
    public void setEmailAddress(String emailAddress) {
        this.customerEmailAddress = emailAddress;
    }

    /**
     * Returns the currently booked lessons of the customer
     * @return an ArrayList of currently booked lessons
     */
    public ArrayList<Lesson> getCurrentBookedLessons() {
        return bookedLessons;
    }

    /**
     * Sets the currently booked lessons of the customer
     * @param currentBookedLessons the ArrayList of currently booked lessons to set for the customer
     */
    public void setCurrentBookedLessons(ArrayList<Lesson> currentBookedLessons) {
        this.bookedLessons = currentBookedLessons;
    }

    /**
     * Returns the name of the customer
     * @return the name of the customer
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets the name of the customer
     * @param customerName the name to set for the customer
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Returns the currently booked lessons of the customer
     * @return an ArrayList of currently booked lessons
     */
    public ArrayList<Lesson> currentBookedLessons() {
        return bookedLessons;
    }


    /**
     * Adds a lesson to the customer's list of booked lessons
     * @param lesson the lesson to add to the customer's list of booked lessons
     */
    public void addLesson(Lesson lesson) {
        this.bookedLessons.add(lesson);
    }

    public void removeLesson(Lesson lesson) {
        this.bookedLessons.remove(lesson);
    }

    public void addBooking(Booking booking){
        this.currentBookings.add(booking);
    }

    public void removeBooking(Booking booking){
        this.currentBookings.remove(booking);
    }


    public ArrayList<String> currentBookingsToString(){
        ArrayList<String> currentBookingsString = new ArrayList<>();
        for (Booking currentBooking : this.getCurrentBookings()){
            if(!currentBooking.getStatus().equals("attended") && !currentBooking.getStatus().equals("cancelled")){
                String bookingID = currentBooking.getBookingId();
                String lessonString = currentBooking.getLesson().lessonToString();
                currentBookingsString.add("[ID: '" + bookingID + "'] " + lessonString);
            }
        }
        return currentBookingsString;
    }

    /**
     * @return true if the customer has active bookings.
     */
    public boolean hasBooking() {
        return this.currentBookingsToString().size() > 0;
    }
}
