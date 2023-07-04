package by.modsen.practice;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static by.modsen.practice.Constants.*;

public class Exchange {
    private final int value;
    private final char type;

    //  constructor using for init kopecks or cents
    public Exchange(int value, char type) {
        this.value = value;
        this.type = type;
    }

    //  constructor using for init rub or usd and transfer them in kopecks or cents
    public Exchange(double value, char type) {
        this.value = (int) (value * 100);
        this.type = type;
    }

    public Exchange(Exchange exchange) {
        this(exchange.value, exchange.type);
    }

    public Exchange(String line) throws FileNotFoundException {
        this(convertBetweenExchange(line));
    }

    //  convert a value between currencies
    private static Exchange convertBetweenExchange(String line) throws FileNotFoundException {
        try (Scanner sc = new Scanner(new FileReader(USD_RATES_PATH))) {
            final double DOLLAR_RATE = Double.parseDouble(sc.next());
            int finalValue = (int) (Double.parseDouble(line.replaceAll(NUM_REG, "").
                    replace(COMMA, DOT)) * 100);
            if (line.contains(TO_RUB)) {
                return new Exchange(finalValue * DOLLAR_RATE / 100, RUB);
            } else {
                return new Exchange(finalValue / DOLLAR_RATE / 100, USD);
            }
        }
    }

    public int getValue() {
        return value;
    }

    public char getType() {
        return type;
    }

    public Exchange add(Exchange exchange) {
        return new Exchange(value + exchange.value, type);
    }

    public Exchange sub(Exchange exchange) {
        return new Exchange(value - exchange.value, type);
    }

    @Override
    public String toString() {
        String outputValue = String.format("%d.%02d", value / 100, Math.abs(value % 100));
        return type == USD ? type + outputValue : outputValue + type;
    }
}
