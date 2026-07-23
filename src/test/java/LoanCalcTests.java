import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class LoanCalcTests {
    @BeforeEach
    void openURL(TestInfo test_info){
        System.out.println("Тест " + test_info.getDisplayName() + " - начали выполнение.");
        open("https://slqamsk.github.io/cases/loan-calc/v01");
    }

    @AfterEach
    void closeWindowURL(TestInfo test_info) {
        closeWindow();
        System.out.println("Тест " + test_info.getDisplayName() + " - закончили выполнение.\n");
    }

    @Test
    void fillingCellsTest01() {
        //Заполнить 3 поля и нажать кнопку "Рассчитать" - Самая низкая сложность //
        $x("//input[@id='amount']")
                .shouldBe(exist)
                .shouldBe(visible)
                .sendKeys("5000000");

        $x("//input[@id='term']")
                .shouldBe(exist)
                .shouldBe(visible)
                .sendKeys("150");

        $x("//input[@id='rate']")
                .shouldBe(exist)
                .shouldBe(visible)
                .sendKeys("24.7");

        $x("//input[@value='annuity']")
                .shouldBe(exist)
                .shouldBe(visible)
                .click();

        //sleep(5000);

        $x("//button[contains(text(),'Рассчитать')]")
                .shouldBe(exist)
                .shouldBe(visible)
                .click();

    /*Дождаться появления результатов расчёта и проверить, что указанные параметры кредита корректные,
    а также, что сумма аннуитетного платежа вычислена правильно - Низкая сложность */

        Configuration.timeout = 12000;
        //sleep(10000);

        $x("//h2[contains(text(),'Результаты расчёта')]")
                .shouldBe(exist)
                .shouldBe(visible);

        $x("//span[@id='result-amount']")
                .shouldBe(exist)
                .shouldBe(visible)
                .shouldHave(text("5000000.00"));

        $x("//span[@id='result-term']")
                .shouldBe(exist)
                .shouldBe(visible)
                .shouldHave(text("150"));

        $x("//span[@id='result-rate']")
                .shouldBe(exist)
                .shouldBe(visible)
                .shouldHave(text("24.7"));

        $x("//span[@id='result-payment-type']")
                .shouldBe(exist)
                .shouldBe(visible)
                .shouldHave(text("Аннуитетный"));

        $x("//span[@id='monthly-payment']")
                .shouldBe(exist)
                .shouldBe(visible)
                .shouldHave(text("108000.05"));

        $x("//span[@id='total-payment']")
                .shouldBe(exist)
                .shouldBe(visible)
                .shouldHave(text("16200008.10"));


    /*Открыть график платежей и убедиться, что туда корректно перенесены данные
    со страницы результатов расчёта - Средняя сложность  */

        $x("//button[contains(text(),'Открыть график платежей')]")
                .shouldBe(exist)
                .shouldBe(visible)
                .click();

        switchTo().window(1);
        $x("//h2[contains(text(),'График платежей')]")
                .shouldBe(exist)
                .shouldBe(visible);

        //проверка результатов расчета
        $x("//p[contains(text(),'Сумма кредита')]")
                .shouldBe(exist)
                .shouldBe(visible)
                .shouldHave(text("5000000.00"));

        $x("//p[contains(text(),'Срок кредита')]")
                .shouldBe(exist)
                .shouldBe(visible)
                .shouldHave(text("150"));

        $x("//p[contains(text(),'Процентная ставка')]")
                .shouldBe(exist)
                .shouldBe(visible)
                .shouldHave(text("24.7"));

        $x("//p[contains(text(),'Тип платежа')]")
                .shouldBe(exist)
                .shouldBe(visible)
                .shouldHave(text("Аннуитетный"));

        //$x("//tr[2]/td[2]") //и цикл до 150
        //.shouldBe(exist)
        //.shouldBe(visible)
        //        .sendKeys("108000.05");

        //Проверка Основного долга, процентов и остатка долга-? //

        Selenide.closeWindow();

        switchTo().window(0);
        $x("//h2[contains(text(),'Результаты расчёта')]")
                .shouldBe(exist)
                .shouldBe(visible);

        $x("//button[contains(text(),'Выполнить новый расчёт')]")
                .shouldBe(exist)
                .shouldBe(visible)
                .click();

    }

    //@Test
    /*Добавить 2-й параметризованный автотест, который проверит расчёт для разных сроков кредита - Сложность чуть выше среднего */
    //void fillingCellsTest02(){    }

    /*напишите полный набор автотестов для тестирования этого кредитного калькулятора - Наибольшая сложность */

}
