public class Booking {
    private String bookingId;
    private Custormer custormer;
    private Lesson lesson;
    private String status;
    private Rating rating;

    public Booking(String bookingId, Custormer custormer, Lesson lesson) {
        this.bookingId = bookingId;
        this.custormer = custormer;
        this.lesson = lesson;
        this.status = "booked";
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public Custormer getCustormer() {
        return custormer;
    }

    public void setCustormer(Custormer custormer) {
        this.custormer = custormer;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }
}
