package br.com.fiap.speventos.bo;

import java.util.List;

import br.com.fiap.speventos.beans.Local;
import br.com.fiap.speventos.dao.LocalDAO;

//private int codigoLocal;
//private String nomeLocal;
//private String enderecoLocal;

public class LocalBO {


	public static String novoLocal(Local local) throws Exception {
		
		if (local.getCodigoLocal() < 1) {
			return "Codigo do local invalido";
		}	
		if (local.getNomeLocal().isEmpty() || local.getNomeLocal().length() > 60) {
			return "Nome do local invalido";
		}
		if (local.getEnderecoLocal().isEmpty() || local.getEnderecoLocal().length() > 100) {
			return "Endereco do local invalido";
		}
		
		LocalDAO dao = new LocalDAO();

		Local localRepetido = LocalBO.consultaLocalPorCodigo(local.getCodigoLocal());
		
		if (localRepetido.getCodigoLocal() > 0) {
			return "Local já existe";
		}		
		
		int retorno = dao.cadastrar(local);
		dao.fechar();
		
		return retorno + "registro cadastrado";
	}
	
	public static Local consultaLocalPorCodigo(int codLocal) throws Exception {
		
		// FAZER AS RNs, VALIDACOES E PADRONIZACOES
		
		LocalDAO dao = new LocalDAO();
		
		Local retorno = dao.consultarPorCodigo(codLocal);
		dao.fechar();
		
		return retorno; 
	}
	
	public static List<Local> consultaLocalPorNome(String nomeLocal) throws Exception {
		
		// FAZER AS RNs, VALIDACOES E PADRONIZACOES

		
		LocalDAO dao = new LocalDAO();
		
		List<Local> listaLocal = dao.consultarPorNome(nomeLocal);
		dao.fechar();
		
		return listaLocal; 
		
	}
	
	public static String edicaoLocal(Local local) throws Exception {
		
		// FAZER AS RNs, VALIDACOES E PADRONIZACOES
		
		LocalDAO dao = new LocalDAO();
		
		int retorno = dao.editar(local);
		dao.fechar();
		
		return retorno + "registro editado";
	}
	
	public static String remocaoLocal(int codLocal) throws Exception {
		
		// FAZER AS RNs, VALIDACOES E PADRONIZACOES
		
		LocalDAO dao = new LocalDAO();
		
		int retorno = dao.remover(codLocal);
		dao.fechar();
		
		return retorno + "registro removido";
	}
}
