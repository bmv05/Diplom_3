import User.User;
import User.UserAction;
import User.UserAssertion;
import User.UserGenerator;
import helper.DriverHelper;
import helper.EnvConfig;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.WebDriver;
import pages.*;

import static junit.framework.Assert.*;

public class AuthorizationTest {
    @Rule
    public DriverHelper driverHelper = new DriverHelper();
    private String accessToken = "";
    User user = UserGenerator.randomUser();
    ;

    @Before
    public void createUser() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        ValidatableResponse response = UserAction.createNewUser(user);
        if (UserAssertion.isAuthorized(response)) {
            accessToken = response.extract().path("accessToken");
        }
    }

    @After
    public void deleteUser() {
        if (!accessToken.isEmpty()) {
            ValidatableResponse deleteUser = UserAction.deleteCreatedUser(accessToken);
            UserAssertion.assertUserDelete(deleteUser);
        }
    }

    @Test
    @DisplayName("Проверка входа через кнопку «Личный кабинет»")
    public void authorizationFromAccountTest() {
        WebDriver driver = driverHelper.getDriver();
        MainPage mainPage = new MainPage(driverHelper.getDriver());
        AuthorizationPage authorizationPage = new AuthorizationPage(driverHelper.getDriver(), user);
        BurgerMainPage burgerMainPage = new BurgerMainPage(driverHelper.getDriver());

        driver.get(EnvConfig.BASE_URL);
        mainPage.clickAccountButton();
        authorizationPage.isLoginPageVisible();
        authorizationPage.login();

        assertTrue("Страница 'Соберите бургер' не отобразилась", burgerMainPage.isBurgerPageVisible());
    }

    @Test
    @DisplayName("Проверка входа по кнопке «Войти в аккаунт» на главной")
    public void authorizationFromHomePageTest() {
        WebDriver driver = driverHelper.getDriver();
        AuthorizationPage authorizationPage = new AuthorizationPage(driverHelper.getDriver(), user);
        BurgerMainPage burgerMainPage = new BurgerMainPage(driverHelper.getDriver());

        driver.get(EnvConfig.BASE_URL);
        burgerMainPage.enterAccountFromMain();
        authorizationPage.isLoginPageVisible();
        authorizationPage.login();

        assertTrue("Страница 'Соберите бургер' не отобразилась", burgerMainPage.isBurgerPageVisible());
    }

    @Test
    @DisplayName("Проверка входа через кнопку в форме регистрации")
    public void authorizationFromFormRegistrationTest() {
        WebDriver driver = driverHelper.getDriver();
        MainPage mainPage = new MainPage(driverHelper.getDriver());
        AuthorizationPage authorizationPage = new AuthorizationPage(driverHelper.getDriver(), user);
        RegistrationPage registrationPage = new RegistrationPage(driverHelper.getDriver(), user);
        BurgerMainPage burgerMainPage = new BurgerMainPage(driverHelper.getDriver());

        driver.get(EnvConfig.BASE_URL);
        mainPage.clickAccountButton();
        authorizationPage.isLoginPageVisible();
        authorizationPage.clickRegistrationButton();
        registrationPage.clickEnterButton();
        authorizationPage.isLoginPageVisible();
        authorizationPage.login();

        assertTrue("Страница 'Соберите бургер' не отобразилась", burgerMainPage.isBurgerPageVisible());
    }

    @Test
    @DisplayName("Проверка входа через кнопку в форме восстановления пароля")
    public void authorizationFromFormRecoveryTest() {
        WebDriver driver = driverHelper.getDriver();
        MainPage mainPage = new MainPage(driverHelper.getDriver());
        AuthorizationPage authorizationPage = new AuthorizationPage(driverHelper.getDriver(), user);
        BurgerMainPage burgerMainPage = new BurgerMainPage(driverHelper.getDriver());
        PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driverHelper.getDriver());

        driver.get(EnvConfig.BASE_URL);
        mainPage.clickAccountButton();
        authorizationPage.isLoginPageVisible();
        authorizationPage.clickResetButton();
        passwordRecoveryPage.isResetPasswordPageVisible();
        passwordRecoveryPage.clickEnterButton();
        authorizationPage.login();

        assertTrue("Страница 'Соберите бургер' не отобразилась", burgerMainPage.isBurgerPageVisible());
    }
}
