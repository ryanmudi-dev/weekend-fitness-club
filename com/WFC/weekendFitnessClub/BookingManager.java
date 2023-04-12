package com.WFC.weekendFitnessClub;

import java.util.HashMap;

public class BookingManager {
    private final HashMap<String, Booking> bookingHashMap;
    private int lastBooking;

    public BookingManager() {
        this.bookingHashMap = new HashMap<>();
        this.lastBooking = 0;
    }

    /**
     * @return generate unique IDs for each new bookings
     */
    private String bookingIdGenerator(){
        return "booking" + (this.lastBooking + 1);
    }

    /**
     * @param customer Customer object registering the new booking
     * @param lesson The lesson chosen by the customer
     * @return the booking ID of the newly created booking
     */
    public String registerBooking(Customer customer, Lesson lesson){
        if (customer == null || lesson == null) {
            throw new IllegalArgumentException("Customer and lesson cannot be null");
        }
        if (customer.getCurrentBookedLessons().contains(lesson)) {
            throw new IllegalArgumentException("Customer has already booked this lesson");
        }
        String newBookingId = bookingIdGenerator();
        Booking newBooking = new Booking(newBookingId, customer, lesson);
        bookingHashMap.put(newBookingId,newBooking);
        customer.addLesson(lesson);
        lesson.updateNumberOfBookings("increase");
        customer.addBooking(newBooking);
        this.lastBooking += 1;
        return newBookingId;
    }

    /**
     * @param booking the booking to be cancelled
     */
    public void cancelBooking(Booking booking){
        if (booking == null) {
            throw new IllegalArgumentException("Booking cannot be null");
        }
        booking.setStatus("cancelled");
        Lesson lesson = booking.getLesson();

        booking.getCustomer().removeLesson(lesson);
        lesson.updateNumberOfBookings("decrease");
    }


    /**
     * @param bookingId booking ID in string
     * @return booking object with the provided booking ID
     */
    public Booking getSpecificBooking(String bookingId){
        try {
            return bookingHashMap.get(bookingId);
        } catch (Exception e) {
            return null;
        }
    }

    /**

     Attends a previously booked lesson and records the rating given by the customer.
     @param booking The booking to attend.
     @param rating The rating given by the customer.
     */
    public void attendBookedLesson(Booking booking, Rating rating){
        if (booking == null || rating == null) {
            throw new IllegalArgumentException("Booking and rating cannot be null");
        }
        booking.setStatus("attended");
        booking.setRating(rating);
        booking.getLesson().addRating(rating);
        booking.getLesson().addAttendance();
    }

    /**

     Changes a previously booked lesson to a new lesson for a customer.
     @param customer The customer whose booking is being changed.
     @param booking The booking to change.
     @param oldLesson The lesson previously booked by the customer.
     @param newLesson The new lesson to book.
     */
    public void changeBookedLesson(Customer customer, Booking booking, Lesson oldLesson, Lesson newLesson){
        if (customer == null || booking == null || oldLesson == null || newLesson == null) {
            throw new IllegalArgumentException("Customer, booking, old lesson, and new lesson cannot be null");
        }
        booking.setLesson(newLesson);
        booking.setStatus("changed");
        newLesson.updateNumberOfBookings("increase");
        oldLesson.updateNumberOfBookings("decrease");
        customer.removeLesson(oldLesson);
        customer.addLesson(newLesson);
    }

    /**

     Verifies that a given booking ID is valid for the current customer.
     @param customer The customer whose booking ID is being verified.
     @param bookingId The booking ID to verify.
     @return True if the booking ID is valid for the current customer, false otherwise.
     */
    public boolean verifyBookingId(Customer customer, String bookingId){
        if (customer == null || bookingId == null) {
            throw new IllegalArgumentException("Customer and booking ID cannot be null");
        }
        return this.bookingHashMap.containsKey(bookingId) && customer.getCurrentBookedLessons().contains(this.bookingHashMap.get(bookingId).getLesson());
    }


}
