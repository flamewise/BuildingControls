package com.example.buildingcontrols.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApartmentTest {

    @Test
    void testApartmentCreation() {
        // Arrange
        String apartmentId = "A101";
        String ownerName = "John Doe";

        // Act
        Apartment apartment = new Apartment(apartmentId, ownerName);

        // Assert
        assertEquals(apartmentId, apartment.getId());
        assertEquals(ownerName, apartment.getOwnerName());
        assertTrue(apartment.getTemperature() >= 10.0 && apartment.getTemperature() <= 40.0, 
            "Temperature should be initialized to a random value between 10 and 40.");
        assertFalse(apartment.isHeatingEnabled());
        assertFalse(apartment.isCoolingEnabled());
    }

    @Test
    void testSetOwnerName() {
        // Arrange
        Apartment apartment = new Apartment("A101", "John Doe");

        // Act
        apartment.setOwnerName("Jane Smith");

        // Assert
        assertEquals("Jane Smith", apartment.getOwnerName());
    }

    @Test
    void testToString() {
        // Arrange
        Apartment apartment = new Apartment("A101", "John Doe");

        // Act
        String result = apartment.toString();

        // Assert
        assertTrue(result.contains("id='A101'"));
        assertTrue(result.contains("ownerName='John Doe'"));
    }
}
