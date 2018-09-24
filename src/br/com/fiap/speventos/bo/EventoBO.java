package br.com.fiap.speventos.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.speventos.beans.Evento;
import br.com.fiap.speventos.dao.EventoDAO_cibele;

/**
 * Classe para validar e padronizar dados para a tabela T_SGE_EVENTO
 * @version 1.0
 * @since 1.0
 * @author Techbot Solutions
 * @see Evento
 * @see EventoDAO_cibele
 *
 */

public class EventoBO {
	
	/**
	 * Metodo responsavel por verificar regras de negócio, validacoes e padronizacoes
	 * relacionadas a inserção de um novo Evento
	 * Regras de negocio validadas:
	 * O codigo da evento deve ter entre 1 a 5 digitos
	 * O endereco do link da imagem deve ter de 1 a 50 caracteres
	 * O nome do evento deve ter de 1 a 80 caracteres
	 * O tipo de evento deve ter de 1 a 30 caracteres
	 * O subtipo de evento deve ter de 1 a 30 caracteres
	 * A descricao do evento deve ter de 1 a 2000 caracteres
	 * O codigo do evento não poda se cadastrado caso ja exista o banco
	 * @author Techbot Solutions
	 * @param evento recebe um objeto do tipo Evento (Beans)
	 * @return uma String com a quantidade de registros inseridos ou o erro ocorrido
	 * @throws Exception - Chamada da exceção checked 
	 *
	 */
	
	public static String novoEvento(Evento evento) throws Exception {
		if (evento.getCodigoEvento() < 1 || evento.getCodigoEvento() > 99999) {
			return "Codigo de evento invalido";
		}
		
		if (evento.getLinkImagem().isEmpty() || evento.getLinkImagem().length() > 50) {
			return "Link da noticia invalido";
		}
		
		if (evento.getNomeEvento().isEmpty() || evento.getNomeEvento().length() > 80) {
			return "Nome do evento invalido";
		}
		
		if (evento.getTipoEvento().isEmpty() || evento.getTipoEvento().length() > 30) {
			return "Tipo do evento invalido";
		}

		if (evento.getSubtipoEvento().isEmpty() || evento.getSubtipoEvento().length() > 30) {
			return "Categoria da noticia invalida";
		}
		
		if (evento.getDescricaoEvento().isEmpty() || evento.getDescricaoEvento().length() > 2000) {
			return "Descrição do evento invalida";
		}
		

		evento.setNomeEvento(evento.getNomeEvento().toUpperCase());
		evento.setTipoEvento(evento.getTipoEvento().toUpperCase());
		evento.setDescricaoEvento(evento.getDescricaoEvento().toUpperCase());

		EventoDAO_cibele dao = new EventoDAO_cibele();

		Evento eventoCodRepetido = dao.consultar(evento.getCodigoEvento());

		if (eventoCodRepetido.getCodigoEvento() > 0) {
			return "Evento já existe";
		}
		int retorno = dao.cadastrar(evento);;
		

		dao.fechar();
		
		return retorno + "registro cadastrado";
	}
	
	/**
	 * Metodo responsavel por verificar regras de negocio, validacoes e padronizacoes
	 * relacionadas a consulta de um Evento por codigo
	 * Regras de negocio validadas:
	 * O codigo do evento deve ter entre 1 a 5 digitos
	 * @param codigoEvento recebe um objeto do tipo int
	 * @return um construtor vazio
	 * @throws Exception - Chamada da excecao checked
	 */

	public static Evento consultaEvento(int codigoEvento) throws Exception {
		if (codigoEvento < 1) {
			return new Evento();
		}

		EventoDAO_cibele dao = new EventoDAO_cibele();

		Evento retorno = dao.consultar(codigoEvento);

		dao.fechar();
		return retorno;

	}
	
