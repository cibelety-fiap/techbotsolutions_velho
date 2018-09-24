package br.com.fiap.speventos.bo;

import java.util.ArrayList;
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
	 * @throws Exception - Chamada da exceção checked Exception
	 */
	public static String novaRealizacaoEvento(RealizacaoEvento realizacaoEvento) throws Exception {

//		private int codigoRealizacaoEvento;
//		private Evento evento;
//		private Local local;
//		private Date dataHoraInicio;
//		private Date dataHoraTermino;
		
		if (realizacaoEvento.getCodigoRealizacaoEvento() < 1 || 
				realizacaoEvento.getCodigoRealizacaoEvento() > 99999) {
			return "Codigo de realizacao de evento invalido";
		}
		
		if (realizacaoEvento.getEvento().getCodigoEvento() < 1 || 
				realizacaoEvento.getEvento().getCodigoEvento() > 99999) {
			return "Codigo de evento invalido";
		}
		
		if (realizacaoEvento.getLocal().getCodigoLocal() < 1 || 
				realizacaoEvento.getLocal().getCodigoLocal() > 99999) {
			return "Codigo de evento invalido";
		}

		if (!DataBO.validacaoDataHora(realizacaoEvento.getDataHoraInicio())) {
			return "Data/hora de inicio invalida(s)";
		}
		
		if (!DataBO.validacaoDataHora(realizacaoEvento.getDataHoraTermino())) {
			return "Data/hora de termino invalida(s)";
		}
		
		if(!DataBO.compararDtHrInicioTermino(realizacaoEvento.getDataHoraInicio(), realizacaoEvento.getDataHoraTermino())) {
			return "Data de inicio maior que data de termino";
		}
					
		RealizacaoEventoDAO dao = new RealizacaoEventoDAO();

		RealizacaoEvento realizacaoEventoRepetido = dao
				.consultarPorCodigo(realizacaoEvento.getCodigoRealizacaoEvento());

		if (realizacaoEventoRepetido.getCodigoRealizacaoEvento() > 0) {
			return "Realizacao de evento ja existe";
		}

		Evento eventoExiste = EventoBO_cibele.consultaEvento(realizacaoEvento.getEvento().getCodigoEvento());
		
		if(eventoExiste.getCodigoEvento() == 0) {
			return "Evento relacionado a realizacao de evento nao existe";
		} 
		

		Local localExiste = LocalBO.consultaLocalPorCodigo(realizacaoEvento.getLocal().getCodigoLocal());
//		System.out.println(localExiste.getCodigoLocal());
		
		String localValido = "";
		
		if(localExiste.getCodigoLocal() == 0) {
			localValido = LocalBO.novoLocal(realizacaoEvento.getLocal());
		}
		System.out.println(localValido);
		String retorno = null;

		if(localValido.equals("1 registro cadastrado") || localValido.isEmpty()) {
			retorno = dao.cadastrar(realizacaoEvento) + " registro cadastrado";
		}
		
		dao.fechar();
		return retorno;
	}

	public static RealizacaoEvento consultaRealizEventoPorCodigo(int codRealizEvento) throws Exception {
		if (codRealizEvento < 1 || codRealizEvento > 99999 ) {
			return new RealizacaoEvento();
		}
		
		RealizacaoEventoDAO dao = new RealizacaoEventoDAO();
		RealizacaoEvento retorno = dao.consultarPorCodigo(codRealizEvento);
		dao.fechar();
		
		return retorno;
	}

	public static List<RealizacaoEvento> consultaRealizEventoPorNomeEvento(String nomeEvento) throws Exception {

		List<RealizacaoEvento> listaRealizacaoEvento = new ArrayList<RealizacaoEvento>();

		if (nomeEvento.isEmpty() || nomeEvento.length() > 80 ) {
			return listaRealizacaoEvento;
		}
		
		nomeEvento = nomeEvento.toUpperCase();
		RealizacaoEventoDAO dao = new RealizacaoEventoDAO();
		listaRealizacaoEvento = dao.consultarPorNomeEvento(nomeEvento);
		dao.fechar();
		
		return listaRealizacaoEvento;
	}

	public static String edicaoRealizacaoEvento(RealizacaoEvento realizacaoEvento, int codRealizEvento) throws Exception {
		
		if (realizacaoEvento.getCodigoRealizacaoEvento() < 1 || 
				realizacaoEvento.getCodigoRealizacaoEvento() > 99999) {
			return "Codigo de realizacao de evento invalido";
		}
		
		if (realizacaoEvento.getEvento().getCodigoEvento() < 1 || 
				realizacaoEvento.getEvento().getCodigoEvento() > 99999) {
			return "Codigo de evento invalido";
		}
		
		if (realizacaoEvento.getLocal().getCodigoLocal() < 1 || 
				realizacaoEvento.getLocal().getCodigoLocal() > 99999) {
			return "Codigo de evento invalido";
		}

		if (!DataBO.validacaoDataHora(realizacaoEvento.getDataHoraInicio())) {
			return "Data/hora de inicio invalida(s)";
		}
		
		if (!DataBO.validacaoDataHora(realizacaoEvento.getDataHoraTermino())) {
			return "Data/hora de termino invalida(s)";
		}
		
		if(!DataBO.compararDtHrInicioTermino(realizacaoEvento.getDataHoraInicio(), realizacaoEvento.getDataHoraTermino())) {
			return "Data de inicio maior que data de termino";
		}
					
		RealizacaoEventoDAO dao = new RealizacaoEventoDAO();

		Evento eventoExiste = EventoBO_cibele.consultaEvento(realizacaoEvento.getCodigoRealizacaoEvento());
		
		if(eventoExiste.getCodigoEvento() == 0) {
			return "Evento relacionado a realizacao de evento nao existe";
		} 
		
		Local localExiste = LocalBO.consultaLocalPorCodigo(realizacaoEvento.getLocal().getCodigoLocal());
				
		String localValido = null;
		
		if(localExiste.getCodigoLocal() == 0) {
			//o usuario nao pode editar o local, somente adicionar um novo com valores alterados
			localValido = LocalBO.novoLocal(realizacaoEvento.getLocal());
		}
		
		String retorno = null;

		if(localValido.equals("1 registro inserido") || localValido.equals(null)) {
			retorno = dao.cadastrar(realizacaoEvento) + " registro editado";
		}

		dao.fechar();
		return retorno;
	}

	public static String remocaoRealizacaoEvento(int codRealizEvento) throws Exception {

		if (codRealizEvento < 1 || codRealizEvento > 99999) {
			return "Codigo invalido";
		}
		
		RealizacaoEventoDAO dao = new RealizacaoEventoDAO();
		
		String retorno = dao.remover(codRealizEvento) + " registro removido";
		
		dao.fechar();
		return retorno;
	}
}
