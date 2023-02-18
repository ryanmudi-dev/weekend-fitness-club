import java.util.Dictionary;
import java.util.Hashtable;

public class BookingManager {
    private Dictionary<String, Booking> bookingsDict = new Hashtable<>();
    private int lastBooking;

    public int getLastBooking() {
        return lastBooking;
    }

    public void setLastBooking(int lastBooking) {
        this.lastBooking = lastBooking;
    }

    public Dictionary<String, Booking> getBookings() {
        return bookingsDict;
    }

    public BookingManager(Dictionary<String, Booking> bookings) {
        this.bookingsDict = bookings;
        this.lastBooking = 0;
    }

    private String bookingIdGenerator(){
        return "booking" + this.lastBooking + 1;
    }

    public void registerBooking(Custormer custormer, Lesson lesson){
        String newBookingId = bookingIdGenerator();
        Booking newBooking = new Booking(newBookingId, custormer, lesson);
        bookingsDict.put(newBookingId,newBooking);
        this.lastBooking += 1;

    }

    public void removeBooking(String bookingId){
        bookingsDict.remove(bookingId);
    }

    public void cancelBooking(String bookingId){
        Booking bookingToBeCancelled = bookingsDict.get(bookingId);
        bookingToBeCancelled.setStatus("cancelled");

        Lesson lesson = bookingToBeCancelled.getLesson();

        bookingToBeCancelled.getCustormer().removeLesson(lesson);
        lesson.updateNumberOfBookings("decrease");

    }

    public void attendBookedLesson(Custormer custormer, Booking booking, Rating rating){
        booking.setStatus("attended");
        booking.setRating(rating);
        booking.getLesson().addRating(rating);
        custormer.removeLesson(booking.getLesson());
    }
}
