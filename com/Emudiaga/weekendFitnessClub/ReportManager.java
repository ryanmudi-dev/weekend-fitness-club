package com.Emudiaga.weekendFitnessClub;

import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class ReportManager implements Serializable {
    private final CalendarManager calendarManager;

    /**
     * @param calendarManager Calendar Manager object of the app.
     */
    public ReportManager(CalendarManager calendarManager) {
        this.calendarManager = calendarManager;
    }

    /**
     * @param month month number in int
     * @return report for that month
     */
    public String getMonthlyLessonReport(int month){
        StringBuilder monthlyReport = new StringBuilder();
        ArrayList<Lesson> lessonsForTheMonth = this.calendarManager.getLessonsByMonth().get(month);
        if (lessonsForTheMonth == null){
            return null;
        }
        for(Lesson lesson : lessonsForTheMonth){
            float averageRating = lesson.getAverageRating();
            int attendance = lesson.getNumberOfAttendance();
            monthlyReport.append(lesson.lessonToString().substring(0, lesson.lessonToString().length() - 12)).append("\n").append("Number of Attendees: ").append(attendance).append(" | Average Rating: ").append(averageRating).append("/5.0\n------------------------------------------------\n");
        }
        return monthlyReport.toString();
    }

    /**
     * @param month month number in int
     * @return Champion report for that month
     */
    public String getMonthlyChampionFitnessReport(int month){
        StringBuilder monthlyReport = new StringBuilder();
        HashMap<FitnessActivity, Float> activitiesIncome = new HashMap<>();
        activitiesIncome.put(calendarManager.getSpecificActivity("Yoga"), (float) 0);
        activitiesIncome.put(calendarManager.getSpecificActivity("Spin"), (float) 0);
        activitiesIncome.put(calendarManager.getSpecificActivity("Zumba"), (float) 0);
        activitiesIncome.put(calendarManager.getSpecificActivity("Aquacise"), (float) 0);

        ArrayList<Lesson> currentLessons = calendarManager.getLessonsByMonth().get(month);
        if (currentLessons == null){
            return null;
        }

        for (Lesson currentLesson : currentLessons){
            float income = currentLesson.getAmountGenerated();
            float currentTotal = activitiesIncome.get(currentLesson.getFitnessActivity());
            activitiesIncome.replace(currentLesson.getFitnessActivity(), currentTotal + income);
        }

        FitnessActivity championActivity = calendarManager.getSpecificActivity("Yoga");
        for (FitnessActivity currentActivity : activitiesIncome.keySet()){
            if (activitiesIncome.get(currentActivity) > activitiesIncome.get(championActivity)){
                championActivity = currentActivity;
            }
        }
        StringBuilder monthString = new StringBuilder(new DateFormatSymbols().getMonths()[month - 1]);

        if (activitiesIncome.get(championActivity) == 0){
            return "No Lesson has been attended for " + monthString;
        }

        StringBuilder otherActivities = new StringBuilder();
        String string0 = " Activity";
        String string1 = " is ";
        String string2 = "";

        ArrayList<String> champions = new ArrayList<>();
        StringBuilder championsName = new StringBuilder(championActivity.getActivityName());

        for (FitnessActivity currentActivity : activitiesIncome.keySet()){
            if (currentActivity != championActivity){
                if (Objects.equals(activitiesIncome.get(currentActivity), activitiesIncome.get(championActivity))){
                    champions.add(currentActivity.getActivityName());
                    string0 = " Activities";
                    string1 = " are ";
                    string2 = " each";

                }else{
                    otherActivities.append(currentActivity.getActivityName()).append(": £").append(activitiesIncome.get(currentActivity)).append("\n");
                }

            }
        }
        for(String currentChampion : champions){
            if(Objects.equals(currentChampion, champions.get(champions.size() - 1))){
                championsName.append(" and ").append(currentChampion);
            } else {
                championsName.append(", ").append(currentChampion);
            }
        }

        monthlyReport.append("The Highest grossing Fitness").append(string0).append(" for ").append(monthString).append(string1).append(championsName).append(" with a total of £").append(activitiesIncome.get(championActivity)).append(string2).append(".\nThe Income for the other activities are listed below:\n").append(otherActivities);

        return monthlyReport.toString();
    }
}
