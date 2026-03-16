import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("unit")
public class ReadDNATest {

    @TempDir
    Path tempDir;

    @Test
    void readFile_validFile_returnsContent() throws IOException {
        Path filePath = tempDir.resolve("sample-dna.txt");
        Files.writeString(filePath, "ACGT");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ReadDNA readDNA = new ReadDNA(new PrintStream(out));

        var content = readDNA.readFile(filePath.toString());

        assertTrue(content.isPresent());
        assertEquals("ACGT", content.get());
    }

    @Test
    void readFile_missingFile_printsError_andReturnsEmpty() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ReadDNA readDNA = new ReadDNA(new PrintStream(out));

        var content = readDNA.readFile(tempDir.resolve("missing.txt").toString());
        String output = out.toString();

        assertTrue(content.isEmpty());
        assertTrue(output.contains("Error while reading file"));
    }

    @Test
    void readFile_emptyFile_printsEmptyError_andReturnsEmpty() throws IOException {
        Path filePath = tempDir.resolve("empty-dna.txt");
        Files.writeString(filePath, "");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ReadDNA readDNA = new ReadDNA(new PrintStream(out));

        var content = readDNA.readFile(filePath.toString());
        String output = out.toString();

        assertTrue(content.isEmpty());
        assertTrue(output.contains("Error: file is empty"));
    }
}
