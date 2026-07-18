import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class XPathSimpleForm {

        @Test
        void testTextSearch01 () {
            open("https://slqa.ru/cases/xPathSimpleForm/");

            // Так - правильно, т.к. он не выбирает родительский div
            $x("//div[contains(text(),'Москва')]").shouldHave(text("250 единиц"));

            // Так - неправильно, т.к. он вместо нужного элемента возьмёт родительский div
            $x("//div[contains(.,'Москва')]").shouldHave(text("250 единиц"));
        }
        @Test
        void testTextSearch02 () {
            open("https://slqa.ru/cases/xPathSimpleForm/");

            $x("//div[contains(text(),'Питер')]").shouldHave(text("180 единиц"));
        }
        @Test
        void testTextSearch03 () {
            open("https://slqa.ru/cases/xPathSimpleForm/");

            $x("//div[starts-with(text(),'Казахстан')]").shouldHave(text("площадь 2 724 902"));
        }
}
