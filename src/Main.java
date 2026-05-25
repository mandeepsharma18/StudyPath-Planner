import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudyPlanner planner = new StudyPlanner(scanner);
        planner.start();
        scanner.close();
    }
}
