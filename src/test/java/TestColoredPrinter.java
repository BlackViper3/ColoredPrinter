/**
 * MIT License
 *
 * Copyright (c) 2021 BlackViper3
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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
