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

public class GeraRelatorios {
		
	public static void geraRelatorioEmpresasSemMovimento(List<Empresa> empresas) {
	
		File filePath = new File("G:\\DF\\PROJETO SLIM\\EMPRESAS SEM MOVIMENTOS\\" + DatasController.getDataFilePath());
		
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		
		try {
			
			FileWriter fw = new FileWriter(filePath + "\\Empresas sem Lançamento NFTS.txt");
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
	
	public static void geraRelatorioEmpresasSemMovimentoNFS(List<String> lancamentos) {
		
		File filePath = new File("G:\\DF\\PROJETO SLIM\\EMPRESAS SEM MOVIMENTOS\\" + DatasController.getDataFilePath());
		
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		
		try {
			
			FileWriter fw = new FileWriter(filePath + "\\Empresas sem Lançamento NFS.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write("EMPRESAS SEM MOVIMENTO: ");
			bw.newLine();
			bw.newLine();
			
			for (String	lanc : lancamentos) {
				bw.write(lanc);
				bw.newLine();
			}
			
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
