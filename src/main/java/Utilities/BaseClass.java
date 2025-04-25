package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseClass {

   public WebDriver driver;



    public void launchBrowser(){
        System.setProperty("webdriver.chrome.driver","C:\\M2\\chromedriver.exe");
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com/finance");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }
}
