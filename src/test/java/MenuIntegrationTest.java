import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("integration")
class MenuIntegrationTest {

    @TempDir
    Path tempDir;

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
    void analysisMenu_option2_readsAndWritesReplication() throws Exception {
        Path inputFile = tempDir.resolve("input-dna.txt");
        Path outputFile = tempDir.resolve("output-dna.txt");
        Files.writeString(inputFile, "ACGTGA");

        String input = "2\n" + inputFile + "\n" + outputFile + "\n9\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Menu menu = new Menu(new Scanner(in), new PrintStream(out));
        menu.analysisMenu();
        String output = out.toString();

        assertTrue(output.contains("DNA Replication"));
        assertTrue(output.contains("Enter file path to output file: "));
        assertTrue(output.contains("Closing down the lab"));
        assertTrue(Files.exists(outputFile));
        assertEquals("TGCACT", Files.readString(outputFile));
    }

    @Test
    void analysisMenu_option2_invalidDna_printsErrorAndDoesNotPromptForOutputPath() throws Exception {
        Path inputFile = tempDir.resolve("invalid-dna.txt");
        Files.writeString(inputFile, "ACGTG");

        String input = "2\n" + inputFile + "\n9\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Menu menu = new Menu(new Scanner(in), new PrintStream(out));
        menu.analysisMenu();
        String output = out.toString();

        assertTrue(output.contains("DNA Replication"));
        assertTrue(output.contains("DNA length must be divisible by 3"));
        assertTrue(output.contains("Closing down the lab"));
        assertTrue(!output.contains("Enter file path to output file: "));
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

    @Test
    void analysisMenu_option1_readsAndWritesFile() throws Exception {
        Path inputFile = tempDir.resolve("input-dna.txt");
        Path outputFile = tempDir.resolve("output-dna.txt");
        Files.writeString(inputFile, "ACGTGA");

        String input = "1\n" + inputFile + "\nACG\n" + outputFile + "\n9\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Menu menu = new Menu(new Scanner(in), new PrintStream(out));
        menu.analysisMenu();

        String output = out.toString();
        assertTrue(output.contains("DNA Match"));
        assertTrue(output.contains("Enter codon: "));
        assertTrue(output.contains("Codon ACG appears 1 times."));
        assertTrue(output.contains("Closing down the lab"));
        assertTrue(Files.exists(outputFile));
        assertEquals("ACGTGA", Files.readString(outputFile));
    }

    @Test
    void analysisMenu_option1_writeFailure_printsError() throws Exception {
        Path inputFile = tempDir.resolve("input-dna.txt");
        Files.writeString(inputFile, "ACGTGA");

        String input = "1\n" + inputFile + "\nACG\n" + tempDir + "\n9\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Menu menu = new Menu(new Scanner(in), new PrintStream(out));
        menu.analysisMenu();

        String output = out.toString();
        assertTrue(output.contains("DNA Match"));
        assertTrue(output.contains("Enter codon: "));
        assertTrue(output.contains("Codon ACG appears 1 times."));
        assertTrue(output.contains("Error while writing file"));
        assertTrue(output.contains("Closing down the lab"));
    }

    @Test
    void analysisMenu_option1_readFailure_doesNotPromptForOutputPath() throws Exception {
        Path missingFile = tempDir.resolve("missing-dna.txt");
        String input = "1\n" + missingFile + "\n9\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Menu menu = new Menu(new Scanner(in), new PrintStream(out));
        menu.analysisMenu();

        String output = out.toString();
        assertTrue(output.contains("DNA Match"));
        assertTrue(output.contains("Error while reading file"));
        assertTrue(output.contains("No data to write; aborting."));
        assertTrue(output.contains("Closing down the lab"));
        assertTrue(!output.contains("Enter file path to output file: "));
    }

    @Test
    void analysisMenu_option1_emptyFile_doesNotPromptForOutputPath() throws Exception {
        Path emptyFile = tempDir.resolve("empty-dna.txt");
        Files.writeString(emptyFile, "");

        String input = "1\n" + emptyFile + "\n9\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Menu menu = new Menu(new Scanner(in), new PrintStream(out));
        menu.analysisMenu();

        String output = out.toString();
        assertTrue(output.contains("DNA Match"));
        assertTrue(output.contains("Error: file is empty"));
        assertTrue(output.contains("No data to write; aborting."));
        assertTrue(output.contains("Closing down the lab"));
        assertTrue(!output.contains("Enter file path to output file: "));
    }

    @Test
    void analysisMenu_option1_codonCount_zeroTimes() throws Exception {
        Path inputFile = tempDir.resolve("input-dna.txt");
        Path outputFile = tempDir.resolve("output-dna.txt");
        Files.writeString(inputFile, "ACGACGACG");

        String input = "1\n" + inputFile + "\nAAA\n" + outputFile + "\n9\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Menu menu = new Menu(new Scanner(in), new PrintStream(out));
        menu.analysisMenu();

        String output = out.toString();
        assertTrue(output.contains("Codon AAA appears 0 times."));
    }

    @Test
    void analysisMenu_option1_codonCount_multipleTimes() throws Exception {
        Path inputFile = tempDir.resolve("input-dna.txt");
        Path outputFile = tempDir.resolve("output-dna.txt");
        Files.writeString(inputFile, "ACGACGACG");

        String input = "1\n" + inputFile + "\nACG\n" + outputFile + "\n9\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Menu menu = new Menu(new Scanner(in), new PrintStream(out));
        menu.analysisMenu();

        String output = out.toString();
        assertTrue(output.contains("Enter codon: "));
        assertTrue(!output.contains("Invalid codon. Please enter a 3-letter codon using A, C, G, T."));
        assertTrue(output.contains("Codon ACG appears 3 times."));
    }

    @Test
    void analysisMenu_option1_invalidCodonInput_reprompts() throws Exception {
        Path inputFile = tempDir.resolve("input-dna.txt");
        Path outputFile = tempDir.resolve("output-dna.txt");
        Files.writeString(inputFile, "ACGACGACG");

        String input = "1\n" + inputFile + "\nAX1\nACG\n" + outputFile + "\n9\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Menu menu = new Menu(new Scanner(in), new PrintStream(out));
        menu.analysisMenu();

        String output = out.toString();
        assertTrue(output.contains("Invalid codon. Please enter a 3-letter codon using A, C, G, T."));
        assertTrue(output.contains("Enter codon: "));
        assertTrue(output.contains("Codon ACG appears 3 times."));
    }

    @Test
    void analysisMenu_option1_blankCodonInput_reprompts() throws Exception {
        Path inputFile = tempDir.resolve("input-dna.txt");
        Path outputFile = tempDir.resolve("output-dna.txt");
        Files.writeString(inputFile, "ACGACGACG");

        String input = "1\n" + inputFile + "\n   \nACG\n" + outputFile + "\n9\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Menu menu = new Menu(new Scanner(in), new PrintStream(out));
        menu.analysisMenu();

        String output = out.toString();
        assertTrue(output.contains("Invalid codon. Please enter a 3-letter codon using A, C, G, T."));
        assertTrue(output.contains("Codon ACG appears 3 times."));
    }

    @Test
    void analysisMenu_option1_lowercaseCodonInput_isNormalized() throws Exception {
        Path inputFile = tempDir.resolve("input-dna.txt");
        Path outputFile = tempDir.resolve("output-dna.txt");
        Files.writeString(inputFile, "ACGACGACG");

        String input = "1\n" + inputFile + "\nacg\n" + outputFile + "\n9\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Menu menu = new Menu(new Scanner(in), new PrintStream(out));
        menu.analysisMenu();

        String output = out.toString();
        assertTrue(output.contains("Codon ACG appears 3 times."));
    }

    @Test
    void analysisMenu_option1_secondRunReadFailure_doesNotPromptForOutputPath() throws Exception {
        Path inputFile = tempDir.resolve("input-dna.txt");
        Path outputFile = tempDir.resolve("output-dna.txt");
        Path missingFile = tempDir.resolve("missing-dna.txt");
        Files.writeString(inputFile, "ACGACGACG");

        String firstInput = "1\n" + inputFile + "\nACG\n" + outputFile + "\n9\n";
        ByteArrayInputStream firstIn = new ByteArrayInputStream(firstInput.getBytes());
        ByteArrayOutputStream firstOut = new ByteArrayOutputStream();

        Menu firstMenu = new Menu(new Scanner(firstIn), new PrintStream(firstOut));
        firstMenu.analysisMenu();
        String firstOutput = firstOut.toString();
        assertTrue(firstOutput.contains("DNA Match"));
        assertTrue(firstOutput.contains("Enter codon: "));
        assertTrue(firstOutput.contains("Codon ACG appears 3 times."));
        assertTrue(firstOutput.contains("Enter file path to output file: "));

        String secondInput = "1\n" + missingFile + "\n9\n";
        ByteArrayInputStream secondIn = new ByteArrayInputStream(secondInput.getBytes());
        ByteArrayOutputStream secondOut = new ByteArrayOutputStream();

        Menu secondMenu = new Menu(new Scanner(secondIn), new PrintStream(secondOut));
        secondMenu.analysisMenu();

        String secondOutput = secondOut.toString();
        assertTrue(secondOutput.contains("Error while reading file"));
        assertTrue(secondOutput.contains("No data to write; aborting."));
        assertTrue(!secondOutput.contains("Enter file path to output file: "));
    }
}
