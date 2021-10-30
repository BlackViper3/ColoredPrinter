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
