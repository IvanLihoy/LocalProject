package Pages;

import Utils.Tools;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SocialLoginPage extends Tools {

    public SocialLoginPage(WebDriver driver) throws IOException {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "a[id = 'main-menu']") WebElement accountDropDown;
    @FindBy(css = "i[class='fa fa-sign-out']") WebElement logoutButton;
    @FindBy(css = "button[class='logIn']") WebElement signInButton;
    @FindBy(css = "li[class = 'login-user text-center header_navbar_title undefined dropdown']") WebElement homePageDropDown;

    /*Facebook login*/
    public static final String FB_EMAIL = "opporty@opporty.com";
    public static final String FB_PASS = "2w68t4fi";
    public static final String FB_FIRST_NAME = "Nodislav";
    public static final String FB_PAGE_TITLE = "Войдите на Facebook | Facebook";

    @FindBy(css = "div[class='social-facebook']") WebElement buttonFB;
    @FindBy(id = "email") WebElement emailFieldFB;
    @FindBy(id = "pass") WebElement passFieldFB;
    @FindBy(id = "loginbutton") WebElement loginButtonFB;

    public void openLoginPageFB() {
        loginPopUp("say_Friend_and_enter", "3UrC1Nks20U2kDvtIP0GVrU1jiplerU5iU4oepM5zVm3nPeY0x");
        openPage(HOME_PAGE_URL);
        waitUntilVisible(signInButton);
        signInButton.click();
        waitUntilVisible(buttonFB);
        buttonFB.click();
    }

    public void loginUserFB() {
        emailFieldFB.sendKeys(FB_EMAIL);
        passFieldFB.sendKeys(FB_PASS);
        loginButtonFB.click();
        //assertTrue(homePageDropDown.isDisplayed());
    }

    public void verifyUserFB() {
        assertVisibility(homePageDropDown);
        homePageDropDown.click();
        assertVisibility(logoutButton);
        logoutButton.click();
        assertVisibility(signInButton);
        assertTrue(signInButton.isDisplayed());
    }

    /*LinkedIn login*/
    @FindBy(css = "div[class='social-linkedin']") WebElement buttonIN;
    @FindBy(css = "input[type='text']") WebElement emailFieldIN;
    @FindBy(css = "input[type='password']") WebElement passFieldIN;
    @FindBy(css = "input[type='submit']") WebElement loginButtonIN;

    public static final String IN_EMAIL ="khvostyak@singree.com";
    public static final String IN_PASS = "123scarys123";
    public static final String IN_PAGE_TITLE = "Authorize | LinkedIn";

    public  void openLoginPageIN() {
        loginPopUp("say_Friend_and_enter", "3UrC1Nks20U2kDvtIP0GVrU1jiplerU5iU4oepM5zVm3nPeY0x");
        openPage(HOME_PAGE_URL);
        signInButton.click();
        waitForElementIsClickable(buttonIN);
        buttonIN.click();
    }

    public  void loginUserIN() {
        emailFieldIN.sendKeys(IN_EMAIL);
        passFieldIN.sendKeys(IN_PASS);
        loginButtonIN.click();
    }

    public  void verifyUserIN() {
        assertVisibility(homePageDropDown);
        homePageDropDown.click();
        logoutButton.isDisplayed();
        logoutButton.click();
        assertVisibility(signInButton);
        assertTrue(signInButton.isDisplayed());
    }
}
