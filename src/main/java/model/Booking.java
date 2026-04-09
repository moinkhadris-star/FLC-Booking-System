package model;

public class Booking {
    private int bookingId;
    private Member member;
    private Lesson lesson;
    private String status;

    public Booking(int bookingId, Member member, Lesson lesson) {
        this.bookingId = bookingId;
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
}