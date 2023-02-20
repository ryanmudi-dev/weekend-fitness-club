import java.io.*;
import java.util.HashMap;

public class CustomersManager implements Serializable{
    private HashMap<String, Customer> customers;

    public HashMap<String, Customer> getCustomers() {
        return customers;
    }

    @Override
    public String toString() {
        return "CustomersManager{" +
                "customers=" + customers +
                '}';
    }

    public CustomersManager(){
    this.customers = new HashMap<>();
    }

    public boolean registerNewCustomer(String firstName, String lastName){
        Customer newCustomer = new Customer(firstName + " " + lastName);
        String customerKey = firstName.toLowerCase() + lastName.toLowerCase();
        if(this.customers.containsKey(customerKey)){
            return false;
        }else{
            this.customers.put(customerKey, newCustomer);
            return true;
        }

    }

    public Customer getSpecificCustomer(String firstName, String lastName){
        /** Provide First Name and Last Name to return Customer Object **/
        String customerKey = firstName.toLowerCase() + lastName.toLowerCase();
        try {
            return this.customers.get(customerKey);
        } catch (Exception e){
            return null;
        }
    }
}
