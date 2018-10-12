package Pages;

import Utils.Tools;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;

public class OfferPage extends Tools {

    public OfferPage(WebDriver driver) throws IOException {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
        PageFactory.initElements(driver, this);
    }

    private String PATH_TO_IMAGE = "bin\\image.jpg";
    private String OFFER_NAME = "New Offer";
    private String CITY = "Odessa";
    private String BUDGET = "300";
    private String DESCRIPTION = "Looking for high-quality, professional and quick market research and analysis services. I need quality unbiased market insights and industry analysis for devising a marketing strategy and business planning.";
    private String DATE = getNextDay();

    @FindBy(xpath = "//button[contains(text() , 'Add Offer')]") WebElement addOfferButton;
    @FindBy(css = "div[class = 'box col-xs-2']") WebElement checkBox;
    @FindBy(css = "div[class= 'selected_cat']") WebElement checkAddedField;
    @FindBy(css = "button[id='next']") WebElement nextButton;
    @FindBy(css = "input[id = 'name']") WebElement nameField;
    @FindBy(css = "textarea[id = 'text']") WebElement describeField;
    @FindBy(css = "input[class = 'hidden uploadedFile']") WebElement addFile;
    @FindBy(xpath = "//span[contains(text(), 'imageLogo.jpg')]") WebElement fileToload;
    @FindBy(xpath = "(//input[@role = 'combobox'])[1]") WebElement stateField;
    @FindBy(xpath = "//div[contains(text() , 'Your city')]") WebElement cityField;
    @FindBy(css = "div[class = 'Select-menu-outer']") WebElement selectCity;

    @FindBy(css = "input[id = 'budget']") WebElement budgetField;
    @FindBy(xpath = "//*[@id=\"date\"]/div/input") WebElement dateField;
    @FindBy(xpath = "//div[contains(text(), 'Your offer was added')]") WebElement successfulModalWindow;
    @FindBy(css = "li[class = 'col-sm-12  ']") WebElement addCategoryLowServices;
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div/div/div/span/div/div[2]/div/div[3]/div/div[1]/ul/div[1]/div[1]/li/div[1]") WebElement addCategoryCheckbox;
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div/div/div/div/div[2]/div/div[4]/ul/li[1]") WebElement additionalCategoryCheckbox;
    @FindBy(css = "div[class = 'file  file-selected']") WebElement selectedFileInput;
    @FindBy(css = "input[id = 'asap-checkbox']") WebElement asapCheckBox;

    public void addOfferFirstStep() {
        waitForElementDisplayed(addOfferButton);
        addOfferButton.click();
        waitForElementDisplayed(addCategoryLowServices);
        addCategoryLowServices.click();
        addCategoryCheckbox.click();
        clickJS(nextButton);
    }

    public void addOfferSecondStep() {
        waitForElementClickable(additionalCategoryCheckbox);
        additionalCategoryCheckbox.click();
        clickJS(nextButton);
    }

    public void addOfferThirdStep() {
        waitForElementDisplayed(nameField);
        nameField.sendKeys(OFFER_NAME);
        describeField.sendKeys(DESCRIPTION);
        addFile.sendKeys(getAbsolutePath(PATH_TO_IMAGE));
        waitForElementDisplayed(selectedFileInput);
        clickJS(nextButton);
    }

    public void addOfferFourthStep() {
        cityField.click();
        selectCity.click();
        budgetField.sendKeys(BUDGET);
        asapCheckBox.click();
        clickJS(nextButton);
        waitForElementDisplayedLong(successfulModalWindow, 5);
        Assert.assertTrue(successfulModalWindow.isDisplayed());
    }
}
