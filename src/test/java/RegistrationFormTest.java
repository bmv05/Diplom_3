import User.User;
import User.UserAction;
import User.UserAssertion;
import User.UserGenerator;
import helper.DriverHelper;
import helper.EnvConfig;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.AuthorizationPage;
import pages.RegistrationPage;

public class RegistrationFormTest {
    @Rule
    public DriverHelper driverHelper = new DriverHelper();
    private String accessToken = "";
    User user;


    @After
    public void deleteUser() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        ValidatableResponse response = UserAction.authorizationUser(user);
        if (UserAssertion.isAuthorizated(response)) {
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
        user = UserGenerator.randomUser();
        WebDriver driver = driverHelper.getDriver();
        MainPage mainPage = new MainPage(driverHelper.getDriver());
        AuthorizationPage authorizationPage = new AuthorizationPage(driverHelper.getDriver());
        RegistrationPage registrationPage = new RegistrationPage(driverHelper.getDriver(), user);
        driver.get(EnvConfig.BASE_URL);
        mainPage.clickAccountButton();
        authorizationPage.isLoginPageVisible();
        authorizationPage.clickRegistrationButton();
        registrationPage.register();
        authorizationPage.isLoginPageVisible();

    }

    @Test
    @DisplayName("Проверка ошибки регистрации. Пароль меньше 6 символов")
    public void failRegistrationTest() {
        user = UserGenerator.randomUserWithFiveSymbolPassword();
        WebDriver driver = driverHelper.getDriver();
        MainPage mainPage = new MainPage(driverHelper.getDriver());
        AuthorizationPage authorizationPage = new AuthorizationPage(driverHelper.getDriver());
        RegistrationPage registrationPage = new RegistrationPage(driverHelper.getDriver(), user);
        driver.get(EnvConfig.BASE_URL);
        mainPage.clickAccountButton();
        authorizationPage.isLoginPageVisible();
        authorizationPage.clickRegistrationButton();
        registrationPage.register();
        Assert.assertTrue(registrationPage.isPasswordErrorVisible());
    }
}
