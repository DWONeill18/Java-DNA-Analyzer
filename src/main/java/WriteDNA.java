import java.io.FileWriter;
import java.io.IOException;
public class WriteDNA {

    public void writeFile(String outputPath, String content) throws IOException {
        try (FileWriter fileWriter = new FileWriter(outputPath, false)) {
            fileWriter.write(content);
        }
    }
}
