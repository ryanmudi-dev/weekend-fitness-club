package com.WFC.weekendFitnessClub;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class wfcAppTestSuite {
    private Customer customer;
    private BookingManager bookingManager;
    private CalendarManager calendarManager;
    private CustomersManager customersManager;
    private Lesson lesson;
    private FitnessActivity fitnessActivity;


    @BeforeEach
    void setUp() {
        customer = new Customer("John Doe", "johndoe@gmail.com");
        bookingManager = new BookingManager();
        calendarManager = new CalendarManager();
        customersManager = new CustomersManager();
        fitnessActivity = new FitnessActivity("Yoga", 25);
        lesson = new Lesson(fitnessActivity, "03010101");
    }

    @AfterEach
    void tearDown() {
        customer = null;
        bookingManager = null;
        calendarManager = null;
        customersManager = null;
        lesson = null;
        fitnessActivity = null;
    }


    /**
     This method tests the registration of new customers by invoking the registerNewCustomer() method of
     the CustomersManager class. It does the following:
     Registers a new customer with the name "John Doe" and email "john.doe@example.com".
     Checks that the registration is successful by verifying that the method returns true.
     Attempts to register the same customer again with the same email address.
     Checks that the second registration attempt fails by verifying that the method returns false.
     */
    @Test
    public void testRegisterNewCustomer() {
        // Register a new customer
        boolean result = customersManager.registerNewCustomer("John", "Doe", "john.doe@example.com");
        assertTrue(result);

        // Attempt to register the same customer again
        result = customersManager.registerNewCustomer("John", "Doe", "john.doe@example.com");
        assertFalse(result);
    }



    /**
       Tests the functionality of changing a booked lesson by invoking the changeBookedLesson method of the BookingManager class.
       Creates a FitnessActivity and Lesson object, registers a booking with a customer for the lesson,
       changes the booked lesson to a new lesson using the BookingManager, and asserts that the booking
       reflects the new lesson and the customer's booked lessons list has been updated accordingly.
       Also asserts that the number of bookings for the original lesson was decreased and the number of bookings for
       the new lesson was increased.
     */
    @Test
    public void testChangeBookedLesson() {
        Lesson newLesson = new Lesson(fitnessActivity, "03010102");
        String bookingId = bookingManager.registerBooking(customer, lesson);
        Booking booking = bookingManager.getSpecificBooking(bookingId);
        bookingManager.changeBookedLesson(customer, booking, lesson, newLesson);

        // Assert that the booking reflects the new lesson and the customer's booked lessons list has been updated
        assertEquals(booking.getLesson(), newLesson);
        assertFalse(customer.getCurrentBookedLessons().contains(lesson));
        assertTrue(customer.getCurrentBookedLessons().contains(newLesson));

        // Assert that the number of bookings for the original lesson is 0 and the number of bookings for the new lesson is 1
        assertEquals(lesson.getNumberOfBookings(), 0);
        assertEquals(newLesson.getNumberOfBookings(), 1);
    }


    /**
     This method tests the hasBooking() method of the Customer class. It does the following:
     Creates a new Customer object.
     Checks that the hasBooking() method returns false for the new customer.
     Adds a booking to the customer by invoking the addBooking() method.
     Checks that the hasBooking() method returns true for the customer after adding the booking.
     */
    @Test
    public void testHasBooking() {
        assertFalse(customer.hasBooking());
        Booking booking = new Booking("booking13", customer, lesson);
        customer.addBooking(booking);
        assertTrue(customer.hasBooking());
    }


    /**
     This method tests the attendBookedLesson() method of the BookingManager class. It does the following:
     Registers a new booking for a lesson by invoking the registerBooking() method of the BookingManager class.
     Retrieves the booking object by invoking the getSpecificBooking() method of the BookingManager class.
     Creates a new rating object.
     Attends the booked lesson and adds the rating to the booking by invoking the attendBookedLesson() method of the BookingManager class.
     Checks that the booking status is updated to "attended".
     Checks that the booking rating is updated to the newly created rating object.
     Checks that the lesson's number of attendance is incremented by 1.
     Checks that the lesson's average rating is updated to the average of all ratings received by the lesson.
     */
    @Test
    public void testAttendBookedLesson() {
        String bookingId = bookingManager.registerBooking(customer, lesson);
        Booking booking = bookingManager.getSpecificBooking(bookingId);
        Rating rating = new Rating(5);
        bookingManager.attendBookedLesson(booking, rating);
        assertEquals(booking.getStatus(), "attended");
        assertEquals(booking.getRating(), rating);
        assertEquals(lesson.getNumberOfAttendance(), 1);
        assertEquals(lesson.getAverageRating(), rating.getRating(), 0.001);
    }


    /**
     This method tests the capacity by checking if the array of available lessons include lessons that has reached full capacity:
     Uses the getAvailableLessons method of the CalenderManager class.
     Retrieves an available lesson from the calendar on Saturday.
     Registers 5 new customers for the lesson.
     Checks that the lesson is no longer available after all spaces are filled.
     Cancels the booking for the first customer and checks that the lesson is available again.
     */
    @Test
    public void testLessonCapacity() {
        Lesson testLesson = calendarManager.getAvailableLessons("Saturday").get(0);
        for(int i = 0; i < 5; i++){
            String email = "johndoe" + i + "@gmail.com";
            customersManager.registerNewCustomer("John", "Doe", email);
            Customer currentCustomer = customersManager.getSpecificCustomer(email);
            assertTrue(calendarManager.getAvailableLessons("Saturday").contains(testLesson));
            bookingManager.registerBooking(currentCustomer, testLesson);
        }

        assertFalse(calendarManager.getAvailableLessons("Saturday").contains(testLesson));

        bookingManager.cancelBooking(bookingManager.getSpecificBooking("booking1"));
        assertTrue(calendarManager.getAvailableLessons("Saturday").contains(testLesson));
    }
}