package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class CadastroLoginUsuarios {
    WebDriver driver;
    String url;

    String pastaPrint = "evidencias/petz/" + new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(Calendar.getInstance().getTime()) + "/";

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
    public void iniciar(){
        url = "https://www.petz.com.br";

        ChromeOptions chOptions = new ChromeOptions(); // instanciar o objeto de configuração do Chromedriver
        chOptions.addArguments("--disable-notifications"); //desabilita pop-up do Chrome
        chOptions.addArguments("--suppress-message-center-popups");

        System.setProperty("webdriver.chrome.driver","drivers/chrome/87/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20000, TimeUnit.MILLISECONDS);
        System.out.println("Passo 1 - Preparou o setup");
    }

    @After
    public void finalizar(){
        driver.quit();
        System.out.println("Passo 6 - Fechou o navegador");
    }

    @Dado("^que acesso o site da Petz.com.br$")
    public void que_acesso_o_site_da_Petz() throws Throwable {
        driver.get(url);
        System.out.println("Passo 2 - Acessou o site da Petz");
        tirarPrint("Passo 2 - Acessou o site da Petz");
    }

    @Quando("^clico em ENTRAR$")
    public void clico_em_ENTRAR() throws IOException, InterruptedException {
        driver.findElement(By.cssSelector("button.greetings")).click(); // clica no botao de login p/ aparecer o botão  ENTRAR
        tirarPrint("Passo 3 - Clicou em ENTRAR");
        driver.findElement(By.cssSelector("a.button-login")).click(); //clica no botão ENTRAR
        System.out.println("Passo 3 - Clicou em ENTRAR");
    }

    @Entao("^clico em Criar Conta e preencho os campos de cadastro \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" e  clico no botao Criar Conta$")
    public void clico_em_Criar_Conta_e_preencho_os_campos_de_cadastro_e_clico_no_botao_Criar_Conta(String nome, String email, String ddd, String celular, String sexo, String dataNascimento, String cpf, String senha) throws IOException {
        driver.findElement(By.cssSelector("a.btn.modal-petz-btn.btn-redondo-transparente")).click(); // clica no botão Criar Minha Conta
        driver.findElement(By.id("Nome")).sendKeys(nome); // preenche o campo Nome
        driver.findElement(By.id("Email")).sendKeys(email); // preenche o campo email
        driver.findElement(By.name("cliente.dddCelular")).sendKeys(ddd); // preenche o campo DDD Celular
        driver.findElement(By.name("cliente.celular")).sendKeys(celular); // preenche o campo Celular
        WebElement clicaSexo = driver.findElement(By.id("Sexo")); // Armazena o elemento referente ao campo Sexo
        clicaSexo.click(); // Clica no campo Sexo
        clicaSexo.sendKeys(sexo + Keys.ENTER); // Pressiona a tecla 'M' ou 'F'(originados na massa de dados) e tecla ENTER
        driver.findElement(By.id("dataNascimento")).sendKeys(dataNascimento); // preenche a data de nascimento
        driver.findElement(By.id("CPF-CNPJ")).sendKeys(cpf); // preenche o CPF
        driver.findElement(By.id("Senha")).sendKeys(senha); // preenche a senha
        driver.findElement(By.id("confirmasenha")).sendKeys(senha); // preenche a confirmação da senha
        tirarPrint("Passo 4 - Clicou em Criar Conta, preencheu os daos e clicou em Criar Conta");
        driver.findElement(By.id("criarContaButton")).click(); // clica no botão Criar Conta
        System.out.println("Passo 4 - Clicou em Criar Conta, preencheu os daos e clicou em Criar Conta");
    }

    @Entao("^verifico se a conta foi criada$")
    public void verifico_se_a_conta_foi_criada() throws IOException {
        assertEquals("Dados salvos com sucesso",driver.findElement(By.xpath("//p[contains(text(),'Dados salvos com sucesso')]")).getText()); //verifica se o texto de dados gravados com sucesso é exibido
        tirarPrint("Passo 5 - Verifica se a conta foi criada");
        driver.findElement(By.xpath("//a[contains(text(),'Entendi')]")).click(); // clica no botao Entendi
        System.out.println("Passo 5 - Verifica se a conta foi criada");
    }

    @Quando("^preencho os campos de \"([^\"]*)\" \"([^\"]*)\" e clico no botao Entrar$")
    public void preencho_os_campos_de_e_clico_no_botao_Entrar(String email, String senha) throws IOException {
        driver.findElement(By.name("login")).sendKeys(email);
        driver.findElement(By.name("senha")).sendKeys(senha + Keys.ENTER);
        tirarPrint("Passo 1 - Preenche os dados de login e senha");
        //driver.findElement(By.cssSelector("btn.modal-petz-btn.btn-redondo-verde")).click();
        System.out.println("Passo 1 - Preenche os dados de login e senha");
    }

    @Entao("^verifico se o primeiro nome \"([^\"]*)\" do usuario logado eh exibido$")
    public void verifico_se_o_primeiro_nome_do_usuario_logado_eh_exibido(String primeiroNome) throws IOException {
        assertEquals(primeiroNome,driver.findElement(By.cssSelector("span.username")).getText()); // verifica se o primeiro nome do usuario é exibido no canto superior direito da tela
        //assertEquals(primeiroNome,driver.findElement(By.xpath("//span[contains(text(),\""+ primeiroNome + "\")]")).getText());
        tirarPrint("Passo 2 - Verifica se o primeiro nome do usuaro é exibido na tela");
        System.out.println("Passo 2 - Verifica se o primeiro nome do usuaro é exibido na tela");
    }

    @Quando("^preencho os campos de \"([^\"]*)\" \"([^\"]*)\" com algum valor incorreto e clico no botao Entrar$")
    public void preencho_os_campos_de_com_algum_valor_incorreto_e_clico_no_botao_Entrar(String email, String senha) throws IOException {
        driver.findElement(By.name("login")).sendKeys(email);
        driver.findElement(By.name("senha")).sendKeys(senha + Keys.ENTER);
        tirarPrint("Passo 1 - Preenche os dados incorretos de login e senha");
        System.out.println("Passo 1 - Preenche os dados incorretos de login e senha");
    }

    @Entao("^verifico se o texto Dados Incorretos eh exibido$")
    public void verifico_se_o_texto_Dados_Incorretos_eh_exibido() throws IOException {
        assertEquals("Dados incorretos!",driver.findElement(By.xpath("//div[contains(text(),'Dados incorretos!')]")).getText());
        tirarPrint("Passo 3 - Verifica se a msg Dados Incorretos eh exibida");
        System.out.println("Passo 3 - Verifica se a msg Dados Incorretos eh exibida");
    }

}
