package br.com.fiap.speventos.bo;

import br.com.fiap.speventos.beans.Usuario2;
import br.com.fiap.speventos.dao.UsuarioDAO2;

public class UsuarioBO2 {
	
	public Usuario2 login(String email, String senha) throws Exception{
		
		//RNs, validações, padronizações
		
		UsuarioDAO2 dao = new UsuarioDAO2();
		Usuario2 usuario = dao.logar(email, senha);
		dao.fechar();
		
		return usuario;
		
	}

}