	/**
	 * Metodo responsavel por verificar regras de negocio, validacoes e padronizacoes
	 * relacionadas a consulta de um Evento por nome
	 * Regras de negocio validadas:
	 * O nome da evento deve ter de 1 a 80 caracteres
	 * @param nomeEvento recebe um objeto do tipo String
	 * @return uma lista com objetos do tipo Evento
	 * @throws Exception - Chamada da exceção checked
	 */

	public static List<Evento> consultaPorNome(String nomeEvento) throws Exception {
		List<Evento> listaEvento = new ArrayList<Evento>();
		
		if (nomeEvento.isEmpty() || nomeEvento.length() > 80 ) {
			return listaEvento;
		}
		
		nomeEvento = nomeEvento.toUpperCase();

		EventoDAO_cibele dao = new EventoDAO_cibele();
		listaEvento = dao.consultarPorNomeEvento(nomeEvento);
		
		dao.fechar();
		
		return listaEvento;
	}
	
	/**
	 * Metodo responsavel por verificar regras de negocio, validacoes e padronizacoes
	 * relacionadas a edicao do Evento
	 * Regras de negocio validadas:
	 * O codigo da evento deve ter entre 1 a 5 digitos
	 * O endereco do link da imagem deve ter de 1 a 50 caracteres
	 * O nome do evento deve ter de 1 a 80 caracteres
	 * O tipo de evento deve ter de 1 a 30 caracteres
	 * O subtipo de evento deve ter de 1 a 30 caracteres
	 * A descricao do evento deve ter de 1 a 2000 caracteres
	 * @param evento recebe um objeto do tipo Evento
	 * @return uma String com a quantidade de registros inseridos ou o erro ocorrido
	 * @throws Exception - Chamada da excecao Exception
	 */
	

	public static String edicaoEvento(Evento evento, int codigoEvento) throws Exception {
		if (evento.getCodigoEvento() < 1 || evento.getCodigoEvento() > 99999) {
			return "Codigo de evento invalido";
		}
		
		if (evento.getLinkImagem().isEmpty() || evento.getLinkImagem().length() > 50) {
			return "Link da noticia invalido";
		}
		
		if (evento.getNomeEvento().isEmpty() || evento.getNomeEvento().length() > 80) {
			return "Nome do evento invalido";
		}
		
		if (evento.getTipoEvento().isEmpty() || evento.getTipoEvento().length() > 30) {
			return "Tipo do evento invalido";
		}

		if (evento.getSubtipoEvento().isEmpty() || evento.getSubtipoEvento().length() > 30) {
			return "Categoria da noticia invalida";
		}
		
		if (evento.getDescricaoEvento().isEmpty() || evento.getDescricaoEvento().length() > 2000) {
			return "Descrição do evento invalida";
		}

		evento.setNomeEvento(evento.getNomeEvento().toUpperCase());
		evento.setTipoEvento(evento.getTipoEvento().toUpperCase());
		evento.setDescricaoEvento(evento.getDescricaoEvento().toUpperCase());

		EventoDAO_cibele dao = new EventoDAO_cibele();

		String retorno = null;

		retorno = dao.editar(evento) + "Registro editado";
		dao.fechar();
		return retorno;
	}
	
	/**
	 * Metodo responsavel por verificar regras de negocio, validacoes e padronizacoes
	 * relacionadas a remocao do Evento
	 * Regras de negocio validadas:
	 * O codigo do Evento deve ter entre 1 a 5 digitos
	 * @param codigoEvento recebe um objeto do tipo int
	 * @return uma String com o numero de registros removidos
	 * @throws Exception - Chamada da excecao Exception
	 */

	public static String remocaoEvento(int codigoEvento) throws Exception {
		
		if (codigoEvento < 1 || codigoEvento > 99999) {
			return "Codigo invalido";
		}
		
		EventoDAO_cibele dao = new EventoDAO_cibele();
		
		int retorno = dao.remover(codigoEvento);
		dao.fechar();
		
		return retorno  + "Registro removido";

	}

}
