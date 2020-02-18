package br.com.jgm.web;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.jgm.web.config.ConfigChrome;

public class Nav {

	private ConfigChrome wd;
	
	public Nav() {
		this.wd = new ConfigChrome();
	}
	
	
	
	public void navegar() throws InterruptedException {
		
		wd.chromeDriver().get("https://nfe.prefeitura.sp.gov.br/login.aspx");
		wd.chromeDriver().findElement(By.id("ctl00_body_tbCpfCnpj")).sendKeys("914.510.948-68");
		wd.chromeDriver().findElement(By.id("ctl00_body_tbSenha")).sendKeys("446446");
		
		//aguardar captcha, tentar colocar certificado
		Thread.sleep(10000);
		
		wd.chromeDriver().findElement(By.xpath("//*[@id=\"ctl00_body_btEntrar\"]")).click();
		Thread.sleep(2000);
		wd.chromeDriver().get("https://nfe.prefeitura.sp.gov.br/tomador/minhaempresa.aspx");
		Thread.sleep(15000);
		wd.chromeDriver().get("https://nfe.prefeitura.sp.gov.br/contribuinte/consultasnfts.aspx");
		
		//navegar pelas empresas
		List<WebElement> empresas = wd.chromeDriver().findElements(By.xpath("//*[@id=\"ctl00_body_ddlContribuinte\"]"));
		
		for (WebElement webElement : empresas) {
			System.out.println(webElement.getText());
		}
		
		

		
		
	}
	
}
