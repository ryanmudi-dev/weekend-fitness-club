package com.Emudiaga.weekendFitnessClub;

import java.io.Serializable;
import java.util.HashMap;

public class BookingManager implements Serializable {
    private final HashMap<String, Booking> bookingHashMap;
    private int lastBooking;

    public BookingManager() {
        this.bookingHashMap = new HashMap<>();
        this.lastBooking = 0;
    }

    private String bookingIdGenerator(){
        return "booking" + (this.lastBooking + 1);
    }

    public String registerBooking(Customer customer, Lesson lesson){
        String newBookingId = bookingIdGenerator();
        Booking newBooking = new Booking(newBookingId, customer, lesson);
        bookingHashMap.put(newBookingId,newBooking);
        customer.addLesson(lesson);
        lesson.updateNumberOfBookings("increase");
        customer.addBooking(newBooking);
        this.lastBooking += 1;
        return newBookingId;

    }

    public void cancelBooking(Booking booking){
        booking.setStatus("cancelled");
        Lesson lesson = booking.getLesson();

        booking.getCustormer().removeLesson(lesson);
        lesson.updateNumberOfBookings("decrease");
    }

    public Booking getSpecificBooking(String bookingId){
        try {
            return bookingHashMap.get(bookingId);
        } catch (Exception e) {
            return null;
        }
    }

    public void attendBookedLesson(Customer customer, Booking booking, Rating rating){
        /** Provide Customer, Booking and Rating Objects */

        booking.setStatus("attended");
        booking.setRating(rating);
        booking.getLesson().addRating(rating);
        booking.getLesson().addAttendance();
    }

    public void changeBookedLesson(Customer customer, Booking booking, Lesson oldLesson, Lesson newLesson){
        /** Provide Customer, Booking, Old Lesson and the new Lesson Objects */
        booking.setLesson(newLesson);
        booking.setStatus("changed");
        newLesson.updateNumberOfBookings("increase");
        oldLesson.updateNumberOfBookings("decrease");
        customer.removeLesson(oldLesson);
        customer.addLesson(newLesson);
    }

    public boolean verifyBookingId(Customer customer, String bookingId){
        return this.bookingHashMap.containsKey(bookingId) && customer.currentBookedLessons().contains(this.bookingHashMap.get(bookingId).getLesson());
    }


}
