package br.com.jgm.controller;

import br.com.jgm.dao.DAO;
import br.com.jgm.model.Empresa;

public class EmpresaController {

	private static DAO dao = new DAO();
	
	public static Empresa retornaEmpresa (String inscrMunicipal, String nome) {
		return dao.getSituacaoEmpresa(inscrMunicipal, nome);
	}
	
}
