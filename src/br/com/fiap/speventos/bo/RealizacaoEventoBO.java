package br.com.fiap.speventos.bo;

import java.util.List;

import br.com.fiap.speventos.beans.Evento;
import br.com.fiap.speventos.beans.Local;
import br.com.fiap.speventos.beans.RealizacaoEvento;
import br.com.fiap.speventos.dao.RealizacaoEventoDAO;


/**
 *  Classe para validar e padronizar dados para a tabela T_SGE_REALIZACAO_EVENTO
 *  @version 1.0
 *  @since 1.0
 *  @author Techbot Solutions
 *  @see ClienteDAO
 *  @see Cliente
 */

public class RealizacaoEventoBO {

	/**
	 * Método para verificar regras de negócio, validações e padronizações 
	 * relacionadas à inserção de uma nova RealizacaoEvento 
	 * Regras de negócio validadas:
	 * tamanho do nome do evento (etc)
	 * @author Techbot Solutions
	 * @param realizacaoEvento recebe um objeto do tipo RealizacaoEvento (Beans)
	 * @return uma String com a quantidade de registros inseridos ou o erro ocorrido
	 * @throws Exception - Chamada da exceção checked SQLException (???)
	 */
	public static String novaRealizacaoEvento(RealizacaoEvento realizacaoEvento) throws Exception {

		if (realizacaoEvento.getCodigoRealizacaoEvento() < 1) {
			return "Codigo de realizacao de evento invalido";
		}
		
//		if (realizacaoEvento.getEvento().get || evento.getNome().length() > 80) {
//			return “Nome do evento inválido”;
//		}
		
//		private int codigoRealizacaoEvento;
//		private Evento evento;
//		private Local local;
//		private Date dataHoraInicio;
//		private Date dataHoraTermino;

		//Validar data
		
		RealizacaoEventoDAO dao = new RealizacaoEventoDAO();

		RealizacaoEvento realizacaoEventoCodRepetido = dao
				.consultarPorCodigo(realizacaoEvento.getCodigoRealizacaoEvento());

		if (realizacaoEventoCodRepetido.getCodigoRealizacaoEvento() > 0) {
			return "Realizacao de evento ja existe!";
		}

		Evento eventoExiste = EventoBO.consultaEvento(realizacaoEvento.getCodigoRealizacaoEvento());
		
		if(eventoExiste.getCodigoEvento() == 0) {
			return "Evento relacionado a realizacao de evento invalido";
		} 
		
		Local existeLocal = LocalBO.consultaLocalPorCodigo(realizacaoEvento.getLocal().getCodigoLocal());
				if(existe.consultagetLocal().getCodigoLocal() )
		
		String localValido = LocalBO.novoLocal(realizacaoEvento.getLocal());

		String retorno = null;

		if ((localValido.equals("OK") || localValido.equals(null))
				&& (eventoValido.equals("OK") || eventoValido.equals(null))) {
			retorno = dao.cadastrar(realizacaoEvento) + "registro inserido";
		}

		dao.fechar();
		return retorno;
	}

	public static RealizacaoEvento consultaEventoPorCodigo(int codRealizEvento) throws Exception {

		// FAZER AS RNs, VALIDACOES E PADRONIZACOES
		
		RealizacaoEventoDAO dao = new RealizacaoEventoDAO();

			RealizacaoEvento retorno = dao.consultarPorCodigo(codRealizEvento);

		dao.fechar();
		return retorno;
	}

	public static List<RealizacaoEvento> consultaEventoPorNomeEvento(String nomeEvento) throws Exception {

		// FAZER AS RNs, VALIDACOES E PADRONIZACOES
		
		RealizacaoEventoDAO dao = new RealizacaoEventoDAO();

			List<RealizacaoEvento> listaRealizEvento = dao.consultarPorNomeEvento(nomeEvento);

		dao.fechar();
		return listaRealizEvento;
	}

	public static String edicaoRealizacaoEvento(RealizacaoEvento realizacaoEvento, int codRealizEvento) throws Exception {

		// FAZER AS RNs, VALIDACOES E PADRONIZACOES
		
		RealizacaoEventoDAO dao = new RealizacaoEventoDAO();

		String retorno = dao.editar(realizacaoEvento) + "registro editado";

		dao.fechar();
		return retorno;
	}

	public static String remocaoRealizacaoEvento(int codRealizEvento) throws Exception {

		if (codRealizEvento < 1) {
			return "Codigo invalido";
		}
		
		RealizacaoEventoDAO dao = new RealizacaoEventoDAO();
		
		String retorno = dao.remover(codRealizEvento) + "registro removido";
		
		dao.fechar();
		return retorno;
	}
}
