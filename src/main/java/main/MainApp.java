package main;

import model.Member;
import model.Lesson;
import model.Booking;

public class MainApp {
    public static void main(String[] args) {

        Member m1 = new Member(1, "Moin");

        Lesson lesson = new Lesson("Yoga", "Saturday", "Morning", 10.0);

        Booking booking = new Booking(1, m1, lesson);

        System.out.println("Member: " + booking.getMember().getName());
        System.out.println("Lesson: " + booking.getLesson().getName());
        System.out.println("Status: " + booking.getStatus());
    }
}