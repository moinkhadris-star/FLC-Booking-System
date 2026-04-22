package main;

import model.Member;
import model.Lesson;
import service.LessonService;
import service.BookingService;
import model.Booking;
import java.util.Scanner;
import service.ReportService;
import service.MemberLoader;
import java.util.List;


public class MainApp {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        LessonService lessonService = new LessonService();
        lessonService.generateLessons();

        BookingService bookingService = new BookingService();
        ReportService reportService =
                new ReportService(bookingService.getAllBookings());

        MemberLoader loader = new MemberLoader();
        List<Member> members = loader.loadMembers("members.csv");


        while (true) {
            System.out.println("1. View Timetable");
            System.out.println("2. Book Lesson");
            System.out.println("3. Attend Lesson");
            System.out.println("4. Monthly Report");
            System.out.println("5. Champion Exercise Report");
            System.out.println("0. Exit");

            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:

                    System.out.println("\nView by:");
                    System.out.println("1. Day");
                    System.out.println("2. Exercise");

                    int viewChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (viewChoice == 1) {
                        System.out.print("Enter day (Saturday/Sunday): ");
                        String day = scanner.nextLine();

                        lessonService.printLessons(
                                lessonService.getLessonsByDay(day)
                        );

                    } else if (viewChoice == 2) {
                        System.out.print("Enter exercise (Yoga/Zumba/etc): ");
                        String ex = scanner.nextLine();

                        lessonService.printLessons(
                                lessonService.getLessonsByExercise(ex)
                        );
                    } else {
                        System.out.println("Invalid option");
                    }

                    break;

                case 2:

                    System.out.println("\n---- Members ----");

                    for (Member m : members) {
                        System.out.println(m.getId() + ". " + m.getName());
                    }

                    System.out.print("Select Member ID: ");
                    int memberId = scanner.nextInt();
                    scanner.nextLine();


                    Member selectedMember = null;

                    for (Member m : members) {
                        if (m.getId() == memberId) {
                            selectedMember = m;
                            break;
                        }
                    }

                    if (selectedMember == null) {
                        System.out.println("Invalid Member ID");
                        break;
                    }

                    // Show lessons
                    System.out.println("\n---- Available Lessons ----");

                    int index = 1;
                    for (Lesson l : lessonService.getAllLessons()) {
                        System.out.println(index + ". Week " + l.getWeek() + " | " +
                                l.getName() + " | " + l.getDay() + " | " + l.getTime());
                        index++;
                    }

                    // Select lesson
                    System.out.print("\nEnter lesson number: ");
                    int lessonChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (lessonChoice < 1 || lessonChoice > lessonService.getAllLessons().size()) {
                        System.out.println("Invalid lesson selection");
                        break;
                    }

                    Lesson selectedLesson =
                            lessonService.getAllLessons().get(lessonChoice - 1);

                    // Book lesson
                    bookingService.bookLesson(selectedMember, selectedLesson);

                    break;

                case 3:

                    // Show bookings first
                    System.out.println("\n---- Your Bookings ----");

                    for (Booking b : bookingService.getAllBookings()) {
                        System.out.println("ID: " + b.getBookingId() +
                                " | " + b.getMember().getName() +
                                " | " + b.getLesson().getName() +
                                " | Week " + b.getLesson().getWeek() +
                                " | Status: " + b.getStatus());
                    }

                    if (bookingService.getAllBookings().isEmpty()) {
                        System.out.println("No bookings available");
                        break;
                    }

                    // Take input
                    System.out.print("\nEnter Booking ID: ");
                    int bId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter rating (1-5): ");
                    int rating = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter review: ");
                    String comment = scanner.nextLine();

                    // Call service
                    bookingService.attendLesson(bId, rating, comment);

                    break;

                case 4:
                    reportService.generateMonthlyReport();
                    break;

                case 5:
                    reportService.generateChampionReport();
                    break;

                case 0:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}