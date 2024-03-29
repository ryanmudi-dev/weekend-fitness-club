package com.WFC.weekendFitnessClub;

import java.text.DateFormatSymbols;
import java.util.ArrayList;

/**

 A class representing a fitness lesson, including details about the fitness activity, date and time slot,
 the number of bookings and attendance, the average rating and amount generated, and whether the lesson is filled or not.
 */

public class Lesson {
    private final FitnessActivity fitnessActivity;
    private final String dateSlot;
    final static int CAPACITY = 5;
    private int numberOfBookings;
    private int numberOfAttendance;
    private final ArrayList<Rating> reviews;
    private boolean isFilled;

    /**
     * Constructs a new Lesson object with the given FitnessActivity and date slot.
     *
     * @param fitnessActivity the fitness activity associated with this lesson.
     * @param dateSlot the date and time slot of this lesson.
     */
    public Lesson(FitnessActivity fitnessActivity, String dateSlot) {
        this.fitnessActivity = fitnessActivity;
        this.dateSlot = dateSlot;
        this.numberOfBookings = 0;
        this.numberOfAttendance = 0;
        this.isFilled = false;
        this.reviews = new ArrayList<>();
        fitnessActivity.addLesson(this);
    }

    /**
     * Returns the number of bookings for this lesson.
     *
     * @return the number of bookings for this lesson.
     */
    public int getNumberOfBookings() {
        return numberOfBookings;
    }

    /**
     * Returns the number of attendances for this lesson.
     *
     * @return the number of attendances for this lesson.
     */
    public int getNumberOfAttendance() {
        return numberOfAttendance;
    }

    /**
     * Increases the number of attendance for this lesson by 1.
     */
    public void addAttendance() {
        this.numberOfAttendance += 1;
    }

    /**
     * Returns the fitness activity associated with this lesson.
     *
     * @return the fitness activity associated with this lesson.
     */
    public FitnessActivity getFitnessActivity() {
        return fitnessActivity;
    }

    /**
     * Returns the date and time slot of this lesson.
     *
     * @return the date and time slot of this lesson.
     */
    public String getDateSlot() {
        return dateSlot;
    }


    /**
     * Updates the number of bookings for this lesson by either increasing or decreasing it by 1, depending on the given order.
     * If the lesson is filled after the update, remove it from the list of available lessons of its fitness activity;
     * if it is not filled after the update, add it to the list of available lessons of its fitness activity.
     *
     * @param order the order to update the number of bookings, either "increase" or "decrease".
     */

    public void updateNumberOfBookings(String order) {

        if (!order.equalsIgnoreCase("decrease") && !order.equalsIgnoreCase("increase")) {
            throw new IllegalArgumentException("Invalid order");
        }

        if (order.equalsIgnoreCase("increase")){
            this.numberOfBookings += 1;
        } else if (order.equalsIgnoreCase("decrease")) {
            if(this.isFilled){
                this.fitnessActivity.addLesson(this);
            }
            this.numberOfBookings -= 1;
        }
        if (isFilled()){
            this.isFilled = true;
            this.fitnessActivity.removeLesson(this);
        }else{
            this.isFilled = false;
        }
    }


    /**
     * Adds the given rating to the list of reviews for this lesson.
     *
     * @param rating the rating to be added to the list of reviews for this lesson.
     */

    public void addRating(Rating rating) {
        this.reviews.add(rating);
    }

    /**

     Calculates the average rating of this Lesson based on the ratings submitted by attendees.
     @return The average rating as a float, or 0 if there are no ratings submitted.
     */
    public float getAverageRating(){
        int sum = 0;
        for (Rating review : this.reviews) {
            sum += review.getRating();
        }
        if (sum > 0) {
            return (float) sum / (float) this.reviews.size();
        }
        return 0;
    }


    /**

     Calculates the total amount of money generated by this Lesson based on the number of attendees and the price of the FitnessActivity.
     @return The total amount generated as a float.
     */
    public float getAmountGenerated(){
        return this.fitnessActivity.getPrice() * this.numberOfAttendance;
    }


    /**

     Determines whether this Lesson is filled to capacity.
     @return True if the number of bookings for this Lesson is equal to or greater than the capacity, false otherwise.
     */
    public boolean isFilled(){
        return this.numberOfBookings >= CAPACITY;
    }


    /**

     Converts this Lesson object to a string representation.
     @return A string representation of this Lesson object.
     */
    public String lessonToString(){
        String dateSlot = this.getDateSlot();
        int monthSlice = Integer.parseInt(dateSlot.substring(0, 2));
        int weekSlice = Integer.parseInt(dateSlot.substring(2, 4));
        int daySlice = Integer.parseInt(dateSlot.substring(4, 6));
        int sessionOfTheDaySlice = Integer.parseInt(dateSlot.substring(6, 8));
        String day;

        String month = new DateFormatSymbols().getMonths()[monthSlice-1];
        if (daySlice == 1) {
            day = "Saturday";
        } else {
            day = "Sunday";
        }
        return "Week " + weekSlice + " of " + month + ": " + day + ", Session " + sessionOfTheDaySlice + ": " + this.getFitnessActivity().getActivityName() + " ------ £" + String.valueOf(this.getFitnessActivity().getPrice());

    }

}
