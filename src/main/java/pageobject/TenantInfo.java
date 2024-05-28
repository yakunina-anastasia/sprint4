package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TenantInfo {
    WebDriver driver;
    private final By orderHeader = By.className("Order_Header__BZXOb");
    private final By name = By.xpath(".//input[@placeholder='* Имя']");
    private final By surname = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By address = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroStation = By.className("select-search__input");
    private final By telephoneNumber = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By buttonNext = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private final String metroStationNaming = ".//button[@value='%s']";

    public TenantInfo(WebDriver driver) {
        this.driver = driver;
    }

    //ожидание загруки страницы заказа
    public TenantInfo waitForLoadOrderPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(orderHeader).getText() != null
                && !driver.findElement(orderHeader).getText().isEmpty()
        ));
        return this;
    }

    //ввести имя
    public TenantInfo inputName(String newName) {
        driver.findElement(name).sendKeys(newName);
        return this;
    }

    //ввести фамилию
    public TenantInfo inputSurname(String newSurname) {
        driver.findElement(surname).sendKeys(newSurname);
        return this;
    }

    //ввести адрес
    public TenantInfo inputAddress(String newAddress) {
        driver.findElement(address).sendKeys(newAddress);
        return this;
    }

    //изменение станции
    public TenantInfo changeMetroStation(int stateNumber) {
        driver.findElement(metroStation).click();
        By newStateMetro = By.xpath(String.format(metroStationNaming, stateNumber));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(newStateMetro));
        driver.findElement(newStateMetro).click();
        return this;
    }

    //ввести номер телефона
    public TenantInfo inputTelephoneNumber(String newTelephone) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(telephoneNumber));
        driver.findElement(telephoneNumber).sendKeys(newTelephone);
        return this;
    }

    //нажать далее
    public void clickNextButton() {
        driver.findElement(buttonNext).click();
    }

}
