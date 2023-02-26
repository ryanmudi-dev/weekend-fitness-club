package com.Emudiaga.weekendFitnessClub;

import java.io.Serializable;

public class Booking implements Serializable {
    private String bookingId;
    private Customer customer;
    private Lesson lesson;
    private String status;
    private Rating rating;

    public Booking(String bookingId, Customer customer, Lesson lesson) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.lesson = lesson;
        this.status = "booked";
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }
}
