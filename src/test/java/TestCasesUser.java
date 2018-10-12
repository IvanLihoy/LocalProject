import Pages.*;
import Report.TestRail;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestCasesUser extends ChromeConfig {

    private LoginUserPage loginUserPage;
    private RegistrationUserPage registrationUserPage;
    private ActivationUserApi activationUserApi;
    private RequestPage requestPage;
    private SocialLoginPage socialLoginPage;
    private ForgotPasswordPage forgotPasswordPage;
    private LoginCompanyPage loginCompanyPage;
    private RegistrationCompanyPage registrationCompanyPage;
    private CreateYourCompanyProfilePage createYourCompanyProfilePage;
    private OfferPage offerPage;
    private ServicePage servicePage;

    @BeforeClass
    protected void initDBPages() throws IOException {
        loginUserPage = new LoginUserPage(driver);
        registrationUserPage = new RegistrationUserPage(driver);
        activationUserApi = new ActivationUserApi(driver);
        requestPage = new RequestPage(driver);
        socialLoginPage = new SocialLoginPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        loginCompanyPage = new LoginCompanyPage(driver);
        registrationCompanyPage = new RegistrationCompanyPage(driver);
        createYourCompanyProfilePage = new CreateYourCompanyProfilePage(driver);
        offerPage = new OfferPage(driver);
        servicePage = new ServicePage(driver);
    }

    @Test(description = "43032. Positive Registration User", priority = 1)
    public void positiveRegistrationUserTest() {
        registrationUserPage.openRegistrationUserPage();
        registrationUserPage.inputRegistrationDataUser();
    }

    @Test(description = "43033. Activation User", priority = 2)//, dependsOnMethods = "positiveRegistrationUserTest")
    public void activateUserTest() throws Exception {
        activationUserApi.openActivationLink();
        activationUserApi.deleteActivationMessage();
    }

    @Test(description = "43034. Positive Login User", priority = 3)//, dependsOnMethods = "activateUserTest")
    public void positiveLoginUserTest() {
        loginUserPage.loginUser();
    }

    @Test(description = "43035. Add Request With Price", priority = 4)//, dependsOnMethods = "positiveLoginUserTest")
    public void addRequestTestWithPrice() throws AWTException, IOException {
        requestPage.createRequestFirstStep();
        requestPage.createRequestSecondStep();
        requestPage.createRequestThirdStep();
        requestPage.createRequestFourthStepWithPrice();
    }

    @Test(description = "43036. Add Request With No Price", priority = 5)//, dependsOnMethods = "addRequestTestWithPrice")
    public void addRequestTestNoPrice() throws AWTException, IOException {
        requestPage.createRequestFirstStep();
        requestPage.createRequestSecondStep();
        requestPage.createRequestThirdStep();
        requestPage.createRequestFourthStepNoPrice();
        loginUserPage.verifyLogin();
    }

    @Test(description = "43037. Login With Facebook", priority = 6)
    public void loginWithFacebookTest() {
        socialLoginPage.openLoginPageFB();
        socialLoginPage.loginUserFB();
        socialLoginPage.verifyUserFB();
    }

    @Test(description = "43038. Login With LinkedIn", priority = 7)
    public void loginWithLinkedInTest() {
        socialLoginPage.openLoginPageIN();
        socialLoginPage.loginUserIN();
        socialLoginPage.verifyUserIN();
    }

    @Test(description = "43039. Forgot Password", priority = 8)
    public void forgotPasswordTest() throws Exception {
        forgotPasswordPage.getForgotPassword();
        forgotPasswordPage.enterPassword();
        forgotPasswordPage.updatePassword();
        forgotPasswordPage.loginWithNewPassword();
        loginUserPage.verifyLogin();
        forgotPasswordPage.deleteMessageResetPassword();
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

//    @Test(description = "43043. Add New Service", priority = 16)
//    public void addNewServiceTest() throws AWTException{
//        servicePage.addService();
//    }
}
