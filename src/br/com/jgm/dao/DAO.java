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
		
		String query = "select codi_emp, nome_emp, stat_emp from bethadba.geempre where imun_emp = '" + inscrMunicipal + " and stat_emp  = 'A' or imun_emp = '" + inscrMunicipal.replace(".", "").replace("-", "") + "' and stat_emp  = 'A'";
			
		Statement stmt;
		try {
			stmt = con.createStatement();
			stmt.executeQuery(query);
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				 empresa.setCodigo(rs.getString("codi_emp"));
				 empresa.setAtiva(true);
			}
			stmt.close();
			rs.close();
			
			if (empresa.getCodigo() == null) {
				System.out.println(empresa);
			}

			return empresa;
		} catch (SQLException e) {
			throw new EmpresaNotFoundException(inscrMunicipal, nome);
		}
		
	}
	
}
