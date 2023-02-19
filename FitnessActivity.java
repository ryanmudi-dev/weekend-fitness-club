import java.util.ArrayList;

public class FitnessActivity {
    private String activityName;
    private float price;
    private ArrayList<Lesson> availableLesson;

    public FitnessActivity(String activityName, float price) {
        this.activityName = activityName;
        this.price = price;
        this.availableLesson = new ArrayList<Lesson>();
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

    public ArrayList<Lesson> getAvailableLessons() {
        return availableLesson;
    }

    public void addLesson(Lesson lesson){
        this.availableLesson.add(lesson);
    }

    public void removeLesson(Lesson lesson){
        this.availableLesson.remove(lesson);
    }
}
