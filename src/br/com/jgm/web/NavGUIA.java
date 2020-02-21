package br.com.jgm.web;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import br.com.jgm.controller.EmpresaController;
import br.com.jgm.controller.UrlController;
import br.com.jgm.io.EnumTipo;
import br.com.jgm.io.TransfereArquivos;
import br.com.jgm.model.Empresa;
import br.com.jgm.web.config.ConfigChrome;

public class NavGUIA {

	private ConfigChrome wd;
	
	public NavGUIA() {
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
		wd.chromeDriver().get("https://nfe.prefeitura.sp.gov.br/contribuinte/guias.aspx");

		for (Empresa empresa : EmpresaController.retornaEmpresasAtivas(wd.chromeDriver().findElement(By.xpath("//*[@id=\"ctl00_body_ddlInscricao\"]")).getText())) {

			wd.chromeDriver().get(UrlController.retornaUrlGuia(empresa.getInscrMunicipal()));
			
			if (wd.chromeDriver().findElements(By.id("ctl00_body_btGerarGuia")).size() != 0) {
				wd.chromeDriver().findElement(By.id("ctl00_body_btGerarGuia")).click();
				
				WebElement click = wd.chromeDriver().findElement(By.id("ctl00_cphBase_btExportar")); 
				((JavascriptExecutor) wd.chromeDriver()).executeScript("arguments[0].click()", click);
			} 
			Thread.sleep(2000);
			TransfereArquivos.transfereArquivos(empresa, EnumTipo.GUIA);
		}
		wd.chromeDriver().close();
	}
	
}
