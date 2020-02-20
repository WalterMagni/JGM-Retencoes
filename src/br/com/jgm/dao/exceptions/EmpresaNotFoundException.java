package br.com.jgm.dao.exceptions;

public class EmpresaNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public EmpresaNotFoundException(String inscrMunicipal, String nome) {
		System.out.println("Empresa: " + inscrMunicipal + " - " + nome + " não encontrada");
	}

}
