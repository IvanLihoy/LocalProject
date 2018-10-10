package Pages;

import Utils.Tools;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.io.IOException;

public class ServicePage extends Tools {

    public ServicePage(WebDriver driver) throws IOException {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver,this);
    }

    private String NAME_SERVICE = "test name test name test name test name";
    private String COST = "100";
    private String RATE_OPTIONS = "Hourly rate";
    private String CATEGORIES = "Best Category";
    private String SECOND_CATEGORIES = "TEST NEW CAT VLAD";
    private String LANGUAGE = "English";
    private String PATH_TO_IMAGE = "bin/imageLogo.jpg";
    private String TEXT = "test description test description test description test description ";
    private String NAME_DOC = "Some doc";

    @FindBy(xpath = "//span[text()='Marketplace']") WebElement marketplaceButton;
    @FindBy(css = "h3.headline") WebElement headerPage;
    @FindBy(xpath = "//div[text()='My services' ]") WebElement myServiceButton;
    @FindBy(css = "div.add_mp_service") WebElement addServiceButton;
    @FindBy(xpath = "(//input[@class ='form-control'])[2]") WebElement nameField;
    @FindBy(css = "textarea.form-control") WebElement descriptionField;
    @FindBy(css = "input[type='number']") WebElement costField;
    @FindBy(xpath = "(//input[@role='combobox'])[1]") WebElement rateOptionField;
    @FindBy(xpath = "//span[text() = 'Hourly rate']") WebElement waitRateOption;
    @FindBy(css = "i.fa-ethereum") WebElement currency;
    @FindBy(xpath = "(//input[@role='combobox'])[2]") WebElement categoriesField;
    @FindBy(xpath = "//span[text() ='Best category']") WebElement waitForCategories;
    @FindBy(xpath = "(//input[@role='combobox'])[3]") WebElement secondCategoriesField;
    @FindBy(xpath = "(//input[@role='combobox'])[4]") WebElement languageField;
    @FindBy(xpath = "(//button[text()='Add file'])[1]") WebElement imageAddButton;
    @FindBy(css = "input[type= 'file']") WebElement attachmentButton;
    @FindBy(xpath = "(//button[text()='Add file'])[2]") WebElement documentsAddButton;
    @FindBy(xpath = "//button[text() = 'Create']") WebElement createButton;
    @FindBy(css = "input[type ='checkbox']") WebElement checkBox;
    @FindBy(xpath = "(//input[@class ='form-control'])[4]") WebElement nameAttachedDocFileField;
    @FindBy(css = "i.text-red")WebElement xIconRed;
    @FindBy(xpath = "(//button[contains(text(),'Save')])[2]") WebElement saveAttButton;
    @FindBy(css = "button.button-blue") WebElement saveButton;

    public void addService () throws AWTException {
        waitForElementDisplayed(marketplaceButton);
        marketplaceButton.click();
        waitForElementDisplayed(headerPage);
        myServiceButton.click();
        waitForElementDisplayed(addServiceButton);
        addServiceButton.click();
        waitForElementDisplayed(nameField);
        nameField.sendKeys(NAME_SERVICE);
        descriptionField.sendKeys(RESPONSE_TEXT);
        costField.sendKeys(COST);
        rateOptionField.sendKeys(RATE_OPTIONS);
        sendEnter();
        waitForElementClickable(waitRateOption);

        currency.click();
        categoriesField.sendKeys(CATEGORIES);
        sendEnter();
        waitForElementDisplayed(waitForCategories);
        secondCategoriesField.sendKeys(SECOND_CATEGORIES);
        sendEnter();
        languageField.sendKeys(LANGUAGE);

        sendEnter();
        imageAddButton.click();
        waitForElementDisplayed(attachmentButton);
        attachmentButton.sendKeys(getAbsolutePath(PATH_TO_IMAGE));
        createButton.click();
        waitForElementClickable(saveAttButton);
        saveAttButton.click();
        waitForElementDisplayed(checkBox);
        scrollDown(300);
        waitForElementDisplayed(documentsAddButton);
        documentsAddButton.click();
        makeVisibleElementById();
        waitForElementDisplayed(attachmentButton);
        attachmentButton.sendKeys(getAbsolutePath(TEXT));
        waitForElementDisplayed(xIconRed);
        nameAttachedDocFileField.sendKeys(NAME_DOC);
        saveAttButton.click();

        waitForElementClickable(saveButton);
        saveButton.click();

        waitForElementDisplayed(addServiceButton);
    }
}
