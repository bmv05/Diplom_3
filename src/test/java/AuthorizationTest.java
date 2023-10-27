import User.User;
import User.UserAction;
import User.UserAssertion;
import User.UserGenerator;
import helper.DriverHelper;
import helper.EnvConfig;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;
import pages.AuthorizationPage;
import pages.BurgerMainPage;
import pages.MainPage;

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
        if (UserAssertion.isAuthorizated(response)) {
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
    @DisplayName("Проверка входа по кнопке «Войти в аккаунт» на главной")
    public void authorizationFromHomePageTest() {
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
}
