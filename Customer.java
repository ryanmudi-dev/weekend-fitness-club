import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable {

    private String customerName;
    private String customerEmailAddress;
    private ArrayList<Lesson> currentBookedLessons;

    public String getEmailAddress() {
        return customerEmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.customerEmailAddress = emailAddress;
    }

    public ArrayList<Lesson> getCurrentBookedLessons() {
        return currentBookedLessons;
    }

    public void setCurrentBookedLessons(ArrayList<Lesson> currentBookedLessons) {
        this.currentBookedLessons = currentBookedLessons;
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ArrayList<Lesson> currentBookedLessons() {
        return currentBookedLessons;
    }

    public Customer(String name, String emailAddress){
        this.customerName = name;
        this.customerEmailAddress = emailAddress;
        this.currentBookedLessons = new ArrayList<Lesson>();
    }

    public void addLesson(Lesson lesson) {
        this.currentBookedLessons.add(lesson);
    }

    public void removeLesson(Lesson lesson) {
        this.currentBookedLessons.remove(lesson);
    }
}
