package razepl.dev.mwobackend.config.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class WebDriverConfig {

    @Value("chrome,firefox,edge,opera,safari")
    private List<String> browsers;

    public WebDriver setUpWebDriver(String browser){
        if(!browsers.contains(browser)){
            throw new IllegalStateException("No such browser");
        }

        WebDriver driver = switch (browser) {
            case "chrome" -> {
                WebDriverManager.chromedriver()
                        .setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                yield new ChromeDriver();
            }
            case "edge" -> {
                WebDriverManager.edgedriver()
                        .setup();
                yield new EdgeDriver();
            }
            case "safari" -> {
                WebDriverManager.safaridriver()
                        .setup();
                yield new SafariDriver();
            }
            case "opera" -> {
                WebDriverManager.operadriver()
                        .setup();
                yield new OperaDriver();
            }
            default -> {
                WebDriverManager.firefoxdriver()
                        .setup();
                yield new FirefoxDriver();
            }
        };
        return driver;
    }

}
