import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import service.BookingService;
import service.LessonService;
import model.Member;
import model.Lesson;
import model.Booking;

public class BookingServiceTest {

    @Test
    void testBookingSuccess() {

        LessonService lessonService = new LessonService();
        lessonService.generateLessons();

        BookingService bookingService = new BookingService();

        Member member = new Member(1, "TestUser");
        Lesson lesson = lessonService.getAllLessons().get(0);

        Booking booking = bookingService.bookLesson(member, lesson);

        assertNotNull(booking);
        assertEquals("booked", booking.getStatus());
    }

    @Test
    void testDuplicateBooking() {

        LessonService lessonService = new LessonService();
        lessonService.generateLessons();

        BookingService bookingService = new BookingService();

        Member member = new Member(1, "TestUser");
        Lesson lesson = lessonService.getAllLessons().get(0);

        bookingService.bookLesson(member, lesson);
        Booking duplicate = bookingService.bookLesson(member, lesson);

        assertNull(duplicate);
    }

    @Test
    void testLessonCapacityFull() {

        LessonService lessonService = new LessonService();
        lessonService.generateLessons();

        BookingService bookingService = new BookingService();

        Lesson lesson = lessonService.getAllLessons().get(0);

        // Add 4 bookings (max capacity)
        for (int i = 1; i <= 4; i++) {
            Member m = new Member(i, "User" + i);
            bookingService.bookLesson(m, lesson);
        }

        // Try 5th booking
        Member extra = new Member(5, "ExtraUser");
        Booking result = bookingService.bookLesson(extra, lesson);

        assertNull(result);
    }

    @Test
    void testChangeBooking() {

        LessonService lessonService = new LessonService();
        lessonService.generateLessons();

        BookingService bookingService = new BookingService();

        Member member = new Member(1, "TestUser");

        Lesson oldLesson = lessonService.getAllLessons().get(0);
        Lesson newLesson = lessonService.getAllLessons().get(1);

        Booking booking = bookingService.bookLesson(member, oldLesson);

        bookingService.changeBooking(booking.getBookingId(), newLesson);

        assertEquals(newLesson, booking.getLesson());
        assertEquals("changed", booking.getStatus());
    }

}