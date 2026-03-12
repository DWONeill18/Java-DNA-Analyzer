import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReadDNATest {

    @Test
    void analysisMenu_option1_validFile_printsContents_andCloses() throws IOException {
        String filePath = Paths.get("src", "test", "resources", "sample-dna.txt").toString();
        String input = "1\n" + filePath + "\n9\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Menu menu = new Menu(new Scanner(in), new PrintStream(out));
        menu.analysisMenu();
        String output = out.toString();

        assertTrue(output.contains("DNA Match"));
        assertTrue(output.contains("ACGT"));
        assertTrue(output.contains("Closing down the lab"));
    }

    @Test
    void analysisMenu_option1_invalidFile_printsError_andCloses() throws IOException {
        String input = "1\nnon-existent-file.txt\n9\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Menu menu = new Menu(new Scanner(in), new PrintStream(out));
        menu.analysisMenu();
        String output = out.toString();

        assertTrue(output.contains("DNA Match"));
        assertTrue(output.contains("Error while reading file"));
        assertTrue(output.contains("Closing down the lab"));
    }

    @Test
    void analysisMenu_option1_emptyFile_printsError_andCloses() throws IOException {
        String filePath = Paths.get("src", "test", "resources", "empty-dna.txt").toString();
        String input = "1\n" + filePath + "\n9\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Menu menu = new Menu(new Scanner(in), new PrintStream(out));
        menu.analysisMenu();
        String output = out.toString();

        assertTrue(output.contains("DNA Match"));
        assertTrue(output.contains("Error: file is empty"));
        assertTrue(output.contains("Closing down the lab"));
    }
}
