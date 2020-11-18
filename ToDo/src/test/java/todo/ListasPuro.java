// 1- Pacote = Conjunto de Classes
package todo;

// 2- Bibliotecas = Métodos e Funções Prontos

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.security.Key;
import java.util.concurrent.TimeUnit;


// 3- Classe
public class ListasPuro {

    //3.1- Atributos = Características
    String url; //guardará o endereço do site alvo
    WebDriver driver; //objeto do selenium webdriver
    String email = "EMAIL";
    String senha = "SENHA";


    //3.2- Métodos ou Funcionalidades = O que ele sabe fazer
    @Before
    public void inicializar(){
        // Declarando  o endereço do site alvo
        url = "https://to-do.live.com/tasks/";

        // Informando o local do chromedriver
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        // Instanciar o objeto Selenium WebDriver como navegador Chrome
        // Objeto do Selenium webdriver. Poderia utilizar outro nome(selenium, por exemplo), mas por convensão se utiliza 'driver'
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS); // a cada milissegundo é verificado
        driver.manage().window().maximize();

    }

    @After
    public void finalizar(){
        //driver.quit(); //Destrói o objeto Selenium WebDriver

    }

    @Test
    public void criarListaComTresItens() throws InterruptedException {

        driver.get(url);

        // Página de tasks
        driver.findElement(By.id("mectrl_headerPicture")).click(); // clica no ícone de sign-In / login
        Thread.sleep(3000);
        driver.findElement(By.id("i0116")).sendKeys(email); // preenche/cola o e-mail
        driver.findElement(By.id("idSIButton9")).click(); // clicar no botão próximo
        Thread.sleep(3000);
        //driver.findElement(By.id("i0118")).sendKeys(Keys.chord(senha)); // Digita a senha
        driver.findElement(By.id("i0118")).sendKeys(senha); // Digita a senha
        driver.findElement(By.id("idSIButton9")).click(); // clica no sign In
        Thread.sleep(3000);
        driver.findElement(By.id("idSIButton9")).click(); // clica em "Sim" para confirmar pergunta de se manter logado
        Thread.sleep(10000);

        // Página de tasks
        driver.findElement(By.id("baseAddInput-addList")).click(); // Clica no elemento "Nova Lista"
        driver.findElement(By.id("baseAddInput-addList")).clear(); // Apaga o texto no elemento
        //driver.findElement(By.id("baseAddInput-addList")).sendKeys("Musicas"); // Cola a palavra "Música"
        driver.findElement(By.id("baseAddInput-addList")).sendKeys(Keys.chord("Musicas")); // Soletra a palavra "Música"
        // Todo: implementar o print da tela
        driver.findElement(By.id("baseAddInput-addList")).sendKeys(Keys.ENTER); // pressiona a tecla ENTER

        // Adiciona 3 músicas a lista
        driver.findElement(By.id("baseAddInput-addTask")).sendKeys("Feel" + Keys.ENTER);
        driver.findElement(By.id("baseAddInput-addTask")).sendKeys("Stay (Faraway, so close)" + Keys.ENTER);
        driver.findElement(By.id("baseAddInput-addTask")).sendKeys("Sultans of Swing" + Keys.ENTER);
    }

    @Test
    public void alterarNomeLista() throws InterruptedException {

        driver.get(url);

        //Login
        driver.findElement(By.id("mectrl_headerPicture")).click(); // clica no ícone de sign-In / login
        Thread.sleep(3000);
        driver.findElement(By.id("i0116")).sendKeys(email); // preenche/cola o e-mail
        driver.findElement(By.id("idSIButton9")).click(); // clicar no botão próximo
        Thread.sleep(3000);
        driver.findElement(By.id("i0118")).sendKeys(senha); // Digita a senha
        driver.findElement(By.id("idSIButton9")).click(); // clica no sign In
        Thread.sleep(2000);
        driver.findElement(By.id("idSIButton9")).click(); // clica em "Sim" para confirmar pergunta de se manter logado
        Thread.sleep(8000);

        //Clica na lista Musicas do lado esquerdo
        //driver.findElement(By.id("AQMkADAwATNiZmYAZC1iMzlkLTdjOTQtMDACLTAwCgAuAAADeFtgENXbxUeiBn84bZhH6AEAEPDXIW9PukGsvzLQ-1PfugAEplwrjQAAAA==")).click();
        driver.findElement(By.xpath("//div[contains(@aria-label,\"Musicas\")]")).click();
        Thread.sleep(2000);


        // Clica no nome da lista na página central. O nome se torna 'editável'
        driver.findElement(By.cssSelector(".listTitle")).click();
        Thread.sleep(2000);
        //Insere o novo nome da lista e tecla ENTER
        driver.findElement(By.cssSelector(".tasksToolbar-input")).sendKeys("Minhas Musicas" + Keys.ENTER);
    }

    @Test
    public void excluiLista() throws InterruptedException {

        driver.get(url);

        //Login
        driver.findElement(By.id("mectrl_headerPicture")).click(); // clica no ícone de sign-In / login
        Thread.sleep(3000);
        driver.findElement(By.id("i0116")).sendKeys(email); // preenche/cola o e-mail
        driver.findElement(By.id("idSIButton9")).click(); // clicar no botão próximo
        Thread.sleep(3000);
        //driver.findElement(By.id("i0118")).sendKeys(senha)); // Digita a senha
        driver.findElement(By.id("i0118")).sendKeys(senha); // Digita a senha
        driver.findElement(By.id("idSIButton9")).click(); // clica no sign In
        Thread.sleep(2000);
        driver.findElement(By.id("idSIButton9")).click(); // clica em "Sim" para confirmar pergunta de se manter logado
        Thread.sleep(8000);


        //Me adiantei um pouco e pesquisei a respeito de manipular recursos do mouse
        //Actions é uma classe pré definida no selenium, utilizada para utilizar recursos do teclado e mouse
        Actions action = new Actions(driver);

        WebElement listaMusica = driver.findElement(By.xpath("//div[contains(@aria-label,\"Minhas Musicas\")]"));
        Thread.sleep(2000);
        //Clica com o botão direito do mouse
        action.contextClick(listaMusica).build().perform();
        Thread.sleep(2000);
        // Tecla SETA PARA CIMA
        action.sendKeys(Keys.ARROW_UP).build().perform();
        Thread.sleep(2000);
        //Tecla ENTER
        action.sendKeys(Keys.ENTER).build().perform();
        Thread.sleep(2000);
        //Clica no botão DELETE
        driver.findElement(By.cssSelector("button.red>span")).click();

    }

}















