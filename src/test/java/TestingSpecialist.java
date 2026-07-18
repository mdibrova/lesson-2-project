import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.*;

public class TestingSpecialist {
    @BeforeEach
    void openURL() {
        open("https://specialist.ru");
    }

    @Test
    void testingSpecialist(){
        //1) Перейдите в каталог курсов (Курсы - каталог курсов)
        //2) В поле поиска введите слово "тестирование" и запустите поиск.
        //3) Найдите на странице курс "Автоматизированное тестирование веб-приложений с использованием Selenium" (первый в списке)
        //4) Проверьте, что дата начала "24.01.2026"

    }

}
