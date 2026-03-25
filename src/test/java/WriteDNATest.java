import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link WriteDNA}.
 */
@Tag("unit")
public class WriteDNATest {

    /**
     * Temporary directory for output files.
     */
    @TempDir
    Path tempDir;

    /**
     * Verifies that writing to a valid path creates a file with the expected content.
     *
     * @throws IOException when file IO fails
     */
    @Test
    public void writeFile_validPath_writesContent() throws IOException {
        Path outputFilePath = tempDir.resolve("output-dna.txt");
        WriteDNA writeDNA = new WriteDNA();

        writeDNA.writeFile(outputFilePath.toString(), "ACGT");

        assertTrue(Files.exists(outputFilePath));
        String actual = Files.readString(outputFilePath);
        assertEquals("ACGT", actual);
    }

    /**
     * Verifies that writing to a directory path fails with an {@link IOException}.
     */
    @Test
    public void writeFile_directoryPath_throwsIOException() {
        WriteDNA writeDNA = new WriteDNA();

        assertThrows(IOException.class, () -> writeDNA.writeFile(tempDir.toString(), "ACGT"));
    }
}
