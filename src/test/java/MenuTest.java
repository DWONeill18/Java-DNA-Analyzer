import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MenuTest {

    @Test
    void welcomeMenu_printsWelcomeBanner() {
        String input = "";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Menu menu = new Menu(new Scanner(in), new PrintStream(out));
        menu.welcomeMenu();

        String output = out.toString();

        assertTrue(output.contains("---------------------------------"));
        assertTrue(output.contains("---- Welcome to the DNA Lab! ----"));
        assertTrue(output.contains("---------------------------------"));
    }

    @Test
    void analysisMenu_printsMenuOptions_andClosesOnExit() throws IOException {
        String input = "\n9\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Menu menu = new Menu(new Scanner(in), new PrintStream(out));
        menu.analysisMenu();

        String output = out.toString();

        assertTrue(output.contains("Enter option (1-9): "));
        assertTrue(output.contains("1. DNA Match"));
        assertTrue(output.contains("2. DNA Replication"));
        assertTrue(output.contains("3. DNA Transcription"));
        assertTrue(output.contains("4. DNA Translation"));
        assertTrue(output.contains("5. Random DNA Generator"));
        assertTrue(output.contains("6. Random DNA Mutation"));
        assertTrue(output.contains("7. Reverse Transcription"));
        assertTrue(output.contains("8. Information"));
        assertTrue(output.contains("9. Exit"));
        assertTrue(output.contains("Closing down the lab"));
    }

    @Test
    void analysisMenu_option2_printsDnaReplication_andCloses() throws IOException {
        String input = "2\n9\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Menu menu = new Menu(new Scanner(in), new PrintStream(out));
        menu.analysisMenu();
        String output = out.toString();

        assertTrue(output.contains("DNA Replication"));
        assertTrue(output.contains("Closing down the lab"));
    }

    @Test
    void analysisMenu_option3_printsDnaTranscription_andCloses() throws IOException {
        String input = "3\n9\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Menu menu = new Menu(new Scanner(in), new PrintStream(out));
        menu.analysisMenu();
        String output = out.toString();

        assertTrue(output.contains("DNA Transcription"));
        assertTrue(output.contains("Closing down the lab"));
    }

    @Test
    void analysisMenu_option4_printsDnaTranslation_andCloses() throws IOException {
        String input = "4\n9\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Menu menu = new Menu(new Scanner(in), new PrintStream(out));
        menu.analysisMenu();

        String output = out.toString();

        assertTrue(output.contains("DNA Translation"));
        assertTrue(output.contains("Closing down the lab"));
    }

    @Test
    void analysisMenu_option5_printsRandomDnaGenerator_andCloses() throws IOException {
        String input = "5\n9\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Menu menu = new Menu(new Scanner(in), new PrintStream(out));
        menu.analysisMenu();
        String output = out.toString();

        assertTrue(output.contains("Random DNA Generator"));
        assertTrue(output.contains("Closing down the lab"));
    }

    @Test
    void analysisMenu_option6_printsMutation_andCloses() throws IOException {
        String input = "6\n9\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Menu menu = new Menu(new Scanner(in), new PrintStream(out));
        menu.analysisMenu();
        String output = out.toString();

        assertTrue(output.contains("Mutation"));
        assertTrue(output.contains("Closing down the lab"));
    }

    @Test
    void analysisMenu_option7_printsReverseTranscription_andCloses() throws IOException {
        String input = "7\n9\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Menu menu = new Menu(new Scanner(in), new PrintStream(out));
        menu.analysisMenu();
        String output = out.toString();

        assertTrue(output.contains("Reverse Transcription"));
        assertTrue(output.contains("Closing down the lab"));
    }

    @Test
    void analysisMenu_option8_printsOptionInformation_andCloses() throws IOException {
        String input = "8\n9\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Menu menu = new Menu(new Scanner(in), new PrintStream(out));
        menu.analysisMenu();
        String output = out.toString();

        assertTrue(output.contains("Option Information"));
        assertTrue(output.contains("1. DNA Match - Check whether suspect matches sample DNA"));
        assertTrue(output.contains("2. DNA Replication - Replicate a DNA strand to give both it's original and complemetary strands"));
        assertTrue(output.contains("3. DNA Transcription - Transcribe a DNA strand to its mRNA counterpart"));
        assertTrue(output.contains("4. mRNA Translation - Translate an mRNA strand to amino acids in a polypeptide chain"));
        assertTrue(output.contains("5. Random DNA Generator - Create a random DNA strand given a number of DNA bases"));
        assertTrue(output.contains("6. Random DNA Mutation - Insert a random DNA base into a DNA strand to cause a mutation"));
        assertTrue(output.contains("7. Reverse Transcription - Transcribe an RNA strand back to its DNA counterpart"));
        assertTrue(output.contains("Closing down the lab"));
    }

    @Test
    void analysisMenu_option9_printsClosingMessage_andExits() throws IOException {
        String input = "9\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Menu menu = new Menu(new Scanner(in), new PrintStream(out));
        menu.analysisMenu();
        String output = out.toString();

        assertTrue(output.contains("Closing down the lab"));
    }

    @Test
    void analysisMenu_invalidTextInput_printsError_andCloses() throws IOException {
        String input = "abc\n9\n"; // Invalid input, then exit
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Menu menu = new Menu(new Scanner(in), new PrintStream(out));
        menu.analysisMenu();

        String output = out.toString();

        assertTrue(output.contains("Invalid input, please enter a number from 1-9"));
        assertTrue(output.contains("Closing down the lab"));
    }

    @Test
    void analysisMenu_zeroInput_printsRangeError_andCloses() throws IOException {
        String input = "0\n9\n"; // Invalid input, then exit
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Menu menu = new Menu(new Scanner(in), new PrintStream(out));
        menu.analysisMenu();

        String output = out.toString();

        assertTrue(output.contains("Please choose a valid option (1-9)"));
        assertTrue(output.contains("Closing down the lab"));
    }

    @Test
    void analysisMenu_tooHighInput_printsRangeError_andCloses() throws IOException {
        String input = "10\n9\n"; // Invalid input, then exit
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Menu menu = new Menu(new Scanner(in), new PrintStream(out));
        menu.analysisMenu();

        String output = out.toString();

        assertTrue(output.contains("Please choose a valid option (1-9)"));
        assertTrue(output.contains("Closing down the lab"));
    }

    @Test
    void analysisMenu_negativeInput_printsRangeError_andCloses() throws IOException {
        String input = "-25\n9\n"; // Invalid input, then exit
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Menu menu = new Menu(new Scanner(in), new PrintStream(out));
        menu.analysisMenu();

        String output = out.toString();

        assertTrue(output.contains("Please choose a valid option (1-9)"));
        assertTrue(output.contains("Closing down the lab"));
    }
}
