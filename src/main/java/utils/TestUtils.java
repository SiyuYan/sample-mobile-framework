package utils;

import java.util.Random;

public class TestUtils {
    private static final Random random = new Random();
    public static String[] generateRandomPasscode() {
        String[] passcodeDigits = new String[6];
        for (int i = 0; i < 6; i++) {
            passcodeDigits[i] = String.valueOf(random.nextInt(10));
        }
        System.out.println("Generated passcode: " + String.join("", passcodeDigits));
        return passcodeDigits;
    }
}
