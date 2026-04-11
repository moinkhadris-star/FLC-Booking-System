package main;

import model.Member;
import model.Lesson;
import model.Booking;
import service.LessonService;

public class MainApp {
    public static void main(String[] args) {

        LessonService lessonService = new LessonService();
        lessonService.generateLessons();

        System.out.println("---- Saturday Lessons ----");
        lessonService.printLessons(lessonService.getLessonsByDay("Saturday"));

        System.out.println("\n---- Yoga Lessons ----");
        lessonService.printLessons(lessonService.getLessonsByExercise("Yoga"));
    }
}