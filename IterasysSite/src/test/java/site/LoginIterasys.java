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

import static org.junit.Assert.assertEquals;

public class LoginIterasys {
    WebDriver driver;
    String url;
    String email = "EMAIL";  // Substituir por um email válido
    String senha = "SENHA";  // Substituir por uma senha válida
    String pastaPrint = "evidencias/" + new SimpleDateFormat("yyyy-MM-dd HH-mm").format(Calendar.getInstance().getTime()) + "/";

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
        url = "https://iterasys.com.br/login";
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
    public void loginIterasys() throws IOException, InterruptedException {
        driver.get(url);
        //driver.navigate(url); //controla funções do navegador

        driver.findElement(By.id("email")).sendKeys(email); // Insere as informações do login
        driver.findElement(By.id("senha")).sendKeys(senha); // Insere as informações da senha
        tirarPrint("Passo 1 - Acessou a tela de login"); // Tira print da tela

        driver.findElement(By.id("btn_login")).click(); // Clica no botão Entrar
        Thread.sleep(3000);

        //Verfica se o usuário possui o Curso FTS 128
        Assert.assertTrue(driver.findElement(By.cssSelector("a[title=\"FTS 128\"]")).getText().contains("FTS 128"));
        tirarPrint("Passo 2 - Acessou a Home do usuário"); // Tira print da tela
    }



}
