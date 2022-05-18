import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;


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
        driver.rotate(ScreenOrientation.PORTRAIT);
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
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find element",
                5
        );

    }

    @Test
    public void cancelSearch() {
        WebElement element_skip = driver.findElementByXPath("//*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']");
        element_skip.click();

        searchElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find element",
                5
        );
        searchElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "Kotlin",
                "Cannot find element",
                5
        );
        assertElementHasText(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
                "Cannot find element",
                15
        );
        searchElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_close_btn']"),
                "Cannot find element",
                20
        );
        assertElementHasText(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_container']"),
                "Cannot find element",
                15);
    }

    @Test
    public void saveArticles() {
        WebElement element_skip = driver.findElementByXPath("//*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']");
        element_skip.click();

        searchElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia",
                5
        );
        String search_line = "Java";
        searchElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                search_line,
                "Cannot find search_line",
                5
        );
        searchElementAndClick(
                By.xpath("//*[contains(@text, 'High-level programming language')]"),
                "Cannot find Articles One",
                15
        );
        searchElementAndClick(
                By.xpath("//*[contains(@text, 'Save')]"),
                "Cannot find bottom Add",
                15
        );
        searchElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_toolbar_button_search']"),
                "Cannot find bottom Search",
                15
        );
        searchElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                search_line,
                "Cannot find element",
                5
        );
        searchElementAndClick(
                By.xpath("//*[contains(@text, 'Island in Southeast Asia')]"),
                "Cannot find Articles Two",
                15
        );
        searchElementAndClick(
                By.xpath("//*[contains(@text, 'Save')]"),
                "Cannot find bottom Add",
                10
        );
        searchElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find bottom back1",
                5
        );
        searchElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find bottom back2",
                10
        );
        searchElementAndClick(
                By.xpath("//android.widget.ImageButton"),
                "Cannot find bottom back3",
                5
        );
        searchElementAndClick(
                By.xpath("//*[contains(@text, 'Saved')]"),
                "Cannot find bottom Saved",
                5
        );
        searchElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/recycler_view']"),
                "Cannot find bottom Saved to Saved",
                5
        );
        swipeSide(
                By.xpath("//*[contains(@text, 'Island in Southeast Asia')]"),
                "Cannot find articles"
        );
        searchElementAndClick(
                By.xpath("//*[contains(@text, 'High-level programming language')]"),
                "Cannot find articles",
                5
        );
        assertElementHasText(
                By.xpath("//*[contains(@text, 'High-level programming language')]"),
                "Cannot find title",
                5
        );
    }

    @Test
    public void testAssertTitleArticle() {
        WebElement element_skip = driver.findElementByXPath("//*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']");
        element_skip.click();

        searchElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia",
                5
        );
        String search_line = "Java";
        searchElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                search_line,
                "Cannot find search_line",
                5
        );

String search_title = "//*[contains(@text, 'Java (programming language)')]";

        searchElementAndClick(
                By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                "Cannot find titles",
                15
        );
      assertElementPresent(
              By.xpath(search_title),
              "Cannot find Articles"
      );

    }

    private WebElement assertElementHasText(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage((error_message + "\n"));
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement searchElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = assertElementHasText(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement searchElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = assertElementHasText(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;

    }

    protected void swipeSide(By by, String error_message) {
        WebElement element_swipe = assertElementHasText(by, error_message, 10);

        int left_x = element_swipe.getLocation().getX();
        int right_x = left_x + element_swipe.getSize().getWidth();
        int upper_y = element_swipe.getLocation().getY();
        int lower_y = upper_y + element_swipe.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(150)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }
private  int getAmountElements (By by){
        List element = driver.findElements(by);
        return element.size();
}

    private void assertElementPresent(By by, String error_message) {
        int amount_elements = getAmountElements(by);
        if (amount_elements > 0){
            String default_message = "An element'" + by.toString() + "'supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
                    }
        }
}
