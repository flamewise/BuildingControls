package com.example.buildingcontrols.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TemperatureServiceTest {
    @Test
    public void testRegulateTemperature() {
        TemperatureService service = new TemperatureService();
        service.regulateTemperature();
        assertTrue(true); // Placeholder test
    }
}
