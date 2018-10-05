import Pages.ActivationUserApi;
import Pages.LoginCompanyPage;
import Pages.RegistrationCompanyPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestCasesCompany extends ChromeConfig {

    private LoginCompanyPage loginCompanyPage;
    private RegistrationCompanyPage registrationCompanyPage;
    private ActivationUserApi activationUserApi;

    @BeforeClass
    protected void initDBPages() throws IOException {
        loginCompanyPage = new LoginCompanyPage(driver);
        registrationCompanyPage = new RegistrationCompanyPage(driver);
        activationUserApi = new ActivationUserApi(driver);
    }

    @Test(priority = 6)
    public void positiveLoginCompanyTest() {
        loginCompanyPage.openLoginPage();
        loginCompanyPage.loginCompany();
        loginCompanyPage.verifyLogin();
    }

    @Test(priority = 7, dependsOnMethods = "positiveLoginCompanyTest")
    public void positiveRegistrationCompanyTest() {
        registrationCompanyPage.openRegistrationCompanyPage();
        registrationCompanyPage.inputRegistrationDataCompany();
    }

    @Test(priority = 8, dependsOnMethods = "positiveRegistrationCompanyTest")
    public void activateCompanyTest() throws Exception {
        activationUserApi.openActivationLink();
        activationUserApi.deleteActivationMessage();
    }
}
