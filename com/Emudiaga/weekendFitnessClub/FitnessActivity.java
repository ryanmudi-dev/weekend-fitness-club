package com.Emudiaga.weekendFitnessClub;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**

 A class representing a fitness activity with a name, price, and available lessons.
 This class implements Serializable interface to enable objects of this class to be serialized.
 */

public class FitnessActivity implements Serializable {
    private final String activityName; // the name of the fitness activity
    private final float price; // the price of the fitness activity
    private final HashMap<Integer, ArrayList<Lesson>> availableLesson; // a HashMap containing available lessons for the fitness activity

    /**
     * Constructs an instance of the FitnessActivity class with the given activity name and price.
     * Initializes the availableLesson HashMap.
     *
     * @param activityName the name of the fitness activity
     * @param price the price of the fitness activity
     */
    public FitnessActivity(String activityName, float price) {
        this.activityName = activityName;
        this.price = price;
        this.availableLesson = new HashMap<>();
    }

    /**
     * Returns the name of the fitness activity.
     *
     * @return the activity name
     */
    public String getActivityName() {
        return activityName;
    }

    /**
     * Returns the price of the fitness activity.
     *
     * @return the activity price
     */
    public float getPrice() {
        return price;
    }

    /**
     * Returns the HashMap containing the available lessons for the fitness activity.
     *
     * @return the availableLesson HashMap
     */
    public HashMap<Integer, ArrayList<Lesson>> getAvailableLessons() {
        return availableLesson;
    }

    /**
     * Adds a lesson to the availableLesson HashMap for the fitness activity.
     * The lesson is added to the month of the lesson date.
     *
     * @param lesson the lesson to add
     */
    public void addLesson(Lesson lesson){
        int month = Integer.parseInt(lesson.getDateSlot().substring(0, 2));
        if(this.availableLesson.containsKey(month)){
            this.availableLesson.get(month).add(lesson);
        } else {
            this.availableLesson.put(month, new ArrayList<>());
            this.availableLesson.get(month).add(lesson);
        }
    }

    /**
     * Removes a lesson from the availableLesson HashMap for the fitness activity.
     * The lesson is removed from the month of the lesson date.
     *
     * @param lesson the lesson to remove
     */
    public void removeLesson(Lesson lesson){
        int month = Integer.parseInt(lesson.getDateSlot().substring(0, 2));
        this.availableLesson.get(month).remove(lesson);
    }

}
