package com.WFC.weekendFitnessClub;

/**
 * Represents a booking made by a customer for a lesson at the Weekend Fitness Club.
 */

public class Booking {
    private String bookingId;
    private Customer customer;
    private Lesson lesson;
    private String status;
    private Rating rating;

    /**
     * Constructs a new Booking object with the specified booking ID, customer, and lesson.
     *
     * @param bookingId the ID of the booking
     * @param customer the customer who made the booking
     * @param lesson the lesson that was booked
     */
    public Booking(String bookingId, Customer customer, Lesson lesson) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.lesson = lesson;
        this.status = "booked";
    }

    /**
     * Returns the ID of the booking.
     *
     * @return the ID of the booking
     */
    public String getBookingId() {
        return bookingId;
    }

    /**
     * Sets the ID of the booking.
     *
     * @param bookingId the new ID of the booking
     */
    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    /**
     * Returns the customer who made the booking.
     *
     * @return the customer who made the booking
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the customer who made the booking.
     *
     * @param customer the new customer who made the booking
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Returns the lesson that was booked.
     *
     * @return the lesson that was booked
     */
    public Lesson getLesson() {
        return lesson;
    }

    /**
     * Sets the lesson that was booked.
     *
     * @param lesson the new lesson that was booked
     */
    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    /**
     * Returns the status of the booking.
     *
     * @return the status of the booking
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the booking.
     *
     * @param status the new status of the booking
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns the rating for the booking, if it has been rated.
     *
     * @return the rating for the booking
     */
    public Rating getRating() {
        return rating;
    }

    /**
     * Sets the rating for the booking.
     *
     * @param rating the new rating for the booking
     */
    public void setRating(Rating rating) {
        this.rating = rating;
    }
}

