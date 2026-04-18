package service;

import model.Booking;
import model.Lesson;
import model.Member;

import java.util.ArrayList;
import java.util.List;

public class BookingService {

    private List<Booking> bookings;

    public BookingService() {
        bookings = new ArrayList<>();
    }

    public List<Booking> getAllBookings() {
        return bookings;
    }

    public Booking bookLesson(Member member, Lesson lesson) {

        // Step 3.6.1 → Duplicate check
        for (Booking b : lesson.getBookings()) {
            if (b.getMember().getId() == member.getId()) {
                System.out.println("Booking failed: Duplicate booking not allowed");
                return null;
            }
        }

        if (lesson.getBookings().size() >= 4) {
            System.out.println("Booking failed: Lesson is full");
            return null;
        }

        Booking booking = new Booking(member, lesson);

        bookings.add(booking);
        lesson.getBookings().add(booking);

        System.out.println("Booking successful for " + member.getName());

        return booking;
    }

    public Booking findBookingById(int bookingId) {

        for (Booking booking : bookings) {
            if (booking.getBookingId() == bookingId) {
                return booking;
            }
        }

        return null;
    }

    public void cancelBooking(int bookingId) {

        Booking booking = findBookingById(bookingId);

        if (booking == null) {
            System.out.println("Cancel failed: Booking not found");
            return;
        }

        // Remove from lesson
        booking.getLesson().getBookings().remove(booking);

        // Remove from global list
        bookings.remove(booking);

        // Update status
        booking.setStatus("cancelled");

        System.out.println("Booking cancelled successfully");
    }

    public void changeBooking(int bookingId, Lesson newLesson) {

        Booking booking = findBookingById(bookingId);

        if (booking == null) {
            System.out.println("Change failed: Booking not found");
            return;
        }

        // Duplicate check
        for (Booking b : newLesson.getBookings()) {
            if (b.getMember().getId() == booking.getMember().getId()) {
                System.out.println("Change failed: Duplicate booking not allowed");
                return;
            }
        }

        // Capacity check
        if (newLesson.getBookings().size() >= 4) {
            System.out.println("Change failed: New lesson is full");
            return;
        }

        // Remove from old lesson
        booking.getLesson().getBookings().remove(booking);

        // Update lesson reference
        booking.setLesson(newLesson);

        // Add to new lesson
        newLesson.getBookings().add(booking);

        // Update status
        booking.setStatus("changed");

        System.out.println("Booking changed successfully");
    }

    private void updateLesson(Booking booking, Lesson newLesson) {

        booking.setStatus("changed");

        // update lesson reference
        booking.getLesson().getBookings().remove(booking);

        booking = booking; // keep same object
        booking.getLesson(); // no-op (just clarity)

        booking.setStatus("changed");

        // assign new lesson
        newLesson.getBookings().add(booking);
    }


}