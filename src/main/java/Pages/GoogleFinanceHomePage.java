package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GoogleFinanceHomePage {

    public WebDriver driver;

    public GoogleFinanceHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    By headerText = By.xpath("//a[@id='sdgBod']/span[contains(text(),'Finance')]");
    By headerFullText = By.xpath("//a[@id='sdgBod']");
    By watchListTitleText = By.xpath("//div[@role='main']//div[@id='smart-watchlist-title']");
    By displayedTickersList = By.xpath("//div[@role='main']//ul[@class='sbnBtf']/li//div[@class='COaKTb']");
    By displayedStockPriceList = By.xpath("//div[@role='main']//ul[@class='sbnBtf']//div[@class='SEGxAb']");
    By stockFollowButton1 = By.xpath("//div[@class='H8Ch1']//li[1]//div[@role='button']");
    By marketTrendHeaderText = By.xpath("//div[@class='sO6IOb']/div[contains(text(),'Market trends')]");
    By marketTrendLinkSection = By.xpath("//div[@class='yT4Sxe']");
    By marketIndexesLink = By.partialLinkText("indexes");
    By exploreMarketTrendHeaderText = By.xpath("//div[@class='TnyjJd'][contains(text(),'Explore market trends')]");
    By stockSearchInputField = By.xpath("//div[@class='AyKEed']//input[@role='combobox']");
    By searchResultStockSymbolHeader = By.xpath("//div[@class='PdOqHc']//text()");
    By displayedNegativeStockPrice =By.xpath("//div[@role='main']//ul[@class='sbnBtf']//li");


    public String pageHeader() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(headerText).isDisplayed();
        return driver.findElement(headerFullText).getText();
    }

    public String watchListTittle() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(watchListTitleText));
        driver.findElement(watchListTitleText).isDisplayed();
        String watchListHeaderText = driver.findElement(watchListTitleText).getText();
        System.out.println(" WatchList header title: " + watchListHeaderText);
        return watchListHeaderText;
    }

    public List<WebElement> retrieveStockSymbols() {
        List<WebElement> listOfDisplayedTickers = new ArrayList<>();
        try {
            listOfDisplayedTickers = driver.findElements(displayedTickersList);
            System.out.println("Number of stock symbols:" + listOfDisplayedTickers.size());

        } catch (Exception e) {
            System.out.println(" Exception caught " + e.getMessage());
        }
        return listOfDisplayedTickers;
    }

    public List<WebElement> retrieveStockPrice() {
        List<WebElement> listOfDisplayedStockPrice = new ArrayList<>();
        try {
            listOfDisplayedStockPrice = driver.findElements(displayedStockPriceList);
            try {
                if (!listOfDisplayedStockPrice.isEmpty()) {
                    System.out.println(" Number of Stock price list: " + listOfDisplayedStockPrice.size());
                }
            } catch (IllegalArgumentException e) {
                System.out.println(" Stock price list is empty()!");
                return new ArrayList<>();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.print(" Stock price list: ");
        for (WebElement element : listOfDisplayedStockPrice) {
            System.out.print(element.getText() + " ");
        }
        return listOfDisplayedStockPrice;
    }

    public String stockFollowButtonToolTip() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement followButton = driver.findElement(stockFollowButton1);
        Actions action = new Actions(driver);
        action.moveToElement(followButton).build().perform();
        String tooltipText = driver.findElement(stockFollowButton1).getAttribute("data-tooltip");
        System.out.println("Tool tip text: " + tooltipText);
        return tooltipText;

    }

    public Boolean marketTrendSectionLinks() {
        driver.findElement(marketTrendHeaderText).isDisplayed();
        driver.findElement(marketTrendLinkSection).isDisplayed();
        driver.findElement(marketIndexesLink).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(exploreMarketTrendHeaderText));
        return driver.findElement(exploreMarketTrendHeaderText).isDisplayed();
    }

    public void stockSearch(String stockSymbol) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(stockSearchInputField));
        WebElement searchInput = driver.findElement(stockSearchInputField);
        searchInput.isDisplayed();
        searchInput.click();
        searchInput.clear();
        searchInput.sendKeys(stockSymbol);
        System.out.println("Search text is entered! ");
        searchInput.sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public void pageRefresh() {
        driver.get("https://www.google.com/finance");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public void searchResultStockSymbolHeaderList() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultStockSymbolHeader));
        List<WebElement> searchResultHeaderTextList = driver.findElements(searchResultStockSymbolHeader);
        for(WebElement element:searchResultHeaderTextList ){
            System.out.print(element.getText() + " ");
        }


    }
    public List<WebElement> retrieveStockWithPriceValues(){
        List<WebElement> listOfDisplayedStocks = new ArrayList<>();

        try {
            listOfDisplayedStocks = driver.findElements(displayedNegativeStockPrice);
            if (!listOfDisplayedStocks.isEmpty()) {
                System.out.println(" Number of price list: " + listOfDisplayedStocks.size());
            }
            } catch (IllegalArgumentException e) {
                System.out.println(" Stock list is empty()!");
                return new ArrayList<>();
            }
        return listOfDisplayedStocks;
        }

}

