
import lib.CoreTestCase;
import lib.ui.ArticalPageObject;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.*;


public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testMainTest() {
        System.out.println("My Test");
    }


    @Test
    public void testSearch() {

        WebElement element_skip = driver.findElementByXPath("//*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']");
        element_skip.click();

        WebElement element_to_enter_search = MainPageObject.assertElementHasText(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find element",
                5
        );

    }

    @Test
    public void testCancelSearch() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.skipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        SearchPageObject.clickCancelButton();
        SearchPageObject.waitForCancelButton();
    }

    @Test
    public void testSaveArticles() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.skipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.clickArticlSearchResult("High-level programming language");
        SearchPageObject.clickSaveButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.clickSearchInTitle("java");
        SearchPageObject.clickArticlSearchResult("Island in Southeast Asia");
        SearchPageObject.clickSaveButton();
        SearchPageObject.clickButtonBack();
        SearchPageObject.clickButtonBack();
        SearchPageObject.clickButtonBack();
        SearchPageObject.clickButtonSaved();
        SearchPageObject.clickButtonSaved();

        ArticalPageObject ArticalPageObject = new ArticalPageObject(driver);
        ArticalPageObject.swipeToLeft("Island in Southeast Asia");

        SearchPageObject.waitForSearchResult("High-level programming language");
    }

    @Test
    public void testAssertTitleArticle() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.skipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.clickArticlSearchResult("High-level programming language");
        SearchPageObject.waitForSearchResult("High-level programming language");

    }

}
