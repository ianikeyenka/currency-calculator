import by.modsen.practice.Exchange;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

import static by.modsen.practice.Constants.*;
import static by.modsen.practice.ExchangeParser.parseExchangeFromString;

public class TestRunner {
    @Test
    public void testConstructorCurrency() throws FileNotFoundException {
        Assert.assertEquals(563, new Exchange(563, USD).getValue());
        Assert.assertEquals(USD, new Exchange(563, USD).getType());
        Assert.assertEquals(RUB, new Exchange(563, RUB).getType());

        Assert.assertEquals(5630, new Exchange(56.3, USD).getValue());
        Assert.assertEquals(1, new Exchange(0.01, USD).getValue());

        Assert.assertEquals(15000,
                new Exchange(TO_USD + OPEN_PARENTHESES + 9000 + RUB + CLOSE_PARENTHESES).getValue());
        Assert.assertEquals(24000,
                new Exchange(TO_RUB + OPEN_PARENTHESES + USD + 4 + CLOSE_PARENTHESES).getValue());

        Assert.assertEquals(USD,
                new Exchange(TO_USD + OPEN_PARENTHESES + 9000 + RUB + CLOSE_PARENTHESES).getType());
        Assert.assertEquals(RUB,
                new Exchange(TO_RUB + OPEN_PARENTHESES + USD + 4 + CLOSE_PARENTHESES).getType());
    }

    @Test
    public void testToStringCurrency() {
        Assert.assertEquals("$5.63", new Exchange(5.63, USD).toString());
        Assert.assertEquals("$-3.56", new Exchange(-356, USD).toString());
        Assert.assertEquals("5.43p", new Exchange(543, RUB).toString());
        Assert.assertEquals("-54.30p", new Exchange(-54.3, RUB).toString());
        Assert.assertEquals("0.03p", new Exchange(0.03, RUB).toString());
        Assert.assertEquals("$0.30", new Exchange(0.3, USD).toString());
    }

    @Test
    public void testAddCurrency() throws FileNotFoundException {
        Assert.assertEquals(300, new Exchange(200, USD).add(new Exchange(100, USD)).getValue());
        Assert.assertEquals(94000, new Exchange(700.0, RUB).
                add(new Exchange(TO_RUB + OPEN_PARENTHESES + USD + 4 + CLOSE_PARENTHESES)).getValue());
        Assert.assertEquals(1100, new Exchange(4.0, RUB).add(new Exchange(7.0, RUB)).getValue());
    }

    @Test
    public void testSubCurrency() throws FileNotFoundException {
        Assert.assertEquals(-10000, new Exchange(200.0, USD).sub(new Exchange(300.0, USD)).getValue());
        Assert.assertEquals(700, new Exchange(70.0, RUB).
                sub(new Exchange(TO_RUB + OPEN_PARENTHESES + USD + 1.05 + CLOSE_PARENTHESES)).getValue());
        Assert.assertEquals(-300, new Exchange(4.0, RUB).sub(new Exchange(7.0, RUB)).getValue());
    }

    @Test
    public void testParseExchangeFromString() throws FileNotFoundException {
        Assert.assertEquals("$1.00", parseExchangeFromString("toDollars(60p)").toString());
        Assert.assertEquals("63.00p", parseExchangeFromString("toRubles($1,05)").toString());
        Assert.assertEquals("63.00p", parseExchangeFromString("toRubles($1.05)").toString());
        Assert.assertEquals("$97.68", parseExchangeFromString("toDollars(737р + toRubles($85,4))").toString());
        Assert.assertEquals("-25387.80p", parseExchangeFromString("toRubles($64 + toDollars(646.36p) + $80 - toDollars(34674,1p))").toString());
    }
}
