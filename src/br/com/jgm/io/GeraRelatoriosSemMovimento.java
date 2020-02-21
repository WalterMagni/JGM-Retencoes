package br.com.jgm.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.jgm.controller.DatasController;
import br.com.jgm.model.Empresa;

public class GeraRelatoriosSemMovimento {
	
//	public static void main(String[] args) {
//		
//		Empresa e1 = new Empresa("1206", "VANMAX AGENCIAMENTO DE TRANSPORTES INTERNACIONAIS LTDA", "VANMAX", "4.959.202-5", true);
//		Empresa e2 = new Empresa("1247", "COMERCIO DE ROUPAS R.YEIRELI", "R.Y F 1", "5.043.675-9", true);
//		
//		List<Empresa> empresas = new ArrayList<>();
//		empresas.addAll(Arrays.asList(e1, e2));
//		
//		geraRelatorioEmpresasSemMovimento(empresas);
//		
//	}
	
	public static void geraRelatorioEmpresasSemMovimento(List<Empresa> empresas) {
	
		File filePath = new File("G:\\DF\\PROJETO SLIM\\EMPRESAS SEM MOVIMENTOS\\" + DatasController.getDataFilePath());
		
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		
		try {
			
			FileWriter fw = new FileWriter(filePath + "\\Empresas sem Lançamento.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write("EMPRESAS SEM MOVIMENTO: ");
			bw.newLine();
			bw.newLine();
			
			for (Empresa empresa : empresas) {
				bw.write(empresa.getCodigo() + " - " + empresa.getApelido());
				bw.newLine();
			}
			
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
