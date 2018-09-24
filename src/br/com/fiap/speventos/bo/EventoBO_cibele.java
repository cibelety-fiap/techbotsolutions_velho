package br.com.fiap.speventos.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.speventos.beans.Evento_cibele;
import br.com.fiap.speventos.dao.EventoDAO_cibele;

public class EventoBO_cibele {
	public static String novoEvento(Evento_cibele evento) throws Exception {

		if (evento.getCodigoEvento() < 1) {
			return "Codigo de evento invalido";
		}
		if (evento.getLinkImagem().isEmpty() || evento.getLinkImagem().length() > 50) {
			return "Link do evento invalido";
		}
		if (evento.getNomeEvento().isEmpty() || evento.getNomeEvento().length() > 80) {
			return "Nome do evento invalido";
		}
		if (evento.getTipoEvento().isEmpty() || evento.getTipoEvento().length() > 30) {
			return "Tipo do evento invalido";
		}
		if (evento.getSubtipoEvento().isEmpty() || evento.getSubtipoEvento().length() > 30) {
			return "Subtipo do evento invalido";
		}
		if (evento.getDescricaoEvento().isEmpty() || evento.getDescricaoEvento().length() > 2000) {
			return "Descricao do evento invalida";
		}
		if (evento.getContatoMaisInfo().isEmpty() || evento.getContatoMaisInfo().length() > 60) {
			return "Contato mais info invalido";
		}
		
		EventoDAO_cibele dao = new EventoDAO_cibele();

		Evento_cibele eventoCodRepetido = dao.consultar(evento.getCodigoEvento());

		if (eventoCodRepetido.getCodigoEvento() > 0) {
			return "Evento já existe";
		}
		
		evento.setLinkImagem(evento.getLinkImagem().toUpperCase());
		evento.setNomeEvento(evento.getNomeEvento().toUpperCase());
		evento.setTipoEvento(evento.getTipoEvento().toUpperCase());
		evento.setSubtipoEvento(evento.getSubtipoEvento().toUpperCase());
		evento.setDescricaoEvento(evento.getDescricaoEvento().toUpperCase());
		evento.setContatoMaisInfo(evento.getContatoMaisInfo().toUpperCase());

		String retorno = dao.cadastrar(evento) + "registro inserido";

		dao.fechar();
		return retorno;
	}

	public static Evento_cibele consultaEvento(int codigoEvento) throws Exception {
		if (codigoEvento < 1) {
			return new Evento_cibele();
		}

		EventoDAO_cibele dao = new EventoDAO_cibele();

		Evento_cibele retorno = dao.consultar(codigoEvento);

		dao.fechar();
		return retorno;

	}

	public static List<Evento_cibele> consultaPorNome(String nomeEvento) throws Exception {

		List<Evento_cibele> listaEvento = new ArrayList<Evento_cibele>();
		
		if (nomeEvento.isEmpty() || nomeEvento.length() > 80 ) {
			return listaEvento;
		}
		
		nomeEvento = nomeEvento.toUpperCase();
		EventoDAO_cibele dao = new EventoDAO_cibele();
		listaEvento = dao.consultarPorNomeEvento(nomeEvento);
		
		dao.fechar();
		return listaEvento;
	}
	
	public static List<Evento_cibele> consultaPorUsuario(int codUsuario) throws Exception {

		List<Evento_cibele> listaEvento = new ArrayList<Evento_cibele>();
		
		if (codUsuario < 1 || codUsuario > 99999) {
			return listaEvento;
		}

		EventoDAO_cibele dao = new EventoDAO_cibele();
		listaEvento = dao.consultarPorUsuario(codUsuario);
		
		dao.fechar();
		return listaEvento;
	}

	public static String edicaoEvento(Evento_cibele evento) throws Exception {
		
		if (evento.getCodigoEvento() < 1) {
			return "Codigo de evento invalido";
		}
		if (evento.getLinkImagem().isEmpty() || evento.getLinkImagem().length() > 50) {
			return "Link do evento invalido";
		}
		if (evento.getNomeEvento().isEmpty() || evento.getNomeEvento().length() > 80) {
			return "Nome do evento invalido";
		}
		if (evento.getTipoEvento().isEmpty() || evento.getTipoEvento().length() > 30) {
			return "Tipo do evento invalido";
		}
		if (evento.getSubtipoEvento().isEmpty() || evento.getSubtipoEvento().length() > 30) {
			return "Subtipo do evento invalido";
		}
		if (evento.getDescricaoEvento().isEmpty() || evento.getDescricaoEvento().length() > 2000) {
			return "Descricao do evento invalida";
		}
		if (evento.getContatoMaisInfo().isEmpty() || evento.getContatoMaisInfo().length() > 60) {
			return "Contato mais info invalido";
		}

		EventoDAO_cibele dao = new EventoDAO_cibele();

		String retorno = dao.editar(evento) + "registro editado";
		
		evento.setLinkImagem(evento.getLinkImagem().toUpperCase());
		evento.setNomeEvento(evento.getNomeEvento().toUpperCase());
		evento.setTipoEvento(evento.getTipoEvento().toUpperCase());
		evento.setSubtipoEvento(evento.getSubtipoEvento().toUpperCase());
		evento.setDescricaoEvento(evento.getDescricaoEvento().toUpperCase());
		evento.setContatoMaisInfo(evento.getContatoMaisInfo().toUpperCase());
		
		dao.fechar();	
		return retorno;
	}

	public static String remocaoEvento(int codigoEvento) throws Exception {
		
		if (codigoEvento < 1) {
			return "Codigo invalido";
		}
		
		EventoDAO_cibele dao = new EventoDAO_cibele();

		String retorno = dao.remover(codigoEvento) + "registro removido";

		dao.fechar();
		return retorno;
	}
}