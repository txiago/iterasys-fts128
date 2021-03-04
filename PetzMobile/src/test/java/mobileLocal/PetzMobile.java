package mobileLocal;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

public class PetzMobile {

    private AndroidDriver<MobileElement> driver;


    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("automationName", "UIAutomator2");
        desiredCapabilities.setCapability("appPackage", "br.com.petz");
        desiredCapabilities.setCapability("appActivity", "br.com.hanzo.petz.view.MainActivity");
        desiredCapabilities.setCapability("platformVersion", "10.0");
        desiredCapabilities.setCapability("autoGrantPermissions", "true");
        //desiredCapabilities.setCapability("unicodeKeyboard", "true");
        //desiredCapabilities.setCapability("resetKeyboard", "true");


        URL remoteUrl = new URL("http://localhost:4723/wd/hub");

        driver = new AndroidDriver<MobileElement>(remoteUrl, desiredCapabilities);
    }

    @Test
    public void realizarLogin() throws InterruptedException {

        /*login: petz_mobile@maildrop.cc
         * senha: SenhaTest@123
         * cpf: 29840751450
         * nasc.: 01/01/1980*/
        Thread.sleep(1000);
        MobileElement campoEmail = (MobileElement) driver.findElementById("br.com.petz:id/et_user_name");
        campoEmail.sendKeys("petz_mobile@maildrop.cc");
        Thread.sleep(1000);
        MobileElement campoSenha = (MobileElement) driver.findElementById("br.com.petz:id/et_password");
        campoSenha.sendKeys("SenhaTest@123");
        Thread.sleep(1000);
        MobileElement btnEntrar = (MobileElement) driver.findElementById("br.com.petz:id/rl_do_login");
        btnEntrar.click();
        Thread.sleep(10000);

        MobileElement btnMais = (MobileElement) driver.findElementById("br.com.petz:id/navigation_more");
        btnMais.click();
        Thread.sleep(1000);
        MobileElement btnMeuCadastro = (MobileElement) driver.findElementById("br.com.petz:id/cl_more_profile");
        btnMeuCadastro.click();
        Thread.sleep(1000);
        MobileElement campoEmailCadastro = (MobileElement) driver.findElementById("br.com.petz:id/et_user_email");

        // Verifica se o email da página de dados pessoais é igual ao email utilizado no acesso do app
        Assert.assertEquals("petz_mobile@maildrop.cc",campoEmailCadastro.getText());
    }

    @Test
    public void buscaProduto() throws InterruptedException {
        Thread.sleep(1000);
        MobileElement campoEmail = (MobileElement) driver.findElementById("br.com.petz:id/et_user_name");
        campoEmail.sendKeys("petz_mobile@maildrop.cc");
        Thread.sleep(1000);
        MobileElement campoSenha = (MobileElement) driver.findElementById("br.com.petz:id/et_password");
        campoSenha.sendKeys("SenhaTest@123");
        Thread.sleep(1000);
        MobileElement btnEntrar = (MobileElement) driver.findElementById("br.com.petz:id/rl_do_login");
        btnEntrar.click();
        Thread.sleep(10000);


        MobileElement menuPesquisa = (MobileElement) driver.findElementById("br.com.petz:id/menu_search");
        menuPesquisa.click();
        Thread.sleep(1000);
        MobileElement barraPesquisa = (MobileElement) driver.findElementById("br.com.petz:id/et_search");
        barraPesquisa.click();
        Thread.sleep(1000);
        barraPesquisa.sendKeys("Racao");
        Thread.sleep(1000);
        driver.executeScript("mobile:performEditorAction", ImmutableMap.of("action", "done")); //  TECLA ENTER NO CAMPO DE BUSCA

        Thread.sleep(5000);

        // Busca pelo nome do produto e realiza um clique
        // Lembrar de alterar a codificação do arquivo para ISO
        MobileElement nomeProduto = ((AndroidDriver<MobileElement>)driver).
                findElementByAndroidUIAutomator("new UiSelector().text(\"Ração Royal Canin Maxi - Cães Adultos - 15kg\")");
        nomeProduto.click();
        Thread.sleep(3000);
        Assert.assertEquals("Ração Royal Canin Maxi - Cães Adultos - 15kg", driver.findElementById("br.com.petz:id/tv_prod_name").getText());

        Thread.sleep(5000);
        //br.com.petz:id/tv_prod_main_price - Preço normal 232.69
        //br.com.petz:id/tv_subscription_price - Preço assinante  R$ 209,42
    }

    @Test
    public void precoProduto() throws InterruptedException {
        Thread.sleep(1000);
        MobileElement campoEmail = (MobileElement) driver.findElementById("br.com.petz:id/et_user_name");
        campoEmail.sendKeys("petz_mobile@maildrop.cc");
        Thread.sleep(1000);
        MobileElement campoSenha = (MobileElement) driver.findElementById("br.com.petz:id/et_password");
        campoSenha.sendKeys("SenhaTest@123");
        Thread.sleep(1000);
        MobileElement btnEntrar = (MobileElement) driver.findElementById("br.com.petz:id/rl_do_login");
        btnEntrar.click();
        Thread.sleep(10000);


        MobileElement menuPesquisa = (MobileElement) driver.findElementById("br.com.petz:id/menu_search");
        menuPesquisa.click();
        Thread.sleep(1000);
        MobileElement barraPesquisa = (MobileElement) driver.findElementById("br.com.petz:id/et_search");
        barraPesquisa.click();
        Thread.sleep(1000);
        barraPesquisa.sendKeys("Racao");
        Thread.sleep(1000);
        driver.executeScript("mobile:performEditorAction", ImmutableMap.of("action", "done")); //  TECLA ENTER NO CAMPO DE BUSCA

        Thread.sleep(5000);

        // Busca pelo nome do produto e realiza um clique
        // Lembrar de alterar a codificação do arquivo para ISO
        MobileElement nomeProduto = ((AndroidDriver<MobileElement>)driver).
                findElementByAndroidUIAutomator("new UiSelector().text(\"Ração Royal Canin Maxi - Cães Adultos - 15kg\")");
        nomeProduto.click();
        Thread.sleep(3000);
        //Assert.assertEquals("Ração Royal Canin Maxi - Cães Adultos - 15kg", driver.findElementById("br.com.petz:id/tv_prod_name").getText());
        Assert.assertEquals("232.69", driver.findElementById("br.com.petz:id/tv_prod_main_price").getText()); // Verifica Preço Normal
        Assert.assertEquals("R$ 209,42", driver.findElementById("br.com.petz:id/tv_subscription_price").getText()); // Verifica Preço Normal
        Thread.sleep(5000);
        //br.com.petz:id/tv_prod_main_price - Preço normal 232.69
        //br.com.petz:id/tv_subscription_price - Preço assinante  R$ 209,42
    }


    @After
    public void finalizar() {

        driver.quit();
    }
}
