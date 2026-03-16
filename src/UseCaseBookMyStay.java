package BookMyStay;

import java.util.*;
class Reservation {
    private String reservationId;
    private String roomType;
    public Reservation(String reservationId, String roomType) {
        this.reservationId = reservationId;
        this.roomType = roomType;
    }
    public String getReservationId() {
        return reservationId;
    }
    public String getRoomType() {
        return roomType;
    }
}
class CancellationService {
    private Map<String, Reservation> reservations = new HashMap<>();
    private Map<String, Integer> inventory = new HashMap<>();
    private Stack<String> rollbackStack = new Stack<>();
    public CancellationService() {
        inventory.put("Single", 5);
        Reservation r = new Reservation("Single-1", "Single");
        reservations.put(r.getReservationId(), r);
    }
    public void cancelBooking(String reservationId) {
        if (!reservations.containsKey(reservationId)) {
            System.out.println("Cancellation failed: Reservation not found.");
            return;
        }
        Reservation r = reservations.remove(reservationId);
        rollbackStack.push(reservationId);
        String roomType = r.getRoomType();
        inventory.put(roomType, inventory.get(roomType) + 1);
        System.out.println("Booking cancelled successfully. Inventory restored for room type: " + roomType);
        System.out.println();
        System.out.println("Rollback History (Most Recent First):");
        while (!rollbackStack.isEmpty()) {
            System.out.println("Released Reservation ID: " + rollbackStack.pop());
        }
        System.out.println();
        System.out.println("Updated Single Room Availability: " + inventory.get("Single"));
    }
}
public class UseCaseBookMyStay {
    public static void main(String[] args) {
        System.out.println("Booking Cancellation");
        CancellationService service = new CancellationService();
        service.cancelBooking("Single-1");
    }
}

