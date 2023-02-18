import java.util.ArrayList;

public class Custormer {
    private String customerName;
    private ArrayList<Booking> currentBookings;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ArrayList<Booking> getCurrentBookings() {
        return currentBookings;
    }

    public Custormer(String name){
        this.customerName = name;
        this.currentBookings = new ArrayList<Booking>();
    }

    public void addBooking(Booking booking) {
        this.currentBookings.add(booking);
    }

    public void removeBooking(Booking booking) {
        this.currentBookings.remove(booking);
    }
}
