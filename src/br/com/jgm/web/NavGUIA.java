package br.com.jgm.web;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import br.com.jgm.model.Empresa;
import br.com.jgm.web.config.ConfigChrome;

public class NavGUIA {

	private ConfigChrome wd;
	private List<Empresa> empresasSemServicosPeriodo;
	
	public NavGUIA() {
		this.wd = new ConfigChrome();
		this.empresasSemServicosPeriodo = new ArrayList<>();
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
		
	
		WebElement dropDown = wd.chromeDriver().findElement(By.xpath("//*[@id=\"ctl00_body_ddlInscricao\"]"));
		 
		List<String> teste = Arrays.asList("41569970", "36347515", "11760877", "34535748");

		for (String string : teste) {

			wd.chromeDriver().get("https://nfe.prefeitura.sp.gov.br/contribuinte/guia.aspx?inscricao=" + string + "&exercicio=2020&mes=2&");
			
			//verifica se possui guias
			if (wd.chromeDriver().findElements(By.id("ctl00_body_btGerarGuia")).size() != 0) {
				wd.chromeDriver().findElement(By.id("ctl00_body_btGerarGuia")).click();
				
				//download arquivo
				WebElement click = wd.chromeDriver().findElement(By.id("ctl00_cphBase_btExportar")); 
				((JavascriptExecutor) wd.chromeDriver()).executeScript("arguments[0].click()", click);
			} 
			
			Thread.sleep(2000);
		}

		wd.chromeDriver().close();

	}
	
}
