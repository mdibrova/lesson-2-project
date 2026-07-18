import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SecondLoginTests {
    /*Тесты в Chrome проходят успешно "из коробки", Firefox и Edge потребовали отдельной установки драйверов в репозиторий*/

    //01. Корректные логин и пароль - успешный вход в систему по нажатию кнопки "Login"
    @Test
    void test01_success_login_button() {
        Configuration.browser = "chrome";
        System.out.println("Начали тест test01_success_login_button");
        open("https://slqa.ru/cases/ChatGPTLogin/");
        $("#username").sendKeys("standard_user");
        $("#password").sendKeys("secret_sauce");
        $("#loginButton").click();
        $("#message").shouldHave(text("Вход в систему выполнен успешно! Загрузка..."));
        $("#message").shouldBe(visible);
        $("#message").shouldBe(cssClass("success"));
        $("#greeting").shouldHave(text("Welcome, standard_user!"));
        $("#greeting").shouldBe(visible);
        System.out.println("Закончили тест test01_success_login_button");
        closeWindow();
    }
    //02. Корректный логин, пароль не соответствует логину - ошибка
    @Test
    void test02_error_wrong_password() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
        Configuration.browser = "firefox";
        System.out.println("Начали тест test02_error_wrong_password");
        open("https://slqa.ru/cases/ChatGPTLogin/");
        $("#username").sendKeys("standard_user");
        $("#password").sendKeys("wrong_password");
        $("#loginButton").click();
        $("#message").shouldHave(text("Invalid username or password."));
        $("#message").shouldBe(visible);
        $("#message").shouldBe(cssClass("error"));
        $("#greeting").shouldBe(empty);
        $("#greeting").shouldNotBe(visible);
        System.out.println("Закончили тест test02_error_wrong_password");
        closeWindow();
    }
    //03. Корректные логин и пароль - успешный вход в систему по нажатию клавиши Enter на клавиатуре
    @Test
    void test03_success_login_enter() {
        System.setProperty("webdriver.edge.driver", "src/test/resources/msedgedriver.exe");
        Configuration.browser = "edge";
        System.out.println("Начали тест test03_success_login_enter");
        open("https://slqa.ru/cases/ChatGPTLogin/");
        $("#username").sendKeys("standard_user");
        $("#password").sendKeys("secret_sauce");
        $("#password").pressEnter();
        $("#message").shouldHave(text("Вход в систему выполнен успешно! Загрузка..."));
        $("#message").shouldBe(visible);
        $("#message").shouldBe(cssClass("success"));
        $("#greeting").shouldHave(text("Welcome, standard_user!"));
        $("#greeting").shouldBe(visible);
        System.out.println("Закончили тест test03_success_login_enter");
        closeWindow();
    }
    //04. Выход из системы
    @Test
    void test04_logout_success() {
        Configuration.browser = "chrome";
        System.out.println("Начали тест test04_logout_success");
        open("https://slqa.ru/cases/ChatGPTLogin/");
        $("#username").sendKeys("standard_user");
        $("#password").sendKeys("secret_sauce");
        $("#loginButton").click();
        $("#logoutButton").click();
        $("#logoutButton").shouldNotBe(visible);
        $("#loginContainer").shouldBe(visible);
        $("#loginInfo").shouldBe(visible);
        $("#username").shouldBe(visible);
        $("#password").shouldBe(visible);
        $("#loginButton").shouldBe(visible);
        System.out.println("Закончили тест test04_logout_success");
        closeWindow();
    }
    //05. Некорректный логин, пароль от корректного логина - ошибка
    @Test
    void test05_wrong_login_correct_password() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
        Configuration.browser = "firefox";
        System.out.println("Начали тест test05_wrong_login_correct_password");
        open("https://slqa.ru/cases/ChatGPTLogin/");
        $("#username").sendKeys("incorrect_login");
        $("#password").sendKeys("secret_sauce");
        $("#loginButton").click();
        $("#message").shouldHave(text("Invalid username or password."));
        $("#message").shouldBe(visible);
        $("#message").shouldBe(cssClass("error"));
        $("#greeting").shouldBe(empty);
        $("#greeting").shouldNotBe(visible);
        System.out.println("Закончили тест test05_wrong_login_correct_password");
        closeWindow();
    }
    //06. Корректный логин, пароль от другого корректного логина - ошибка
    /*Эту проверку в текущей реализации невозможно реализовать,
    т.к. у всех пользователей один и тот же пароль*/
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
    //07. Проверить, что под заблокированным пользователем нельзя войти в систему
    @Test
    void test07_error_blocked_user() {
        Configuration.browser = "chrome";
        System.out.println("Начали тест test07_error_blocked_user");
        open("https://slqa.ru/cases/ChatGPTLogin/");
        $("#username").sendKeys("locked_out_user");
        $("#password").sendKeys("secret_sauce");
        $("#loginButton").click();
        $("#message").shouldHave(text("Пользователь заблокирован."));
        $("#message").shouldBe(visible);
        $("#message").shouldBe(cssClass("error"));
        $("#greeting").shouldBe(empty);
        $("#greeting").shouldNotBe(visible);
        System.out.println("Закончили тест test07_error_blocked_user");
        closeWindow();
    }
    //08. Пустой логин, пароль от корректного логина
    @Test
    void test08_empty_login_correct_password() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
        Configuration.browser = "firefox";
        System.out.println("Начали тест test08_empty_login_correct_password");
        open("https://slqa.ru/cases/ChatGPTLogin/");
        $("#password").sendKeys("secret_sauce");
        $("#loginButton").click();
        $("#message").shouldHave(text("Username is required."));
        $("#message").shouldBe(visible);
        $("#message").shouldBe(cssClass("error"));
        $("#greeting").shouldBe(empty);
        $("#greeting").shouldNotBe(visible);
        System.out.println("Закончили тест test08_empty_login_correct_password");
        closeWindow();
    }
    //09. Пустой пароль, корректный логин
    @Test
    void test09_error_empty_password() {
        System.setProperty("webdriver.edge.driver", "src/test/resources/msedgedriver.exe");
        Configuration.browser = "edge";
        System.out.println("Начали тест test09_error_empty_password");
        open("https://slqa.ru/cases/ChatGPTLogin/");
        $("#username").sendKeys("standard_user");
        $("#loginButton").click();
        $("#message").shouldHave(text("Password is required."));
        $("#message").shouldBe(visible);
        $("#message").shouldBe(cssClass("error"));
        $("#greeting").shouldBe(empty);
        $("#greeting").shouldNotBe(visible);
        System.out.println("Закончили тест test09_error_empty_password");
        closeWindow();
    }
    //10. Пустые логин и пароль
    @Test
    void test10_error_empty_login_and_password() {
        Configuration.browser = "chrome";
        System.out.println("Начали тест test10_error_empty_login_and_password");
        open("https://slqa.ru/cases/ChatGPTLogin/");
        $("#loginButton").click();
        $("#message").shouldHave(text("Username and Password are required."));
        $("#message").shouldBe(visible);
        $("#message").shouldBe(cssClass("error"));
        $("#greeting").shouldBe(empty);
        $("#greeting").shouldNotBe(visible);
        System.out.println("Закончили тест test10_error_empty_login_and_password");
        closeWindow();
    }
    //11. Проверить, что при вводе пароль скрыт за звёздочками
    //Нет технической возможности автоматизировать эту проверку
    //Требуется выполнять эту проверку ручным способом.
