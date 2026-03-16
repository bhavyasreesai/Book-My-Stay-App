package BookMyStay;

import java.util.*;
class ConcurrentBookingProcessor {
    private Map<String, Integer> inventory = new HashMap<>();
    private Map<String, Integer> roomCounters = new HashMap<>();
    public ConcurrentBookingProcessor() {
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
        roomCounters.put("Single", 0);
        roomCounters.put("Double", 0);
        roomCounters.put("Suite", 0);
    }
    public synchronized void bookRoom(String guestName, String roomType) {
        if (inventory.get(roomType) > 0) {
            int count = roomCounters.get(roomType) + 1;
            roomCounters.put(roomType, count);
            String roomId = roomType + "-" + count;
            inventory.put(roomType, inventory.get(roomType) - 1);
            System.out.println("Booking confirmed for Guest: " + guestName + ", Room ID: " + roomId);
        } else {
            System.out.println("Booking failed for " + guestName + ". No rooms available.");
        }
    }
    public void printInventory() {
        System.out.println();
        System.out.println("Remaining Inventory:");
        System.out.println("Single: " + inventory.get("Single"));
        System.out.println("Double: " + inventory.get("Double"));
        System.out.println("Suite: " + inventory.get("Suite"));
    }
}
class BookingThread extends Thread {
    private ConcurrentBookingProcessor processor;
    private String guestName;
    private String roomType;
    public BookingThread(ConcurrentBookingProcessor processor, String guestName, String roomType) {
        this.processor = processor;
        this.guestName = guestName;
        this.roomType = roomType;
    }
    public void run() {
        processor.bookRoom(guestName, roomType);
    }
}
public class UseCaseBookMyStay {
    public static void main(String[] args) {
        System.out.println("Concurrent Booking Simulation");
        ConcurrentBookingProcessor processor = new ConcurrentBookingProcessor();
        Thread t1 = new BookingThread(processor, "Abhi", "Single");
        Thread t2 = new BookingThread(processor, "Vanmathi", "Double");
        Thread t3 = new BookingThread(processor, "Kural", "Suite");
        Thread t4 = new BookingThread(processor, "Subha", "Single");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        processor.printInventory();
    }
}


