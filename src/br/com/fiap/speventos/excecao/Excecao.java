package br.com.fiap.speventos.excecao;

public class Excecao extends Exception{
	
	public static String tratarExcecao(Exception e) {
		
		if (e.getClass().getName().equals("java.sql.SQLException")) {
			return "Ocorreu um ero ao conectar no banco";
			
		} else if (e.getClass().getName().equals("java.lag.NumberFormatException")) {
			return "Numero invalido!";
			
		} else if (e.getClass().getName().equals("java.sql.SQLSyntaxErrorException")) {
			return "Erro de sintaxe de SQL";	
		} else {
			return "Ocorreu um problema";
		}
	}
}
