import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SimpleFormTest {
    @Test
    void test01_specific_commands(){
        open("https://slqa.ru/cases/SimpleForm/");
        $(By.id("unique_id"))
                .sendKeys("Поле по ID найдено успешно"); //внесите в него любой текст
        $(By.name("unique_name"))
                .sendKeys("Поле по имени найдено успешно"); //внесите в него любой текст
        $(By.tagName("blockquote"))
                .shouldHave(text("спрашивает")); //проверьте, что в ней есть слово "спрашивает"
        $(By.className("unique_class"))
                .shouldBe(visible); //проверьте, что он видимый на странице
        sleep(5_000);

    }

    @Test
    void test02_CSS_selectors(){
        open("https://slqa.ru/cases/SimpleForm/");
        $("#unique_id")
                .sendKeys("Поле по ID найдено успешно"); //внесите в него любой текст
        $("[name=unique_name]")
                .sendKeys("Поле по имени найдено успешно"); //внесите в него любой текст
        $("blockquote")
                .shouldHave(text("спрашивает")); //проверьте, что в ней есть слово "спрашивает"
        $(".unique_class")
                .shouldBe(visible); //проверьте, что он видимый на странице
        sleep(5_000);

    }

    @Test
    void test03_xPath(){
        open("https://slqa.ru/cases/SimpleForm/");
        $x("//*[@id='unique_id']")
                .sendKeys("Поле по ID найдено успешно"); //внесите в него любой текст
        $x("//*[@name='unique_name']")
                .sendKeys("Поле по имени найдено успешно"); //внесите в него любой текст
        $x("//blockquote")
                .shouldHave(text("спрашивает")); //проверьте, что в ней есть слово "спрашивает"
        $x("//*[@class='unique_class']")
                .shouldBe(visible); //проверьте, что он видимый на странице
        sleep(5_000);
    }
}
