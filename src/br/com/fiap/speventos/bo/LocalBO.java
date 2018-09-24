package br.com.fiap.speventos.bo;

import java.util.List;

import br.com.fiap.speventos.beans.Local;
import br.com.fiap.speventos.dao.LocalDAO;

public class LocalBO {


	public static String novoLocal(Local local) throws Exception {
		
		// FAZER AS RNs, VALIDACOES E PADRONIZACOES
		
		LocalDAO dao = new LocalDAO();
		
		int retorno = dao.cadastrar(local);
		dao.fechar();
		
		return retorno + "registro cadastrado";
	}
	
	public Local consultaLocalPorCodigo(int codLocal) throws Exception {
		
		// FAZER AS RNs, VALIDACOES E PADRONIZACOES
		
		LocalDAO dao = new LocalDAO();
		
		Local retorno = dao.consultarPorCodigo(codLocal);
		dao.fechar();
		
		return retorno; 
	}
	
	public List<Local> consultaLocalPorNome(String nomeLocal) throws Exception {
		
		// FAZER AS RNs, VALIDACOES E PADRONIZACOES

		
		LocalDAO dao = new LocalDAO();
		
		List<Local> listaLocal = dao.consultarPorNome(nomeLocal);
		dao.fechar();
		
		return listaLocal; 
		
	}
	
	public String edicaoLocal(Local local) throws Exception {
		
		// FAZER AS RNs, VALIDACOES E PADRONIZACOES
		
		LocalDAO dao = new LocalDAO();
		
		int retorno = dao.editar(local);
		dao.fechar();
		
		return retorno + "registro editado";
	}
	
	public String remocaoLocal(int codLocal) throws Exception {
		
		// FAZER AS RNs, VALIDACOES E PADRONIZACOES
		
		LocalDAO dao = new LocalDAO();
		
		int retorno = dao.remover(codLocal);
		dao.fechar();
		
		return retorno + "registro removido";
	}
}
