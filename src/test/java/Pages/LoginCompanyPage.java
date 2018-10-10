package Pages;

import Utils.Tools;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class LoginCompanyPage extends Tools {

    public LoginCompanyPage(WebDriver driver) throws IOException {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver,this);
    }

    private String COMPANY_EMAIL = RegistrationCompanyPage.EMAIL_COMPANY;
    private String COMPANY_PASSWORD = "1q2w3e4r5t";

    @FindBy(css = "input[name='email']") WebElement emailField;
    @FindBy(css = "input[name='password']") WebElement passwordField;
    @FindBy(css = "button[class='btn-react btn btn-default']") WebElement submitButton;
    @FindBy(css = "button[class = 'btn btn btn-default']") WebElement getStartedButton;
    @FindBy(css = "a[id = 'main-menu']") WebElement accountDropDown;
    @FindBy(css = "i[class='fa fa-sign-out']") WebElement logoutButton;
    @FindBy(css = "button[class='logIn']") WebElement signInButton;
    @FindBy(css = "div[class = 'activate-login']") WebElement signInAfterActivation;

    public void openLoginPage() {
        loginPopUp("say_Friend_and_enter", "3UrC1Nks20U2kDvtIP0GVrU1jiplerU5iU4oepM5zVm3nPeY0x");
        openPage(HOME_PAGE_URL);
        waitForElementDisplayed(signInButton);
        signInButton.click();
        waitForElementDisplayed(emailField);
    }

    public  void loginCompany() {
        //signInAfterActivation.click();
        emailField.sendKeys(COMPANY_EMAIL);
        passwordField.sendKeys(COMPANY_PASSWORD);
        waitForElementClickable(submitButton);
        submitButton.click();
    }

    public  void verifyLogin(){
        waitForElementClickable(accountDropDown);
        accountDropDown.click();
        waitForElementDisplayed(logoutButton);
        logoutButton.click();
        waitForElementDisplayed(signInButton);
    }
}
