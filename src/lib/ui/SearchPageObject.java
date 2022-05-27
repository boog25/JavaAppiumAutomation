package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {
private static final String
    SKIP = "//*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']",
    SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
    SEARCH_INPUT = "//*[@resource-id='org.wikipedia:id/search_src_text']",
    SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[contains(@text,'{SUBSTRING}')]",
    SEARCH_CANCEL_BUTTON = "//*[@resource-id='org.wikipedia:id/search_close_btn']",
    SEARCH_INPUT_IN_TITLE = "//*[@resource-id='org.wikipedia:id/page_toolbar_button_search']",
    CLICK_BUTTON_SAVE = "//*[@resource-id='org.wikipedia:id/page_actions_tab_layout']//*[contains(@text, 'Save')]",
    CLICK_BUTTON_BACK = "//android.widget.ImageButton[@content-desc='Navigate up']",
    CLICK_BUTTON_SAVED = "//*[contains(@text, 'Saved')]",
    CLICK_PACKAGE_SAVED = "//*[@resource-id='org.wikipedia:id/recycler_view']";


    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}",substring);
    }
    public void skipButton(){
        this.searchElementAndClick(By.xpath(SKIP),"Cannot find button skip",5);
    }
    public void initSearchInput(){
        this.searchElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click element", 5);
        this.assertElementHasText(By.xpath(SEARCH_INIT_ELEMENT),"Cannot find element", 5);
    }
    public void typeSearchLine(String search_line){
        this.searchElementAndSendKeys(By.xpath(SEARCH_INPUT),search_line,"Cannot find search input",15);
    }
    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.assertElementPresent(By.xpath(search_result_xpath), "Cannot find result " + substring);
    }
        public void clickArticlSearchResult(String substring){
            String search_result_xpath = getResultSearchElement(substring);
            this.searchElementAndClick(By.xpath(search_result_xpath),"Cannot find and click result " +substring, 20);
    }
    public void clickSearchInTitle(String search_line){
        this.searchElementAndSendKeys(By.xpath(SEARCH_INPUT_IN_TITLE),search_line,"Cannot find Search line",10);
    }
    public void waitForCancelButton(){
        this.assertElementPresent(By.xpath(SEARCH_CANCEL_BUTTON),"Find element cancel button");
    }
    public void clickCancelButton(){
    this.searchElementAndClick(By.xpath(SEARCH_CANCEL_BUTTON),"Cannot find cancel button",5);
    }
    public void clickSaveButton(){
        this.searchElementAndClick(By.xpath(CLICK_BUTTON_SAVE),"Cannot find button Save",5);
    }
    public void clickButtonBack(){
        this.searchElementAndClick(By.xpath(CLICK_BUTTON_BACK),"Cannot find button back", 10);
    }
    public void clickButtonSaved(){
        this.searchElementAndClick(By.xpath(CLICK_BUTTON_SAVED),"Cannot find button Saved", 15);
        }
    public void setClickPackageSaved(){
        this.searchElementAndClick(By.xpath(CLICK_PACKAGE_SAVED),"Cannot find package Saved",15);
         }





}
