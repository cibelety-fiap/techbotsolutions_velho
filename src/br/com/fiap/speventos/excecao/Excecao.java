package br.com.fiap.speventos.excecao;

public class Excecao extends Exception {

	public static String tratarExcecao(Exception e) {

		if (e.getClass().getName().equals("java.sql.SQLException")) {
			return "Ocorreu um ero ao conectar com o banco";
		} else if (e.getClass().getName().equals("java.sql.SQLRecoverableException")) {
			return "Ocorreu um problema com o banco de dados. Espere alguns minutos e tente de novo.";	
		} else if (e.getClass().getName().equals("java.sql.SQLSyntaxErrorException")) {
			return "Erro de sintaxe no SQL";
		} else if (e.getClass().getName().equals("java.lag.NumberFormatException")) {
			return "Formato de numero invalido";
		} else {
			return "Ocorreu um problema";
		}
	}
}
