package helper;

public class EnvConfig {
    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";
    public static final int TIMEOUT_DEFAULT = 10;
    public static final int TIMEOUT_LONG = 30;

    public static final String WEB_DRIVER_CHROME_BIN =  "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome";
    public static final String WEB_DRIVER_CHROME = System.getenv("projects_dir") + "/libs/drivers/chrome/chromedriver";

    public static final String WEB_DRIVER_FIREFOX_BIN =  "/Applications/Firefox.app/Contents/MacOS/Firefox";
    public static final String WEB_DRIVER_FIREFOX = System.getenv("projects_dir") + "/libs/drivers/firefox/geckodriver";
}
