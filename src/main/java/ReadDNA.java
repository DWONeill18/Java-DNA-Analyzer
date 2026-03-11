import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class ReadDNA {

    private final PrintStream out;

    public ReadDNA(PrintStream out) {
        this.out = out;
    }

    public void readFile(String fileName) throws IOException {

        try (FileReader reader = new FileReader(fileName)) {
            int ch;
            while ((ch = reader.read()) != -1) {
                out.print((char) ch);
            }
        } catch (IOException e) {
            out.println("Error while reading file");
        }
    }

}
