import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public void welcomeMenu() {
        System.out.println("############################################");
        System.out.println("##### DNA Lab Analysis -- Version 1.2 #####");
        System.out.println("############################################");
        System.out.println("############################################");
        System.out.println("########## Welcome to the DNA Lab! #########");
    }

    public void analysisMenu() {
        boolean startAnalysisMenu = true;
        while (startAnalysisMenu) {
            System.out.println(System.lineSeparator() + "1. DNA Match");
            System.out.println("2. DNA Replication");
            System.out.println("3. DNA Transcription");
            System.out.println("4. DNA Translation");
            System.out.println("5. Random DNA Generator");
            System.out.println("6. Random DNA Mutation");
            System.out.println("7. Reverse Transcription");
            System.out.println("8. Information");
            System.out.println("9. Exit" + System.lineSeparator());

            Scanner scanner = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Enter option (1-9): " + System.lineSeparator());

            int option = 0;
            try {
                option = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Invalid input, please enter a number from 1-9");
            }

            switch (option) {
                case 1 -> System.out.println("DNA Match");
                case 2 -> System.out.println("DNA Replication");
                case 3 -> System.out.println("DNA Transcription");
                case 4 -> System.out.println("DNA Translation");
                case 5 -> System.out.println("Random DNA Generator");
                case 6 -> System.out.println("Mutation");
                case 7 -> System.out.println("Reverse Transcription");
                case 8 -> System.out.println(
                        "Option Information" + System.lineSeparator() +
                        "1. DNA Match - Check whether suspect matches smaple DNA" + System.lineSeparator() +
                        "2. DNA Replication - Replicate a DNA strand to give both it's original and complemetary strands" + System.lineSeparator() +
                        "3. DNA Transcription - Transcribe a DNA strand to its mRNA counterpart" + System.lineSeparator() +
                        "4. mRNA Translation - Translate an mRNA strand to amino acids in a polypeptide chain" + System.lineSeparator() +
                        "5. Random DNA Generator - Create a random DNA strand given a number of DNA bases" + System.lineSeparator() +
                        "6. Random DNA Mutation - Insert a random DNA base into a DNA strand to cause a mutation" + System.lineSeparator() +
                        "7. Reverse Transcription - Transcribe an RNA strand back to its DNA counterpart" + System.lineSeparator());
                case 9 -> {
                    System.out.println("Closing down the lab" + System.lineSeparator());
                    startAnalysisMenu = false;
                }
            }
        }
    }
}
