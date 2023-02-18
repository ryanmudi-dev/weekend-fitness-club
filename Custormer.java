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

    private void addBooking(Booking booking) {
        this.currentBookings.add(booking);
    }

    private void removeBooking(Booking booking) {
        this.currentBookings.remove(booking);
    }
}
