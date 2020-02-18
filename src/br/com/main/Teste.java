
package br.com.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.jgm.dao.DAO;
import br.com.jgm.model.Empresa;

public class Teste {

	
	
	public static void main(String[] args) throws IOException {
		
		DAO dao = new DAO();
		
		List<Empresa> empresas = new ArrayList<>();
		
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\walter.marques\\Documents\\RETENCOES\\NFTS\\Empresas.txt"));
		String line;
		while ((line = br.readLine()) != null) {
			
			line = line.trim();
			
			String inscrEstadual = line.substring(0, line.indexOf(" "));
			String nome = line.replace(inscrEstadual, "").replace("- ", "").trim();
			
			Empresa empresa = new Empresa(null, nome, inscrEstadual, false);
			empresas.add(empresa);	
	
		}
		
		for (Empresa empresa : empresas) {
			dao.getSituacaoEmpresa(empresa.getInscrMunicipal(), empresa.getNome());
		}

	}

}
