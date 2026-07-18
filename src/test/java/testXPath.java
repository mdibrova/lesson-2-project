import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class testXPath {
    @BeforeEach
    void openURL(){
        open("https://slqamsk.github.io/tmp/xPath01.html");
    }

    @Test
    void test01_PageH1(){
        $x("//h1").shouldBe(text("Учебная страница для XPath"));
    }

    @Test
    void test02_SpecialParagraph(){
        $x("//p[@class='special-paragraph']").shouldBe(text("Этот параграф особенный - он единственный на странице с таким классом"));
        $x("//p[@class='info-text']").shouldBe(text("Это первый информационный текст"));
    }

    @Test
    void test03_InfoTextsCount(){
        $x("//p[@class='info-text'][1]").shouldBe(text("Это первый информационный текст"));
        $x("//p[@class='info-text'][2]").shouldBe(text("Это второй информационный текст"));
        $x("//p[@class='info-text'][3]").shouldBe(text("Это третий информационный текст"));
    }

    @Test
    void test04_ExternalLinks(){
        $x("//a[@class='external-link'][1]").shouldBe(text("Внешняя ссылка (Example)"));
        $x("//a[@class='external-link'][2]").shouldBe(text("Внешняя ссылка (Google)"));

    }

//    @Test
//    void test04_ExternalLinks02(){
//        $x("//a[@class='external-link'][1]").shouldBe(text("Внешняя ссылка (Example)"));
//        $x("//a[@class='external-link'][2]").shouldBe(text("Внешняя ссылка (Google)"));
//    }
}
