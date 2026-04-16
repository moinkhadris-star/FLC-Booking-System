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
        Member m3 = new Member(3, "John");
        Member m4 = new Member(4, "Sara");
        Member m5 = new Member(5, "David");

// Pick one lesson
        Lesson lesson = lessonService.getAllLessons().get(0);

// Normal bookings
        bookingService.bookLesson(m1, lesson);
        bookingService.bookLesson(m2, lesson);
        bookingService.bookLesson(m3, lesson);
        bookingService.bookLesson(m4, lesson);


        System.out.println("\n---- All Bookings ----");

        for (Booking b : bookingService.getAllBookings()) {
            System.out.println(
                    "Booking ID: " + b.getBookingId() +
                            " | Member: " + b.getMember().getName() +
                            " | Lesson: " + b.getLesson().getName() +
                            " | Week: " + b.getLesson().getWeek() +
                            " | Status: " + b.getStatus()
            );
        }
// This should fail (capacity full)
        bookingService.bookLesson(m5, lesson);

// Duplicate booking test
        bookingService.bookLesson(m1, lesson);
    }
}