import java.util.ArrayList;

public class Lesson {
    private FitnessActivity fitnessActivity;
    private String dateSlot;
    final int CAPACITY = 5;
    private int numberOfBookings;
    private ArrayList<Rating> reviews;

    public FitnessActivity getFitnessActivity() {
        return fitnessActivity;
    }

    public void setFitnessActivity(FitnessActivity fitnessActivity) {
        this.fitnessActivity = fitnessActivity;
    }

    public String getDateSlot() {
        return dateSlot;
    }

    public void setDateSlot(String dateSlot) {
        this.dateSlot = dateSlot;
    }

    public int getNumberOfBookings() {
        return numberOfBookings;
    }

    public void setNumberOfBookings(int numberOfBookings) {
        this.numberOfBookings = numberOfBookings;
    }

    public ArrayList<Rating> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Rating> reviews) {
        this.reviews = reviews;
    }

    public Lesson(FitnessActivity fitnessActivity, String dateSlot) {
        this.fitnessActivity = fitnessActivity;
        this.dateSlot = dateSlot;
        this.numberOfBookings = 0;
        this.reviews = new ArrayList<Rating>();
    }

    private void addRating(Rating rating) {
        this.reviews.add(rating);
    }

    private float calculateAverageRating(){
        int sum = 0;
        for (Rating review : this.reviews) {
            sum += review.getRating();
        }
        if (sum > 0) {
            return (float) sum / (float) this.reviews.size();
        }
        return -1;
    }

}
