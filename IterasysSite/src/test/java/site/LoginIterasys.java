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
    String email = "EMAIL";  // Substituir por um email v�lido
    String senha = "SENHA";  // Substituir por uma senha v�lida
    String pastaPrint = "evidencias/" + new SimpleDateFormat("yyyy-MM-dd HH-mm").format(Calendar.getInstance().getTime()) + "/";

    public void tirarPrint(String nomePrint) throws IOException {

        // Selenium(driver) > Camera (TakesScreenshot) > Botao da m�quina (getScreenshotAs) > Tira a foto >
        // Grava na mem�ria RAM o print de uma tela
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
        //driver.navigate(url); //controla fun��es do navegador

        driver.findElement(By.id("email")).sendKeys(email); // Insere as informa��es do login
        driver.findElement(By.id("senha")).sendKeys(senha); // Insere as informa��es da senha
        tirarPrint("Passo 1 - Acessou a tela de login"); // Tira print da tela

        driver.findElement(By.id("btn_login")).click(); // Clica no bot�o Entrar
        Thread.sleep(3000);

        //Verfica se o usu�rio possui o Curso FTS 128
        Assert.assertTrue(driver.findElement(By.cssSelector("a[title=\"FTS 128\"]")).getText().contains("FTS 128"));
        tirarPrint("Passo 2 - Acessou a Home do usu�rio"); // Tira print da tela
    }



}
