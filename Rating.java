import java.io.Serializable;

public class Rating implements Serializable {
    private String review;
    private int rating;

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Rating (int rating, String review){
        this.rating = rating;
        this.review = review;
    }
    public Rating (int rating){
        this.rating = rating;
        this.review = "";
    }
}
