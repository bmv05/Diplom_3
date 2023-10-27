package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class AuthorizationPage {
    private final WebDriver driver;
    private final static By LOGIN_PAGE_HEADER = By.xpath("//h2[text()='Вход']");
    private final By emailField = By.name("name");
    private final By passwordField = By.name("Пароль");
    private final By enterButton = By.xpath("//*[text()='Войти']");

    private final By resetButton = By.xpath("//a[text()='Восстановить пароль']");

    final By registrationButton = By.xpath("//a[text()='Зарегистрироваться']");


    public AuthorizationPage(WebDriver driver) {
        this.driver = driver;
    }



    public void clickRegistrationButton() {
        driver.findElement(registrationButton).isEnabled();
        driver.findElement(registrationButton).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public void clickResetButton() {
        driver.findElement(resetButton).isEnabled();
        driver.findElement(resetButton).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public void inputEmail(String email){
        driver.findElement(emailField).sendKeys(email);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    public void inputPassword(String password){
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public void clickEnterButton() {
        driver.findElement(enterButton).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public void login(String email, String password) {
        inputEmail(email);
        inputPassword(password);
        clickEnterButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public boolean isLoginPageVisible(){
        List<WebElement> loginHeader = driver.findElements(LOGIN_PAGE_HEADER);
        return loginHeader.size() == 1;
    }
}

