import coloredprinter.ColoredPrinter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestColoredPrinter {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));

    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);

    }


    public static HashMap<String, String> colorAndUnicodeMap = new HashMap<>();


    static {
        Properties colorCodeProperties = new Properties();
        try {
            colorCodeProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("ANSII_Color_Code.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        colorAndUnicodeMap = (HashMap<String, String>) colorCodeProperties
                .entrySet()
                .stream()
                .collect(
                        Collectors.toMap(
                                entry -> entry.getKey().toString(),
                                entry -> entry.getValue().toString())
                );
    }

    @Test
    public void testPrintColoredMessage() throws IOException {
        ColoredPrinter coloredPrinter = new ColoredPrinter();
        coloredPrinter.coloredSysOut("Test Data","RED");
        assertTrue(outContent.toString().contains(colorAndUnicodeMap.get("RED")));
        outContent.reset();
        coloredPrinter.coloredSysOut("Test Data","BLACK");
        assertTrue(outContent.toString().contains(colorAndUnicodeMap.get("BLACK")));
        outContent.reset();

        coloredPrinter.coloredSysOut("Test Data","GREEN");
        assertTrue(outContent.toString().contains(colorAndUnicodeMap.get("GREEN")));
        outContent.reset();

        coloredPrinter.coloredSysOut("Test Data","CYAN");
        assertTrue(outContent.toString().contains(colorAndUnicodeMap.get("CYAN")));
        outContent.reset();

        coloredPrinter.coloredSysOut("Test Data","YELLOW");
        assertTrue(outContent.toString().contains(colorAndUnicodeMap.get("YELLOW")));
        outContent.reset();

        coloredPrinter.coloredSysOut("Test Data","PURPLE");
        assertTrue(outContent.toString().contains(colorAndUnicodeMap.get("PURPLE")));
        outContent.reset();

        coloredPrinter.coloredSysOut("Test Data","WHITE");
        assertTrue(outContent.toString().contains(colorAndUnicodeMap.get("WHITE")));
        outContent.reset();

        coloredPrinter.coloredSysOut("Test Data","BLACK");
        assertTrue(!outContent.toString().contains(colorAndUnicodeMap.get("WHITE")));



    }
}
