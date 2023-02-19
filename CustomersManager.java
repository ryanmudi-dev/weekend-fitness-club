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

    private static final long serialVersionUID = 1L;
    private final static String filePath = "Serialization/customers.dat";


    public CustomersManager() throws IOException {
        try{
             FileInputStream fis = new FileInputStream(filePath);
             ObjectInputStream ois = new ObjectInputStream(fis);

            this.customers = (HashMap<String, Customer>) ois.readObject();
            ois.close();
            fis.close();
        } catch(Exception e) {
//            FileOutputStream fos = new FileOutputStream(filePath);
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            oos.writeObject(customers);
//            oos.close();
//            fos.close();
        System.out.println(e);
        }



    }

    public void saveCustomersData() throws IOException{
        try{FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.customers);
            oos.close();
            fos.close();
        } catch(Exception e) {
            System.out.println(e);
        }
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
