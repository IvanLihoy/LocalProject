package Pages;

import Utils.Tools;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class ForgotPasswordPage extends Tools {
    private ForgotPasswordApi forgotPasswordApi;

    public ForgotPasswordPage(WebDriver driver) throws IOException{
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
        PageFactory.initElements(driver, this);
    }

    public static final String HOME_PAGE_URL="https://testing.opporty.com/";
    public static final String USER_EMAIL = "lihoy+55@singree.com";
    public static final String NEW_PASS = "1q2w3e4r5t";

    @FindBy(css = "button[class='logIn']") WebElement signInButton;
    @FindBy(css = "span[class = 'forgot']") WebElement forgotPassButton;
    @FindBy(css = "input[name = 'email']") WebElement emailField;
    @FindBy(xpath = "//button[contains(text(), 'Email me')]") WebElement emailMeButton;
    @FindBy(xpath = "//button[contains(text(), 'Ok')]") WebElement okButton;
    @FindBy(css = "input[name = 'password']") WebElement passField;
    @FindBy(css = "input[name = 'repassword']") WebElement repass;
    @FindBy(xpath = "//button[contains(text(), 'Update password')]") WebElement UpdateButton;
    @FindBy(xpath = "//button[contains(text(), 'Close')]") WebElement closeButton;
    @FindBy(xpath = "//button[contains(text(), 'Sign In')]") WebElement loginButton;
    @FindBy(xpath = "//span[contains(text(), 'Sign In')]") WebElement loginafterReset;

    public void getForgotPassword () throws Exception{
        loginPopUp("say_Friend_and_enter", "3UrC1Nks20U2kDvtIP0GVrU1jiplerU5iU4oepM5zVm3nPeY0x");
        openPage(HOME_PAGE_URL);
        waitForElementIsClickable(signInButton);
        signInButton.click();
        waitForElementIsClickable(forgotPassButton);
        assertVisibility(forgotPassButton);
        forgotPassButton.click();
        assertVisibility(emailField);
        emailField.sendKeys(USER_EMAIL);
        emailMeButton.click();
        //waitUntilVisible(okButton);
        clickJS(okButton);
    }

    public void enterPassword() throws Exception {
        sleep(5);
        forgotPasswordApi = new ForgotPasswordApi(driver);
        forgotPasswordApi.enterPassword();
    }

    public void updatePassword()throws Exception{
        passField.sendKeys(NEW_PASS);
        repass.sendKeys(NEW_PASS);
        UpdateButton.click();
        closeButton.click();
        sleep(2);
    }

    public void loginWithNewPassword(){
        loginafterReset.click();
        emailField.sendKeys(USER_EMAIL);
        passField.sendKeys(NEW_PASS);
        loginButton.click();
    }

    public void deleteMessageResetPassword() throws Exception {
        forgotPasswordApi = new ForgotPasswordApi(driver);
        forgotPasswordApi.deletePasswordMessage();
    }
}
