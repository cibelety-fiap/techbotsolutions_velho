package br.com.fiap.speventos.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.speventos.beans.Local;
import br.com.fiap.speventos.dao.LocalDAO;

public class LocalBO {


/*	private int codigoLocal;
	private String nomeLocal;
	private String enderecoLocal;
*/
	public String novoLocal(Local local) throws Exception {
		
		
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
		
		List<Local> listaLocal = new ArrayList<Local>();
		
		stmt
		
	}
	

}
