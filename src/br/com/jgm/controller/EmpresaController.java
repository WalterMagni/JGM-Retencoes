package br.com.jgm.controller;

import java.util.ArrayList;
import java.util.List;

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
	
	public static List<Empresa> retornaEmpresasAtivas(String text) {
		
		String[]linhas = text.split("\\n");
		
		List<Empresa> empresas = new ArrayList<>();
		Empresa emp = null;
		
		for (int i = 0; i < linhas.length; i++) {		
	
			String textEmp = linhas[i].trim();
			
			if (textEmp.contains("o contribuinte desejado...")) {
				emp = new Empresa("", "","", "", false);
			} else {
				String inscrMunicipal = textEmp.substring(0, textEmp.indexOf(" "));
				String nome = textEmp.replace(inscrMunicipal, "").replace(" - ", "").trim();		
				emp = dao.getSituacaoEmpresa(inscrMunicipal, nome);
			}
			
			if (emp.getAtiva() == true) {
				empresas.add(emp);
			}	
		}
		
		return empresas;
	}
	
	
}
