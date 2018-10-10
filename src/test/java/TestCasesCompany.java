import Pages.*;
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

//    @Test(priority = 1)
//    public void positiveRegistrationCompanyTest() {
//        registrationCompanyPage.openRegistrationCompanyPage();
//        registrationCompanyPage.inputRegistrationDataCompany();
//    }
//
//    @Test(priority = 2, dependsOnMethods = "positiveRegistrationCompanyTest")
//    public void activateCompanyTest() throws Exception {
//        activationUserApi.openActivationLink();
//        activationUserApi.deleteActivationMessage();
//    }

    @Test(priority = 1)//, dependsOnMethods = "activateCompanyTest")
    public void positiveLoginCompanyTest() {
        loginCompanyPage.openLoginPage();
        loginCompanyPage.loginCompany();
    }

//    @Test(priority = 2)//, dependsOnMethods = "activateCompanyTest")
//    public void fillInCompanyProfileTest() {
//        createYourCompanyProfilePage.fillInCompanyProfile();
//    }

//    @Test(priority = 3)
//    public void createRequestByCompanyWithPriceTest() {
//        requestPage.createRequestFirstStep();
//        requestPage.createRequestSecondStep();
//        requestPage.createRequestThirdStep();
//        requestPage.createRequestFourthStepWithPrice();
//    }
//
//    @Test(priority = 4)
//    public void createRequestByCompanyNoPriceTest() {
//        requestPage.createRequestFirstStep();
//        requestPage.createRequestSecondStep();
//        requestPage.createRequestThirdStep();
//        requestPage.createRequestFourthStepNoPrice();
//        loginCompanyPage.verifyLogin();
//    }

//    @Test(priority = 2)
//    public void createOfferASAPTest() throws AWTException {
//        offerPage.addOfferFirstStep();
//        offerPage.addOfferSecondStep();
//        offerPage.addOfferThirdStep();
//        offerPage.addOfferFourthStep();
//    }

    @Test(priority = 2)
    public void addNewServiceTest() throws AWTException{
        servicePage.addService();
    }

}
