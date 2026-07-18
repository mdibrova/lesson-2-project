import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.*;

public class TestingCinema {
    @BeforeEach
    void openURL(){
        open("https://slqa.ru/cases/cinema/index_only_age.html");
    }

    @Test
    void testCinema(){
        $x("//button[contains(text(),'Рассчитать')]/ancestor::form");
        sleep(5_000);
    }
}
