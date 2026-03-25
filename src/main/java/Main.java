import java.io.IOException;
import java.util.Scanner;

/**
 * Application entry point that boots the CLI menu.
 */
public class Main {
    /**
     * Starts the DNA Lab console application.
     *
     * @param args command-line arguments (unused)
     * @throws IOException when menu flow triggers IO failures
     */
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        Menu menu = new Menu(scanner, System.out);

        menu.welcomeMenu();
        menu.analysisMenu();

        scanner.close();
    }
}
