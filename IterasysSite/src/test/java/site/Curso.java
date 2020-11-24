// 1- pacotes
package site;

// 2- bibliotecas



import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// 3- classe
@RunWith(Parameterized.class) // Esta classe � parametrizada = l� uma massa de testes
public class Curso {

    //3.1 - Atributos
    WebDriver driver;
    String url;
    String pastaPrint = "evidencias/" + new SimpleDateFormat("yyyy-MM-dd HH-mm").format(Calendar.getInstance().getTime()) + "/";

    //3.2 - M�todos ou Fun��es

    // M�todos ou fun��es de apoio (util / commons)
    // M�todo: n�o possui retorno
    // Fun��o: possui retorno

    public void tirarPrint(String nomePrint) throws IOException {

        // Selenium(driver) > Camera (TakesScreenshot) > Botao da m�quina (getScreenshotAs) > Tira a foto >
        // Grava na mem�ria RAM o print de uma tela
        File foto = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); // selenium com classe de tirar foto

        // Utilizar Biblioteca commons IO para manipular o arquivo do print da tela
        // baixar no mvnrepository
        // colocar nos arquivo build.gradle
        //FileUtils.copyFile(foto,new File("evidencias/print1.png"));
        //FileUtils.copyFile(foto,new File("evidencias/" + nomePrint + ".png"));
        FileUtils.copyFile(foto,new File(pastaPrint + nomePrint + ".png"));
    }

    // Fun��o para ler um arquivo com massa de testes

    // 1 - Atributos / Massa de Teste
    private String id;
    private String curso;
    private String valor;
    private String subtotal;
    private String parcelamento;
    private String browser;

    // 2 - Construtor: Faz o De-Para entre os campos na massa e os atributos
    public Curso(String id, String curso, String valor, String subtotal, String parcelamento, String browser) {
        this.id = id;
        this.curso = curso;
        this.valor = valor;
        this.subtotal = subtotal;
        this.parcelamento = parcelamento;
        this.browser = browser;
    }

    // 3 - Collection Intermedi�ria entre construtor e a Collection que vai fazer  a leitura de verdade
    // Ela serve para apontar a pasta e o nome do arquivo a ser lido
    // Collection neste caso � uma cole��o de texto, uma tabela. Um vetor bidimensional
    @Parameters
    public static Collection<String[]> LerArquivo() throws IOException {
        return LerCSV("db/FTS128 Massa Iterasys.csv");
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



    @Before
    public void iniciar() {
        url = "https://iterasys.com.br/";
        System.setProperty("webdriver.chrome.driver","drivers/chrome/87/chromedriver.exe");
        driver = new ChromeDriver();
        //Maximiniza a janela
        driver.manage().window().maximize();
        // Espera um elemento por um dado tempo
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
    }

    @After
    public void finalizar(){
        driver.quit();
    }

    @Test
    public void consultarCurso() throws IOException {
        driver.get(url);
        //driver.navigate(url); //controla fun��es do navegador

        tirarPrint("Passo 1 - Acessou a Home"); // Tira print da tela

        driver.findElement(By.cssSelector("a.cc-btn.cc-dismiss")).click();
        //Escreve no campo de busca e tecla ENTER
        //driver.findElement(By.id("searchtext")).sendKeys("Forma��o em teste de software" + Keys.ENTER);
        driver.findElement(By.id("searchtext")).sendKeys(curso + Keys.ENTER);
        tirarPrint("Passo 2 - Exibe os cursos relacionados ao Forma��o em teste de software"); // Tira print da tela

        driver.findElement(By.cssSelector("span.comprar")).click();
        //tirarPrint("Passo 3 - Exibe o titulo, valor e parcelamento do curso"); // Tira print da tela

        // Validar nome do curso
        String resultadoEsperado = curso;
        // Pega o texto de "span.item-title" e coloca em uma vari�vel String
        String resultadoAtual = driver.findElement(By.cssSelector("span.item-title")).getText();
        assertEquals(resultadoEsperado,resultadoAtual);

        // Validar pre�o
        //assertEquals("R$ 1.990,00",driver.findElement(By.cssSelector("span.new-price")).getText());
        assertEquals(valor,driver.findElement(By.cssSelector("span.new-price")).getText());

        // Validar o SUBTOTAL
        //assertEquals("SUBTOTAL R$ 1.990,00",driver.findElement(By.cssSelector("div.subtotal")).getText());
        assertEquals(subtotal,driver.findElement(By.cssSelector("div.subtotal")).getText());

        // Validar Valor da Parcela
        //assertEquals("ou em 12 x de R$ 8,03 * no cart[�]o",driver.findElement(By.cssSelector("div.ou-parcele")).getText());
        assertTrue(driver.findElement(By.cssSelector("div.ou-parcele")).getText().contains(parcelamento));
        tirarPrint("Passo 3 - Exibe o titulo, valor e parcelamento do curso"); // Tira print da tela
    }

}
