package model;

public class Lesson {
    private String name;
    private String day;
    private String time;
    private double price;
    private int week;

    public Lesson(int week, String name, String day, String time, double price) {
        this.name = name;
        this.week = week;
        this.day = day;
        this.time = time;
        this.price = price;
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
}