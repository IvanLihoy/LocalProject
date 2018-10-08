package Pages;

import Utils.Tools;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;

public class CreateYourCompanyProfilePage extends Tools {

    public CreateYourCompanyProfilePage(WebDriver driver) throws IOException {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
        PageFactory.initElements(driver, this);
    }

    private String COMPANY_ADDRESS = "445 Mount Eden Road, Mount Eden, Auckland.";
    private String WEBSITE = "https://gogo.com";
    private String ZIPCODE = "98001";
    private String CITY ="Odessa TX";
    private String PATH_TO_LOGO_FILE = "bin\\imageLogo.jpg";
    private String PHONE_NUMBER = "1234567890";
    private String DESCRIPTION = "test company description";

    /*Step 1*/
    @FindBy(xpath = "//span[contains(text(), 'Welcome to Opporty!')]") WebElement headerOfPageGreeting;
    @FindBy(xpath = "//span[contains(text(), 'Create your company profile')]") WebElement headerOfPageStep1;
    @FindBy(xpath = "//span[contains(text(), 'Get started']") WebElement getStartedButton;
    @FindBy(css = "input[placeholder = 'Enter your company street address']") WebElement companyAddressField;
    @FindBy(css = "input[placeholder = 'Enter your company website']") WebElement websiteField;
    @FindBy(css = "input[placeholder = 'Enter ZIP']") WebElement zipField;
    @FindBy(css = "input[aria-activedescendant= 'react-select-2--value']") WebElement cityField;
    @FindBy(css = "input[aria-activedescendant= 'react-select-3--value']") WebElement stateField;
    @FindBy(css = "input[id = 'file']") WebElement addFileInput;
    @FindBy(xpath = "//button[contains(text(), 'Save')]") WebElement saveButton;
    @FindBy(css = "input[placeholder = 'Enter telephone number']") WebElement telephoneNumberField;
    @FindBy(xpath = "//button[contains(text(), 'Next')]") WebElement nextButtonStep1;
    @FindBy(css = "button[id = 'next']") WebElement nextButton;
    @FindBy(css = "label[id = 'addimage']") WebElement addFileButton;
    @FindBy(xpath = "//button[contains(text(), 'Crop')]") WebElement cropButton;

    /*Step 2*/
    @FindBy(css = "//p[contains(text(), 'Describe your products or services.')]") WebElement headerPageStep2;
    @FindBy(xpath = "(//div[@class = 'box col-xs-1'])[1]") WebElement selectCheckBox;
    @FindBy(css = "div.selected_cat") WebElement addedCategories; //Assert checkBox
    @FindBy(xpath = "(//button[contains(text(), 'Next')])[1]") WebElement nextButtonStep2;

    /*Step 3*/
    @FindBy(css = "//span[contains(text(), 'Provide additional information.')]") WebElement headerPageStep3;
    @FindBy(css = "textarea[id = 'about']") WebElement descibeYouCompany; //15 chars
    @FindBy(xpath = "//button[contains(text(), 'Next')]") WebElement nextButtonStep3;
    @FindBy(css = "div.compleiton-list-header") WebElement complitionHeader;
    @FindBy(css = "li[class = 'col-sm-12  ']") WebElement addCategoryLowServices;
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div/div/span/div/div[2]/div/div[3]/div/div[1]/ul/div[1]/div[2]/li/div[1]") WebElement addCategoryCheckbox;
    @FindBy(xpath = "//span[contains(text(), 'skip tour')]") WebElement skipTourLink;

    public void fillInCompanyProfile() {
//        openPage(HOME_PAGE_URL);
//        waitUntilVisible(headerOfPageGreeting);
        getStartedButton.click();
        waitUntilVisible(headerOfPageStep1);
        companyAddressField.sendKeys(COMPANY_ADDRESS);
        websiteField.sendKeys(WEBSITE);
        zipField.sendKeys(ZIPCODE);
        addFileButton.click();
        addFileInput.sendKeys(getAbsolutePath(PATH_TO_LOGO_FILE));
        sleep(2);
        clickJS(cropButton);
        clickJS(saveButton);
        waitUntilVisible(telephoneNumberField);
        telephoneNumberField.sendKeys(PHONE_NUMBER);
        clickJS(nextButton);
        addCategoryLowServices.click();
        addCategoryCheckbox.click();
        clickJS(nextButton);
        clickJS(nextButton);
        descibeYouCompany.sendKeys(DESCRIPTION);
        clickJS(nextButton);
        Assert.assertTrue(skipTourLink.isDisplayed());
        skipTourLink.click();
    }

}
