package Pages;

import Utils.Tools;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class RequestPage extends Tools{

    public RequestPage(WebDriver driver)throws IOException {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver,this);
    }

    public static String REQUEST_NAME = "Test Automation Request";
    public static String REQUEST_DESCRIPTION = "Looking for high-quality, professional and quick market research and analysis services. I need quality unbiased market insights and industry analysis for devising a marketing strategy and business planning.";
    public static String FILE_PATH = "bin\\image.jpg";
    public static String ZIP_CODE = "32003";
    public static String BUDGET = "300";
    public static String DATE = currentDate();

    @FindBy(xpath = "//button[contains(text(), 'Add Request')]") WebElement requestButton;
    @FindBy(css = "li[class = 'col-sm-12  ']") WebElement addCategoryLowServices;
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div/div/div/span/div/div[2]/div/div[3]/div/div[1]/ul/div[1]/div[1]/li/div[1]") WebElement addCategoryCheckbox;
    @FindBy(css = "button[id='next']") WebElement nextButton;
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div/div/div/div/div[2]/div/div[4]/ul/li[1]") WebElement additionalCategoryCheckbox;
    @FindBy(css = "input[id='name']") WebElement addRequestNameField;
    @FindBy(css = "textarea[id='text']") WebElement addRequestDescriptionField;
    @FindBy(xpath = "//*[contains(text(), 'Add fIle')]") WebElement uploadFileButton;
    @FindBy (css = "input[class = 'hidden uploadedFile']") WebElement fileInput;
    @FindBy(css = "input[id='zip']") WebElement addZipCodeField;
    @FindBy(xpath = "(//input[@role = 'combobox'])[2]") WebElement locationField;
    @FindBy(css = "input[id='budget']") WebElement budgetField;
    @FindBy(xpath = "//*[@id=\"date\"]/div/input") WebElement dateField;
    @FindBy(xpath = "//*[@id=\"date\"]/div/div/div/table/tbody/tr[5]/td[3]") WebElement date_in_calendar;
    @FindBy(xpath = "//div[contains(text(), 'Your request was added.')]") WebElement confirmation_popup;
    @FindBy(css = "button[class = 'close btn']") WebElement closePopupButton;
    @FindBy(xpath = "//span[contains(text(), 'Business Catalog')]") WebElement businessCatalogLink;

    public void createRequestFirstStep() {
        waitForElementClickable(requestButton);
        requestButton.click();
        waitForElementDisplayed(addCategoryLowServices);
        addCategoryLowServices.click();
        addCategoryCheckbox.click();
        clickJS(nextButton);
    }

    public void createRequestSecondStep() {
        waitForElementClickable(additionalCategoryCheckbox);
        additionalCategoryCheckbox.click();
        clickJS(nextButton);
    }

    public void createRequestThirdStep() {
        waitForElementClickable(addRequestNameField);
        addRequestNameField.sendKeys(REQUEST_NAME);
        addRequestDescriptionField.sendKeys(REQUEST_DESCRIPTION);
        sleep(2);
        fileInput.sendKeys(getAbsolutePath(FILE_PATH));
        sleep(2);
        clickJS(nextButton);
    }

    public void createRequestFourthStepWithPrice() {
        react_selectAsync(0, 1, 1, "odessa");
        budgetField.sendKeys(BUDGET);
        dateField.click();
        dateField.clear();
        date_in_calendar.click();
        scrollDown(60);
        clickJS(nextButton);
        sleep(2);
        clickJS(closePopupButton);
        sleep(2);
    }

    public void createRequestFourthStepNoPrice() {
        react_selectAsync(0, 1, 1, "odessa");
        dateField.click();
        dateField.clear();
        date_in_calendar.click();
        scrollDown(60);
        clickJS(nextButton);
        sleep(2);
        clickJS(closePopupButton);
        sleep(2);
    }
}

