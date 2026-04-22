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
            System.out.println("3. Change / Cancel Booking");
            System.out.println("4. Attend Lesson");
            System.out.println("5. Monthly Report");
            System.out.println("6. Champion Exercise Report");
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

                    System.out.print("\nEnter Booking ID: ");
                    int bookingId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("1. Change Booking");
                    System.out.println("2. Cancel Booking");

                    int action = scanner.nextInt();
                    scanner.nextLine();

                    if (action == 2) {
                        bookingService.cancelBooking(bookingId);
                    } else if (action == 1) {

                        System.out.println("\n---- Available Lessons ----");

                        int newIndex  = 1;
                        for (Lesson l : lessonService.getAllLessons()) {
                            System.out.println(newIndex  + ". Week " + l.getWeek() + " | " +
                                    l.getName() + " | " + l.getDay() + " | " + l.getTime());
                            newIndex ++;
                        }

                        System.out.print("\nEnter new lesson number: ");
                        int newLessonChoice = scanner.nextInt();
                        scanner.nextLine();

                        if (newLessonChoice < 1 || newLessonChoice > lessonService.getAllLessons().size()) {
                            System.out.println("Invalid lesson selection");
                            break;
                        }

                        Lesson newLesson =
                                lessonService.getAllLessons().get(newLessonChoice - 1);

                        bookingService.changeBooking(bookingId, newLesson);
                    } else {
                        System.out.println("Invalid option");
                    }

                    break;

                case 4:

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

                    System.out.println("\nRate the lesson:");
                    System.out.println("1 - Very dissatisfied");
                    System.out.println("2 - Dissatisfied");
                    System.out.println("3 - Ok");
                    System.out.println("4 - Satisfied");
                    System.out.println("5 - Very satisfied");

                    System.out.print("Enter rating (1-5): ");
                    int rating = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter review: ");
                    String comment = scanner.nextLine();

                    // Call service
                    bookingService.attendLesson(bId, rating, comment);

                    break;

                case 5:

                    System.out.println("Select Month:");
                    System.out.println("1. May");
                    System.out.println("2. June");

                    int monthChoice = scanner.nextInt();
                    scanner.nextLine();

                    reportService.generateMonthlyReport(monthChoice);

                    break;

                case 6:

                    System.out.println("Select Month:");
                    System.out.println("1. May");
                    System.out.println("2. June");

                    int championMonthChoice = scanner.nextInt();
                    scanner.nextLine();

                    reportService.generateChampionReport(championMonthChoice);

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