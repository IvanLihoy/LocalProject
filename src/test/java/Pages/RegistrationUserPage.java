package Pages;

import Utils.Tools;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class RegistrationUserPage extends Tools {

    public RegistrationUserPage(WebDriver driver)throws IOException {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver,this);
    }

    public static final String HOME_PAGE_URL="https://testing.opporty.com/";
    public static final String FIRST_NAME = "Auto";
    public static final String LAST_NAME = "Test";
    public static final String EMAIL = "lihoy+245@singree.com";
    public static final String PASSWORD = "1q2w3e4r5t";
    public static final String CONFIRMATION_PASSWORD = "1q2w3e4r5t";

    @FindBy(css = "input[name = 'firstname']") WebElement firstNameField;
    @FindBy(css = "input[name = 'lastname']") WebElement lastNameField;
    @FindBy(css = "input[name = 'email']") WebElement emailField;
    @FindBy(css = "input[name = 'password']") WebElement passField;
    @FindBy(css = "input[name = 'repassword']") WebElement confPassField;
    @FindBy(css = "input[name = 'companyName']") WebElement companyNameField;
    @FindBy(xpath = "//a[contains(text(), 'Individual')]") WebElement individualButton;
    @FindBy(xpath = "//a[contains(text(), 'Company')]") WebElement companyButton;
    @FindBy(css = "button[class = 'signIn']") WebElement signUpButton;
    @FindBy(css = "button[class = 'btn-react sign-in btn btn-default']") WebElement createButton;
    @FindBy(xpath = "//button[contains(text(), 'Ok')]") WebElement agreeRegistrationButton;

    public void openRegistrationUserPage() {
        loginPopUp("say_Friend_and_enter", "3UrC1Nks20U2kDvtIP0GVrU1jiplerU5iU4oepM5zVm3nPeY0x");
        openPage(HOME_PAGE_URL);
        getCurrentUrl(HOME_PAGE_URL);
        signUpButton.click();
        waitForElementIsClickable(individualButton);
        sleep(2);
    }

    public void inputRegistrationDataUser() {
        individualButton.click();
        emailField.sendKeys(EMAIL);
        passField.sendKeys(PASSWORD);
        confPassField.sendKeys(CONFIRMATION_PASSWORD);
        firstNameField.sendKeys(FIRST_NAME);
        lastNameField.sendKeys(LAST_NAME);
        createButton.click();
        sleep(2);
        assertVisibility(agreeRegistrationButton);
        agreeRegistrationButton.click();
        sleep(10);
    }
}
