package br.com.fiap.speventos.beans;

import java.util.ArrayList;
import java.util.List;

public class RespostaChatbot {

	private String nomeEvento;
	private String linkImagem;
	private List<ArrayList<String>> horariosLocalPorFilme = new ArrayList<ArrayList<String>>();

	public RespostaChatbot() {
	}

	public RespostaChatbot(String nomeEvento, String linkImagem, List<ArrayList<String>> horariosLocalPorFilme) {
		this.nomeEvento = nomeEvento;
		this.linkImagem = linkImagem;
		this.horariosLocalPorFilme = horariosLocalPorFilme;
	}

	public String getAll() {
		String todosEventos = new String();
		for (ArrayList<String> horariosPorLocal: horariosLocalPorFilme) {
			for (String horarioOuLocal: horariosPorLocal) {
				todosEventos = todosEventos + " " + horarioOuLocal;
			}
		}
		return nomeEvento + "\n" + linkImagem + "\n" + todosEventos;
	}
	
	public String getNomeEvento() {
		return nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}

	public String getLinkImagem() {
		return linkImagem;
	}

	public void setLinkImagem(String linkImagem) {
		this.linkImagem = linkImagem;
	}

	public List<ArrayList<String>> getHorariosLocalPorFilme() {
		return horariosLocalPorFilme;
	}

	public void setHorariosLocalPorFilme(List<ArrayList<String>> horariosLocalPorFilme) {
		this.horariosLocalPorFilme = horariosLocalPorFilme;
	}

}