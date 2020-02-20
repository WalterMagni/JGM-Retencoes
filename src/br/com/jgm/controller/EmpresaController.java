package br.com.jgm.controller;

import br.com.jgm.dao.DAO;
import br.com.jgm.model.Empresa;

public class EmpresaController {

	private static DAO dao = new DAO();
	
	private static Empresa retornaEmpresa (String inscrMunicipal, String nome) {
		return dao.getSituacaoEmpresa(inscrMunicipal, nome);
	}

	public static boolean validaEmpresa(String text) {
		
		if (text.equals("Selecione o contribuinte desejado...")) {
			return false;
		} else {
			
			String inscrMunicipal = text.substring(0, text.indexOf(" "));
			String nome = text.replace(inscrMunicipal, "").replace(" - ", "").trim();
			Empresa emp = retornaEmpresa(inscrMunicipal, nome);

			//verifica se existe a empresa consultada
			if (emp.getAtiva().equals(true)) {
				return true;
			} else {
				return false;
			}
			
		}
	}
	
	
}
