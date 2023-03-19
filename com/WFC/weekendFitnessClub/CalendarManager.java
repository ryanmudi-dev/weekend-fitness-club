package com.WFC.weekendFitnessClub;


import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;

/**

 The CalendarManager class represents a manager of fitness lessons, it holds information about the available lessons,
 the fitness activities, and the schedule for each month.
 */

public class CalendarManager implements Serializable {


    //____________________________ Activities _____________________________________
    private final FitnessActivity yoga = new FitnessActivity("Yoga", 25);
    private final FitnessActivity spin = new FitnessActivity("Spin", 15);
    private final FitnessActivity zumba = new FitnessActivity("Zumba", 35);
    private final FitnessActivity aquacise = new FitnessActivity("Aquacise", 25);

    private HashMap<Integer, ArrayList<Lesson>> lessonsByMonth;

    /**
     * Returns the list of all lessons sorted by month
     *
     * @return the lessons sorted by month
     */

    public HashMap<Integer, ArrayList<Lesson>> getLessonsByMonth() {
        return lessonsByMonth;
    }

    /**
     * Returns the fitness activity with the given name
     *
     * @param name the name of the activity
     * @return the activity with the given name
     */
    public FitnessActivity getSpecificActivity(String name) {
        return switch (name) {
            case "Yoga" -> yoga;
            case "Spin" -> spin;
            case "Zumba" -> zumba;
            case "Aquacise" -> aquacise;
            default -> null;
        };
    }

    /**
     * Creates a new instance of the CalendarManager class.
     * Initializes the lessonsByMonth with the available lessons for March and April.
     */
    public CalendarManager() {

        this.lessonsByMonth = new HashMap<>();

        //------------------------------------Calendar-------------------------------------//

        //----------------------------------March-------------------------------------------//
        this.createNewLesson(this.yoga, 3,1,1,1);
        this.createNewLesson(this.spin, 3,1,1,2);
        this.createNewLesson(this.zumba, 3,1,2,1);
        this.createNewLesson(this.aquacise, 3,1,2,2);

        this.createNewLesson(this.zumba, 3,2,1,1);
        this.createNewLesson(this.aquacise, 3,2,1,2);
        this.createNewLesson(this.spin, 3,2,2,1);
        this.createNewLesson(this.yoga, 3,2,2,2);

        this.createNewLesson(this.yoga, 3,3,1,1);
        this.createNewLesson(this.spin, 3,3,1,2);
        this.createNewLesson(this.zumba, 3,3,2,1);
        this.createNewLesson(this.aquacise, 3,3,2,2);

        this.createNewLesson(this.aquacise, 3,4,1,1);
        this.createNewLesson(this.zumba, 3,4,1,2);
        this.createNewLesson(this.yoga, 3,4,2,1);
        this.createNewLesson(this.spin, 3,4,2,2);


        //----------------------------------April-------------------------------------------//

        this.createNewLesson(this.yoga, 4,1,1,1);
        this.createNewLesson(this.spin, 4,1,1,2);
        this.createNewLesson(this.zumba, 4,1,2,1);
        this.createNewLesson(this.aquacise, 4,1,2,2);

        this.createNewLesson(this.zumba, 4,2,1,1);
        this.createNewLesson(this.aquacise, 4,2,1,2);
        this.createNewLesson(this.yoga, 4,2,2,1);
        this.createNewLesson(this.spin, 4,2,2,2);

        this.createNewLesson(this.yoga, 4,3,1,1);
        this.createNewLesson(this.spin, 4,3,1,2);
        this.createNewLesson(this.zumba, 4,3,2,1);
        this.createNewLesson(this.aquacise, 4,3,2,2);

        this.createNewLesson(this.zumba, 4,4,1,1);
        this.createNewLesson(this.aquacise, 4,4,1,2);
        this.createNewLesson(this.yoga, 4,4,2,1);
        this.createNewLesson(this.spin, 4,4,2,2);
    }

