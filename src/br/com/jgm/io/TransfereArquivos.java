package br.com.jgm.io;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import br.com.jgm.controller.DatasController;
import br.com.jgm.model.Empresa;

public class TransfereArquivos {
	
	private static File pathDownloads = new File("C:\\Users\\walter.marques\\Documents\\RETENCOES\\NFTS");
	
	public static void transfereArquivos(Empresa empresa) {
		
			String emp = empresa.getCodigo() + "-" + empresa.getApelido();
			
			File pathDestino = new File( "G:\\DF\\PROJETO SLIM\\" + emp + "\\" + DatasController.getDataFilePath() + "\\");
			
			if (!pathDestino.exists()) {
				pathDestino.mkdir();
			}
			
			for (File file : pathDownloads.listFiles()) {
				
				if (file.getName().startsWith("NFTS_" + empresa.getInscrMunicipal().replace(".", "").replace("-", ""))) {
					System.out.println(file);
					try {
						FileUtils.copyToDirectory(file, pathDestino);
						System.out.println("Arquivo " + file.getName() + "trasnferido para a empresa: " + emp);
						file.delete();
					} catch (IOException e) {
						System.out.println("Não foi possível transferir o arquivo: " + file.getName() + " da empresa: " + emp);
					}
				}
			}
		
	}

}
