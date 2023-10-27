package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final By LOGO = By.xpath("//*[@class='AppHeader_header__logo__2D0X2']");
    private final By constructor = By.xpath("//p[text()='Конструктор']");
    private final By ordersButton = By.xpath("//p[text()='Лента Заказов']");
    private final By accountButton = By.xpath("//p[text()='Личный Кабинет']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLogo() {
        driver.findElement(LOGO).isEnabled();
        driver.findElement(LOGO).click();
    }

    public void clickConstructorButton() {
        driver.findElement(constructor).isEnabled();
        driver.findElement(constructor).click();
    }

    public void clickAccountButton() {
        driver.findElement(accountButton).isEnabled();
        driver.findElement(accountButton).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
}

