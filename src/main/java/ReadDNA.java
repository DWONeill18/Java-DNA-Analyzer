import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Optional;

public class ReadDNA {

    private final PrintStream out;

    public ReadDNA(PrintStream out) {
        this.out = out;
    }

    public Optional<String> readFile(String fileName) {
        StringBuilder sb = new StringBuilder();

        try (FileReader reader = new FileReader(fileName)) {
            int ch = reader.read();
            if (ch == -1) {
                out.println("Error: file is empty");
                return Optional.empty();
            }
            do {
                sb.append((char) ch);
            } while ((ch = reader.read()) != -1);
        } catch (IOException e) {
            out.println("Error while reading file");
            return Optional.empty();
        }

        return Optional.of(sb.toString());
    }

}
