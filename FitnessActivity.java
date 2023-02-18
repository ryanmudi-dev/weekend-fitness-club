import java.util.ArrayList;

public class FitnessActivity {
    private String activityName;
    private float price;
    private ArrayList<String> availableSlots;

    public FitnessActivity(String activityName, float price) {
        this.activityName = activityName;
        this.price = price;
        this.availableSlots = new ArrayList<String>();
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

    public ArrayList<String> getAvailableSlots() {
        return availableSlots;
    }

    public void addSlot(String slot){
        this.availableSlots.add(slot);
    }
}
