import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    public WebDriver getDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        WebDriver driver = new ChromeDriver(options);
        driver.get("http://localhost:8016/tasks/");
        return driver;
    }


    @RepeatedTest(100)
    public void deveSalvarTarefaComSucesso() {

        WebDriver driver = getDriver();
        LocalDate dataFutura = LocalDate.now().plusDays(1);


        WebElement botao = driver.findElement(By.id("addTodo"));
        botao.click();

        WebElement inputTask = driver.findElement(By.xpath("//input[@id=\"task\"]"));
        inputTask.sendKeys("Tarefa 1");

        WebElement inputDueDate = driver.findElement(By.xpath("//input[@name=\"dueDate\"]"));
        inputDueDate.sendKeys(dataFutura.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        WebElement btnSave = driver.findElement(By.xpath("//input[@value=\"Save\"]"));
        btnSave.click();

        String messagem = driver.findElement(By.id("message")).getText();
//        assertEquals("Sucess!", messagem);

        driver.quit();

    }

    @RepeatedTest(100)
    public void NaoDeveSalvarTarefaSemDescricao() {

        WebDriver driver = getDriver();
        LocalDate dataFutura = LocalDate.now().plusDays(1);


        WebElement botao = driver.findElement(By.id("addTodo"));
        botao.click();

        WebElement inputDueDate = driver.findElement(By.xpath("//input[@name=\"dueDate\"]"));
        inputDueDate.sendKeys(dataFutura.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        WebElement btnSave = driver.findElement(By.xpath("//input[@value=\"Save\"]"));
        btnSave.click();

        String messagem = driver.findElement(By.id("message")).getText();
        assertEquals("Fill the task description", messagem);

        driver.quit();

    }

    @RepeatedTest(100)
    public void NaoDeveSalvarTarefaSemData() {

        WebDriver driver = getDriver();


        WebElement botao = driver.findElement(By.id("addTodo"));
        botao.click();

        WebElement inputTask = driver.findElement(By.xpath("//input[@id=\"task\"]"));
        inputTask.sendKeys("Tarefa 1");


        WebElement btnSave = driver.findElement(By.xpath("//input[@value=\"Save\"]"));
        btnSave.click();

        String messagem = driver.findElement(By.id("message")).getText();
        assertEquals("Fill the due date", messagem);

        driver.quit();

    }

    @RepeatedTest(100)
    public void NaoDeveSalvarTarefaComDataPassada() {

        WebDriver driver = getDriver();
        LocalDate dataFutura = LocalDate.now().plusDays(-1);


        WebElement botao = driver.findElement(By.id("addTodo"));
        botao.click();

        WebElement inputTask = driver.findElement(By.xpath("//input[@id=\"task\"]"));
        inputTask.sendKeys("Tarefa 1");

        WebElement inputDueDate = driver.findElement(By.xpath("//input[@name=\"dueDate\"]"));
        inputDueDate.sendKeys(dataFutura.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        WebElement btnSave = driver.findElement(By.xpath("//input[@value=\"Save\"]"));
        btnSave.click();

        String messagem = driver.findElement(By.id("message")).getText();
        assertEquals("Due date must not be in past", messagem);

        driver.quit();

    }
}
