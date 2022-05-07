import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:/homework/JavaAppiumAutomation/apks/wikipedia.apk");

        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void MainTest() {
        System.out.println("My Test");
    }

    @Test
    public void Test() {
        WebElement element_skip = driver.findElementByXPath("//*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']");
        element_skip.click();

        WebElement element_to_enter_search = assertElementHasText(
                "//*[contains(@text, 'Search Wikipedia')]",
                "Cannot find element",
                5
        );

    }

    @Test
    public void cancelSearch() {
        WebElement element_skip = driver.findElementByXPath("//*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']");
        element_skip.click();

        searchElementAndClick(
                "//*[contains(@text, 'Search Wikipedia')]",
                "Cannot find element",
                5
        );
        searchElementAndSendKeys(
                "//*[contains(@text, 'Search…')]",
                "Kotlin",
                "Cannot find element",
                5
        );
        assertElementHasText(
                "//*[@resource-id='org.wikipedia:id/page_list_item_title']",
                "Cannot find element",
                15
        );
        searchElementAndClick(
                "//*[@resource-id='org.wikipedia:id/search_close_btn']",
                "Cannot find element",
                20
        );
        assertElementHasText(
                "//*[@resource-id='org.wikipedia:id/search_container']",
                "Cannot find element",
                15);
    }

    private WebElement assertElementHasText(String xpath, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage((error_message + "\n"));
        By by = By.xpath(xpath);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement searchElementAndClick(String xpath, String error_message, long timeoutInSeconds) {
        WebElement element = assertElementHasText(xpath, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement searchElementAndSendKeys(String xpath, String value, String error_message, long timeoutInSeconds) {
        WebElement element = assertElementHasText(xpath, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

}
