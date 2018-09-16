package br.com.fiap.speventos.bo;

import java.util.List;

import br.com.fiap.speventos.beans.Local;
import br.com.fiap.speventos.dao.LocalDAO;

public class LocalBO {


	public static String novoLocal(Local local) throws Exception {
		
		
		LocalDAO dao = new LocalDAO();
		
		int retorno = dao.cadastrar(local);
		dao.fechar();
		
		return retorno + "registro cadastrado";
	}
	
	public Local consultaLocalPorCodigo(int codLocal) throws Exception {
		
		LocalDAO dao = new LocalDAO();
		
		Local retorno = dao.consultarPorCodigo(codLocal);
		dao.fechar();
		
		return retorno; 
	}
	
	public List<Local> consultaLocalPorNome(String nomeLocal) throws Exception {
				
		LocalDAO dao = new LocalDAO();
		
		return dao.consultarPorNome(nomeLocal);
		
	}
	
	public String edicaoLocal(Local local) throws Exception {
		
		LocalDAO dao = new LocalDAO();
		
		int retorno = dao.editar(local);
		dao.fechar();
		
		return retorno + "registro editado";
	}
	
	public String remocaoLocal(int codLocal) throws Exception {
		LocalDAO dao = new LocalDAO();
		
		int retorno = dao.remover(codLocal);
		dao.fechar();
		
		return retorno + "registro removido";
	}
}
