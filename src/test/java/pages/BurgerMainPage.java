package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BurgerMainPage {
    private final WebDriver driver;
    public static final By BURGER_PAGE_HEADER = By.xpath("//h1[text()='Соберите бургер']");
    private final By enterAccountButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By bunsButton = By.xpath("//span[text()='Булки']");
    private final By saucesButton = By.xpath("//span[text()='Соусы']");
    private final By fillingsButton = By.xpath("//span[text()='Начинки']");
    private final By focusedConstructorPart = By.xpath("//div[contains(@class,'current')]");

    public BurgerMainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterAccountFromMain() {
        driver.findElement(enterAccountButton).isEnabled();
        driver.findElement(enterAccountButton).click();
    }


    public Boolean isBurgerPageVisible() {
        List<WebElement> makeBurger = driver.findElements(BURGER_PAGE_HEADER);
        return ((List<?>) makeBurger).size() == 1;
    }

    public void selectBuns() {
        driver.findElement(bunsButton).click();
    }

    public void selectSauces() {
        driver.findElement(saucesButton).click();
    }

    public void selectFillings() {
        driver.findElement(fillingsButton).click();
    }

    public String getElementInFocus() {
        return driver.findElement(focusedConstructorPart).getText();
    }
}
