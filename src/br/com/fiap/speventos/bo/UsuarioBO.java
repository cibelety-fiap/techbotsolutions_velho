package br.com.fiap.speventos.bo;

import br.com.fiap.speventos.beans.Usuario;
import br.com.fiap.speventos.dao.UsuarioDAO;

public class UsuarioBO {

	public static String novoUsuario(Usuario usuario) throws Exception{

		if(usuario.getCodigoUsuario()<=0) {
			return "Código inválido";
		}

		if(usuario.getNome().isEmpty() || usuario.getNome().length()>60) {
			return "Nome inválido";
		}

		if(usuario.getEmail().isEmpty() || usuario.getEmail().length()>60) {
			return "Email inválido";
		}

		if(usuario.getEmail().indexOf("@")<0 || usuario.getEmail().indexOf(".")<0) {
			return "Email invalido";
		}

		if(usuario.getSenha().length()<8 || usuario.getSenha().length()>20) {
			return "Senha inválida";
		}

		usuario.setNome(usuario.getNome().toUpperCase());
		usuario.setEmail(usuario.getEmail().toUpperCase());
		usuario.setSenha(usuario.getSenha().toUpperCase());

		UsuarioDAO dao = new UsuarioDAO();
		//Usuario resultado = dao.consultarPorCodigo(usuario.getCodigoUsuario());

		//		if(resultado.getCodigoUsuario()>0) {
		//			dao.fechar();
		//			return "Usuário já existe";
		//		}

		//O QUE RETORNAR? CLOSE+"USUARIO CADASTRADO"?
		dao.fechar();
		return "";
	}
	
	public static Usuario login(String email, String senha) throws Exception{
		
		//RNs, validacoes, etc
		
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = dao.logar(email, senha);
		dao.fechar();
		
		return usuario;
		
	}


	public static String edicaoUsuario(Usuario usuario) throws Exception{

		return "";
	}

}
