package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class ProfilePage {
    private final WebDriver driver;
    private final By PROFILE_HEADER = By.xpath("//a[text()='Профиль']");
    private final By logoutButton = By.xpath("//button[text()='Выход']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public Boolean isProfileVisible() {
        List<WebElement> profile = driver.findElements(PROFILE_HEADER);
        return profile.size() == 1;
    }

    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
}
