package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static pageObject.constants.ScooterColours.*;

public class ScooterInfo {
    WebDriver driver;
    private final By rentHeader = By.className("Order_Header__BZXOb");
    private final By date = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By durationOfRent = By.xpath(".//span[@class='Dropdown-arrow']");
    private final By colourBlack = By.id("black");
    private final By colourGrey = By.id("grey");
    private final By comment = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By createOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    public ScooterInfo(WebDriver driver) {
        this.driver = driver;
    }

    //метод ожидания загрузки страницы
    public ScooterInfo waitInfoAboutRentHeader() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(rentHeader).getText() != null
                && !driver.findElement(rentHeader).getText().isEmpty()
        ));
        return this;
    }

    //ввод даты
    public ScooterInfo inputDate(String newDate) {
        driver.findElement(date).sendKeys(newDate);
        return this;
    }

    //ввод продолжительности аренды
    public ScooterInfo inputDuration(String newDuration) {
        driver.findElement(durationOfRent).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.className("Dropdown-menu"))).click();
        return this;
    }

    //изменение цвета
    public ScooterInfo changeColour(Enum colour) {
        if (colour.equals(BLACK_PEARL)) {
            driver.findElement(colourBlack).click();
        } else if (colour.equals(GREY_HOPELESSNESS)) {
            driver.findElement(colourGrey).click();
        }
        return this;
    }

    //ввод комментария к заказу
    public ScooterInfo inputComment(String newComment) {
        driver.findElement(comment).sendKeys(newComment);
        return this;
    }

    //нажать кнопку создать заказ
    public void clickButtonCreateOrder() {
        driver.findElement(createOrderButton).click();
    }

}
