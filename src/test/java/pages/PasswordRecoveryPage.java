package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class PasswordRecoveryPage {
    private final WebDriver driver;
    private final By RESET_PASSWORD_HEADER = By.xpath("//h2[text()='Восстановление пароля']");
    private final By enterButton = By.xpath("//*[text()='Войти']");

    public PasswordRecoveryPage(WebDriver driver) {
        this.driver = driver;
    }


    public void clickEnterButton() {
        driver.findElement(enterButton).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    public void isResetPasswordPageVisible() {
        List<WebElement> loginHeader = driver.findElements(RESET_PASSWORD_HEADER);
    }
}
