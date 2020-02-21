package br.com.jgm.controller;

import java.time.LocalDate;

public class DatasController {

	private static LocalDate currentDate = LocalDate.now();
	
	public static String getDataFilePath() {
		
		if (currentDate.getMonthValue() == 1) {
			return "12" + Integer.toString(currentDate.getYear() - 1);
		} else {
			String data = Integer.toString(currentDate.getMonthValue() - 1) + Integer.toString(currentDate.getYear());	
			
			if (data.length() == 5) {
				return "0" + data;
			} else {
				return data;
			}
		}
	}
	
	public static String getMes() {	
		if (currentDate.getMonthValue() == 1) {
			return "12";
		} 
		return Integer.toString(currentDate.getMonthValue() -1);
	}
	
	public static String getAno() {		
		if (getMes().equals("12")) {
			return Integer.toString(currentDate.getYear() -1); 
		} 	
		return Integer.toString(currentDate.getYear());		
	}
}
