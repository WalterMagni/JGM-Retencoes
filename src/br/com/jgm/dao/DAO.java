package br.com.jgm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.jgm.api.dao.AbstractDAO;
import br.com.jgm.dao.exceptions.EmpresaNotFoundException;
import br.com.jgm.model.Empresa;

public class DAO extends AbstractDAO{

	public Empresa getSituacaoEmpresa(String inscrMunicipal, String nome) {
		
		Empresa empresa = new Empresa();
		empresa.setNome(nome);
		empresa.setInscrMunicipal(inscrMunicipal);
		
		String query = "select codi_emp, nome_emp, stat_emp from bethadba.geempre where imun_emp = '" + inscrMunicipal + "' or imun_emp = '" + inscrMunicipal.replace(".", "").replace("-", "") + "'";

		Statement stmt;
		try {
			stmt = con.createStatement();
			stmt.executeQuery(query);
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				empresa.setCodigo(rs.getString("codi_emp")); 
				if (rs.getString("stat_emp").equals("A")) {
					empresa.setAtiva(true);
				}	 
			}
			stmt.close();
			rs.close();

			return empresa;
		} catch (SQLException e) {
			System.out.println("Empresa: " + inscrMunicipal + " - " + nome + " não encontrada");	
			return empresa;
		}
		
	}
	
}
