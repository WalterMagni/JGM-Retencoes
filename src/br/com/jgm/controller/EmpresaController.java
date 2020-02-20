package br.com.jgm.controller;

import br.com.jgm.dao.DAO;
import br.com.jgm.model.Empresa;

public class EmpresaController {

	private static DAO dao = new DAO();
	
	public static Empresa retornaEmpresa (String text) {
		
		if (text.contains("o contribuinte desejado...")) {
			return new Empresa("", "","", "", false);
		} else {
			String inscrMunicipal = text.substring(0, text.indexOf(" "));
			String nome = text.replace(inscrMunicipal, "").replace(" - ", "").trim();		
			
			return dao.getSituacaoEmpresa(inscrMunicipal, nome);
		}
	}
	
}
