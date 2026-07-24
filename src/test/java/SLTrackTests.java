import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SLTrackTests {
    @BeforeEach
    void openURL(TestInfo test_info) {
        System.out.println("Тест " + test_info.getDisplayName() + " - начали выполнение.");
        open("https://slqamsk.github.io/cases/sltrack/v02");
    }

    @AfterEach
    void closeWindowURL(TestInfo test_info) {
        closeWindow();
        System.out.println("Тест " + test_info.getDisplayName() + " - закончили выполнение.\n");
    }

    @Test
    void simpleTestsForm(){
        $x("//h2[contains(text(), 'SL-Track — Вход в систему управления задачами')]")
                .shouldBe(exist)
                .shouldBe(visible);
        $x("//label[contains(text(),'Email')]")
                .shouldBe(exist)
                .shouldBe(visible);
        $x("//label[contains(text(),'Пароль')]")
                .shouldBe(exist)
                .shouldBe(visible);
        $x("//input[@placeholder='user@example.com']")
                .shouldBe(exist)
                .shouldBe(visible);
        $x("//input[@placeholder='••••••••']")
                .shouldBe(exist)
                .shouldBe(visible);
    }

    @Test
    void avtorizationTest(){
        $x("//input[@id='username']")
                .shouldBe(exist)
                .shouldBe(visible)
                .sendKeys("admin@example.com");
        $x("//input[@id='password']")
                .shouldBe(exist)
                .shouldBe(visible)
                .sendKeys("admin123");
        $x("//button[contains(text(),'Войти')]")
                .shouldBe(exist)
                .shouldBe(visible)
                .click();

        $x("//h2[contains(text(), 'Добро пожаловать в систему управления задачами SL-Track!')]")
                .shouldBe(exist)
                .shouldBe(visible);
        $x("//span[@id='user-name']")
                .shouldBe(exist)
                .shouldBe(visible)
                .shouldHave(Condition.text("Пётр Иванов"));
        $x("//span[@id='user-role']")
                .shouldBe(exist)
                .shouldBe(visible)
                .shouldHave(Condition.text("Администратор"));
        $x("//button[contains(text(),'Выйти')]")
                .shouldBe(exist)
                .shouldBe(visible);


        $x("//button[contains(text(),'Мои задачи')]")
                .shouldBe(exist)
                .shouldBe(visible)
                .click();

        //Проверка, что на "Пётр Иванов" вообще есть задачи
        ElementsCollection taskIvanov = $$x("//div[contains(@class, 'task-assignee') and contains(text(),'Пётр Иванов')]");
        taskIvanov.shouldHave(sizeGreaterThan(0));
        // 2. Проверяем, что лишних задач нет
        ElementsCollection taskForeign = $$x("//div[contains(@class, 'task-assignee') and not(contains(text(),'Пётр Иванов'))]");
        taskForeign.shouldBe(com.codeborne.selenide.CollectionCondition.empty);
    }
}
