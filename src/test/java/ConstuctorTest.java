import helper.DriverHelper;
import helper.EnvConfig;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;
import pages.AuthorizationPage;
import pages.BurgerMainPage;
import pages.MainPage;

public class ConstuctorTest {
    @Rule
    public DriverHelper driverHelper = new DriverHelper();

    @Test
    @DisplayName("Проверка перехода в раздел «Булки»")
    public void checkGoToBunsSectionTest() {
        WebDriver driver = driverHelper.getDriver();
        BurgerMainPage burgerMainPage = new BurgerMainPage(driverHelper.getDriver());

        driver.get(EnvConfig.BASE_URL);
        burgerMainPage.isBurgerPageVisible();

        String focusedElementText = burgerMainPage.getElementInFocus();
        Assert.assertEquals("Булки",focusedElementText);
    }
    @Test
    @DisplayName("Проверка, что при открытии страницы отображается активный раздел «Булки»")
    public void checkBunsSectionTest() {
        WebDriver driver = driverHelper.getDriver();
        BurgerMainPage burgerMainPage = new BurgerMainPage(driverHelper.getDriver());

        driver.get(EnvConfig.BASE_URL);
        burgerMainPage.isBurgerPageVisible();

        String focusedElementText = burgerMainPage.getElementInFocus();
        Assert.assertEquals("Булки",focusedElementText);
    }
    @Test
    @DisplayName("Проверка перехода в раздел «Соусы»")
    public void checkGoToSaucesSectionTest() {
        WebDriver driver = driverHelper.getDriver();
        BurgerMainPage burgerMainPage = new BurgerMainPage(driverHelper.getDriver());

        driver.get(EnvConfig.BASE_URL);
        burgerMainPage.isBurgerPageVisible();

        burgerMainPage.selectSauces();
        String focusedElementText = burgerMainPage.getElementInFocus();
        Assert.assertEquals("Соусы",focusedElementText);
    }

@Test
    @DisplayName("Проверка перехода в раздел «Начинки»")
    public void checkGoToFillingsSectionTest() {
        WebDriver driver = driverHelper.getDriver();
        BurgerMainPage burgerMainPage = new BurgerMainPage(driverHelper.getDriver());

        driver.get(EnvConfig.BASE_URL);
        burgerMainPage.isBurgerPageVisible();

        burgerMainPage.selectFillings();
        String focusedElementText = burgerMainPage.getElementInFocus();
        Assert.assertEquals("Начинки",focusedElementText);
    }
}
