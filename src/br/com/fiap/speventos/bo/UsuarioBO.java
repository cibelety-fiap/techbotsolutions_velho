package br.com.fiap.speventos.bo;

import br.com.fiap.speventos.beans.Usuario;
import br.com.fiap.speventos.dao.UsuarioDAO;

/**
 *  Classe para validar e padronizar dados para a tabela T_SGE_USUARIO
 *  @version 1.0
 *  @since 1.0
 *  @author Techbot Solutions
 *  @see UsuarioDAO
 *  @see Usuario
 */
public class UsuarioBO {

	/**
	 * Metodo para verificar regras de negocio, validacoes e padronizacoes 
	 * relacionadas a insercao de um novo usuario 
	 * Regras de negocio validadas:
	 * tamanho do nome do usuario, tamanho do codigo do usuario, tamanho do email do usuario,
	 * se o email tem '@' ou '.', tamanho da senha
	 * @author Techbot Solutions
	 * @param recebe objetos do tipo Usuario
	 * @return uma String com a quantidade de registros inseridos ou o erro ocorrido
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static String novoUsuario(Usuario usuario) throws Exception{

		if(usuario.getCodigoUsuario()<1 || usuario.getCodigoUsuario() > 99999) {
			return "C�digo inv�lido";
		}

		if(usuario.getNome().isEmpty() || usuario.getNome().length()>60) {
			return "Nome inv�lido";
		}

		if(usuario.getEmail().isEmpty() || usuario.getEmail().length()>60) {
			return "Email inv�lido";
		}

		if(usuario.getEmail().indexOf("@")<0 || usuario.getEmail().indexOf(".")<0) {
			return "Email invalido";
		}

		if(usuario.getSenha().length()<8 || usuario.getSenha().length()>20) {
			return "Senha inv�lida";
		}

		usuario.setNome(usuario.getNome().toUpperCase());
		usuario.setEmail(usuario.getEmail().toUpperCase());
		usuario.setSenha(usuario.getSenha().toUpperCase());

		UsuarioDAO dao = new UsuarioDAO();
	
		String retorno = dao.cadastrar(usuario) + "registro inserido";
		dao.fechar();
		return retorno;
	}

	/**
	 * Metodo para verificar regras de negocio, validacoes e padronizacoes 
	 * relacionadas ao login de um usuario 
	 * Regras de negocio validadas:
	 * tamanho do email do usuario, se o email tem '@' ou '.' e tamanho da senha
	 * @author Techbot Solutions
	 * @param recebe objeto do tipo Usuario
	 * @return usuario
	 * @throws Exception - Chamada da Exception
	 */
	public static Usuario login(String email, String senha) throws Exception{

		if(email.isEmpty() || email.length()>60) {
			return new Usuario();
		}

		if(email.indexOf("@")<0 || email.indexOf(".")<0) {
			return new Usuario();
		}

		if(senha.length()<8 || senha.length()>20) {
			return new Usuario();
		}

		email.toUpperCase();
		senha.toUpperCase();

		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = dao.logar(email, senha);
		dao.fechar();

		return usuario;
	}

	/**
	 * Metodo para verificar regras de negocio, validacoes e padronizacoes 
	 * relacionadas a edicao de um usuario 
	 * Regras de negocio validadas:
	 * tamanho do nome do usuario, tamanho do codigo do usuario, tamanho do email do usuario,
	 * se o email tem '@' ou '.', tamanho da senha
	 * @author Techbot Solutions
	 * @param recebe objetos do tipo Usuario
	 * @return uma String com a quantidade de registros inseridos ou o erro ocorrido
	 * @throws Exception - Chamada da Exception
	 */
	public static String edicaoUsuario(Usuario usuario) throws Exception{

		if(usuario.getCodigoUsuario()<1 || usuario.getCodigoUsuario() > 99999) {
			return "C�digo inv�lido";
		}

		if(usuario.getNome().isEmpty() || usuario.getNome().length()>60) {
			return "Nome inv�lido";
		}

		if(usuario.getEmail().isEmpty() || usuario.getEmail().length()>60) {
			return "Email inv�lido";
		}

		if(usuario.getEmail().indexOf("@")<0 || usuario.getEmail().indexOf(".")<0) {
			return "Email invalido";
		}

		if(usuario.getSenha().length()<8 || usuario.getSenha().length()>20) {
			return "Senha inv�lida";
		}

		usuario.setNome(usuario.getNome().toUpperCase());
		usuario.setEmail(usuario.getEmail().toUpperCase());
		usuario.setSenha(usuario.getSenha().toUpperCase());

		UsuarioDAO dao = new UsuarioDAO();

		String retorno = dao.editar(usuario) + "registro editado";
		dao.fechar();
		return retorno;
	}

	/**
	 * Metodo para remover um usuario do banco de dados 
	 * @author Techbot Solutions
	 * @param UsuarioBO recebe objeto do tipo Usuario
	 * @return uma String com a quantidade de registros inseridos ou o erro ocorrido
	 * @throws Exception - Chamada da Exception
	 */
	public static String remocaoUsuario(int usuario) throws Exception{

		UsuarioDAO dao = new UsuarioDAO();

		String retorno = dao.remover(usuario) + "registro removido";

		dao.fechar();
		return retorno;
	}
}
