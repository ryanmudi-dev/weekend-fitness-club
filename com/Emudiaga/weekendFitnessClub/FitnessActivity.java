package com.Emudiaga.weekendFitnessClub;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class FitnessActivity implements Serializable {
    private final String activityName;
    private final float price;
    private final HashMap<Integer, ArrayList<Lesson>> availableLesson;

    public FitnessActivity(String activityName, float price) {
        this.activityName = activityName;
        this.price = price;
        this.availableLesson = new HashMap<>();
    }

    public String getActivityName() {
        return activityName;
    }

    public float getPrice() {
        return price;
    }


    public HashMap<Integer, ArrayList<Lesson>> getAvailableLessons() {
        return availableLesson;
    }

    public void addLesson(Lesson lesson){
        int month = Integer.parseInt(lesson.getDateSlot().substring(0, 2));
        if(this.availableLesson.containsKey(month)){
            this.availableLesson.get(month).add(lesson);
        } else {
            this.availableLesson.put(month, new ArrayList<>());
            this.availableLesson.get(month).add(lesson);
        }
    }

    public void removeLesson(Lesson lesson){
        int month = Integer.parseInt(lesson.getDateSlot().substring(0, 2));
        this.availableLesson.get(month).remove(lesson);
    }
}
