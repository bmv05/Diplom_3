import User.User;
import User.UserAction;
import User.UserAssertion;
import User.UserGenerator;
import com.github.javafaker.Faker;
import helper.DriverHelper;
import helper.EnvConfig;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.AuthorizationPage;
import pages.RegistrationPage;

public class RegistrationFormTest {
    @Rule
    public DriverHelper driverHelper = new DriverHelper();
    private String accessToken = "";
    Faker faker = new Faker();
    User user;


    @After
    public void deleteUser() {
        RestAssured.baseURI = EnvConfig.BASE_URL;
        ValidatableResponse response = UserAction.authorizationUser(user);

        if (UserAssertion.isAuthorized(response)) {
            accessToken = response.extract().path("accessToken");
        }

        if (!accessToken.isEmpty()) {
            ValidatableResponse deleteUser = UserAction.deleteCreatedUser(accessToken);
            UserAssertion.assertUserDelete(deleteUser);
        }
    }

    @Test
    @DisplayName("Проверка успешной регистрации")
    public void successfulRegistrationTest() {
        user = UserGenerator.randomUser(faker);
        WebDriver driver = driverHelper.getDriver();
        MainPage mainPage = new MainPage(driverHelper.getDriver());
        AuthorizationPage authorizationPage = new AuthorizationPage(driverHelper.getDriver(), user);
        RegistrationPage registrationPage = new RegistrationPage(driverHelper.getDriver(), user);

        driver.get(EnvConfig.BASE_URL);
        mainPage.clickAccountButton();
        authorizationPage.isLoginPageVisible();
        authorizationPage.clickRegistrationButton();
        registrationPage.register();

        Assert.assertTrue(authorizationPage.isLoginPageVisible());

    }

    @Test
    @DisplayName("Проверка ошибки регистрации. Пароль меньше 6 символов")
    public void failRegistrationTest() {
        user = UserGenerator.randomUserWithFiveSymbolPassword(faker);
        WebDriver driver = driverHelper.getDriver();
        MainPage mainPage = new MainPage(driverHelper.getDriver());
        AuthorizationPage authorizationPage = new AuthorizationPage(driverHelper.getDriver(), user);
        RegistrationPage registrationPage = new RegistrationPage(driverHelper.getDriver(), user);

        driver.get(EnvConfig.BASE_URL);
        mainPage.clickAccountButton();
        authorizationPage.isLoginPageVisible();
        authorizationPage.clickRegistrationButton();
        registrationPage.register();

        Assert.assertTrue(registrationPage.isPasswordErrorVisible());
    }
}
