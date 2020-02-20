package br.com.jgm.web;

import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.com.jgm.controller.EmpresaController;
import br.com.jgm.controller.UrlController;
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
		wd.chromeDriver().findElement(By.id("ctl00$body$ccCodigo")).click();
		
		//aguardar captcha, tentar colocar certificado
		Thread.sleep(5000);
		wd.chromeDriver().findElement(By.xpath("//*[@id=\"ctl00_body_btEntrar\"]")).click();
		Thread.sleep(1000);
		wd.chromeDriver().get("https://nfe.prefeitura.sp.gov.br/tomador/minhaempresa.aspx");
		Thread.sleep(1000);
		wd.chromeDriver().get("https://nfe.prefeitura.sp.gov.br/contribuinte/consultasnfts.aspx");
		
		//navegar pelas empresas
		WebElement dropDown = wd.chromeDriver().findElement(By.xpath("//*[@id=\"ctl00_body_ddlContribuinte\"]"));		
		Iterator<WebElement> empresas = dropDown.findElements(By.tagName("option")).iterator();
		
		int cont = 1;
		while(empresas.hasNext()) {			
			
			WebElement empresa = wd.chromeDriver().findElement(By.xpath("//*[@id=\"ctl00_body_ddlContribuinte\"]/option[" + cont + "]"));		
			
			if (EmpresaController.validaEmpresa(empresa.getText()) == true) {
				
				wd.chromeDriver().get(UrlController.retornaUrlEmpresa(empresa.getText()));
				
				
				if (wd.chromeDriver().findElements(By.id("ctl00_cphPopUp_lblErro")).size() == 0) {			
					wd.chromeDriver().findElement(By.xpath("/html/body/form/div[3]/table/tbody/tr[7]/td/select[1]/option[3]")).click();
					wd.chromeDriver().findElement(By.id("ctl00_cphPopUp_true_btGerar")).click();				
					
				} else {
					System.out.println("Empresa sem serviços prestados");
				}

				
				Thread.sleep(1000);
				wd.chromeDriver().navigate().back();
				
				
				cont++;
			} else {
				System.out.println("Empresa desativa : " + empresa.getText());
				cont++;
			}
			
		}
		
	}
	
}
