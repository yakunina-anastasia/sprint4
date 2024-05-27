package page.object;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

import static page.object.constants.Site.SITE;

public class ForTests {
    public WebDriver driver;

    @Before
   public void startUp() {
       WebDriverManager.chromedriver().setup();
     driver = new ChromeDriver();
      driver.get(SITE);
   }

//    @Before
//    public void startUp() {
//        WebDriverManager.firefoxdriver().setup();
//        driver = new FirefoxDriver();
//        driver.get(SITE);
//    }

    @After
    public void shutdown() {
        driver.quit();
    }
}
