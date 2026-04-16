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
}