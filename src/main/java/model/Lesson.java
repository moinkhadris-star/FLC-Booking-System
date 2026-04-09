package model;

public class Lesson {
    private String name;
    private String day;
    private String time;
    private double price;

    public Lesson(String name, String day, String time, double price) {
        this.name = name;
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
}