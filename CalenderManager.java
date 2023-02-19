import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.util.ArrayList;

public class CalenderManager implements Serializable {


    //____________________________ Activities _____________________________________
    private final FitnessActivity yoga = new FitnessActivity("Yoga", 25);
    private final FitnessActivity spin = new FitnessActivity("Spin", 15);
    private final FitnessActivity zumba = new FitnessActivity("Zumba", 35);
    private final FitnessActivity aquacise = new FitnessActivity("Aquacise", 25);

    private ArrayList<FitnessActivity> allActivities = new ArrayList<>();



    //_________________________ Calender _________________________________________________

    /* DateSlot Format: First 2 digit; Month, Second 2 digit; Week Number, Third 2 digit; Day 1 or 2(Sat or Sunday)
     * Last 2 digit; First or Second lesson of the day.
     */

    public Lesson marchWeek1SatLesson1 = new Lesson(yoga, "03010101");
    public Lesson marchWeek1SatLesson2 = new Lesson(spin, "03010102");
    public Lesson marchWeek1SunLesson1 = new Lesson(zumba, "03010201");
    public Lesson marchWeek1SunLesson2 = new Lesson(aquacise, "03010202");

    public Lesson marchWeek2SatLesson1 = new Lesson(zumba, "03020101");
    public Lesson marchWeek2SatLesson2 = new Lesson(aquacise, "03020102");
    public Lesson marchWeek2SunLesson1 = new Lesson(yoga, "03020201");
    public Lesson marchWeek2SunLesson2 = new Lesson(spin, "03020202");

    public Lesson marchWeek3SatLesson1 = new Lesson(yoga, "03030101");
    public Lesson marchWeek3SatLesson2 = new Lesson(spin, "03030102");
    public Lesson marchWeek3SunLesson1 = new Lesson(zumba, "03030201");
    public Lesson marchWeek3SunLesson2 = new Lesson(aquacise, "03030202");

    public Lesson marchWeek4SatLesson1 = new Lesson(zumba, "03040101");
    public Lesson marchWeek4SatLesson2 = new Lesson(yoga, "03040102");
    public Lesson marchWeek4SunLesson1 = new Lesson(aquacise, "03040201");
    public Lesson marchWeek4SunLesson2 = new Lesson(spin, "03040202");


    public Lesson aprilWeek1SatLesson1 = new Lesson(yoga, "04010101");
    public Lesson aprilWeek1SatLesson2 = new Lesson(spin, "04010102");
    public Lesson aprilWeek1SunLesson1 = new Lesson(zumba, "04010201");
    public Lesson aprilWeek1SunLesson2 = new Lesson(aquacise, "04010202");

    public Lesson aprilWeek2SatLesson1 = new Lesson(yoga, "04020101");
    public Lesson aprilWeek2SatLesson2 = new Lesson(spin, "04020102");
    public Lesson aprilWeek2SunLesson1 = new Lesson(aquacise, "04020201");
    public Lesson aprilWeek2SunLesson2 = new Lesson(zumba, "04020202");

    public Lesson aprilWeek3SatLesson1 = new Lesson(yoga, "04030101");
    public Lesson aprilWeek3SatLesson2 = new Lesson(spin, "04030102");
    public Lesson aprilWeek3SunLesson1 = new Lesson(zumba, "04030201");
    public Lesson aprilWeek3SunLesson2 = new Lesson(aquacise, "04030202");

    public Lesson aprilWeek4SatLesson1 = new Lesson(zumba, "04040101");
    public Lesson aprilWeek4SatLesson2 = new Lesson(spin, "04040102");
    public Lesson aprilWeek4SunLesson1 = new Lesson(aquacise, "04040201");
    public Lesson aprilWeek4SunLesson2 = new Lesson(spin, "04040202");

    public CalenderManager() {
        this.allActivities.add(yoga);
        this.allActivities.add(spin);
        this.allActivities.add(zumba);
        this.allActivities.add(aquacise);
    }

//    public ArrayList<String> getAvailableLessons(String day){
//        ArrayList<String> availableLessons = new ArrayList<>();
//        if(day.equals("Saturday")){
//            for (FitnessActivity currentActivity : allActivities) {
//                ArrayList<Lessons> availableSlots = currentActivity.getAvailableSlots();
//                for (String currentSlot : availableSlots) {
//                    if (Integer.parseInt(currentSlot.substring(4, 6)) == 1) {
//                        availableLessons.add(currentActivity.getActivityName() + ": " +convertDateSlotToDateStrings(currentSlot));
//                    }
//                }
//            }
//        } else if (day.equals("Sunday")){
//            for (FitnessActivity currentActivity : allActivities) {
//                ArrayList<String> availableSlots = currentActivity.getAvailableSlots();
//                for (String currentSlot : availableSlots) {
//                    if (Integer.parseInt(currentSlot.substring(4, 6)) == 2) {
//                        availableLessons.add(convertDateSlotToDateStrings(currentSlot));
//                    }
//                }
//            }
//        }
//
//        return availableLessons;
//    }

    public ArrayList<Lesson> getAvailableLessons(String day){
        ArrayList<Lesson> availableLessons = new ArrayList<>();
        if(day.equals("Saturday")){
            for (FitnessActivity currentActivity : allActivities) {
                ArrayList<Lesson> currentAvailableLessons = currentActivity.getAvailableLessons();
                for (Lesson currentLesson : currentAvailableLessons) {
                    if (Integer.parseInt(currentLesson.getDateSlot().substring(4, 6)) == 1) {
                        availableLessons.add(currentLesson);
                    }
                }
            }
        } else if (day.equals("Sunday")){
            for (FitnessActivity currentActivity : allActivities) {
                ArrayList<Lesson> currentAvailableLessons = currentActivity.getAvailableLessons();
                for (Lesson currentLesson : currentAvailableLessons) {
                    if (Integer.parseInt(currentLesson.getDateSlot().substring(4, 6)) == 2) {
                        availableLessons.add(currentLesson);
                    }
                }
            }
        }

        return availableLessons;
    }

    public ArrayList<Lesson> getAvailableLessons(FitnessActivity fitnessActivity){
       return fitnessActivity.getAvailableLessons();
    }

    private ArrayList<String> convertLessonArrayToDateStringsArray(ArrayList<Lesson> lessonArray) {
        ArrayList<String> dateSlotArray = new ArrayList<String>();
        for (Lesson currentLesson : lessonArray) {
            String dateSlot = currentLesson.getDateSlot();
            int monthSlice = Integer.parseInt(dateSlot.substring(0, 2));
            int weekSlice = Integer.parseInt(dateSlot.substring(2, 4));
            int daySlice = Integer.parseInt(dateSlot.substring(4, 6));
            int sessionOfTheDaySlice = Integer.parseInt(dateSlot.substring(6, 8));
            String day;

            String month = new DateFormatSymbols().getMonths()[monthSlice];
            if (daySlice == 1) {
                day = "Saturday";
            } else {
                day = "Sunday";
            }
            dateSlotArray.add("Week" + weekSlice + " of " + month + ": " + day + ", Session " + sessionOfTheDaySlice);
        }
        return dateSlotArray;
    }

}
