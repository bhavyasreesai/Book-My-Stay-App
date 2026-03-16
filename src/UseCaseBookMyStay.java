package BookMyStay;

import java.util.Scanner;
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}
class InvalidBookingValidator {
    public static void validateRoomType(String roomType) throws InvalidBookingException {
        if (!(roomType.equals("Single") || roomType.equals("Double") || roomType.equals("Suite"))) {
            throw new InvalidBookingException("Booking failed: Invalid room type selected.");
        }
    }
}
public class UseCaseBookMyStay {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Booking Validation");
        System.out.print("Enter guest name: ");
        String guestName = scanner.nextLine();
        System.out.print("Enter room type (Single/Double/Suite): ");
        String roomType = scanner.nextLine();
        try {
            InvalidBookingValidator.validateRoomType(roomType);
            System.out.println("Booking successful for " + guestName);
        } catch (InvalidBookingException e) {
            System.out.println(e.getMessage());
        }
        scanner.close();
    }
}


