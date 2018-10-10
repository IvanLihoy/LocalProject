import Report.TestRail;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestRailConfig {

    private TestRail trReport;

    @BeforeSuite
    protected void prepareTestRailRun() throws Exception {
        trReport = new TestRail();
        trReport.startRun(8, "Positive Test-suite - " + new SimpleDateFormat("dd/MM/yy HH:mm").format(new Date()));
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
