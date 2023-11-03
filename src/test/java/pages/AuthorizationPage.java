package pages;

import User.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class AuthorizationPage {
    private final WebDriver driver;
    private final User user;
    private final static By LOGIN_PAGE_HEADER = By.xpath("//h2[text()='Вход']");
    private final By emailField = By.name("name");
    private final By passwordField = By.name("Пароль");
    private final By enterButton = By.xpath("//*[text()='Войти']");

    private final By resetButton = By.xpath("//a[text()='Восстановить пароль']");

    final By registrationButton = By.xpath("//a[text()='Зарегистрироваться']");


    public AuthorizationPage(WebDriver driver, User user) {
        this.driver = driver;
        this.user = user;
    }



    public void clickRegistrationButton() {
        driver.findElement(registrationButton).isEnabled();
        driver.findElement(registrationButton).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    public void clickResetButton() {
        driver.findElement(resetButton).isEnabled();
        driver.findElement(resetButton).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    public void fillInputEmail(){
        driver.findElement(emailField).sendKeys(user.getEmail());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    public void fillInputPassword(){
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(user.getPassword());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    public void clickEnterButton() {
        driver.findElement(enterButton).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    public void login() {
        this.fillInputEmail();
        this.fillInputPassword();
        this.clickEnterButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public boolean isLoginPageVisible(){
        List<WebElement> loginHeader = driver.findElements(LOGIN_PAGE_HEADER);
        return loginHeader.size() == 1;
    }
}

