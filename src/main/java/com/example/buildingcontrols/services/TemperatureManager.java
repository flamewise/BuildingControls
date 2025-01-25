package com.example.buildingcontrols.services;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import com.example.buildingcontrols.controllers.BuildingController;
import com.example.buildingcontrols.models.Building;
import com.example.buildingcontrols.models.Room;

public class TemperatureManager {
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final Random random = new Random();

    public void start() {
        scheduler.scheduleAtFixedRate(this::updateTemperatures, 0, 5, TimeUnit.SECONDS);
    }

    public void stop() {
        scheduler.shutdown();
    }

    private void updateTemperatures() {
        for (Building building : BuildingController.getInstance().getAllBuildings()) {
            for (Room room : building.getRooms()) {
                // Update heating/cooling states based on the building's requested temperature
                room.updateHeatingCoolingStates(building.getRequestedTemperature());

                double adjustment = generateTemperatureAdjustment();

                if (room.isHeatingEnabled()) {
                    room.setTemperature(room.getTemperature() + adjustment);
                } else if (room.isCoolingEnabled()) {
                    room.setTemperature(room.getTemperature() - adjustment);
                } else {
                    // Minor random fluctuation if neither heating nor cooling is enabled
                    double minorFluctuation = generateMinorFluctuation();
                    room.setTemperature(
                        clampTemperature(room.getTemperature() + minorFluctuation, building.getRequestedTemperature())
                    );
                }
            }
        }
    }

    /**
     * Generates a temperature adjustment using a normal distribution.
     *
     * @return a random adjustment value.
     */
    private double generateTemperatureAdjustment() {
        double mean = 0.5; // Average adjustment value
        double stdDev = 0.1; // Standard deviation of adjustment
        return mean + stdDev * random.nextGaussian();
    }

    /**
     * Generates a minor fluctuation for when no heating or cooling is active.
     *
     * @return a small random fluctuation value.
     */
    private double generateMinorFluctuation() {
        double mean = 0.0; // Center around no change
        double stdDev = 0.1; // Small standard deviation
        return mean + stdDev * random.nextGaussian();
    }

    /**
     * Clamps the temperature to stay close to the requested temperature.
     *
     * @param currentTemp      the current temperature of the room.
     * @param requestedTemp    the requested temperature of the building.
     * @return the clamped temperature value.
     */
    private double clampTemperature(double currentTemp, double requestedTemp) {
        double margin = 1.0; // Allow up to ±1.0 degree deviation
        double lowerBound = requestedTemp - margin;
        double upperBound = requestedTemp + margin;
        return Math.max(lowerBound, Math.min(currentTemp, upperBound));
    }
}
