package pageobject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertTrue;

import pageobject.constants.Buttons;
import pageobject.constants.RentingDays;
import pageobject.constants.ScooterColours;


import static pageobject.constants.Buttons.NEXT_BUTTON;
import static pageobject.constants.Buttons.ORDER_BUTTON;

@RunWith(Parameterized.class)
public class ScooterOrder extends BaseTest {
    private final Buttons buttons;
    private final String name;
    private final String surname;
    private final String address;
    private final int metroStationNumber;
    private final String telephoneNumber;
    private final String date;
    private final String duration;
    private final ScooterColours scooterColour;
    private final String comment;
    private final String expectedHeader = "Заказ оформлен";


    public ScooterOrder(Buttons buttons, String name, String surname, String address, int metroStationNumber, String telephoneNumber, String date, String duration, ScooterColours scooterColour, String comment) {
        this.buttons = buttons;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStationNumber = metroStationNumber;
        this.telephoneNumber = telephoneNumber;
        this.date = date;
        this.duration = duration;
        this.scooterColour = scooterColour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {ORDER_BUTTON, "Владимир", "Ленский", "Ул. Пушкинская, д. 1", 1, "88005553535", "01.06.2024", RentingDays.DAY_1, ScooterColours.BLACK_PEARL, "а ну, ладно"},
                {ORDER_BUTTON, "Евгений", "Онегин", "Ул. Пушкинская, д. 2", 11, "88005553534", "02.06.2024", RentingDays.DAY_2, ScooterColours.GREY_HOPELESSNESS, "хочу спать жесть"},
                {ORDER_BUTTON, "Татьяна", "Ларина", "Ул. Пушкинская, д. 3", 111, "78005553535", "03.06.2024", RentingDays.DAY_3, ScooterColours.BLACK_PEARL, "привезите кибимпаб"},
                {NEXT_BUTTON, "Сестра", "Ларина", "Ул. Пушкинская, д. 4", 12, "88007773535", "04.06.2024", RentingDays.DAY_4, ScooterColours.GREY_HOPELESSNESS, "мамаааааа уууууу"},
                {NEXT_BUTTON, "Жоне", "Рыдайло", "Ул. Пушкинская, д. 5", 13, "89005553535", "05.06.2024", RentingDays.DAY_5, ScooterColours.BLACK_PEARL, "емае альбом от пилотов вышел"},
        };
    }

    @Test
    public void testCreateOrderWithOrderButton() {
        new HomePage(driver)
                .waitForLoadHomePage()
                .clickCreateOrderButton(buttons);

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
                .changeColour(scooterColour)
                .inputComment(comment)
                .clickButtonCreateOrder();

        InformationMessage informationMessage = new InformationMessage(driver);
        informationMessage.clickButtonYes();

        assertTrue(informationMessage.getHeaderAfterCreateOrder().contains(expectedHeader));
    }
}
