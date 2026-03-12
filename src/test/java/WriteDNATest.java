import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WriteDNATest {

    @Test
    public void testWriteDNA() throws IOException {

        // Setup filepath and user input
        String filePath = "src/test/resources/output-dna.txt";
        String input = "1\n" + filePath + "9\n";

        // Setup input and output streams
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // Initialize Menu and output
        Menu menu = new Menu(new Scanner(in), new PrintStream(out));
        menu.analysisMenu();
        String output = out.toString();

        // Write contents of read file to new file
        FileWriter fw = new FileWriter(filePath);
        fw.write(output);

        // Asssertions
        assertTrue(output.contains("DNA Match"));
        assertTrue(output.contains("Closing down the lab"));
        assertEquals("output-dna.txt", filePath);
        assertTrue(new File("src/test/resources/output-dna.txt").exists());



    }
}
