import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        Menu menu = new Menu(scanner, System.out);

        menu.welcomeMenu();
        menu.analysisMenu();

        scanner.close();
    }
}
