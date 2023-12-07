package razepl.dev.mwobackend.config.selenium;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class RegisterPage {

    public static final String URL = "http://localhost:4200/auth/register/";

    private WebDriver webDriver;

    public RegisterPage(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "//*[@id=\"mat-input-2\"]")
    private WebElement txtName;
    @FindBy(xpath = "//*[@id=\"mat-input-3\"]")
    private WebElement txtSurname;
    @FindBy(xpath = "/html/body/app-root/app-register/div/form/app-email-field/mat-form-field/div[1]/div[2]/div/input")
    private WebElement txtEmail;
    @FindBy(xpath = "//*[@id=\"mat-input-5\"]")
    private WebElement txtPassword;
    @FindBy(xpath = "//*[@id=\"mat-input-6\"]")
    private WebElement txtRepeatPassword;

    @FindBy(xpath = "/html/body/app-root/app-register/div/form/div[3]/button[1]")
    private WebElement lnkReturn;
    @FindBy(xpath = "/html/body/app-root/app-register/div/form/div[3]/button[2]")
    private WebElement lnkRegister;

    @FindBy(xpath = "//*[@id=\"mat-mdc-error-2\"]")
    private WebElement nameErrorMsg;
    @FindBy(xpath = "//*[@id=\"mat-mdc-error-3\"]")
    private WebElement surnameErrorMsg;
    @FindBy(xpath = "//*[@id=\"mat-mdc-error-4\"]")
    private WebElement emailErrorMsg;
    @FindBy(xpath = "//*[@id=\"mat-mdc-error-5\"]")
    private WebElement passwordErrorMsg;
    @FindBy(xpath = "//*[@id=\"mat-mdc-error-6\"]")
    private WebElement repeatPasswordErrorMsg;

    public final void clickReturn(){
        lnkReturn.click();
    }

    public final void clickRegister(){
        lnkRegister.click();
    }
}
