package pageobject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;

import pageobject.constants.QuestionList;

import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class QuestionCorrespondances extends BaseTest {
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
                {QuestionList.QUESTION_1, QuestionList.ANSWER_1, QuestionList.ANSWER_PANEL_1, QuestionList.ANSWER_TEXT_1},
                {QuestionList.QUESTION_2, QuestionList.ANSWER_2, QuestionList.ANSWER_PANEL_2, QuestionList.ANSWER_TEXT_2},
                {QuestionList.QUESTION_3, QuestionList.ANSWER_3, QuestionList.ANSWER_PANEL_3, QuestionList.ANSWER_TEXT_3},
                {QuestionList.QUESTION_4, QuestionList.ANSWER_4, QuestionList.ANSWER_PANEL_4, QuestionList.ANSWER_TEXT_4},
                {QuestionList.QUESTION_5, QuestionList.ANSWER_5, QuestionList.ANSWER_PANEL_5, QuestionList.ANSWER_TEXT_5},
                {QuestionList.QUESTION_6, QuestionList.ANSWER_6, QuestionList.ANSWER_PANEL_6, QuestionList.ANSWER_TEXT_6},
                {QuestionList.QUESTION_7, QuestionList.ANSWER_7, QuestionList.ANSWER_PANEL_7, QuestionList.ANSWER_TEXT_7},
                {QuestionList.QUESTION_8, QuestionList.ANSWER_8, QuestionList.ANSWER_PANEL_8, QuestionList.ANSWER_TEXT_8},
        };
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

}

