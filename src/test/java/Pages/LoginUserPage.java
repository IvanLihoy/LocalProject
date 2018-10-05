package Pages;

import Utils.Tools;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class LoginUserPage extends Tools {

    public LoginUserPage(WebDriver driver) throws IOException {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver,this);
    }

    private String HOME_PAGE_URL= "https://testing.opporty.com/";
    //private String USER_EMAIL = "lihoy+55@singree.com";
    private String USER_EMAIL = RegistrationUserPage.EMAIL;
    private String USER_PASSWORD = "1q2w3e4r5t";

    @FindBy(css = "input[name='email']") WebElement emailField;
    @FindBy(css = "input[name='password']") WebElement passwordField;
    @FindBy(css = "button[class='btn-react btn btn-default']") WebElement submitButton;
    @FindBy(css = "a[id = 'main-menu']") WebElement accountDropDown;
    @FindBy(css = "i[class='fa fa-sign-out']") WebElement logoutButton;
    @FindBy(css = "button[class='logIn']") WebElement signInButton;
    @FindBy(xpath = "//a[@id='basic-nav-dropdown']") WebElement homePageDropDown;
    @FindBy(css = "div[class = 'page-subheader']") WebElement accountHeader;
    @FindBy(xpath = "//span[contains(text(), 'Welcome to Opporty!')]") WebElement welcomeText;
    @FindBy(css = "button[class = 'btn btn btn-default']") WebElement getStartedButton;
    @FindBy(css = "div[class = 'activate-login']") WebElement signInAfterActivation;

    public void openLoginPage() {
        loginPopUp("say_Friend_and_enter", "3UrC1Nks20U2kDvtIP0GVrU1jiplerU5iU4oepM5zVm3nPeY0x");
        openPage(HOME_PAGE_URL);
        waitUntilVisible(signInButton);
        signInButton.click();
        waitUntilVisible(emailField);
    }

    public  void loginUser() {
        signInAfterActivation.click();
        emailField.sendKeys(USER_EMAIL);
        passwordField.sendKeys(USER_PASSWORD);
        waitForElementIsClickable(submitButton);
        submitButton.click();
        getCurrentUrl(HOME_PAGE_URL);
    }

    public  void verifyLogin(){
        waitForElementIsClickable(accountDropDown);
        accountDropDown.click();
        assertVisibility(logoutButton);
        logoutButton.click();
        assertVisibility(signInButton);
    }
}
