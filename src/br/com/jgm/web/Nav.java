package br.com.jgm.web;

import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.com.jgm.web.config.ConfigChrome;

public class Nav {

	private ConfigChrome wd;
	
	public Nav() {
		this.wd = new ConfigChrome();
	}

	public void navegar() throws InterruptedException {
		
		wd.chromeDriver().get("https://nfe.prefeitura.sp.gov.br/login.aspx");
		wd.chromeDriver().findElement(By.id("btnAtualizar")).click();
		wd.chromeDriver().findElement(By.id("ctl00_body_tbCpfCnpj")).sendKeys("914.510.948-68");
		wd.chromeDriver().findElement(By.id("ctl00_body_tbSenha")).sendKeys("446446");
		
		//aguardar captcha, tentar colocar certificado
		Thread.sleep(10000);
		
		wd.chromeDriver().findElement(By.xpath("//*[@id=\"ctl00_body_btEntrar\"]")).click();
		Thread.sleep(2000);
		wd.chromeDriver().get("https://nfe.prefeitura.sp.gov.br/tomador/minhaempresa.aspx");
		Thread.sleep(8000);
		wd.chromeDriver().get("https://nfe.prefeitura.sp.gov.br/contribuinte/consultasnfts.aspx");
		
		//navegar pelas empresas
		
		WebElement dropDown = wd.chromeDriver().findElement(By.xpath("//*[@id=\"ctl00_body_ddlContribuinte\"]"));		
		Iterator<WebElement> empresas = dropDown.findElements(By.tagName("option")).iterator();
		
		int cont = 1;
		while(empresas.hasNext()) {			
			System.out.println(wd.chromeDriver().findElement(By.xpath("//*[@id=\"ctl00_body_ddlContribuinte\"]/option[" + cont + "]")).getText());
			wd.chromeDriver().findElement(By.xpath("//*[@id=\"ctl00_body_ddlContribuinte\"]/option[" + cont + "]")).click();
			cont++;
		}
		
//	
//		int cont = 1;
//		for (WebElement webElement : empresas) {	
//			System.out.println(cont + " " + webElement.getText());
//			cont++;	
//		}
//		
//		System.out.println(wd.chromeDriver().findElement(By.xpath("//*[@id=\"ctl00_body_ddlContribuinte\"]/option[2]")).getText());
		
		
	}
	
}
