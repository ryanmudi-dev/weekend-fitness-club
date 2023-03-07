package com.Emudiaga.test;

import com.Emudiaga.weekendFitnessClub.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookingManagerTest {
    private BookingManager manager;
    private Customer customer;
    private Lesson lesson;
    FitnessActivity activity;

    @BeforeEach
    void setUp() {
        manager = new BookingManager();
        activity = new FitnessActivity("Zumba", 25);
        customer = new Customer("John Doe", "johndoe@gmail.com");
        lesson = new Lesson(activity, "03010101");
    }

    @AfterEach
    void tearDown() {
        manager = null;
        customer = null;
        lesson = null;
        activity = null;

    }

    @Test
    void testRegisterBooking() {
        String bookingId = manager.registerBooking(customer, lesson);
        assertNotNull(bookingId);
        Booking booking = manager.getSpecificBooking(bookingId);
        assertNotNull(booking);
        assertEquals(booking.getCustomer(), customer);
        assertEquals(booking.getLesson(), lesson);
    }

    @Test
    public void testChangeBookedLesson() {
        activity = new FitnessActivity("Yoga", 25);
        Lesson newLesson = new Lesson(activity, "03010102");
        String bookingId = manager.registerBooking(customer, lesson);
        Booking booking = manager.getSpecificBooking(bookingId);
        manager.changeBookedLesson(customer, booking, lesson, newLesson);
        assertEquals(booking.getLesson(), newLesson);
        assertFalse(customer.currentBookedLessons().contains(lesson));
        assertTrue(customer.currentBookedLessons().contains(newLesson));
        assertEquals(lesson.getNumberOfBookings(), 0);
        assertEquals(newLesson.getNumberOfBookings(), 1);
    }

    @Test
    public void testCancelBooking() {
        String bookingId = manager.registerBooking(customer, lesson);
        Booking booking = manager.getSpecificBooking(bookingId);
        manager.cancelBooking(booking);
        assertEquals(booking.getStatus(), "cancelled");
        assertFalse(customer.currentBookedLessons().contains(lesson));
        assertEquals(lesson.getNumberOfBookings(), 0);
    }

    @Test
    public void testAttendBookedLesson() {
        String bookingId = manager.registerBooking(customer, lesson);
        Booking booking = manager.getSpecificBooking(bookingId);
        Rating rating = new Rating(5);
        manager.attendBookedLesson(booking, rating);
        assertEquals(booking.getStatus(), "attended");
        assertEquals(booking.getRating(), rating);
        assertEquals(lesson.getNumberOfAttendance(), 1);
        assertEquals(lesson.getAverageRating(), rating.getRating(), 0.001);
    }
}