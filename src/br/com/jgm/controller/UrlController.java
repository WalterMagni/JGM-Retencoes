package br.com.jgm.controller;

public class UrlController {

	public static String retornaUrlEmpresa(String inscrMunicipal) {
				
		inscrMunicipal = inscrMunicipal.replace(".", "").replace("-", "");

		return "https://nfe.prefeitura.sp.gov.br/contribuinte/notasrecapuradasnfts.aspx?inscricao=" + inscrMunicipal + "&cpfcnpj=&nome=&regime=2&ano=2020&mes=1&pagas=1&pendentes=1&canceladas=1&incidencia=true";	

	}
	
}
