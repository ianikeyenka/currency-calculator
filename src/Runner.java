import by.modsen.practice.Exchange;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static by.modsen.practice.ExchangeParser.parseExchangeFromString;

public class Runner {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner sc = new Scanner(System.in)) {
            String input = sc.nextLine();
            Exchange finalValue = parseExchangeFromString(input);
            System.out.println(finalValue);
        }
    }
}
