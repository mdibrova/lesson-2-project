import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;

public class IFrameTest {
    @Test
    void test01IFrame() {
        //Configuration.pageLoadTimeout = 120_000;
        Configuration.pageLoadStrategy = "eager";
        open("https://demoqa.com/frames");
        getWebDriver().manage().window().maximize();

        switchTo().frame($x("//*[@id='frame1']"));
        $x("//*[@id='frame1']").click();

        //sleep(5_000);
        //$x("//h1").shouldHave(text("This is a sample page"));
    }
}
