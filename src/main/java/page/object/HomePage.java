package page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.object.constants.Buttons;
import page.object.constants.ScooterColours;

import java.time.Duration;

public class HomePage {
    WebDriver driver;
    private final By homeHeader = By.className("Home_Header__iJKdX");
    private final By orderButton = By.className("Button_Button__ra12g");
    private final By nextOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private final By questionsHeader = By.className("Home_FourPart__1uthg");
    private final By stateOfOrder = By.xpath(".//button[text()='Статус заказа']");
    private final By numberOfOrder = By.xpath(".//input[@placeholder='Введите номер заказа']");
    private final By goButton = By.xpath(".//button[text()='Go!']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //ожидание загрузки главной страницы
    public HomePage waitForLoadHomePage() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(driver -> (driver.findElement(homeHeader).getText() != null
                && !driver.findElement(homeHeader).getText().isEmpty()
        ));
        return this;
    }

    //ожиданиу загрузки ответа на вопрос
    public void waitForLoadAnswer(By accordionLabel) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(accordionLabel).getText() != null
                && !driver.findElement(accordionLabel).getText().isEmpty()
        ));
    }

    //прокрутка к вопросам о важном
    public HomePage scrollToQuestions() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(questionsHeader));
        return this;
    }

    //прокрутка ко второй кнопке заказать
    public HomePage scrollToNextOrderButton() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(nextOrderButton));
        return this;
    }

    //нажать кнопку заказать
    public HomePage clickOrderButton() {
        driver.findElement(orderButton).click();
        return this;
    }

    //нажать вторую кнопку заказать
    public HomePage clickNextOrderButton() {
        driver.findElement(nextOrderButton).click();
        return this;
    }

    public HomePage clickCreateOrderButton(Buttons buttons) {
        switch (buttons) {
            case ORDER_BUTTON:
                clickOrderButton();
                break;
            case NEXT_BUTTON:
                scrollToNextOrderButton();
                clickNextOrderButton();
                break;
        }
        return  this;
    }

    //нажать на вопросы
    public HomePage clickQuestion(By question) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(question))
                .click();
        return this;
    }
}
