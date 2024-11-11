import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of grades to be entered: ");
        int numGrades = scanner.nextInt();

        double[] grades = new double[numGrades];

        System.out.println("Enter the grades:");
        for (int i = 0; i < numGrades; i++) {
            System.out.printf("Enter the grade for subject %d: ", i + 1);;
            grades[i] = scanner.nextDouble();
        }

        double average = calculateAverage(grades);

        System.out.printf("The average grade is: %.2f\n", average);
    }

    public static double calculateAverage(double[] grades) {
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.length;
    }
}