//    @Test
//    void test11_password_fied_displays_asterisks() {
//    Configuration.browser = "edge";
//    }
    //12. Проверить вход в систему под несколькими разными логинами
    @Test
    void test12_success_logins_different_users() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
        Configuration.browser = "firefox";
        System.out.println("Начали тест test12_success_logins_different_users");
        open("https://slqa.ru/cases/ChatGPTLogin/");
        $("#username").setValue("standard_user");
        $("#password").setValue("secret_sauce");
        $("#loginButton").click();
        $("#message").shouldHave(text("Вход в систему выполнен успешно! Загрузка..."));
        $("#message").shouldBe(visible);
        $("#message").shouldBe(cssClass("success"));
        $("#greeting").shouldHave(text("Welcome, standard_user!"));
        $("#greeting").shouldBe(visible);
        $("#logoutButton").click();

        $("#username").setValue("problem_user");
        $("#password").setValue("secret_sauce");
        $("#loginButton").click();
        $("#message").shouldHave(text("Вход в систему выполнен успешно! Загрузка..."));
        $("#message").shouldBe(visible);
        $("#message").shouldBe(cssClass("success"));
        $("#greeting").shouldHave(text("Welcome, problem_user!"));
        $("#greeting").shouldBe(visible);
        $("#logoutButton").click();

        $("#username").setValue("performance_glitch_user");
        $("#password").setValue("secret_sauce");
        $("#loginButton").click();
        $("#message").shouldHave(text("Вход в систему выполнен успешно! Загрузка..."));
        $("#message").shouldBe(visible);
        $("#message").shouldBe(cssClass("success"));
        $("#greeting").shouldHave(text("Welcome, performance_glitch_user!"));
        $("#greeting").shouldBe(visible);
        $("#logoutButton").click();

        $("#username").setValue("error_user");
        $("#password").setValue("secret_sauce");
        $("#loginButton").click();
        $("#message").shouldHave(text("Вход в систему выполнен успешно! Загрузка..."));
        $("#message").shouldBe(visible);
        $("#message").shouldBe(cssClass("success"));
        $("#greeting").shouldHave(text("Welcome, error_user!"));
        $("#greeting").shouldBe(visible);
        $("#logoutButton").click();

        $("#username").setValue("visual_user");
        $("#password").setValue("secret_sauce");
        $("#loginButton").click();
        $("#message").shouldHave(text("Вход в систему выполнен успешно! Загрузка..."));
        $("#message").shouldBe(visible);
        $("#message").shouldBe(cssClass("success"));
        $("#greeting").shouldHave(text("Welcome, visual_user!"));
        $("#greeting").shouldBe(visible);
        $("#logoutButton").click();
        System.out.println("Закончили тест test12_success_logins_different_users");
        closeWindow();
    }
}
