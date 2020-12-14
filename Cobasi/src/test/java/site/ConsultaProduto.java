package site;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class ConsultaProduto {
    WebDriver driver;
    String url;
    String id; // id compartilhado com todos os blocos de steps

    static String pastaPrint =  "evidencias/cobasi/" + new SimpleDateFormat("yyyy-MM-dd HH-mm").format(Calendar.getInstance().getTime()) + "/";

    public void tirarPrint(String id, String pastaPrint, String nomePrint) throws IOException {

        // Selenium(driver) > Camera (TakesScreenshot) > Botao da máquina (getScreenshotAs) > Tira a foto >
        // Grava na memória RAM o print de uma tela
        File foto = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); // selenium com classe de tirar foto

        // Utilizar Biblioteca commons IO para manipular o arquivo do print da tela
        // baixar no mvnrepository
        // colocar no arquivo build.gradle
        FileUtils.copyFile(foto,new File(pastaPrint + "Cenario " + id + "/" + nomePrint + ".png"));
    }

    @Before
    public void inicializar(){
        url = "https://www.cobasi.com.br/";

        System.setProperty("webdriver.chrome.driver","drivers/chrome/87/chromedriver.exe");
        driver = new ChromeDriver();
        System.out.println("Passo 0 - Preparou o setup");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20000, TimeUnit.MILLISECONDS);
    }

    @After
    public void finalizar(){
        driver.quit();
        System.out.println("Passo 6 - Fechou o navegador");
    }

    @Dado("^que acesso o site da Cobasi \"([^\"]*)\"$")
    public void que_acesso_o_site_da_Cobasi(String id) throws IOException {
        driver.get(url);
        this.id = id;
        tirarPrint(id, pastaPrint,"consultarProduto Passo 1 - Acessou o site da Cobasi");
        System.out.println(" 1 - Acessou o site da Cobasi");
    }

    @Quando("^procuro por \"([^\"]*)\" e pressiono ENTER$")
    public void procuro_por_e_pressiono_ENTER(String produto) throws IOException {
        driver.findElement(By.name("q")).sendKeys(produto + Keys.ENTER);
        tirarPrint(id, pastaPrint, "consultarProduto Passo 2 - Procura por " + produto + " e Pressiona ENTER");
        System.out.println("Passo 2 - Procura por " + produto + " e Pressiona ENTER");
    }

    @Entao("^exibe a lista de produtos relacionados a \"([^\"]*)\"$")
    public void exibe_a_lista_de_produtos_relacionados_a(String produto) throws IOException {
        //assertEquals(produto.toLowerCase(),driver.findElement(By.xpath("//strong[contains(text(),\""+ produto +"\")]")).getText());
        assertEquals(produto.toUpperCase(), driver.findElement(By.cssSelector("ul.neemu-breadcrumb-container>li:nth-child(3) > strong:nth-child(1)")).getText());
        tirarPrint(id, pastaPrint, "consultarProduto Passo 3 - Exibe a lista de produtos relacionados a " + produto);
        System.out.println("Passo 3 - Exibe a lista de produtos relacionados a " + produto);
    }

    @Quando("^seleciono o \"([^\"]*)\" da lista$")
    public void seleciono_o_da_lista(String produtoDescricao) throws IOException {
        driver.findElement(By.xpath("//h2/a[contains(text(),\""+ produtoDescricao+"\")]")).click();
        tirarPrint(id, pastaPrint, "consultarProduto Passo 4 - Seleciona o produto");
        System.out.println("Passo 4 - Seleciona o produto " + produtoDescricao + " da lista");
    }

    @Entao("^verifico a marca \"([^\"]*)\" com preco normal de \"([^\"]*)\" e \"([^\"]*)\" para assinantes$")
    public void verifico_a_marca_com_preco_normal_de_e_para_assinantes(String marca, String precoNormal, String precoAssinante) throws IOException {
        assertEquals(marca,driver.findElement(By.className("product__brand")).getText()); //verifica nome marca
        assertEquals("Por: "+ precoNormal +"à vista", driver.findElement(By.cssSelector("span.d-block.price__por")).getText()); //verifica preço normal
        //String textoPrecoAssinante[] = driver.findElement(By.cssSelector("span.price__assinatura-price")).getText().split(" ", 2);
        //assertEquals(precoAssinante, driver.findElement(By.cssSelector("span.price__assinatura-price")).getText()); // verifica preço assinante
        assertEquals(precoAssinante + " (10% OFF)", driver.findElement(By.cssSelector("span.price__assinatura-price")).getText()); // verifica preço assinante
        tirarPrint(id, pastaPrint, "consultarProduto Passo 5 - Verificou a marca, preço normal e preço assinante");
        System.out.println("Passo 5 - Verificou a marca como "+marca+", preço normal como "+ precoNormal+" e preço assinante como "+ precoAssinante);
    }

}
