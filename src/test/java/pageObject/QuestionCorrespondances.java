package pageObject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;
import static pageObject.constants.QuestionList.*;

//import static ;

@RunWith(Parameterized.class)
public class QuestionCorrespondances {
    private WebDriver driver;
    private final String site = "https://qa-scooter.praktikum-services.ru/";
    private final By question;
    private final By answer;
    private final By labelResult;
    private final String expected;

    public QuestionCorrespondances(By question, By answer, By labelResult, String expected) {
        this.question = question;
        this.answer = answer;
        this.labelResult = labelResult;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {QUESTION_1, ANSWER_1, ANSWER_PANEL_1, ANSWER_TEXT_1},
                {QUESTION_2, ANSWER_2, ANSWER_PANEL_2, ANSWER_TEXT_2},
                {QUESTION_3, ANSWER_3, ANSWER_PANEL_3, ANSWER_TEXT_3},
                {QUESTION_4, ANSWER_4, ANSWER_PANEL_4, ANSWER_TEXT_4},
                {QUESTION_5, ANSWER_5, ANSWER_PANEL_5, ANSWER_TEXT_5},
                {QUESTION_6, ANSWER_6, ANSWER_PANEL_6, ANSWER_TEXT_6},
                {QUESTION_7, ANSWER_7, ANSWER_PANEL_7, ANSWER_TEXT_7},
                {QUESTION_8, ANSWER_8, ANSWER_PANEL_8, ANSWER_TEXT_8},
        };
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(site);
    }

    @Test
    public void checkQuestions() {
        new HomePage(driver)
                .waitForLoadHomePage()
                .scrollToQuestions()
                .clickQuestion(question)
                .waitForLoadAnswer(labelResult);
        String result = driver.findElement(answer).getText();

        assertEquals(expected, result);
    }

    @After
    public void shutdown() {
        driver.quit();
    }

}

