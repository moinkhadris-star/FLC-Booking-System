package service;

import model.Booking;
import model.Lesson;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class ReportService {

    private List<Booking> bookings;

    public ReportService(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public void generateMonthlyReport(int monthChoice){

        System.out.println("\n---- Monthly Lesson Report ----");

        List<Lesson> processed = new ArrayList<>();

        for (Booking b : bookings) {

            Lesson lesson = b.getLesson();

            int week = lesson.getWeek();

            if (monthChoice == 1 && week > 4) {
                continue;
            }

            if (monthChoice == 2 && week <= 4) {
                continue;
            }

            if (processed.contains(lesson)) {
                continue;
            }

            processed.add(lesson);

            int attendedCount = 0;
            double totalRating = 0;
            int ratingCount = 0;

            for (Booking lb : lesson.getBookings()) {
                if (lb.getStatus().equals("attended")) {

                    attendedCount++;

                    if (lb.getReview() != null) {
                        totalRating += lb.getReview().getRating();
                        ratingCount++;
                    }
                }
            }

            double avgRating = (ratingCount == 0) ? 0 : totalRating / ratingCount;
            System.out.println(
                    lesson.getName() + " | Week " + lesson.getWeek() +
                            " | Members: " + attendedCount +
                            " | Avg Rating: " + avgRating
            );
        }
    }

    public void generateChampionReport(int monthChoice) {

        System.out.println("\n---- Champion Exercise Report ----");

        Map<String, Double> incomeMap = new HashMap<>();

        for (Booking b : bookings) {

            Lesson lesson = b.getLesson();
            int week = lesson.getWeek();

            if (monthChoice == 1 && week > 4) {
                continue;
            }

            if (monthChoice == 2 && week <= 4) {
                continue;
            }

            if (!b.getStatus().equals("attended")) {
                continue;
            }

            String exercise = lesson.getName();
            double price = lesson.getPrice();

            incomeMap.put(
                    exercise,
                    incomeMap.getOrDefault(exercise, 0.0) + price
            );
        }

        String champion = "";
        double maxIncome = 0;

        for (String ex : incomeMap.keySet()) {

            double income = incomeMap.get(ex);

            System.out.println(ex + " → £" + income);

            if (income > maxIncome) {
                maxIncome = income;
                champion = ex;
            }
        }

        System.out.println("\nChampion Exercise: " + champion);
    }
}