package br.com.fiap.speventos.teste;

import java.util.List;

import br.com.fiap.speventos.beans.RespostaChatbot;
import br.com.fiap.speventos.dao.ChatDAO;
import br.com.fiap.speventos.excecao.Excecao;

public class TesteChat {

	public static void main(String[] args) {
		try {
		ChatDAO dao = new ChatDAO();
		
		List<RespostaChatbot> respostas = dao.consultar("Cinema", "ANIMACAO", "2018-09-04 20:00:00");
		for(RespostaChatbot resposta : respostas) {
			System.out.println(resposta.getAll());

		}
		
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println(Excecao.tratarExcecao(e));
		}
	}
}
