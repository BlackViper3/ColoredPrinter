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
package coloredprinter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.stream.Collectors;

public class ColoredPrinter {

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

    public void coloredSysOut(String Text, String Color) {
        if (colorAndUnicodeMap.get(Color) != null)
            System.out.println(colorAndUnicodeMap.get(Color) + Text + colorAndUnicodeMap.get("RESET"));
        else
            System.out.println(Text);
    }

    public static void main(String[] args) {
        ColoredPrinter coloredPrinter = new ColoredPrinter();
        coloredPrinter.coloredSysOut("Hello","CYAN");
    }
}
