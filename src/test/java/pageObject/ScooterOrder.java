package pageObject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertTrue;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static pageObject.constants.Buttons.NEXT_BUTTON;
import static pageObject.constants.Buttons.ORDER_BUTTON;
import static pageObject.constants.RentingDays.*;
import static pageObject.constants.ScooterColours.*;

@RunWith(Parameterized.class)
public class ScooterOrder {
    private WebDriver driver;
    private final String site = "https://qa-scooter.praktikum-services.ru/";
    private final String name;
    private final String surname;
    private final String address;
    private final int metroStationNumber;
    private final String telephoneNumber;
    private final String date;
    private final String duration;
    private final Enum colour;
    private final String comment;
    private final String expectedHeader = "Заказ оформлен";
    private final Enum button;

    public ScooterOrder(Enum button, String name, String surname, String address, int metroStationNumber, String telephoneNumber,
                        String date, String duration, Enum colour, String comment) {
        this.button = button;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStationNumber = metroStationNumber;
        this.telephoneNumber = telephoneNumber;
        this.date = date;
        this.duration = duration;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {ORDER_BUTTON, "Владимир", "Ленский", "Ул. Пушкинская, д. 1", 1, "88005553535", "01.06.2024", DAY_1, BLACK_PEARL, "а ну, ладно"},
                {ORDER_BUTTON, "Евгений", "Онегин", "Ул. Пушкинская, д. 2", 11, "88005553534", "02.06.2024", DAY_2, GREY_HOPELESSNESS, "хочу спать жесть"},
                {ORDER_BUTTON, "Татьяна", "Ларина", "Ул. Пушкинская, д. 3", 111, "78005553535", "03.06.2024", DAY_3, BLACK_PEARL, "привезите кибимпаб"},
                {NEXT_BUTTON, "Сестра", "Ларина", "Ул. Пушкинская, д. 4", 12, "88007773535", "04.06.2024", DAY_4, GREY_HOPELESSNESS, "мамаааааа уууууу"},
                {NEXT_BUTTON, "Жоне", "Рыдайло", "Ул. Пушкинская, д. 5", 13, "89005553535", "05.06.2024", DAY_5, BLACK_PEARL, "емае альбом от пилотов вышел"},
        };
    }

    @Before
    public void startUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get(site);
    }

    @Test
    public void testCreateOrderWithOrderButton() {
        new HomePage(driver)
                .waitForLoadHomePage()
                .clickCreateOrderButton(button);

        new TenantInfo(driver)
                .waitForLoadOrderPage()
                .inputName(name)
                .inputSurname(surname)
                .inputAddress(address)
                .changeMetroStation(metroStationNumber)
                .inputTelephoneNumber(telephoneNumber)
                .clickNextButton();

        new ScooterInfo(driver)
                .waitInfoAboutRentHeader()
                .inputDate(date)
                .inputDuration(duration)
                .changeColour(colour)
                .inputComment(comment)
                .clickButtonCreateOrder();

        InformationMessage informationMessage = new InformationMessage(driver);
        informationMessage.clickButtonYes();

        assertTrue(informationMessage.getHeaderAfterCreateOrder().contains(expectedHeader));
    }

    @After
    public void shutdown() {
        driver.quit();
    }

}
