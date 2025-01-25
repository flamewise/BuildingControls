import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TemperatureManager {
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public void start() {
        scheduler.scheduleAtFixedRate(this::updateTemperatures, 0, 5, TimeUnit.SECONDS);
    }

    public void stop() {
        scheduler.shutdown();
    }

    private void updateTemperatures() {
        for (Building building : BuildingController.getInstance().getAllBuildings()) {
            for (Room room : building.getRooms()) {
                double currentTemp = room.getTemperature();

                if (room.isHeatingEnabled()) {
                    room.setTemperature(currentTemp + 0.5); // Incrementally increase
                } else if (room.isCoolingEnabled()) {
                    room.setTemperature(currentTemp - 0.5); // Incrementally decrease
                }
            }
        }
    }
}
