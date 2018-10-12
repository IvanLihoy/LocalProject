import Report.TestRail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ChromeConfig {

    protected WebDriver driver;
    private String pathForWin = "chromedriver.exe";
    public TestRail trReport;

    @BeforeTest
    protected void startChrome() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        System.setProperty("webdriver.chrome.driver", pathForWin);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @BeforeSuite
    protected void prepareTestRailRun() throws Exception {
        trReport = new TestRail();
        trReport.startRun(8, "Positive Test-suite - " + new SimpleDateFormat("dd/MM/yy HH:mm").format(new Date()));
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }

    @AfterMethod
    protected void reportResult(ITestResult testResult) throws Exception {
        String testDescription = testResult.getMethod().getDescription();
        trReport.setResult(Integer.parseInt(testDescription.substring(0, testDescription.indexOf("."))), testResult.getStatus());
    }

    @AfterSuite
    protected void closeTestRailRun() throws Exception {
        trReport.endRun();
    }
}
