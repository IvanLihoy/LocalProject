import Pages.*;
import Report.TestRail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestCasesCompany extends ChromeConfig {

    private LoginCompanyPage loginCompanyPage;
    private RegistrationCompanyPage registrationCompanyPage;
    private ActivationUserApi activationUserApi;
    private CreateYourCompanyProfilePage createYourCompanyProfilePage;
    private RequestPage requestPage;
    private OfferPage offerPage;
    private ServicePage servicePage;

    @BeforeClass
    protected void initDBPages() throws IOException {
        loginCompanyPage = new LoginCompanyPage(driver);
        registrationCompanyPage = new RegistrationCompanyPage(driver);
        activationUserApi = new ActivationUserApi(driver);
        createYourCompanyProfilePage = new CreateYourCompanyProfilePage(driver);
        requestPage = new RequestPage(driver);
        offerPage = new OfferPage(driver);
        servicePage = new ServicePage(driver);
    }

    @Test(description = "42851. Registration company", priority = 9)
    public void positiveRegistrationCompanyTest() {
        registrationCompanyPage.openRegistrationCompanyPage();
        registrationCompanyPage.inputRegistrationDataCompany();
    }

    @Test(description = "42853. Activation company", priority = 10, dependsOnMethods = "positiveRegistrationCompanyTest")
    public void activateCompanyTest() throws Exception {
        activationUserApi.openActivationLink();
        activationUserApi.deleteActivationMessage();
    }

    @Test(description = "42854. Login company", priority = 11, dependsOnMethods = "activateCompanyTest")
    public void positiveLoginCompanyTest() {
        //loginCompanyPage.openLoginPage();
        loginCompanyPage.loginCompany();
        //loginCompanyPage.verifyLogin();
    }

    @Test(description = "42855. Complete company profile", priority = 12, dependsOnMethods = "activateCompanyTest")
    public void fillInCompanyProfileTest() {
        createYourCompanyProfilePage.fillInCompanyProfile();
    }

    @Test(description = "43040. Create Request By Company With Price", priority = 13)
    public void createRequestByCompanyWithPriceTest() {
        requestPage.createRequestFirstStep();
        requestPage.createRequestSecondStep();
        requestPage.createRequestThirdStep();
        requestPage.createRequestFourthStepWithPrice();
    }

    @Test(description = "43041. Create Request By Company No Price", priority = 14)
    public void createRequestByCompanyNoPriceTest() {
        requestPage.createRequestFirstStep();
        requestPage.createRequestSecondStep();
        requestPage.createRequestThirdStep();
        requestPage.createRequestFourthStepNoPrice();
        //loginCompanyPage.verifyLogin();
    }

    @Test(description = "43042. Create Offer ASAP", priority = 15)
    public void createOfferASAPTest() throws AWTException {
        offerPage.addOfferFirstStep();
        offerPage.addOfferSecondStep();
        offerPage.addOfferThirdStep();
        offerPage.addOfferFourthStep();
    }

    @Test(description = "43043. Add New Service", priority = 16)
    public void addNewServiceTest() throws AWTException{
        servicePage.addService();
    }

}
