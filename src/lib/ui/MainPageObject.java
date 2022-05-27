package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageObject {
    protected AppiumDriver driver;
    public MainPageObject (AppiumDriver driver){
        this.driver=driver;
    }

    public WebElement assertElementHasText(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage((error_message + "\n"));
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement searchElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = assertElementHasText(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement searchElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = assertElementHasText(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;

    }

    public void swipeSide(By by, String error_message) {
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
    public  int getAmountElements (By by){
        List element = driver.findElements(by);
        return element.size();
    }

    public void assertElementPresent(By by, String error_message) {
        int amount_elements = getAmountElements(by);
        if (amount_elements > 0){
            String default_message = "An element'" + by.toString() + "'supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }
}
