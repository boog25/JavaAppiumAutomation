package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;


public class ArticalPageObject extends MainPageObject {
    private static final String
    SWIPE_DELETE_TPL = "//*[contains(@text, '{SUBSTRING}')]";

    public ArticalPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getDeleteElement(String substring) {
        return SWIPE_DELETE_TPL.replace("{SUBSTRING}", substring);
    }

    public void swipeToLeft(String substring){
        String delete_xpath = getDeleteElement(substring);
        this.swipeSide(By.xpath(delete_xpath),"Cannot Cannot find articles");
    }
}
