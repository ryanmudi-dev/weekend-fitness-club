import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class CalenderManager implements Serializable {


    //____________________________ Activities _____________________________________
    private final FitnessActivity yoga = new FitnessActivity("Yoga", 25);
    private final FitnessActivity spin = new FitnessActivity("Spin", 15);
    private final FitnessActivity zumba = new FitnessActivity("Zumba", 35);
    private final FitnessActivity aquacise = new FitnessActivity("Aquacise", 25);

    private HashMap<Integer, ArrayList<Lesson>> lessonsByMonth;

    public FitnessActivity getSpecificActivity(String name) {
        return switch (name) {
            case "Yoga" -> yoga;
            case "Spin" -> spin;
            case "Zumba" -> zumba;
            case "Aquacise" -> aquacise;
            default -> null;
        };
    }


    private ArrayList<FitnessActivity> allActivities = new ArrayList<>();





    public CalenderManager() {
        this.allActivities.add(yoga);
        this.allActivities.add(spin);
        this.allActivities.add(zumba);
        this.allActivities.add(aquacise);
        this.lessonsByMonth = new HashMap<>();

        //------------------------------------Calender-------------------------------------//

        //----------------------------------March-------------------------------------------//
        this.createNewLesson(this.yoga, 3,1,1,1);
        this.createNewLesson(this.spin, 3,1,1,2);
        this.createNewLesson(this.zumba, 3,1,2,1);
        this.createNewLesson(this.aquacise, 3,1,2,2);

        this.createNewLesson(this.zumba, 3,2,1,1);
        this.createNewLesson(this.aquacise, 3,2,1,2);
        this.createNewLesson(this.yoga, 3,2,2,1);
        this.createNewLesson(this.spin, 3,2,2,2);

        this.createNewLesson(this.yoga, 3,3,1,1);
        this.createNewLesson(this.spin, 3,3,1,2);
        this.createNewLesson(this.zumba, 3,3,2,1);
        this.createNewLesson(this.aquacise, 3,3,2,2);

        this.createNewLesson(this.zumba, 3,4,1,1);
        this.createNewLesson(this.aquacise, 3,4,1,2);
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


    public ArrayList<Lesson> getAvailableLessons(String day){
        ArrayList<Lesson> availableLessons = new ArrayList<>();
        if(day.equals("Saturday")){
            for (FitnessActivity currentActivity : allActivities) {
                ArrayList<Lesson> currentAvailableLessons = currentActivity.getAvailableLessons();
                for (Lesson currentLesson : currentAvailableLessons) {
                    if (Integer.parseInt(currentLesson.getDateSlot().substring(4, 6)) == 1 && !currentLesson.isFilled()) {
                        availableLessons.add(currentLesson);
                    }
                }
            }
        } else if (day.equals("Sunday")){
            for (FitnessActivity currentActivity : allActivities) {
                ArrayList<Lesson> currentAvailableLessons = currentActivity.getAvailableLessons();
                for (Lesson currentLesson : currentAvailableLessons) {
                    if (Integer.parseInt(currentLesson.getDateSlot().substring(4, 6)) == 2 && !currentLesson.isFilled()) {
                        availableLessons.add(currentLesson);
                    }
                }
            }
        }

        return availableLessons;
    }

    public ArrayList<Lesson> getAvailableLessons(FitnessActivity fitnessActivity){
        ArrayList<Lesson> allAvailableLessons = fitnessActivity.getAvailableLessons();
        ArrayList<Lesson> availableLessons = new ArrayList<>();
        for (Lesson currentLesson : allAvailableLessons) {
            if (!currentLesson.isFilled()) {
                availableLessons.add(currentLesson);
            }
        }
       return availableLessons;
    }

    public ArrayList<String> convertLessonArrayToDateStringsArray(ArrayList<Lesson> lessonArray) {
        ArrayList<String> dateSlotArray = new ArrayList<String>();
        for (Lesson currentLesson : lessonArray) {
            dateSlotArray.add(currentLesson.lessonToString());
        }
        return dateSlotArray;
    }



}
