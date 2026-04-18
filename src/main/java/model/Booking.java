package model;

public class Booking {
    private int bookingId;
    private Member member;
    private Lesson lesson;
    private String status;
    private static int counter = 1;

    public Booking(Member member, Lesson lesson) {
        this.bookingId = counter++;
        this.member = member;
        this.lesson = lesson;
        this.status = "booked";
    }

    public int getBookingId() {
        return bookingId;
    }

    public Member getMember() {
        return member;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
}