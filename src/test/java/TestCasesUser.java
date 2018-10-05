import Pages.ActivationUserApi;
import Pages.LoginUserPage;
import Pages.RegistrationUserPage;
import Pages.RequestPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestCasesUser extends ChromeConfig {

    private LoginUserPage loginUserPage;
    private RegistrationUserPage registrationUserPage;
    private ActivationUserApi activationUserApi;
    private RequestPage requestPage;

    @BeforeClass
    protected void initDBPages() throws IOException {
        loginUserPage = new LoginUserPage(driver);
        registrationUserPage = new RegistrationUserPage(driver);
        activationUserApi = new ActivationUserApi(driver);
        requestPage = new RequestPage(driver);
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
}
