import com.codeborne.selenide.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class FreeCalcTests {
    //Проверка страницы: найти поле и вставить в него сумму, сделать расчет, а потом попытаться заменить сумму
    @Test
    //ошибочный тест
    void freeCalcTests() throws InterruptedException {
        open("https://slqa.ru/cases/fc/v01/");
        //Первый расчет
        $(By.name("sum")).sendKeys("200");
        $(By.name("submit")).click();
        sleep(3_000);
        //$(By.name("sum")).clear();

        SelenideElement se = $(By.name("sum"));
        System.out.println("Тег: " + se.getTagName()); //выводит span, уже другое поле
        System.out.println("Текст: " + se.getOwnText());
        System.out.println("Значение: " + se.getValue());
        System.out.println("Атрибут type: " + se.getAttribute("type"));
        se.clear();

        //Второй расчет
        $(By.name("sum")).setValue("500");
        $(By.name("submit")).click();
        sleep(3_000);
        //$(By.name("sum")).clear();

    }
    @Test
        //корректный тест (в CSS)
    void freeCalcTests2() throws InterruptedException {
        open("https://slqa.ru/cases/fc/v01/");
        //Первый расчет
        $("[name=sum]").sendKeys("100");
        $("[name=submit]").click();
        sleep(3_000);
        //добавить проверку
        $("h3").shouldBe(visible);
        $("span[name=sum]").shouldBe(Condition.text("100"));
        $("span[name=com]").shouldBe(Condition.text("10"));
        $("span[name=total]").shouldBe(Condition.text("110"));

        //Второй расчет
        $("input[name=sum]").clear();
        $("input[name=sum]").sendKeys("1000");
        $("[name=submit]").click();
        sleep(3_000);
        //добавить проверку
        $("h3").shouldBe(visible);
        $("span[name=sum]").shouldBe(Condition.text("1000"));
        $("span[name=com]").shouldBe(Condition.text("10"));
        $("span[name=total]").shouldBe(Condition.text("1010"));

        //Третий расчет
        $("input[name=sum]").clear();
        $("input[name=sum]").sendKeys("2000");
        $("[name=submit]").click();
        sleep(3_000);
        //добавить проверку
        $("h3").shouldBe(visible);
        $("span[name=sum]").shouldBe(Condition.text("2000"));
        $("span[name=com]").shouldBe(Condition.text("20"));
        $("span[name=total]").shouldBe(Condition.text("220"));

        //Четвертый расчет
        $("input[name=sum]").clear();
        $("input[name=sum]").sendKeys("10000");
        $("[name=submit]").click();
        sleep(3_000);
        //добавить проверку
        $("h3").shouldBe(visible);
        $("span[name=sum]").shouldBe(Condition.text("10000"));
        $("span[name=com]").shouldBe(Condition.text("100"));
        $("span[name=total]").shouldBe(Condition.text("10100"));

        //Пятый расчет
        $("input[name=sum]").clear();
        $("input[name=sum]").sendKeys("20000");
        $("[name=submit]").click();
        sleep(3_000);
        //добавить проверку
        $("h3").shouldBe(visible);
        $("span[name=sum]").shouldBe(Condition.text("20000"));
        $("span[name=com]").shouldBe(Condition.text("100"));
        $("span[name=total]").shouldBe(Condition.text("20100"));
    }
}
