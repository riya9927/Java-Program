import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PasswordGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the desired password length: ");
        int passwordLength = scanner.nextInt();

        List<Character> charPool = new ArrayList<>();
        System.out.print("Include numbers? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            charPool.addAll(getCharRange('0', '9'));
        }
        System.out.print("Include lowercase letters? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            charPool.addAll(getCharRange('a', 'z'));
        }
        System.out.print("Include uppercase letters? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            charPool.addAll(getCharRange('A', 'Z'));
        }
        System.out.print("Include special characters? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            charPool.addAll(getCharRange('!', '/'));
            charPool.addAll(getCharRange(':', '@'));
            charPool.addAll(getCharRange('[', '`'));
            charPool.addAll(getCharRange('{', '~'));
        }

        String password = generatePassword(passwordLength, charPool);
        System.out.println("Your randomly generated password is: " + password);
    }

    private static List<Character> getCharRange(char start, char end) {
        List<Character> charRange = new ArrayList<>();
        for (char c = start; c <= end; c++) {
            charRange.add(c);
        }
        return charRange;
    }

    private static String generatePassword(int length, List<Character> charPool) {
        Collections.shuffle(charPool);
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            password.append(charPool.get(random.nextInt(charPool.size())));
        }
        return password.toString();
    }
}