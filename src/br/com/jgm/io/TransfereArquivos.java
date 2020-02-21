package br.com.jgm.io;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import br.com.jgm.controller.DatasController;
import br.com.jgm.model.Empresa;

public class TransfereArquivos {
	
	private static File pathDownloads = new File("C:\\Users\\walter.marques\\Documents\\RETENCOES\\NFTS");
	
	public static void transfereArquivos(Empresa empresa, EnumTipo tipo) throws InterruptedException {
		
			String emp = empresa.getCodigo() + "-" + empresa.getApelido();
			
			File pathDestino = new File( "G:\\DF\\PROJETO SLIM\\" + emp + "\\" + DatasController.getDataFilePath() + "\\");
			
			if (!pathDestino.exists()) {
				pathDestino.mkdir();
			}
			
			if (tipo == EnumTipo.NFTS) {
				transfereArquivosNfts(empresa, pathDownloads.listFiles(), pathDestino, emp);
			} else {
				transfereArquivosGuia(empresa, pathDownloads.listFiles(), pathDestino, emp);
			}
		
	}
	
	private static void transfereArquivosNfts(Empresa empresa, File[] files, File destino, String emp) throws InterruptedException {
		
		for (File file : pathDownloads.listFiles()) {
			
			if (file.getName().startsWith("NFTS_" + empresa.getInscrMunicipal().replace(".", "").replace("-", ""))) {
				try {
					FileUtils.copyToDirectory(file, destino);
					file.delete();
				} catch (IOException e) {
					System.out.println("Não foi possível transferir o arquivo: " + file.getName() + " da empresa: " + emp);
				}
			}
		}		
	}
	
	private static void transfereArquivosGuia(Empresa empresa, File[] files, File destino, String emp) throws InterruptedException {
		
		for (File file : pathDownloads.listFiles()) {
			
			if (file.getName().startsWith("DAMSP_" + empresa.getInscrMunicipal().replace(".", "").replace("-", ""))) {
				try {
					FileUtils.copyToDirectory(file, destino);
					file.delete();
				} catch (IOException e) {
					System.out.println("Não foi possível transferir o arquivo: " + file.getName() + " da empresa: " + emp);
				}
			}
		}		
	}

}
