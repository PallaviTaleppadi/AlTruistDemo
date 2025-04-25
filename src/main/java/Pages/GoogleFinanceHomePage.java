package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
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
        List<WebElement> listOfDisplayedTickers = driver.findElements(displayedTickersList);
        System.out.println("Number of stock symbols:" + listOfDisplayedTickers.size());
        return listOfDisplayedTickers;
    }
}

