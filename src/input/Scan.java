package input;

import java.util.Scanner;

/**
 * The {@code Scan} class provides a singleton instance of {@link Scanner}
 * for reading input from various sources, such as keyboard input.
 * <p>
 * This class is part of the {@code input} package and is designed
 * to facilitate easy access to a {@code Scanner} instance throughout the application.
 * </p>
 */
public class Scan {
    /**
     * A static {@link Scanner} instance for reading input.
     */
    public static Scanner scan = new Scanner(System.in);
}
