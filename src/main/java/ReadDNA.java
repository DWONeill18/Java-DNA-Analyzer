import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class ReadDNA {

    private final PrintStream out;

    public ReadDNA(PrintStream out) {
        this.out = out;
    }

    public String readFile(String fileName) {
        StringBuilder sb = new StringBuilder();

        try (FileReader reader = new FileReader(fileName)) {
            int ch = reader.read();
            if (ch == -1) {
                out.println("Error: file is empty");
                return "";
            }
            do {
                sb.append((char) ch);
            } while ((ch = reader.read()) != -1);
        } catch (IOException e) {
            out.println("Error while reading file");
            return "";
        }

        return sb.toString();
    }

}
