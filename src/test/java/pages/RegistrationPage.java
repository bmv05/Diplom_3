package pages;

import User.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class RegistrationPage {
    private final WebDriver driver;
    private final User user;

    final static By REGISTER_PAGE_HEADER = By.xpath("//h2[text()='Регистрация']");
    private final By nameField = By.xpath ("//fieldset[1]/div[1]/div[1]/input[1]");
    private final By emailField = By.xpath("//fieldset[2]/div[1]/div[1]/input[1]");
    private final By passwordField = By.xpath("//fieldset[3]/div[1]/div[1]/input[1]");
    private final By passwordError = By.xpath("//*[text()='Некорректный пароль']");
    private final  By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By enterButton = By.xpath("//*[text()='Войти']");

    public RegistrationPage(WebDriver driver, User user) {
        this.driver = driver;
        this.user = user;
    }


    public void fillInputName(){
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(user.getName());
    }

    public void fillInputEmail(){
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(user.getEmail());
    }
    public void fillInputPassword(){
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(user.getPassword());
    }
    public void fillAllInputs(){
        this.fillInputName();
        this.fillInputEmail();
        this.fillInputPassword();
    }

    public void register(){
        this.fillAllInputs();
        this.clickRegisterButton();
    }

    public void clickRegisterButton(){
        driver.findElement(registerButton).isEnabled();
        driver.findElement(registerButton).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }


    public void clickEnterButton() {
        driver.findElement(enterButton).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    public Boolean isPasswordErrorVisible(){
        List<WebElement> error = driver.findElements(passwordError);
        return error.size() == 1;
    }
}
