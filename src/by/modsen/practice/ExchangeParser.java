package by.modsen.practice;

import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.modsen.practice.Constants.*;

public class ExchangeParser {
    public static Exchange parseExchangeFromString(String line) throws FileNotFoundException {
        Pattern pattern = Pattern.compile(KEY_REG);
        Matcher matcher = pattern.matcher(line);

        String resultHead = "";
        Exchange cost = null;

        if (matcher.find()) {
            resultHead += matcher.group(1) + OPEN_PARENTHESES;
            String[] lines = matcher.group(2).split("\\s");

            Exchange someValue;

            for (int i = 0; i < lines.length; i += 2) {
                if (lines[i].contains(TO_RUB) || lines[i].contains(TO_USD)) {
                    someValue = new Exchange(lines[i]);
                } else {
                    someValue = new Exchange(Double.parseDouble(lines[i].
                            replaceAll(NUM_REG, "").replace(COMMA, DOT)),
                            lines[i].charAt(0) == USD ? USD : RUB);
                }
                if (i == 0) {
                    cost = someValue;
                } else {
                    if (lines[i - 1].equals(PLUS)) {
                        cost = cost.add(someValue);
                    } else {
                        cost = cost.sub(someValue);
                    }
                }
            }
        }
        return new Exchange(resultHead + cost + CLOSE_PARENTHESES);
    }
}
