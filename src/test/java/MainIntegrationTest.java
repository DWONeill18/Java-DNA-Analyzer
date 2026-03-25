import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Integration tests for application startup.
 */
@Tag("integration")
@Tag("smoke")
class MainIntegrationTest {

    /**
     * Ensures the application prints the welcome banner and exit message.
     *
     * @throws Exception when the main method fails to execute
     */
    @Test
    void main_runsAndPrintsWelcomeAndExit() throws Exception {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        ByteArrayInputStream in = new ByteArrayInputStream("9\n".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            System.setIn(in);
            System.setOut(new PrintStream(out));

            Main.main(new String[0]);

            String output = out.toString();
            assertTrue(output.contains("---- Welcome to the DNA Lab! ----"));
            assertTrue(output.contains("Closing down the lab"));
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}
