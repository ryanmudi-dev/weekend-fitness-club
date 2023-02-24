import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable {

    private String customerName;
    private String customerEmailAddress;
    private ArrayList<Lesson> currentBookedLessons;
    private final ArrayList<Booking> currentBookings;

    public String getCustomerEmailAddress() {
        return customerEmailAddress;
    }

    public void setCustomerEmailAddress(String customerEmailAddress) {
        this.customerEmailAddress = customerEmailAddress;
    }

    public ArrayList<Booking> getCurrentBookings() {
        return currentBookings;
    }

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
        this.currentBookings = new ArrayList<Booking>();
    }

    public void addLesson(Lesson lesson) {
        this.currentBookedLessons.add(lesson);
    }

    public void removeLesson(Lesson lesson) {
        this.currentBookedLessons.remove(lesson);
    }

    public void addBooking(Booking booking){
        this.currentBookings.add(booking);
    }

    public void removeBooking(Booking booking){
        this.currentBookings.remove(booking);
    }

    public ArrayList<String> currentBookingsToString(){
        ArrayList<String> currentBookingsString = new ArrayList<String>();
        for (Booking currentBooking : this.getCurrentBookings()){
            String bookingID = currentBooking.getBookingId();
            String lessonString = currentBooking.getLesson().lessonToString();
            currentBookingsString.add("[ID: '" + bookingID + "'] " + lessonString);
        }
        return currentBookingsString;
    }
}
