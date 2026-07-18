import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class WaitsSimpleForm {
    @Test
    void taimingTest(){
    open("https://slqa.ru/cases/WaitsSimpleForm/");
        $x("//input[@id='duration_time']")
            .shouldBe(exist)
            .shouldBe(visible)
            .setValue("10");
    $x("//button[contains(text(),'Загрузить динамическое содержимое')]")
            .shouldBe(exist)
            .shouldBe(visible)
            .click();
    Configuration.timeout = 12000;
    $x("//div[@id='dynamic_content']")
            .shouldBe(exist)
            .shouldBe(visible)
            .shouldHave(text("за 10000 миллисекунд."));
    }
}
