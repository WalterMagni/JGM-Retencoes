package br.com.jgm.web;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;

import br.com.jgm.controller.EmpresaController;
import br.com.jgm.controller.UrlController;
import br.com.jgm.io.EnumTipo;
import br.com.jgm.io.GeraRelatorios;
import br.com.jgm.io.TransfereArquivos;
import br.com.jgm.model.Empresa;
import br.com.jgm.web.config.ConfigChrome;

public class NavNFTS {

	private ConfigChrome wd;
	private List<Empresa> empresasSemServicosPeriodo;
	
	public NavNFTS() {
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
		wd.chromeDriver().get("https://nfe.prefeitura.sp.gov.br/contribuinte/consultasnfts.aspx");
		
		for (Empresa empresa : EmpresaController.retornaEmpresasAtivas(wd.chromeDriver().findElement(By.xpath("//*[@id=\"ctl00_body_ddlContribuinte\"]")).getText())) {	
			
			wd.chromeDriver().get(UrlController.retornaUrlNFTS(empresa.getInscrMunicipal()));
			
			if (wd.chromeDriver().findElements(By.id("ctl00_cphPopUp_lblErro")).size() == 0) {					
				//xls
				wd.chromeDriver().findElement(By.xpath("/html/body/form/div[3]/table/tbody/tr[7]/td/select[1]/option[3]")).click();
				wd.chromeDriver().findElement(By.id("ctl00_cphPopUp_true_btGerar")).click();
				//txt
				wd.chromeDriver().navigate().refresh();
				wd.chromeDriver().findElement(By.xpath("/html/body/form/div[3]/table/tbody/tr[7]/td/select[1]/option[1]")).click();
				wd.chromeDriver().findElement(By.id("ctl00_cphPopUp_true_btGerar")).click();
				Thread.sleep(2000);
				TransfereArquivos.transfereArquivos(empresa, EnumTipo.NFTS);
			} else {
				empresasSemServicosPeriodo.add(empresa);
			}			
		}		
		GeraRelatorios.geraRelatorioEmpresasSemMovimento(empresasSemServicosPeriodo);
		
		wd.chromeDriver().close();
	}
	
}
