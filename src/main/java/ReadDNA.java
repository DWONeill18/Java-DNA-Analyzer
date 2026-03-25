import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Optional;

/**
 * Reads DNA content from a file with basic error reporting.
 */
public class ReadDNA {

    private final PrintStream out;

    /**
     * Creates a reader that writes errors to the given stream.
     *
     * @param out output stream for error messages
     */
    public ReadDNA(PrintStream out) {
        this.out = out;
    }

    /**
     * Reads a file into a single string.
     *
     * @param fileName path to the file to read
     * @return an {@link Optional} containing file contents, or empty when the file is missing or empty
     */
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
