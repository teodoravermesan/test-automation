package tests;
import base.TestBase;
import pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    @Test
    public void loginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goTo();
        loginPage.login("validUser", "validPass");
        Assert.assertTrue(loginPage.isLoggedIn(), "User should be logged in with valid credentials");
    }

    @Test
    public void loginWithInvalidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goTo();
        loginPage.login("invalidUser", "invalidPass");
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error should be displayed for invalid credentials");
    }
}