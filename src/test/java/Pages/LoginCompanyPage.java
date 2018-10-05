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

    private String COMPANY_EMAIL = "lihoy+226@singree.com";
    private String COMPANY_PASSWORD = "1q2w3e4r5t";

    @FindBy(css = "input[name='email']") WebElement emailField;
    @FindBy(css = "input[name='password']") WebElement passwordField;
    @FindBy(css = "button[class='btn-react btn btn-default']") WebElement submitButton;
    @FindBy(css = "button[class = 'btn btn btn-default']") WebElement getStartedButton;
    @FindBy(css = "a[id = 'main-menu']") WebElement accountDropDown;
    @FindBy(css = "i[class='fa fa-sign-out']") WebElement logoutButton;
    @FindBy(css = "button[class='logIn']") WebElement signInButton;

    public void openLoginPage() {
        loginPopUp("say_Friend_and_enter", "3UrC1Nks20U2kDvtIP0GVrU1jiplerU5iU4oepM5zVm3nPeY0x");
        openPage(HOME_PAGE_URL);
        waitUntilVisible(signInButton);
        signInButton.click();
        waitUntilVisible(emailField);
    }

    public  void loginCompany() {
        emailField.sendKeys(COMPANY_EMAIL);
        passwordField.sendKeys(COMPANY_PASSWORD);
        waitForElementIsClickable(submitButton);
        submitButton.click();
    }

    public  void verifyLogin(){
        waitForElementIsClickable(accountDropDown);
        accountDropDown.click();
        assertVisibility(logoutButton);
        logoutButton.click();
        assertVisibility(signInButton);
    }
}