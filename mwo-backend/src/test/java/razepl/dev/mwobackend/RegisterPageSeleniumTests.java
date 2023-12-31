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
import razepl.dev.mwobackend.config.selenium.RegisterPage;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static razepl.dev.mwobackend.config.selenium.constants.PagesConstants.LOGIN_URL;
import static razepl.dev.mwobackend.config.selenium.constants.PagesConstants.REGISTER_URL;

@SpringBootTest
class RegisterPageSeleniumTests {

    private RegisterPage registerPage;
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
        driver.navigate().to("http://localhost:4200/auth/register");
        registerPage = new RegisterPage(driver);
    }

    @AfterEach
    final void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    final void checkIfReturnRedirectsToLoginPage() {
        registerPage.clickReturn();

        assertEquals(LOGIN_URL, driver.getCurrentUrl(),
                String.format("Expected value: %s", LOGIN_URL));
    }

    @Test
    final void shouldDisplayErrorMessages_whenClickedOnFields() {
        registerPage.getTxtName().click();
        registerPage.getTxtSurname().click();
        registerPage.getTxtEmail().click();
        registerPage.getTxtPassword().click();
        registerPage.getTxtRepeatPassword().click();
        registerPage.getTxtName().click();

        String expectedNameErrorMsg = "Name is required";
        String expectedSurnameMessage = "Surname is required";
        String expectedEmailErrorMsg = "Email is required";
        String expectedPasswordMessage = "Password is required";
        String expectedRepeatPassword = "Password is required";

        assertAll(
                () -> assertEquals(expectedNameErrorMsg, registerPage.getNameErrorMsg().getText()),
                () -> assertEquals(expectedSurnameMessage, registerPage.getSurnameErrorMsg().getText()),
                () -> assertEquals(expectedEmailErrorMsg, registerPage.getEmailErrorMsg().getText()),
                () -> assertEquals(expectedPasswordMessage, registerPage.getPasswordErrorMsg().getText()),
                () -> assertEquals(expectedRepeatPassword, registerPage.getRepeatPasswordErrorMsg().getText())
        );
    }

    @Test
    final void shouldDisplayErrorMessages_whenGivenIncorrectInputs() {
        registerPage.getTxtEmail().sendKeys("abc");
        registerPage.getTxtPassword().sendKeys("abc");
        registerPage.getTxtRepeatPassword().sendKeys("abc");
        registerPage.getTxtName().click();

        String expectedEmailErrorMsg = "Please enter a valid email address";
        String expectedPasswordMessage = "Please enter a valid password address";
        String expectedRepeatPassword = "Please enter a valid password address";

        assertAll(
                () -> assertEquals(expectedEmailErrorMsg, registerPage.getEmailErrorMsg().getText()),
                () -> assertEquals(expectedPasswordMessage, registerPage.getPasswordErrorMsg().getText()),
                () -> assertEquals(expectedRepeatPassword, registerPage.getRepeatPasswordErrorMsg().getText())
        );
    }

    @Test
    final void shouldStayOnSite_whenGivenIncorrectInputAndClickedRegister() {
        registerPage.getTxtEmail().sendKeys("abc");
        registerPage.clickRegister();

        assertEquals(REGISTER_URL, driver.getCurrentUrl(),
                String.format("Expected value: %s", REGISTER_URL));
    }
}
