package model;

import java.util.ArrayList;
import java.util.List;

public class Lesson {
    private String name;
    private String day;
    private String time;
    private double price;
    private int week;
    private List<Booking> bookings;

    public Lesson(int week, String name, String day, String time, double price) {
        this.name = name;
        this.week = week;
        this.day = day;
        this.time = time;
        this.price = price;
        this.bookings = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public double getPrice() {
        return price;
    }

    public int getWeek() {
        return week;
    }

    public List<Booking> getBookings() {
        return bookings;
    }
}