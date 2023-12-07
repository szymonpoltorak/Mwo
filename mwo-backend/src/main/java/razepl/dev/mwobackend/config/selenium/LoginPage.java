package razepl.dev.mwobackend.config.selenium;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Accessors(fluent = true)
@Getter
public class LoginPage {

    private WebDriver webDriver;

    public LoginPage(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "/html/body/app-root/app-register/div/form/app-email-field/mat-form-field/div[1]/div[2]/div/input")
    private WebElement txtEmail;

    @FindBy(xpath = "/html/body/app-root/app-register/div/form/div[2]/app-password-field[1]/mat-form-field/div[1]/div[2]/div/input")
    private WebElement txtPassword;

    @FindBy(xpath = "/html/body/app-root/app-register/div/form/div[3]/button[2]")
    private WebElement lnkRegister;

    @FindBy(xpath = "/html/body/app-root/app-register/div/form/div[3]/button[1]")
    private WebElement lnkLogin;

    @FindBy(xpath = "/html/body/app-root/app-register/div/form/app-email-field/mat-form-field/div[2]/div/mat-error")
    private WebElement emailErrorMsg;

    @FindBy(xpath = "/html/body/app-root/app-register/div/form/div[2]/app-password-field[1]/mat-form-field/div[2]/div/mat-error")
    private WebElement passwordErrorMsg;

    public final void clickRegister(){
        lnkRegister.click();
    }

    public final void clickLogin(){
        lnkLogin.click();
    }

}
