import java.io.FileWriter;
import java.io.IOException;
/**
 * Writes DNA content to a file.
 */
public class WriteDNA {

    /**
     * Writes content to the given path, replacing any existing file contents.
     *
     * @param outputPath destination path
     * @param content    content to write
     * @throws IOException when the file cannot be written
     */
    public void writeFile(String outputPath, String content) throws IOException {
        try (FileWriter fileWriter = new FileWriter(outputPath, false)) {
            fileWriter.write(content);
        }
    }
}
