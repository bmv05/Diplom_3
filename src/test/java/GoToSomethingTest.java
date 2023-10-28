import User.User;
import User.UserAction;
import User.UserGenerator;
import User.UserAssertion;
import helper.DriverHelper;
import helper.EnvConfig;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.*;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;
import pages.AuthorizationPage;
import pages.BurgerMainPage;
import pages.MainPage;
import pages.ProfilePage;

public class GoToSomethingTest {

    @Rule
    public DriverHelper driverHelper = new DriverHelper();
    private String accessToken = "";
    User user = UserGenerator.randomUser();


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
    @DisplayName("Проверка перехода по клику на «Личный кабинет»")
    public void checkGoToProfileTest() {
        WebDriver driver = driverHelper.getDriver();
        MainPage mainPage = new MainPage(driverHelper.getDriver());
        AuthorizationPage authorizationPage = new AuthorizationPage(driverHelper.getDriver(), user);
        ProfilePage profilePage = new ProfilePage(driverHelper.getDriver());

        driver.get(EnvConfig.BASE_URL);
        mainPage.clickAccountButton();
        authorizationPage.isLoginPageVisible();
        authorizationPage.login();

        mainPage.clickAccountButton();
        Assert.assertTrue("Страница 'Личный кабинет' не отобразилась", profilePage.isProfileVisible());
    }
    @Test
    @DisplayName("Проверка перехода по клику на «Конструктор» из личного кабинета в конструктор")
    public void checkGoToConstructorClickConstructorTest() {
        WebDriver driver = driverHelper.getDriver();
        MainPage mainPage = new MainPage(driverHelper.getDriver());
        AuthorizationPage authorizationPage = new AuthorizationPage(driverHelper.getDriver(), user);
        BurgerMainPage burgerMainPage = new BurgerMainPage(driverHelper.getDriver());

        driver.get(EnvConfig.BASE_URL);
        mainPage.clickAccountButton();
        authorizationPage.isLoginPageVisible();
        authorizationPage.login();

        mainPage.clickAccountButton();
        mainPage.clickConstructorButton();
        Assert.assertTrue("Страница 'Соберите бургер' не отобразилась", burgerMainPage.isBurgerPageVisible());
    }
    @Test
    @DisplayName("Проверка перехода по клику на логотип Stellar Burgers из личного кабинета в конструктор")
    public void checkGoToConstructorClickLogoTest() {
        WebDriver driver = driverHelper.getDriver();
        MainPage mainPage = new MainPage(driverHelper.getDriver());
        AuthorizationPage authorizationPage = new AuthorizationPage(driverHelper.getDriver(), user);
        BurgerMainPage burgerMainPage = new BurgerMainPage(driverHelper.getDriver());

        driver.get(EnvConfig.BASE_URL);
        mainPage.clickAccountButton();
        authorizationPage.isLoginPageVisible();
        authorizationPage.login();

        mainPage.clickAccountButton();
        mainPage.clickLogo();
        Assert.assertTrue("Страница 'Соберите бургер' не отобразилась", burgerMainPage.isBurgerPageVisible());
    }
    @Test
    @DisplayName("Проверка выходf по кнопке «Выйти» в личном кабинете")
    public void checkLogoutFromAccountTest() {
        WebDriver driver = driverHelper.getDriver();
        MainPage mainPage = new MainPage(driverHelper.getDriver());
        AuthorizationPage authorizationPage = new AuthorizationPage(driverHelper.getDriver(), user);
        BurgerMainPage burgerMainPage = new BurgerMainPage(driverHelper.getDriver());
        ProfilePage profilePage = new ProfilePage(driverHelper.getDriver());

        driver.get(EnvConfig.BASE_URL);
        mainPage.clickAccountButton();
        authorizationPage.isLoginPageVisible();
        authorizationPage.login();

        mainPage.clickAccountButton();
        profilePage.clickLogoutButton();
        Assert.assertTrue("Страница 'Вход' не отобразилась", authorizationPage.isLoginPageVisible());
    }
}

