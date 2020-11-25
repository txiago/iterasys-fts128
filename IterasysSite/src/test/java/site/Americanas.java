package site;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Americanas {
    WebDriver driver;
    String url;
    String pastaPrint = "evidencias/americanas/" + new SimpleDateFormat("yyyy-MM-dd HH-mm").format(Calendar.getInstance().getTime()) + "/";

    public void tirarPrint(String nomePrint) throws IOException {

        // Selenium(driver) > Camera (TakesScreenshot) > Botao da máquina (getScreenshotAs) > Tira a foto >
        // Grava na memória RAM o print de uma tela
        File foto = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); // selenium com classe de tirar foto

        // Utilizar Biblioteca commons IO para manipular o arquivo do print da tela
        // baixar no mvnrepository
        // colocar nos arquivo build.gradle
        FileUtils.copyFile(foto,new File(pastaPrint + nomePrint + ".png"));
    }

    @Before
    public void iniciar() {
        url = "https://www.americanas.com.br/";
        System.setProperty("webdriver.chrome.driver","drivers/chrome/87/chromedriver.exe");
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
    public void buscaAmericanas() throws IOException, InterruptedException {
        driver.get(url);
        //driver.navigate(url); //controla funções do navegador

        Thread.sleep(3000);
        driver.findElement(By.id("lgpd-accept")).click();

        driver.findElement(By.id("h_search-input")).sendKeys("galaxy s20" + Keys.ENTER); // Clica no botão Entrar
        Thread.sleep(3000);
        tirarPrint("Passo 1 - Acessou a home da Americanas e fez uma busca"); // Tira print da tela

        Thread.sleep(3000);
        tirarPrint("Passo 2 - Resultado da busca pelo produto");
        //driver.findElement(By.cssSelector("span.src__Text-sc-154pg0p-0.src__Name-sc-1di8q3f-3.fLqTUE")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("span.fLqTUE")).getText().contains("Galaxy S20"));

    }


}
