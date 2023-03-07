package com.Emudiaga.test;

import com.Emudiaga.weekendFitnessClub.Customer;
import com.Emudiaga.weekendFitnessClub.CustomersManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomersManagerTest {

    private CustomersManager customersManager;

    @BeforeEach
    public void setUp() {
        customersManager = new CustomersManager();
    }

    @AfterEach
    public void tearDown() {
        customersManager = null;
    }

    @Test
    public void testRegisterNewCustomer() {
        // Register a new customer
        boolean result = customersManager.registerNewCustomer("John", "Doe", "john.doe@example.com");
        assertTrue(result);

        // Attempt to register the same customer again
        result = customersManager.registerNewCustomer("John", "Doe", "john.doe@example.com");
        assertFalse(result);
    }

    @Test
    public void testGetSpecificCustomer() {
        // Register a new customer
        customersManager.registerNewCustomer("John", "Doe", "john.doe@example.com");

        // Get the specific customer
        Customer customer = customersManager.getSpecificCustomer("john.doe@example.com");
        assertNotNull(customer);
        assertEquals("John Doe", customer.getCustomerName());
    }
}
