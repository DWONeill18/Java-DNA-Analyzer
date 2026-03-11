import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Menu {

    //Scanner scanner = new Scanner(System.in);  // Create a Scanner object
    private final Scanner scanner;
    private final PrintStream out;

    public Menu(Scanner scanner, PrintStream out) {
        this.scanner = scanner;
        this.out = out;
    }


    public void welcomeMenu() {
        out.println("---------------------------------");
        out.println("---- Welcome to the DNA Lab! ----");
        out.println("---------------------------------");
    }

    private void printMenu() {
        out.println("\n1. DNA Match");
        out.println("2. DNA Replication");
        out.println("3. DNA Transcription");
        out.println("4. DNA Translation");
        out.println("5. Random DNA Generator");
        out.println("6. Random DNA Mutation");
        out.println("7. Reverse Transcription");
        out.println("8. Information");
        out.println("9. Exit\n");
    }

    private int readOption() {
        while (true) {

            printMenu();
            out.print("Enter option (1-9): ");

            if (!scanner.hasNextInt()) {
                out.println("Invalid input, please enter a number from 1-9");
                scanner.nextLine(); // clear bad input
                continue;
            }

            int option = scanner.nextInt();

            if (option < 1 || option > 9) {
                out.println("Please choose a valid option (1-9)");
                continue;
            }

            return option;  // valid input exits loop
        }
    }

    public void analysisMenu() throws IOException {
        boolean running = true;
        while (running) {

            int option = readOption();

            switch (option) {
                case 1 -> {
                    out.println("DNA Match");
                    out.print("Enter file path: ");
                    scanner.nextLine();
                    String fileName = scanner.nextLine();

                    ReadDNA readDNA = new ReadDNA(out);
                    readDNA.readFile(fileName);
                }

                case 2 -> out.println("DNA Replication");
                case 3 -> out.println("DNA Transcription");
                case 4 -> out.println("DNA Translation");
                case 5 -> out.println("Random DNA Generator");
                case 6 -> out.println("Mutation");
                case 7 -> out.println("Reverse Transcription");
                case 8 -> out.println(
                        "Option Information" + System.lineSeparator() +
                        "1. DNA Match - Check whether suspect matches sample DNA" + System.lineSeparator() +
                        "2. DNA Replication - Replicate a DNA strand to give both it's original and complemetary strands" + System.lineSeparator() +
                        "3. DNA Transcription - Transcribe a DNA strand to its mRNA counterpart" + System.lineSeparator() +
                        "4. mRNA Translation - Translate an mRNA strand to amino acids in a polypeptide chain" + System.lineSeparator() +
                        "5. Random DNA Generator - Create a random DNA strand given a number of DNA bases" + System.lineSeparator() +
                        "6. Random DNA Mutation - Insert a random DNA base into a DNA strand to cause a mutation" + System.lineSeparator() +
                        "7. Reverse Transcription - Transcribe an RNA strand back to its DNA counterpart" + System.lineSeparator());
                case 9 -> {
                    out.println("Closing down the lab" + System.lineSeparator());
                    running = false;
                }
            }
        }
    }
}
