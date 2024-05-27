package page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InformationMessage {
    WebDriver driver;
    private final By informationMessageHeaderAfterCreateOrder = By.xpath(".//div[text()='Заказ оформлен']");
    private final By buttonYes = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");

    public InformationMessage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickButtonYes() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(buttonYes)).click();
    }

    public String getHeaderAfterCreateOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(informationMessageHeaderAfterCreateOrder).getText() != null
                && !driver.findElement(informationMessageHeaderAfterCreateOrder).getText().isEmpty()
        ));
        return driver.findElement(informationMessageHeaderAfterCreateOrder).getText();
    }

}
