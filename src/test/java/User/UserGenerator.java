package User;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomUtils;

public class UserGenerator {
    Faker faker = new Faker();

    public static User randomUser(Faker faker) {
        return new User(
                faker.internet().emailAddress(),
                faker.internet().password(),
                faker.name().name());
    }

    public static User randomUserWithFiveSymbolPassword(Faker faker) {
        return new User(
                faker.internet().emailAddress(),
                "Pas" + RandomUtils.nextInt(10, 99),
                faker.name().name());
    }
}
