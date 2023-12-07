package razepl.dev.mwobackend;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import razepl.dev.mwobackend.config.selenium.LoginPage;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static razepl.dev.mwobackend.config.selenium.constants.PagesConstants.REGISTER_URL;

@SpringBootTest
class LoginPageSeleniumTests {

    private LoginPage loginPage;
    private WebDriver driver;
    private static final ChromeOptions chromeOptions = new ChromeOptions();

    @BeforeAll
    static void setUpCrudAutomatedTests() {
        WebDriverManager.chromedriver().setup();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--disable-gpu");
    }

    @BeforeEach
    final void setup() {
        driver = new ChromeDriver(chromeOptions);
        driver.navigate().to("http://localhost:4200");
        loginPage = new LoginPage(driver);
    }

    @Test
    final void whenOnMainPage_CheckIfTitleIsCorrect() {
        String pageTitle = driver.getTitle();
        String expected = "ToRead";

        assertEquals(expected, pageTitle,
                String.format("Expected value: %s", expected));
    }

    @Test
    final void checkIfRedirectsToRegisterPage() {
        loginPage.clickRegister();

        assertEquals(REGISTER_URL, driver.getCurrentUrl(),
                String.format("Expected value: %s", REGISTER_URL));
    }

    @Test
    final void shouldDisplayErrorMessages_whenClickedOnFields() {
        loginPage.getTxtEmail().click();
        loginPage.getTxtPassword().click();
        loginPage.getTxtEmail().click();

        String expectedEmailErrorMsg = "Email is required";
        String expectedPasswordMessage = "Password is required";

        assertAll(
                () -> assertEquals(expectedEmailErrorMsg, loginPage.getEmailErrorMsg().getText(),
                        String.format("Expected value: %s", expectedEmailErrorMsg)),
                () -> assertEquals(expectedPasswordMessage, loginPage.getPasswordErrorMsg().getText(),
                        String.format("Expected value: %s", expectedPasswordMessage))
        );
    }

    @Test
    final void shouldDisplayErrorMessages_whenGivenIncorrectInputs() {
        loginPage.getTxtEmail().sendKeys("abc");
        loginPage.getTxtPassword().sendKeys("abc");
        loginPage.getTxtEmail().click();

        String expectedEmailErrorMsg = "Please enter a valid email address";
        String expectedPasswordMessage = "Please enter a valid password address";

        assertAll(
                () -> assertEquals(expectedEmailErrorMsg, loginPage.getEmailErrorMsg().getText(),
                        String.format("Expected value: %s", expectedEmailErrorMsg)),
                () -> assertEquals(expectedPasswordMessage, loginPage.getPasswordErrorMsg().getText())
        );
    }

    @AfterEach
    final void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
