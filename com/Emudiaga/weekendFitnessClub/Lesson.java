package com.Emudiaga.weekendFitnessClub;

import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.util.ArrayList;

public class Lesson implements Serializable {
    private final FitnessActivity fitnessActivity;
    private final String dateSlot;
    final static int CAPACITY = 5;
    private int numberOfBookings;
    private int numberOfAttendance;
    private final ArrayList<Rating> reviews;
    private boolean isFilled;

    public int getNumberOfAttendance() {
        return numberOfAttendance;
    }

    public void addAttendance() {
        this.numberOfAttendance += 1;
    }

    public FitnessActivity getFitnessActivity() {
        return fitnessActivity;
    }


    public String getDateSlot() {
        return dateSlot;
    }



    public void updateNumberOfBookings(String order) {
        if (order.equals("increase")){
            this.numberOfBookings += 1;
        } else if (order.equals("decrease")) {
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



    public Lesson(FitnessActivity fitnessActivity, String dateSlot) {
        this.fitnessActivity = fitnessActivity;
        this.dateSlot = dateSlot;
        this.numberOfBookings = 0;
        this.numberOfAttendance = 0;
        this.isFilled = false;
        this.reviews = new ArrayList<>();
        fitnessActivity.addLesson(this);
    }

    public void addRating(Rating rating) {
        this.reviews.add(rating);
    }

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

    public float getAmountGenerated(){
        return this.fitnessActivity.getPrice() * this.numberOfAttendance;
    }

    public boolean isFilled(){
        return this.numberOfBookings >= CAPACITY;
    }

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
