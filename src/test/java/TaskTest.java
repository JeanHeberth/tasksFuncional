import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskTest {

   @Test
    public void testAmbiente() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        WebDriver driver = new ChromeDriver(options);
        driver.get("http://localhost:8015/tasks/");
        LocalDate dataFutura = LocalDate.now().plusDays(1);

        WebElement botao = driver.findElement(By.id("addTodo"));
        botao.click();

        WebElement inputTask = driver.findElement(By.xpath("//input[@id=\"task\"]"));
        inputTask.sendKeys("Tarefa 1");

        WebElement inputDueDate = driver.findElement(By.xpath("//input[@name=\"dueDate\"]"));
        inputDueDate.sendKeys(dataFutura.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        WebElement btnSave = driver.findElement(By.xpath("//input[@value=\"Save\"]"));
        btnSave.click();

        // processo de inserção de uma data Atual
//        LocalDate dataAtual = LocalDate.now().plusDays(1);
//        botao.click();
//        inputTask.sendKeys("Tarefa 2");
//        inputDueDate.sendKeys(dataAtual.toString());
//        btnSave.click();


        driver.quit();

    }
}
