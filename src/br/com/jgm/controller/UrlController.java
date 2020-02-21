package br.com.jgm.controller;

public class UrlController {

	public static String retornaUrlNFTS(String inscrMunicipal) {
				
		inscrMunicipal = inscrMunicipal.replace(".", "").replace("-", "");
		String ano = DatasController.getAno();
		String mes = DatasController.getMes();

		return "https://nfe.prefeitura.sp.gov.br/contribuinte/notasrecapuradasnfts.aspx?inscricao=" + inscrMunicipal + "&cpfcnpj=&nome=&regime=2&ano=" + ano + "&mes=" + mes + "&pagas=1&pendentes=1&canceladas=1&incidencia=true";	

	}
	
	public static String retornaUrlGuia(String inscrMunicipal) {
		
		inscrMunicipal = inscrMunicipal.replace(".", "").replace("-", "");
		String ano = DatasController.getAno();
		String mes = DatasController.getMes();

		return "https://nfe.prefeitura.sp.gov.br/contribuinte/guia.aspx?inscricao=" + inscrMunicipal + "&exercicio=" + ano + "&mes= " + mes + "&";	

	}
	
}
