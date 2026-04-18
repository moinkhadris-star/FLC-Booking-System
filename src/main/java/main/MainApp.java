package main;

import model.Member;
import model.Lesson;
import service.LessonService;
import service.BookingService;
import model.Booking;


public class MainApp {
    public static void main(String[] args) {

        LessonService lessonService = new LessonService();
        lessonService.generateLessons();

        BookingService bookingService = new BookingService();

// Create members
        Member m1 = new Member(1, "Moin");
        Member m2 = new Member(2, "Ali");

// Get lessons
        Lesson lesson1 = lessonService.getAllLessons().get(0);
        Lesson lesson2 = lessonService.getAllLessons().get(1);

// Book lessons
        Booking b1 = bookingService.bookLesson(m1, lesson1);
        Booking b2 = bookingService.bookLesson(m2, lesson1);

// Print before change
        System.out.println("\n--- Before Change ---");
        for (Booking b : bookingService.getAllBookings()) {
            System.out.println("ID: " + b.getBookingId() +
                    " | Member: " + b.getMember().getName() +
                    " | Lesson: " + b.getLesson().getName());
        }

// Change booking
        bookingService.changeBooking(b1.getBookingId(), lesson2);

// Cancel booking
        bookingService.cancelBooking(b2.getBookingId());

// Print after change
        System.out.println("\n--- After Change/Cancel ---");
        for (Booking b : bookingService.getAllBookings()) {
            System.out.println("ID: " + b.getBookingId() +
                    " | Member: " + b.getMember().getName() +
                    " | Lesson: " + b.getLesson().getName() +
                    " | Status: " + b.getStatus());
        }
    }
}