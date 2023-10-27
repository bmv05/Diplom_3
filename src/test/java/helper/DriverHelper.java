package helper;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;

import java.io.File;
import java.time.Duration;

import static helper.EnvConfig.*;

public class DriverHelper extends ExternalResource {

    WebDriver driver;

    @Override
    protected void before() {
        if ("firefox".equals(System.getProperty("browser")))
            setUpFirefox();
        else
            setUpChrome();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    private void setUpChrome() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        ChromeDriverService service = new ChromeDriverService.Builder()
                .withLogOutput(System.out)
                .usingDriverExecutable(new File(WEB_DRIVER_CHROME))
                .build();
        ChromeOptions options = new ChromeOptions()
                .setBinary(WEB_DRIVER_CHROME_BIN);
        driver = new ChromeDriver(service, options);
    }

        public void setUpFirefox () {
            System.setProperty("webdriver.http.factory", "jdk-http-client");
            var service = new GeckoDriverService.Builder()
                    .withLogOutput(System.out)
                    .usingDriverExecutable(new File(WEB_DRIVER_FIREFOX))
                    .build();
            FirefoxOptions options = new FirefoxOptions()
                    .setBinary(WEB_DRIVER_FIREFOX_BIN);

            driver = new FirefoxDriver(service, options);
        }

    @Override
    protected void after() {
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
