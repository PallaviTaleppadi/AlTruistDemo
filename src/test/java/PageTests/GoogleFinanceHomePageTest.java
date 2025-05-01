package PageTests;

import Pages.GoogleFinanceHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoogleFinanceHomePageTest {

    public WebDriver driver;

    public static final List<String> expectedFinalTickerList = List.of("NFLX","MSFT","TSLA","AAPL","GOOG");
    public static final List<String> expectedStockPriceList = List.of("+$0.87","-$0.14","-$0.46","+$0.67","+$0.97");

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

    @Test
    public void verifyStockAndPriceTest(){
        GoogleFinanceHomePage homepage = new GoogleFinanceHomePage(driver);
        List<WebElement> listOfDisplayedTickers=homepage.retrieveStockWithPriceValues();
        List<String> actualListOfDisplayedTickers = new ArrayList<>();
        for (WebElement element : listOfDisplayedTickers) {
            actualListOfDisplayedTickers.add(element.getText());
        }
        System.out.println("Actual displayed ticker list: " + actualListOfDisplayedTickers );

        StringBuilder sb = new StringBuilder();
        for(String str:actualListOfDisplayedTickers){
            String[] arr= str.split("[\\s\\n]+");
            char charToCheck ='-';
            for (String s : arr) {
                if (s != null && s.charAt(0) == charToCheck) {
                    sb.append(arr[0]).append(" ");
                }
            }

            }
        System.out.println(" Stock Symbol list with negative price values: " + sb.toString().trim());

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



    @Test (priority = 1)
    public void compareActualListWithGivenExpected(){
        GoogleFinanceHomePage homepage = new GoogleFinanceHomePage(driver);
        List<WebElement> listOfDisplayedTickers =homepage.retrieveStockSymbols();
        List<String> actualListOfDisplayedTickers = new ArrayList<>();

        for (WebElement element : listOfDisplayedTickers) {
            actualListOfDisplayedTickers.add(element.getText());
        }

        Reporter.log("Testing with input list from XML: " + expectedFinalTickerList);
        Reporter.log("Actual displayed ticker list: " + actualListOfDisplayedTickers );

        Assert.assertNotEquals(actualListOfDisplayedTickers, expectedFinalTickerList,"Actual and Expected Stock symbols are same!!");
    }

    @Test (priority=2)
    public void retrieveStockPriceTest(){
        GoogleFinanceHomePage homepage = new GoogleFinanceHomePage(driver);
        List<WebElement> listOfDisplayedStockPrice =homepage.retrieveStockPrice();
        List<String> actualListOfDisplayedStockPrice = new ArrayList<>();
        for (WebElement element : listOfDisplayedStockPrice) {
            actualListOfDisplayedStockPrice.add(element.getText());
        }

        Reporter.log("Actual displayed stock price list: " + actualListOfDisplayedStockPrice);
    }

    @Test (priority =2)
    public void verifyToolTipTest(){
        GoogleFinanceHomePage homepage = new GoogleFinanceHomePage(driver);
        String expectedToolTipText ="Follow";
       String actualToolTipText= homepage.stockFollowButtonToolTip();
       Assert.assertEquals(actualToolTipText, expectedToolTipText," ToolTip doesn't match!");
    }

    @Test (priority =2)
    public void verifyMarketTrendIndexLinkTest(){
        GoogleFinanceHomePage homepage = new GoogleFinanceHomePage(driver);
        Assert.assertTrue(homepage.marketTrendSectionLinks(), " Header is not displayed");
    }

    @DataProvider (name = "StockSymbol_Search")
    public Object[][] dpMethod(){
        return new Object[][] {{"TSLA"},{"AMZN"},{"GOOG"},{"MSFT"}};
    }

    @Test(dataProvider ="StockSymbol_Search", priority = 1)
    public void verifySearchStockSymbolTest(String stockSymbol){
        GoogleFinanceHomePage homepage = new GoogleFinanceHomePage(driver);
        homepage.stockSearch(stockSymbol);
        Reporter.log("Search for:" + stockSymbol);
        //System.out.println(" Search is working fine for stock: !" + stockSymbol);
        homepage.pageRefresh();
    }

    @Test (priority=2)
    public void verifySearchStockHeaderTest(){
        GoogleFinanceHomePage homepage = new GoogleFinanceHomePage(driver);
        String stockSymbol = "AMZN";
        homepage.stockSearch(stockSymbol);
        Reporter.log("Search for:" + stockSymbol);
        homepage.searchResultStockSymbolHeaderList();

    }

    @AfterClass
    public void quitDriver(){
        driver.quit();
    }

}
