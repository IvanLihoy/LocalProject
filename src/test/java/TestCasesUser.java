import Pages.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestCasesUser extends ChromeConfig {

    private LoginUserPage loginUserPage;
    private RegistrationUserPage registrationUserPage;
    private ActivationUserApi activationUserApi;
    private RequestPage requestPage;
    private SocialLoginPage socialLoginPage;
    private ForgotPasswordPage forgotPasswordPage;

    @BeforeClass
    protected void initDBPages() throws IOException {
        loginUserPage = new LoginUserPage(driver);
        registrationUserPage = new RegistrationUserPage(driver);
        activationUserApi = new ActivationUserApi(driver);
        requestPage = new RequestPage(driver);
        socialLoginPage = new SocialLoginPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
    }

    @Test(priority = 1)
    public void positiveRegistrationUserTest() {
        registrationUserPage.openRegistrationUserPage();
        registrationUserPage.inputRegistrationDataUser();
    }

    @Test(priority = 2, dependsOnMethods = "positiveRegistrationUserTest")
    public void activateUserTest() throws Exception {
        activationUserApi.openActivationLink();
        activationUserApi.deleteActivationMessage();
    }

    @Test(priority = 3, dependsOnMethods = "activateUserTest")
    public void positiveLoginUserTest() {
        loginUserPage.loginUser();
    }

    @Test(priority = 4, dependsOnMethods = "positiveLoginUserTest")
    public void addRequestTestWithPrice() throws AWTException, IOException {
        requestPage.createRequestFirstStep();
        requestPage.createRequestSecondStep();
        requestPage.createRequestThirdStep();
        requestPage.createRequestFourthStepWithPrice();
    }

    @Test(priority = 5, dependsOnMethods = "addRequestTestWithPrice")
    public void addRequestTestNoPrice() throws AWTException, IOException {
        requestPage.createRequestFirstStep();
        requestPage.createRequestSecondStep();
        requestPage.createRequestThirdStep();
        requestPage.createRequestFourthStepNoPrice();
        loginUserPage.verifyLogin();
    }

    @Test(priority = 6)
    public void loginWithFacebookTest() {
        socialLoginPage.openLoginPageFB();
        socialLoginPage.loginUserFB();
        socialLoginPage.verifyUserFB();
    }

    @Test(priority = 7)
    public void loginWithLinkedInTest() {
        socialLoginPage.openLoginPageIN();
        socialLoginPage.loginUserIN();
        socialLoginPage.verifyUserIN();
    }

    @Test(priority = 8)
    public void forgotPasswordTest() throws Exception {
        forgotPasswordPage.getForgotPassword();
        forgotPasswordPage.enterPassword();
        forgotPasswordPage.updatePassword();
        forgotPasswordPage.loginWithNewPassword();
        loginUserPage.verifyLogin();
        forgotPasswordPage.deleteMessageResetPassword();
    }
}
