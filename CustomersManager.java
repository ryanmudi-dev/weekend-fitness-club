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

    public boolean registerNewCustomer(String firstName, String lastName, String emailAddress){
        String firstNameTitle = firstName.substring(0,1).toUpperCase() + firstName.substring(1);
        String lastNameTitle = lastName.substring(0,1).toUpperCase() + lastName.substring(1);
        Customer newCustomer = new Customer(firstNameTitle + " " + lastNameTitle, emailAddress);
        String customerKey = emailAddress.toLowerCase();
        if(this.customers.containsKey(customerKey)){
            return false;
        }else{
            this.customers.put(customerKey, newCustomer);
            return true;
        }

    }

    public Customer getSpecificCustomer(String emailAddress){
        /** Provide First Name and Last Name to return Customer Object **/
        String customerKey = emailAddress.toLowerCase();
        try {
            return this.customers.get(customerKey);
        } catch (Exception e){
            return null;
        }
    }
}
