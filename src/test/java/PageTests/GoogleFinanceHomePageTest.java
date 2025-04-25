package PageTests;

import Pages.GoogleFinanceHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoogleFinanceHomePageTest {

    public WebDriver driver;

    @BeforeClass
    public void InitialSetUp(){
        System.setProperty("webdriver.chrome.driver","C:\\M2\\chromedriver.exe");
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com/finance");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }
    @Test (priority = 1)
    public void verifyPageHeaderTest(){
        GoogleFinanceHomePage homepage = new GoogleFinanceHomePage(driver);
        String actualPageHeaderText=homepage.pageHeader();
        String expectedTitleHeader = "Google Finance";
        Assert.assertTrue(expectedTitleHeader.contains(actualPageHeaderText)," Header does not contain the text");
    }

    @Test (priority = 1)
    public void verifyWatchListHeaderTitleTest(){
        GoogleFinanceHomePage homepage = new GoogleFinanceHomePage(driver);
        String actualWatchListTittleText=homepage.watchListTittle();
        Reporter.log("Actual Title: " + actualWatchListTittleText);
        String expectedWatchListTittleText = "You may be interested in";
        Assert.assertTrue(actualWatchListTittleText.contains(expectedWatchListTittleText), "WatchList tittle doesn't match");
    }

    @Test (priority = 1)
    public void verifyRetrieveTickerTest(){
        GoogleFinanceHomePage homepage = new GoogleFinanceHomePage(driver);
        List<WebElement> listOfDisplayedTickers =homepage.retrieveStockSymbols();
        Reporter.log(" Actual Displayed ticker list: ");
        for (WebElement element : listOfDisplayedTickers) {
            Reporter.log(element.getText());
        }
    }

    @Test (priority = 1)
    @Parameters("StockSymbolList")
    public void compareListAndDisplaySymbolsTest(String stockSymbolList) {
        List<String> expectedStockSymbolList = Arrays.asList(stockSymbolList.split(","));

        GoogleFinanceHomePage homepage = new GoogleFinanceHomePage(driver);
        List<WebElement> listOfDisplayedTickers =homepage.retrieveStockSymbols();
        List<String> actualListOfDisplayedTickers = new ArrayList<>();

        for (WebElement element : listOfDisplayedTickers) {
            actualListOfDisplayedTickers.add(element.getText());
        }

        Reporter.log("Testing with input list from XML: " + expectedStockSymbolList);
        Reporter.log("Actual displayed ticker list: " + actualListOfDisplayedTickers );

        Assert.assertNotEquals(expectedStockSymbolList,actualListOfDisplayedTickers,"Actual and Expected Stock symbols are same!!");
    }

    @Test (priority = 1)
    @Parameters("StockSymbolList")
    public void verifyMissingSymbolsInListsTest(String stockSymbolList) {
        List<String> expectedStockSymbolList = Arrays.asList(stockSymbolList.split(","));

        GoogleFinanceHomePage homepage = new GoogleFinanceHomePage(driver);
        List<WebElement> listOfDisplayedTickers =homepage.retrieveStockSymbols();
        List<String> actualListOfDisplayedTickers = new ArrayList<>();

        for (WebElement element : listOfDisplayedTickers) {
            actualListOfDisplayedTickers.add(element.getText());
        }

        Reporter.log("Testing with input list from XML: " + expectedStockSymbolList);
        Reporter.log("Actual displayed ticker list: " + actualListOfDisplayedTickers );

        if (actualListOfDisplayedTickers.size() >= expectedStockSymbolList.size()) {
            actualListOfDisplayedTickers.removeAll(expectedStockSymbolList);
            Reporter.log(" List of Stock Symbols which are in Actual list but not in Expected List");
            System.out.println(actualListOfDisplayedTickers);
        }
        else {
            expectedStockSymbolList.removeAll(actualListOfDisplayedTickers);
           Reporter.log(" List of Stock Symbols which are in Expected list but not in Actual List");
            System.out.println(expectedStockSymbolList);
        }

    }

    @Test (priority = 2)
    @Parameters("StockSymbolList")
    public void compareListPassFailsTest(String stockSymbolList) {
        List<String> expectedStockSymbolList = Arrays.asList(stockSymbolList.split(","));

        GoogleFinanceHomePage homepage = new GoogleFinanceHomePage(driver);
        List<WebElement> listOfDisplayedTickers =homepage.retrieveStockSymbols();
        List<String> actualListOfDisplayedTickers = new ArrayList<>();

        for (WebElement element : listOfDisplayedTickers) {
            actualListOfDisplayedTickers.add(element.getText());
        }

        System.out.println("Testing with input list from XML: " + expectedStockSymbolList);
        System.out.println("Actual displayed ticker list: " + actualListOfDisplayedTickers );

        Assert.assertEquals(expectedStockSymbolList,actualListOfDisplayedTickers,"Actual and Expected Stock symbols are not same!!");
    }

    @AfterClass
    public void quitDriver(){
        driver.quit();
    }

}
