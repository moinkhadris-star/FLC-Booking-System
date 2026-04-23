package model;

public class Booking {
    private int bookingId;
    private Member member;
    private Lesson lesson;
    private String status;
    private Review review;

    public Booking(Member member, Lesson lesson) {

        this.bookingId = generateRandomId();
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

    public void setReview(Review review) {
        this.review = review;
    }

    public Review getReview() {
        return review;
    }

    private int generateRandomId() {

        int id;

        while (true) {
            id = (int)(Math.random() * 900) + 100;

            if (id != 0) {
                break;
            }
        }

        return id;
    }
}