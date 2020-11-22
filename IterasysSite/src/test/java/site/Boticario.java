package site;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Boticario {
    String url = "https://www.boticario.com.br";
    WebDriver driver;

    @Before
    public void iniciar(){

        ChromeOptions chOptions = new ChromeOptions(); // instanciar o objeto de configuração do Chromedriver
        chOptions.addArguments("--disable-notifications"); //desabilita pop-up do Chrome

        System.setProperty("webdriver.chrome.driver","drivers/chrome/87/chromedriver.exe");
        driver = new ChromeDriver(chOptions); // <-- Instanciar o Selenium como um controlador do Chrome
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
    }

    @After
    public void finalizar(){
        //driver.quit();
    }

    @Test
    public void consultarTestLink() throws InterruptedException, IOException {
        driver.get(url);

        // Clica no botão de Políticas de privacidade
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        // todo: fazer o 2º pop-up (do brownser)

        // Clica no pop-up(Eu Quero!) de receber novidades
        //driver.findElement(By.id("onesignal-slidedown-allow-button")).click();


    }

}

