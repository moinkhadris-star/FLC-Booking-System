package service;

import model.Lesson;
import java.util.ArrayList;
import java.util.List;

public class LessonService {

    private List<Lesson> lessons;

    public LessonService() {
        lessons = new ArrayList<>();
    }

    public List<Lesson> getAllLessons() {
        return lessons;
    }

    public void generateLessons() {

        String[] exercises = {"Yoga", "Zumba", "Box Fit", "Body Blitz"};
        String[] days = {"Saturday", "Sunday"};
        String[] times = {"Morning", "Afternoon", "Evening"};

        double[] prices = {10.0, 12.0, 15.0, 20.0};

        for (int week = 1; week <= 8; week++) {
            for (String day : days) {
                for (int i = 0; i < times.length; i++) {

                    String exercise = exercises[i % exercises.length];
                    String time = times[i];
                    double price = prices[i % prices.length];

                    Lesson lesson = new Lesson(week, exercise, day, time, price);
                    lessons.add(lesson);
                }
            }
        }
    }

    public List<Lesson> getLessonsByDay(String day) {
        List<Lesson> result = new ArrayList<>();

        for (Lesson lesson : lessons) {
            if (lesson.getDay().equalsIgnoreCase(day)) {
                result.add(lesson);
            }
        }

        return result;
    }

    public List<Lesson> getLessonsByExercise(String exercise) {
        List<Lesson> result = new ArrayList<>();

        for (Lesson lesson : lessons) {
            if (lesson.getName().equalsIgnoreCase(exercise)) {
                result.add(lesson);
            }
        }

        return result;
    }

    public void printLessons(List<Lesson> lessonsList) {
        for (Lesson lesson : lessonsList) {
            System.out.println(
                    "Week " + lesson.getWeek() + " | " +
                            lesson.getName() + " | " +
                            lesson.getDay() + " | " +
                            lesson.getTime() + " | £" +
                            lesson.getPrice()
            );
        }
    }
}
