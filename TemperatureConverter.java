import java.util.Scanner;

public class TemperatureConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a temperature value: ");
        double temperature = scanner.nextDouble();

        System.out.print("Enter the unit (C for Celsius, F for Fahrenheit): ");
        String unit = scanner.next();

        double convertedTemperature = 0;
        String convertedUnit = "";

        if (unit.equalsIgnoreCase("C")) {
            convertedTemperature = (temperature * 9 / 5) + 32;
            convertedUnit = "Fahrenheit";
        } else if (unit.equalsIgnoreCase("F")) {
            convertedTemperature = (temperature - 32) * 5 / 9;
            convertedUnit = "Celsius";
        } else {
            System.out.println("Enter valid unit!!!");
            return; 
        }

        System.out.println("The temperature in " + convertedUnit + " is: " + convertedTemperature);
    }
}
