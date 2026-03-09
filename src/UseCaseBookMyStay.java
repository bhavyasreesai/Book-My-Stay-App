import java.util.*;

class Reservation {
    String guestName;
    String roomType;
    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}
class RoomAllocationService {
    private Queue<Reservation> bookingQueue = new LinkedList<>();
    private Map<String, Integer> inventory = new HashMap<>();
    private Map<String, Set<String>> allocatedRooms = new HashMap<>();
    public RoomAllocationService() {
        inventory.put("Single", 2);
        inventory.put("Double", 0);
        inventory.put("Suite", 1);
        bookingQueue.add(new Reservation("Abhi", "Single"));
        bookingQueue.add(new Reservation("Subha", "Single"));
        bookingQueue.add(new Reservation("Vanmathi", "Suite"));
    }
    public void processBookings() {
        System.out.println("Room Allocation Processing");
        while (!bookingQueue.isEmpty()) {
            Reservation r = bookingQueue.poll();
            if (inventory.getOrDefault(r.roomType, 0) > 0) {
                String roomId = generateRoomId(r.roomType);
                allocatedRooms
                        .computeIfAbsent(r.roomType, k -> new HashSet<>())
                        .add(roomId);
                inventory.put(r.roomType, inventory.get(r.roomType) - 1);

                System.out.println("Booking confirmed for Guest: "
                        + r.guestName + ", Room Type: " + roomId);
            }
        }
    }
    private String generateRoomId(String roomType) {

        int count = allocatedRooms
                .getOrDefault(roomType, new HashSet<>())
                .size() + 1;

        return roomType + "-" + count;
    }
}
public class UseCaseBookMyStay {

    public static void main(String[] args) {

        RoomAllocationService service = new RoomAllocationService();
        service.processBookings();
    }
}