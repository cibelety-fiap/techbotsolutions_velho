package br.com.fiap.speventos.bo;

import java.util.List;

import br.com.fiap.speventos.beans.RealizacaoEvento;
import br.com.fiap.speventos.dao.RealizacaoEventoDAO;


/**
 *  Classe para validar dados para a tabela T_DDD_Cliente
 *  @version 1.0
 *  @since 1.0
 *  @author Cibele Takaoka Yamamoto
 *  @see ClienteDAO
 *  @see Cliente
 */

public class RealizacaoEventoBO {

	/**
	 * M�todo respons�vel por manipular as regras relacionadas ao Cliente
	 * Regras de Neg�cio validadas:
	 * Quantidade de estrelas do cliente,
	 * Tamanho do nome do cliente, 
	 * se o n�mero do c liente informado.
	 * @author Cibele Takaoka Yamamoto
	 * @param cli
	 * @return
	 * @throws Exception
	 */
	
	
	public String novaRealizacaoEvento(RealizacaoEvento realizacaoEvento) throws Exception {
		// Regras de negocio

		// Validacao

		// Padronizacao

		RealizacaoEventoDAO dao = new RealizacaoEventoDAO();

		RealizacaoEvento realizacaoEventoCodRepetido = dao
				.consultarPorCodigo(realizacaoEvento.getCodigoRealizacaoEvento());

		if (realizacaoEventoCodRepetido.getCodigoRealizacaoEvento() > 0) {
			return "Realizacao de evento ja existe!";
		}

		String localValido = LocalBO.novoLocal(realizacaoEvento.getLocal());
		String eventoValido = EventoBO.novoEvento(realizacaoEvento.getEvento());

		String retorno = null;

		if ((localValido.equals("OK") || localValido.equals(null))
				&& (eventoValido.equals("OK") || eventoValido.equals(null))) {
			retorno = dao.cadastrar(realizacaoEvento);
		}

		dao.fechar();
		return retorno;
	}

	public RealizacaoEvento consultaEventoPorCodigo(int codRealizEvento) throws Exception {

		RealizacaoEventoDAO dao = new RealizacaoEventoDAO();

			RealizacaoEvento retorno = dao.consultarPorCodigo(codRealizEvento);

		dao.fechar();
		return retorno;
	}

	public List<RealizacaoEvento> consultaEventoPorNomeEvento(String nomeEvento) throws Exception {
		RealizacaoEventoDAO dao = new RealizacaoEventoDAO();

			List<RealizacaoEvento> retorno = dao.consultarPorNomeEvento(nomeEvento);

		dao.fechar();
		return retorno;
	}

	public String edicaoRealizacaoEvento(RealizacaoEvento realizacaoEvento, int codRealizEvento) throws Exception {
		RealizacaoEventoDAO dao = new RealizacaoEventoDAO();

		String retorno = null;

			retorno = dao.editar(realizacaoEvento) + "registro editado";

		dao.fechar();
		return retorno;
	}

	public String remocaoRealizacaoEvento(int codRealizEvento) throws Exception {
		RealizacaoEventoDAO dao = new RealizacaoEventoDAO();
		
		String retorno = null;

			retorno = dao.remover(codRealizEvento) + "registro removido";
		
		dao.fechar();
		return retorno;
	}
}
