package User;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class UserGenerator {
    public static User randomUser() {
        return new User(
                RandomStringUtils.randomAlphabetic(5, 10).toLowerCase() + "@stellar.ru",
                "Pas" + RandomUtils.nextInt(1000, 10000),
                RandomStringUtils.randomAlphabetic(5, 15));
    }

    public static User randomUserWithFiveSymbolPassword() {
        return new User(
                RandomStringUtils.randomAlphabetic(5, 10).toLowerCase() + "@stellar.ru",
                "Pas" + RandomUtils.nextInt(10, 99),
                RandomStringUtils.randomAlphabetic(5, 15));
    }
}
