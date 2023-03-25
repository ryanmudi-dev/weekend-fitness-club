package com.WFC.weekendFitnessClub;

import java.io.*;
import java.util.HashMap;

/**

 A class representing a customer manager that manages a collection of customer objects using a HashMap.
 This class implements Serializable interface to enable objects of this class to be serialized.
 */

public class CustomersManager implements Serializable{
    private HashMap<String, Customer> customers;

    /**
     * Constructs an instance of the CustomersManager class.
     * Initializes the customers HashMap.
     */
    public CustomersManager(){
        this.customers = new HashMap<>();
    }

    /**
     * Returns the HashMap containing customer objects.
     *
     * @return the customers HashMap
     */
    public HashMap<String, Customer> getCustomers() {
        return customers;
    }

    /**
     * Returns a string representation of the CustomersManager object.
     *
     * @return a string representation of the CustomersManager object.
     */
    @Override
    public String toString() {
        return "CustomersManager{" +
                "customers=" + customers +
                '}';
    }

    /**
     * Registers a new customer with the given first name, last name, and email address.
     *
     * @param firstName the first name of the new customer
     * @param lastName the last name of the new customer
     * @param emailAddress the email address of the new customer
     * @return true if the customer was successfully registered, false otherwise
     */
    public boolean registerNewCustomer(String firstName, String lastName, String emailAddress){
        if (firstName == null || firstName.trim().isEmpty() ||
                lastName == null || lastName.trim().isEmpty() ||
                emailAddress == null || emailAddress.trim().isEmpty()) {
            // Invalid input, one or more fields are missing or empty
            return false;
        }

        String firstNameTitle = firstName.substring(0,1).toUpperCase() + firstName.substring(1);
        String lastNameTitle = lastName.substring(0,1).toUpperCase() + lastName.substring(1);
        Customer newCustomer = new Customer(firstNameTitle + " " + lastNameTitle, emailAddress);
        String customerKey = emailAddress.toLowerCase();
        if(this.customers.containsKey(customerKey)){
            // Customer with this email already exists
            return false;
        }else{
            this.customers.put(customerKey, newCustomer);
            return true;
        }
    }

    /**
     * Returns the customer object associated with the given email address.
     *
     * @param emailAddress the email address of the customer to retrieve
     * @return the customer object associated with the email address, or null if the customer is not found
     */
    public Customer getSpecificCustomer(String emailAddress){
        String customerKey = emailAddress.toLowerCase();
        try {
            return this.customers.get(customerKey);
        } catch (Exception e){
            return null;
        }
    }
}
