package site;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


@RunWith(Parameterized.class) // Esta classe � parametrizada = l� uma massa de testes
public class GoogleSite {
    WebDriver driver;
    static String url;
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

    // Fun��o para ler um arquivo com massa de testes

    // 1 - Atributos / Massa de Teste
    private String id;
    private String pesquisa;
    private String link;
    private String tituloPagina;
    private String browser;

    // 2 - Construtor: Faz o De-Para entre os campos na massa e os atributos
    public GoogleSite(String id, String pesquisa, String link, String tituloPagina, String browser) {
        this.id = id;
        this.pesquisa = pesquisa;
        this.link = link;
        this.tituloPagina = tituloPagina;
        this.browser = browser;
    }

    // 3 - Collection Intermedi�ria entre construtor e a Collection que vai fazer  a leitura de verdade
    // Ela serve para apontar a pasta e o nome do arquivo a ser lido
    // Collection neste caso � uma cole��o de texto, uma tabela. Um vetor bidimensional
    @Parameterized.Parameters
    public static Collection<String[]> LerArquivo() throws IOException {
        return LerCSV("db/FTS128 Massa Pesquisa Google.csv");
    }

    // 4 - Collection que l� uma arquivo no formato CSV
    // Vai pegar o arquivo de dados inteiro e quebrar em peda�os para a leitura
    public static Collection<String[]> LerCSV(String nomeCSV) throws IOException {
        // L� o arquivo do disco e disponibiliza para a leitura do arquivo pela mem�ria RAM
        BufferedReader arquivo = new BufferedReader(new FileReader(nomeCSV));
        String linha; // Fazer o 'corte' em fileiras da barra de chocolate
        List<String[]> dados = new ArrayList<>();

        // O readline vai lendo linha por linha do arquivo, at� que n�o tenha mais linhas
        // Executa v�rias vezes at� que encontre uma linha nula
        while ((linha = arquivo.readLine()) != null){
            // separa os dados da linha separados por ponto-e-v�rgula
            String[] campos = linha.split(";");
            dados.add(campos);
        }
        arquivo.close();
        return dados;
    }

    @BeforeClass
    public static void antesDeTudo(){
        url = "https://www.google.com/";
        System.setProperty("webdriver.chrome.driver","drivers/chrome/87/chromedriver.exe");
        System.setProperty("webdriver.edge.driver","drivers/edge/msedgedriver.exe");
        System.setProperty("webdriver.gecko.driver","drivers/firefox/geckodriver.exe");
    }

    @Before
    public void iniciar() {

        switch (browser){
            case "Chrome":
                driver = new ChromeDriver(); // <-- Instanciar o Selenium como um controlador do Chrome
                break;
            case "Edge":
                driver = new EdgeDriver();
                break;
            case "Firefox":
                driver = new FirefoxDriver();
                break;
        }

        //driver = new ChromeDriver(); // <-- Instanciar o Selenium como um controlador do Chrome
        //Maximiniza a janela
        driver.manage().window().maximize();
        // Espera um elemento por um dado tempo
        driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
    }

    @After
    public void finalizar(){
        driver.quit();
    }

    @Test
    public void consultaGoogle() throws IOException, InterruptedException {
        driver.get(url);

        driver.findElement(By.name("q")).sendKeys(pesquisa + Keys.ENTER);
        tirarPrint("1 - Pesquisa por Iterasys no Google");
        Thread.sleep(2000);

        // No resultado da busca por 'Iterasys' procura por um link espec�fico(link listado na massa de dados)
        driver.findElement(By.xpath("//a[contains(@href,\'"+link+"\')]")).click();
        Thread.sleep(4000);

        String titulo = driver.getTitle(); //Armazena o t�tulo da p�gina
        //System.out.println(titulo); // Testa no console a sa�da do campo titulo
        tirarPrint("Acesso na p�gina");
        assertTrue(titulo.contains(tituloPagina)); //Verifica se a p�gina acessada corresponde a indicada na massa de testes
        Thread.sleep(1000);
    }


}