    /**
     * Creates a new lesson for the given fitness activity on the specified day and session.
     *
     * @param fitnessActivity the fitness activity for the new lesson
     * @param month the month of the new lesson (1-12)
     * @param week the week of the new lesson (1-4)
     * @param day the day of the new lesson (1 or 2, where 1=Saturday and 2=Sunday)
     * @param session the session of the new lesson (1 or 2)
     */
    public void createNewLesson(FitnessActivity fitnessActivity, int month, int week, int day, int session){
        String dateSlot;
        if (month < 10){
            dateSlot = "0" + month + "0" + week + "0" + day + "0" + session;
        } else{
            dateSlot = "" + month + "0" + week + "0" + day + "0" + session;
        }
        Lesson newLesson = new Lesson(fitnessActivity, dateSlot);
        if(this.lessonsByMonth.containsKey(month)){
            this.lessonsByMonth.get(month).add(newLesson);
        } else {
            this.lessonsByMonth.put(month, new ArrayList<Lesson>());
            this.lessonsByMonth.get(month).add(newLesson);
        }
    }

    /**
     * Gets all available lessons on the specified day.
     *
     * @param day the day to search for available lessons (either "Saturday" or "Sunday")
     * @return an ArrayList of Lesson objects representing the available lessons on the specified day
     */
    public ArrayList<Lesson> getAvailableLessons(String day){
        ArrayList<Lesson> availableLessons = new ArrayList<>();
        if(day.equals("Saturday")){
            for (ArrayList<Lesson> currentMonth : this.lessonsByMonth.values()) {
                for (Lesson currentLesson : currentMonth) {
                    if (Integer.parseInt(currentLesson.getDateSlot().substring(4, 6)) == 1 && !currentLesson.isFilled()) {
                        availableLessons.add(currentLesson);
                    }
                }
            }
        } else if (day.equals("Sunday")){
            for (ArrayList<Lesson> currentMonth : this.lessonsByMonth.values()) {
                for (Lesson currentLesson : currentMonth) {
                    if (Integer.parseInt(currentLesson.getDateSlot().substring(4, 6)) == 2 && !currentLesson.isFilled()) {
                        availableLessons.add(currentLesson);
                    }
                }
            }
        }

        return availableLessons;
    }


    /**

     This method retrieves a list of available lessons for a given FitnessActivity.

     It first retrieves the available lessons for the FitnessActivity by calling its getAvailableLessons() method,

     then loops through each month's lessons and adds any unfilled lessons to an ArrayList of available lessons.

     @param fitnessActivity the FitnessActivity for which to retrieve available lessons

     @return an ArrayList of Lesson objects representing the available lessons for the given FitnessActivity
     */
    public ArrayList<Lesson> getAvailableLessons(FitnessActivity fitnessActivity){
        ArrayList<Lesson> availableLessons = new ArrayList<Lesson>();
        HashMap<Integer, ArrayList<Lesson>> availableLessonsByMonths = fitnessActivity.getAvailableLessons();
        for(ArrayList<Lesson> currentMonthLessons : availableLessonsByMonths.values()){
            for (Lesson currentLesson : currentMonthLessons) {
                if (!currentLesson.isFilled()) {
                    availableLessons.add(currentLesson);
                }
            }
        }

        return availableLessons;
    }

    /**

     This method converts an ArrayList of Lesson objects to an ArrayList of date string representations of each lesson.
     It loops through each lesson in the ArrayList and adds its date slot (in the form of a string) to a new ArrayList.
     @param lessonArray the ArrayList of Lesson objects to convert
     @return an ArrayList of date string representations of each Lesson object in lessonArray
     */
    public ArrayList<String> convertLessonArrayToDateStringsArray(ArrayList<Lesson> lessonArray) {
        ArrayList<String> dateSlotArray = new ArrayList<String>();
        for (Lesson currentLesson : lessonArray) {
            dateSlotArray.add(currentLesson.lessonToString());
        }
        return dateSlotArray;
    }

    /**

     This method converts the HashMap of lessons by month to a list of strings where each string represents a month
     and the lessons available in that month.
     It loops through each month in the HashMap and adds a string to a new ArrayList representing the month and its lessons.
     @return an ArrayList of strings representing the lessons available for each month
     */
    public ArrayList<String> lessonByMonthToString(){
        ArrayList<String> monthsString = new ArrayList<String>();
        for (Integer currentMonth : this.getLessonsByMonth().keySet()){
            String month = new DateFormatSymbols().getMonths()[currentMonth-1];
            monthsString.add("[" + currentMonth + "] For " + month);
        }
        return monthsString;
    }



}
