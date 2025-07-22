package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.*;

public class TestBase {
    protected WebDriver driver;

    protected WebDriver driver;
    protected static ExtentReports extent;
    protected static ExtentTest test;

    @BeforeSuite
    public void setupExtent() {
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
        extent.attachReporter(spark);
    }

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = takeScreenshot(testName);
            test.fail("Test Failed. Screenshot: " + test.addScreenCaptureFromPath(screenshotPath));
        }
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void tearDownExtent() {
        extent.flush();
    }

    private String takeScreenshot(String testName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotDir = "test-output/screenshots/";
        String screenshotPath = screenshotDir + testName + ".png";
        try {
            File destDir = new File(screenshotDir);
            if (!destDir.exists()) destDir.mkdirs();
            FileUtils.copyFile(srcFile, new File(screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenshotPath;
    }
}