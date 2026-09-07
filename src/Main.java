import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        StudyTracker tracker = new StudyTracker();
        int option = 0;
        do {
            System.out.println("==============================");
            System.out.println("       STUDY TRACKER");
            System.out.println("==============================");
            System.out.println("1. Add Subject");
            System.out.println("2. View Subjects");
            System.out.println("3. Add Study Session");
            System.out.println("4. View Study Sessions");
            System.out.println("5. Search Subject");
            System.out.println("6. Track Total Study Time");
            System.out.println("7. Remove Subject");
            System.out.println("8. Exit");
            System.out.println("==============================");

            System.out.print("Choose an option: ");
            try {
                option = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number (1-8).");
                scanner.nextLine();
                continue;
            }
            switch (option) {
                case 1 -> {
                    System.out.print("Enter Subject Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Subject ID: ");
                    String id = scanner.nextLine();
                    AddSubjectResult result = tracker.addSubject(name, id);
                    if (result == AddSubjectResult.INVALID) {
                        System.out.println("Invalid name or ID");
                    } else if (result == AddSubjectResult.ADDED) {
                        System.out.println("Subject added!");
                    } else if (result==AddSubjectResult.DUPLICATE){
                        System.out.println("Subject exists already!");
                    }
                }
                case 2 -> {
                    for(Subject subject: tracker.getSubjects()) {
                        System.out.println("Subject Name: " + subject.getName());
                        System.out.println("Subject ID: " + subject.getId());
                    }
                }
                case 3 -> {
                    System.out.print("Enter Subject ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Duration: ");
                    try {
                        int duration = scanner.nextInt();
                        scanner.nextLine();
                        if (duration < 5) {
                            System.out.println("Duration must be at least 5 minutes!");
                            break;
                        }
                        boolean added = tracker.addStudySession(id, duration);
                        if (added) {
                            System.out.println("Session added!");
                        } else {
                            System.out.println("Unexisting Subject!");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input! Please enter a number.");
                        scanner.nextLine();
                    }
                }
                case 4 -> {
                    for(StudySession session: tracker.getStudySessions()) {
                        System.out.println("Subject Name: " + session.getSubject().getName());
                        System.out.println("Subject ID: " + session.getSubject().getId());
                        System.out.println("Date: " + session.getDate());
                        System.out.println("Duration: " + session.getDuration() + " minutes");
                    }
                }
                case 5 -> {
                    System.out.print("Enter Subject ID: ");
                    String id = scanner.nextLine();
                    if (tracker.searchSubject(id) != null) {
                        System.out.println("Subject exists!");
                    } else {
                        System.out.println("Subject doesn't exist!");
                    }
                }
                case 6 -> {
                    int total = tracker.getTotalStudyTime();
                    System.out.println("Total Study Time: " + total + " minutes");
                }
                case 7 -> {
                    System.out.print("Enter Subject ID: ");
                    String id = scanner.nextLine();
                    if (tracker.removeSubject(id)) {
                        System.out.println("Subject removed!!");
                    } else {
                        System.out.println("Unexisting Subject");
                    }
                }
                case 8 -> {
                    System.out.println("Good bye😊");
                    System.out.println("Exiting....");
                }
                default -> {
                    System.out.println("Invalid option!");
                }
            }
        } while (option != 8);

        scanner.close();
    }
}