package myproject;

import org.example.myproject.App;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void testMainMethod() {
        // Redirect standard out to capture println statements
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Run the main method within a time constraint
        assertTimeout(ofSeconds(5), () -> {
            App.main(null);

            // Delay to allow SwingUtilities.invokeLater to execute
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Check that a frame was created
            assertTrue(outContent.toString().contains("Frame created"));
        });

        // Reset standard out
        System.setOut(System.out);
    }
}
