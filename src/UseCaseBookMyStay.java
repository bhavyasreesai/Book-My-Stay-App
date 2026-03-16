package BookMyStay;
import java.util.*;
class AddOnService {
    private String serviceName;
    private double cost;
    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }
    public double getCost() {
        return cost;
    }
    public String getServiceName() {
        return serviceName;
    }
}
class AddOnServiceManager {
    private Map<String, List<AddOnService>> reservationServices = new HashMap<>();
    public void addService(String reservationId, AddOnService service) {
        reservationServices
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);
    }
    public double calculateTotalCost(String reservationId) {
        List<AddOnService> services = reservationServices.get(reservationId);
        double total = 0;
        if (services != null) {
            for (AddOnService s : services) {
                total += s.getCost();
            }
        }
        return total;
    }
}
public class UseCaseBookMyStay {
    public static void main(String[] args) {
        AddOnServiceManager manager = new AddOnServiceManager();
        String reservationId = "Single-1";
        AddOnService breakfast = new AddOnService("Breakfast", 500.0);
        AddOnService airportPickup = new AddOnService("Airport Pickup", 1000.0);
        manager.addService(reservationId, breakfast);
        manager.addService(reservationId, airportPickup);
        double totalCost = manager.calculateTotalCost(reservationId);
        System.out.println("Add-On Service Selection");
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Total Add-On Cost: " + totalCost);
    }
}
