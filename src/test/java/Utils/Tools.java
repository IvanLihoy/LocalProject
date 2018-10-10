package Utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Tools {
    protected WebDriver driver;
    protected WebDriverWait wait;
    public String HOME_PAGE_URL = "https://testing.opporty.com/";
    public String ACCOUNT_HOME_PAGE_URL = "https://testing.opporty.com/";
    public String REQUEST_PAGE = "https://testing.opporty.com/r";
    public String OFFER_PAGE = "https://testing.opporty.com/o";
    public String RESPONSE_TEXT = "test response";
    public static String PATH_TO_TEXT_FILE = "bin/text.txt";
    public String PATH_TO_IMAGE = "bin/imageLogo.jpg";
    public Robot r;

    public Tools() throws IOException {
    }

    public static void main(String[] args) {
        System.out.println(getNextDay());
    }


    protected void sleep(int timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
        }
    }

    public void loginPopUp(String userName, String pwd){
        String URL = "https://" + userName + ":" + pwd + "@" + "testing.opporty.com";
        driver.get(URL);
    }

    public void openPage(String url){
        try{
            driver.get(url);
            assertEquals(driver.getCurrentUrl(), url);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public  void getCurrentUrl(String element){
        try{
            assertTrue(driver.getCurrentUrl().contains(element));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public  void getCurrentTitle(String element){
        try{
            assertTrue(driver.getTitle().contains(element));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void waitForElementClickable(WebElement element) {
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitForElementDisplayed(WebElement element) {
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementDisplayedCss(String cssSelector) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
    }

    protected void waitForElementDisplayedXpath(String xPath) {
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
    }

    protected void waitForElementClickableLong(WebElement element, int pause) {
        new WebDriverWait(driver, pause).until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitForElementDisplayedLong(WebElement element, int pause) {
        new WebDriverWait(driver, pause).until(ExpectedConditions.visibilityOf(element));
    }

    public void sendEnter() throws AWTException {
        r = new Robot();
        r.keyPress(KeyEvent.VK_ENTER);
        r.keyRelease(KeyEvent.VK_ENTER);
    }
    public void newTab() throws AWTException {
        r = new Robot();
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_T);
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_T);
        //driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"\t");
    }

    public void switchToTab(){
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        int n = tabs.size();
        driver.switchTo().window(tabs.get(n-1));
    }

    public static String getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        //get a date to represent "today"
        Date currentDay = calendar.getTime();
        //set up format
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("EEEEE, MMMMM d, yyyy h:m a");
        //System.out.println(simpleFormatter.format(currentDay));
        return simpleFormatter.format(currentDay);
    }

    public static String getNextDay() {
        // get a calendar instance, which defaults to "now"
        Calendar calendar = Calendar.getInstance();
        // add one day to the date/calendar
        calendar.add(Calendar.DAY_OF_YEAR, 3);
        //calendar.add(Calendar.YEAR, 1);
        // now get "tomorrow"
        Date tomorrow = calendar.getTime();
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("EEEEE, MMMMM d, yyyy h:mm a", Locale.ENGLISH);
        // print out tomorrow's date
        //System.out.println(simpleFormatter.format(tomorrow));
        return simpleFormatter.format(tomorrow);
    }

    public void findListOfObject(String offerName) {
        List<WebElement> offerObj = driver.findElements(By.cssSelector("div.project-item-headline"));
        for (WebElement element : offerObj) {
            if (element.getText().equals(offerName)) {
                element.click();
                System.out.println("click");
                break;
            }
        }
    }
    public static void setClipboardData(String string) {
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(stringSelection, null);
    }

    public static void chooseAFile(String path) {
        setClipboardData(path);

        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public String getAbsolutePath(String pathTofile){
        File f = new File(pathTofile);
        return f.getAbsolutePath();
    }

    public void makeVisibleElementById(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('file').style.display='block';");
    }

    public void scrollDown(int howfar){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "window.scrollBy(0,"+howfar+")";
        js.executeScript(script);
        sleep(1);
    }

    public void clickJS(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void react_selectAsync(int indexInList,int selectInputIndex, int comboIndex, String text) {
        Actions act = new Actions(driver);
        WebElement selectInput=driver.findElement(By.xpath("(//div[@class ='Select-input'])["+selectInputIndex+"]"));
        WebElement input = driver.findElement(By.xpath("(//input[@role='combobox'])["+comboIndex+"]"));
        act.click(selectInput).build().perform();
        act.sendKeys(input, text).build().perform();
        List<WebElement> selectValues=driver.findElements(By.className("Select-option"));
        WebElement firstWebElement=selectValues.get(indexInList);
        act.click(firstWebElement).build().perform();
    }

    public void react_select(int indexInList, int selectInputIndex){
        Actions act = new Actions(driver);
        WebElement selectInput=driver.findElement(By.xpath("(//div[@class ='Select-input'])["+selectInputIndex+"]"));
        act.click(selectInput).build().perform();
        List<WebElement> selectValues=driver.findElements(By.className("Select-option"));
        System.out.println(selectValues.size());
        WebElement firstWebElement = selectValues.get(indexInList);
        act.click(firstWebElement).build().perform();
    }

    public static String currentDate() {
        Calendar calendar = Calendar.getInstance();
        //calendar.add(Calendar.MINUTE, 1);
        Date currentDay = calendar.getTime();
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("EEEEE, MMMMM d, yyyy", Locale.ENGLISH);
        //System.out.println(simpleFormatter.format(date));
        return simpleFormatter.format(currentDay);
    }
}
