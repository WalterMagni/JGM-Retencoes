package br.com.jgm.web;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;

import br.com.jgm.controller.EmpresaController;
import br.com.jgm.controller.UrlController;
import br.com.jgm.io.GeraRelatorios;
import br.com.jgm.model.Empresa;
import br.com.jgm.web.config.ConfigChrome;

public class NavNFS {
	
	private ConfigChrome wd;
	private List<String> movimentoSemLancamento;
	
	public NavNFS() {
		this.wd = new ConfigChrome();
		this.movimentoSemLancamento = new ArrayList<>();
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
		wd.chromeDriver().get("https://nfe.prefeitura.sp.gov.br/contribuinte/consultas.aspx");

		
		
		for (Empresa empresa : EmpresaController.retornaEmpresasAtivas(wd.chromeDriver().findElement(By.xpath("//*[@id=\"ctl00_body_ddlContribuinte\"]")).getText())) {	
			
			wd.chromeDriver().get(UrlController.retornaUrlNfsEmitidas(empresa.getInscrMunicipal()));
			
			if (wd.chromeDriver().findElements(By.id("ctl00_cphPopUp_lblErro")).size() == 0) {			
				
				wd.chromeDriver().findElement(By.xpath("/html/body/form/div[3]/table[1]/tbody/tr[7]/td/select[2]/option[4]")).click();
				wd.chromeDriver().findElement(By.id("ctl00_cphPopUp_true_btGerar")).click();
				
				//TransfereArquivos.transfereArquivos(empresa, EnumTipo.NFTS);
			} else {
				movimentoSemLancamento.add(empresa.getCodigo() + " - " + empresa.getNome() +": Sem lançamento Emitido");
			}	
			
			wd.chromeDriver().get(UrlController.retornaUrlNfsRecebidas(empresa.getInscrMunicipal()));
			
			if (wd.chromeDriver().findElements(By.id("ctl00_cphPopUp_lblErro")).size() == 0) {			
				
				wd.chromeDriver().findElement(By.xpath("/html/body/form/div[3]/table[1]/tbody/tr[7]/td/select[2]/option[4]")).click();
				wd.chromeDriver().findElement(By.id("ctl00_cphPopUp_true_btGerar")).click();
				
				//TransfereArquivos.transfereArquivos(empresa, EnumTipo.NFTS);
			} else {
				movimentoSemLancamento.add(empresa.getCodigo() + " - " + empresa.getNome() +": Sem lançamento Recebido");
			}
			
		}		
		GeraRelatorios.geraRelatorioEmpresasSemMovimentoNFS(movimentoSemLancamento);
		
		wd.chromeDriver().close();
	}

}
