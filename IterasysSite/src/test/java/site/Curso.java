// 1- pacotes
package site;

// 2- bibliotecas



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// 3- classe
public class Curso {

    //3.1 - Atributos
    WebDriver driver;
    String url;

    //3.2 - Métodos ou Funções
    @Before
    public void iniciar() {
        url = "https://iterasys.com.br/";
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        driver = new ChromeDriver();
        //Maximiniza a janela
        driver.manage().window().maximize();
        // Espera um elemento por um dado tempo
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
    }

    @After
    public void finalizar(){
        //driver.quit();
    }

    @Test
    public void consultarTestLink(){
        driver.get(url);
        //driver.navigate(url); //controla funções do navegador

        driver.findElement(By.cssSelector("a.cc-btn.cc-dismiss")).click();
        //Escreve no campo de busca e tecla ENTER
        driver.findElement(By.id("searchtext")).sendKeys("TestLink" + Keys.ENTER);
        driver.findElement(By.cssSelector("span.comprar")).click();

        // Validar nome do curso
        String resultadoEsperado = "TestLink";
        // Pega o texto de "span.item-title" e coloca em uma variável String
        String resultadoAtual = driver.findElement(By.cssSelector("span.item-title")).getText();
        assertEquals(resultadoEsperado,resultadoAtual);

        // Validar preço
        assertEquals("R$ 79,99",driver.findElement(By.cssSelector("span.new-price")).getText());

        // Validar o SUBTOTAL
        assertEquals("SUBTOTAL R$ 79,99",driver.findElement(By.cssSelector("div.subtotal")).getText());


        // Validar Valor da Parcela
        //assertEquals("ou em 12 x de R$ 8,03 * no cart[ã]o",driver.findElement(By.cssSelector("div.ou-parcele")).getText());
        assertTrue(driver.findElement(By.cssSelector("div.ou-parcele")).getText().contains("ou em 12 x de R$ 8,03"));

    }

}
