import java.util.Random;
import java.util.Scanner;

public class TeamBmiCalculator {

    /**
     * Classifies health status based on BMI value.
     */
    public static String getBmiStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25.0) {
            return "Normal";
        } else if (bmi < 30.0) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    /**
     * Calculates BMI for each person and displays a formatted wellness report table.
     */
    public static void printWellnessReport(double[] heights, double[] weights) {
        int n = Math.min(heights.length, weights.length);

        System.out.println("\n----------------------------------------------------------------------------");
        System.out.printf("%-10s | %-12s | %-12s | %-10s | %-15s%n",
                "Person", "Height (m)", "Weight (kg)", "BMI", "Status");
        System.out.println("----------------------------------------------------------------------------");

        for (int i = 0; i < n; i++) {
            double height = heights[i];
            double weight = weights[i];
            double bmi = weight / (height * height);
            String status = getBmiStatus(bmi);

            System.out.printf("Person %-3d | %-12.2f | %-12.2f | %-10.2f | %-15s%n",
                    (i + 1), height, weight, bmi, status);
        }
        System.out.println("----------------------------------------------------------------------------\n");

        System.out.println("Detailed Summary:");
        for (int i = 0; i < n; i++) {
            double height = heights[i];
            double weight = weights[i];
            double bmi = weight / (height * height);
            String status = getBmiStatus(bmi);

            System.out.printf("Person %d — Height: %.2f m, Weight: %.0f kg | BMI: %.2f | Status: %s%n",
                    (i + 1), height, weight, bmi, status);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Corporate Wellness Program: Team BMI Calculator ===");
        System.out.print("Choose mode: (1) Fast Live Demo with 10 people, (2) Manual Entry: ");
        int choice = 1;
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
        }

        if (choice == 2) {
            System.out.print("Enter number of team members: ");
            int count = scanner.nextInt();
            double[] heights = new double[count];
            double[] weights = new double[count];

            for (int i = 0; i < count; i++) {
                System.out.println("\nPerson " + (i + 1) + ":");
                System.out.print("  Height (in meters, e.g. 1.75): ");
                heights[i] = scanner.nextDouble();
                System.out.print("  Weight (in kg, e.g. 70): ");
                weights[i] = scanner.nextDouble();
            }

            printWellnessReport(heights, weights);
        } else {
            // Live Demo with 10 team members with realistic heights and weights
            System.out.println("\nGenerating live demo report for 10 team members...");
            Random random = new Random();
            int count = 10;
            double[] heights = new double[count];
            double[] weights = new double[count];

            for (int i = 0; i < count; i++) {
                // Height between 1.50m and 1.95m
                heights[i] = 1.50 + (random.nextDouble() * 0.45);
                // Weight between 45kg and 110kg
                weights[i] = 45.0 + (random.nextDouble() * 65.0);
            }

            printWellnessReport(heights, weights);
        }

        scanner.close();
    }
}
