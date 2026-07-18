import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class AlertsTests {
    @Test
    void test01AlertButton() {
        open("https://demoqa.com/alerts");
        $("#alertButton").click();
        switchTo().alert().accept();
        sleep(2_000);
    }
    @Test
    void test02TimerAlertButton() {
        open("https://demoqa.com/alerts");
        $("#timerAlertButton").click();
        //"нормальный" вариант
        WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(6));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        //"самый простой" вариант
        //Configuration.timeout = 5000;
        //switchTo().alert().accept();
        sleep(2_000);
    }
    @Test
    void test03ConfirmButton() {
        open("https://demoqa.com/alerts");
        $("#confirmButton").click();
        Alert alert = switchTo().alert();
        Configuration.timeout = 2000;
        alert.accept();
        sleep(2_000);
    }
    @Test
    void test04ConfirmButton() {
        open("https://demoqa.com/alerts");
        $("#confirmButton").click();
        Alert alert = switchTo().alert();
        Configuration.timeout = 2000;
        alert.dismiss();
        sleep(2_000);
    }
    @Test
    void test05PromtButton() {
        open("https://demoqa.com/alerts");
        $("#promtButton").click();
        Alert alert = switchTo().alert();
        //System.out.println(alert.getText());
        alert.sendKeys("Мария");
        Configuration.timeout = 2000;
        alert.accept();
        sleep(2_000);
    }
    @Test
    void test06PromtButton() {
        open("https://demoqa.com/alerts");
        $("#promtButton").click();
        Alert alert = switchTo().alert();
        //System.out.println(alert.getText());
        alert.sendKeys("Мария");
        Configuration.timeout = 2000;
        alert.dismiss();
        sleep(2_000);
    }
}
