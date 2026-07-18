import org.junit.Ignore;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class FirstLoginTests {
    //для тестирования формы логина
    @BeforeEach
    void openURL(){
        open("https://slqa.ru/cases/ChatGPTLogin/");
    }

    @Test
    void Test01() {
        //Корректные логин и пароль - успешный вход в систему по нажатию кнопки "Login"
        $("#username").sendKeys("standard_user");
        $("#password").sendKeys("secret_sauce");
        $("#loginButton").click();
        $("#message").shouldHave(text("Вход в систему выполнен успешно"));
        sleep(3_000);
    }

    @Test
    void Test02() {
        //Корректный логин, пароль не соответствует логину - ошибка
        $("#username").sendKeys("standard_user");
        $("#password").sendKeys("p@SSword*237");
        $("#loginButton").click();
        $("#message").shouldHave(text("Invalid username or password"));
        sleep(3_000);
    }

    @Test
    void Test03() {
        //Корректные логин и пароль - успешный вход в систему по нажатию клавиши Enter на клавиатуре
        $("#username").sendKeys("standard_user");
        $("#password").sendKeys("secret_sauce");
        $("#loginButton").pressEnter();
        $("#message").shouldHave(text("Вход в систему выполнен успешно"));
        sleep(3_000);
    }
    @Test
    void Test04() {
        //Выход из системы
        $("#username").sendKeys("standard_user");
        $("#password").sendKeys("secret_sauce");
        $("#loginButton").click();
        $("#message").shouldHave(text("Вход в систему выполнен успешно"));
        $("#logoutButton").click();
        $("#loginButton").shouldBe(visible);
    }

    @Test
    void Test05() {
        //Некорректный логин, пароль от корректного логина - ошибка
        $("#username").sendKeys("secret_user");
        $("#password").sendKeys("secret_sauce");
        $("#loginButton").click();
        $("#message").shouldHave(text("Invalid username or password"));
        sleep(3_000);
    }

    @Test
    void Test06_login_and_password_not_matched() {
        //Корректный логин, пароль от другого корректного логина - ошибка (https://slqa.ru/cases/ChatGPTLogin/index_v04.html - есть пользователь user06/user06_password)
        open("https://slqa.ru/cases/ChatGPTLogin/index_v04.html");
        $("#username").sendKeys("user06");
        $("#password").sendKeys("user06_password");
        $("#loginButton").click();
        $("#message").shouldHave(text("Вход в систему выполнен успешно"));
        sleep(6_000);
        $("#logoutButton").click();
        $("#username").sendKeys("standard_user");
        $("#password").sendKeys("user06_password");
        $("#loginButton").click();
        $("#message").shouldHave(text("Invalid username or password"));
        sleep(3_000);
    }

    @Test
    void Test07() {
        //Проверить, что под заблокированным пользователем нельзя войти в систему (пользователь locked_out_user)
        $("#username").sendKeys("locked_out_user");
        $("#password").sendKeys("secret_sauce");
        $("#loginButton").click();
        $("#message").shouldHave(text("Пользователь заблокирован"));
        sleep(3_000);
    }

    @Test
    void Test08() {
        //Пустой логин, пароль от корректного логина
        $("#password").sendKeys("secret_sauce");
        $("#loginButton").pressEnter();
        $("#message").shouldHave(text("Username is required"));
        sleep(3_000);
    }

    @Test
    void Test09() {
        //Пустой пароль, корректный логин
        $("#username").sendKeys("standard_user");
        $("#loginButton").click();
        $("#message").shouldHave(text("Password is required"));
        sleep(3_000);
    }
    @Test
    void Test10() {
        //Пустые логин и пароль
        $("#loginButton").click();
        $("#message").shouldHave(text("Username and Password are required"));
        sleep(3_000);
    }
    @Ignore
    @Test
    void Test11() {
        //Проверить, что при вводе пароль скрыт за звёздочками

    }

    @Test
    void Test12() {
        //Проверить вход в систему под несколькими разными логинами
        $("#username").sendKeys("standard_user");
        $("#password").sendKeys("secret_sauce");
        $("#loginButton").click();
        $("#message").shouldHave(text("Вход в систему выполнен успешно"));
        $("#logoutButton").click();
        $("#loginButton").shouldBe(visible);

        $("#username").sendKeys("locked_out_user");
        $("#password").sendKeys("secret_sauce");
        $("#loginButton").click();
        $("#message").shouldHave(text("Пользователь заблокирован"));
        $("#username").clear();
        $("#password").clear();

        $("#username").sendKeys("problem_user");
        $("#password").sendKeys("secret_sauce");
        $("#loginButton").click();
        $("#message").shouldHave(text("Вход в систему выполнен успешно"));
        $("#logoutButton").click();
        $("#loginButton").shouldBe(visible);

        $("#username").sendKeys("performance_glitch_user");
        $("#password").sendKeys("secret_sauce");
        $("#loginButton").click();
        $("#message").shouldHave(text("Вход в систему выполнен успешно"));
        $("#logoutButton").click();
        $("#loginButton").shouldBe(visible);

        $("#username").sendKeys("error_user");
        $("#password").sendKeys("secret_sauce");
        $("#loginButton").click();
        $("#message").shouldHave(text("Вход в систему выполнен успешно"));
        $("#logoutButton").click();
        $("#loginButton").shouldBe(visible);

        $("#username").sendKeys("visual_user");
        $("#password").sendKeys("secret_sauce");
        $("#loginButton").click();
        $("#message").shouldHave(text("Вход в систему выполнен успешно"));
        $("#logoutButton").click();
        $("#loginButton").shouldBe(visible);

    }
}
