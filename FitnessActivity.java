import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class FitnessActivity implements Serializable {
    private String activityName;
    private float price;
    private HashMap<Integer, ArrayList<Lesson>> availableLesson;

    public FitnessActivity(String activityName, float price) {
        this.activityName = activityName;
        this.price = price;
        this.availableLesson = new HashMap<Integer, ArrayList<Lesson>>();
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public HashMap<Integer, ArrayList<Lesson>> getAvailableLessons() {
        return availableLesson;
    }

    public void addLesson(Lesson lesson){
        int month = Integer.parseInt(lesson.getDateSlot().substring(0, 2));
        if(this.availableLesson.containsKey(month)){
            this.availableLesson.get(month).add(lesson);
        } else {
            this.availableLesson.put(month, new ArrayList<Lesson>());
            this.availableLesson.get(month).add(lesson);
        }
    }

    public void removeLesson(Lesson lesson){
        int month = Integer.parseInt(lesson.getDateSlot().substring(0, 2));
        this.availableLesson.get(month).remove(lesson);
    }
}
