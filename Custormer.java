import java.util.ArrayList;

public class Custormer {
    private String customerName;
    private ArrayList<Lesson> currentBookedLessons;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ArrayList<Lesson> currentBookedLessons() {
        return currentBookedLessons;
    }

    public Custormer(String name){
        this.customerName = name;
        this.currentBookedLessons = new ArrayList<Lesson>();
    }

    public void addLesson(Lesson lesson) {
        this.currentBookedLessons.add(lesson);
    }

    public void removeLesson(Lesson lesson) {
        this.currentBookedLessons.remove(lesson);
    }
}
