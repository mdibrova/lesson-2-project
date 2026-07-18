import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.files.DownloadActions.*;

public class TestingPizza {
    @BeforeEach
    void openURL(){
        open("https://slqamsk.github.io/cases/pizza/v08/");
    }
    @Test
    void testAddPizza(){
    /*добавить пиццы "Маргарита" и "Четыре сыра" в корзину, не пользуясь атрибутом data-id*/
        $x("//h3[contains(text(),'Маргарита')]/parent::div/button")
                .shouldBe(exist)
                .shouldBe(visible)
                .click();
        $x("//h3[contains(.,'Четыре сыра')]/../button") //альтернативный вариант написания
                .shouldBe(exist)
                .shouldBe(visible)
                .click();

        $x("//*[@class='cart-link']") //переход в корзину
                .shouldBe(exist)
                .shouldBe(visible)
                .click();
//проверить, что в корзине
        sleep(5_000); //добавляется для презентации
    }
}
