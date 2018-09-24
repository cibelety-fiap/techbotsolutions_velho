package br.com.fiap.speventos.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.speventos.beans.Colaborador;
import br.com.fiap.speventos.dao.ColaboradorDAO;
import br.com.fiap.speventos.dao.UsuarioDAO;

/**
 *  Classe para validar e padronizar dados para a tabela T_SGE_COLABORADOR e T_SGE_USUARIO
 *  @version 1.0
 *  @since 1.0
 *  @author Techbot Solutions
 *  @see ColaboradorDAO
 *  @see Colaborador
 */
public class ColaboradorBO {

	/**
	 * Metodo para verificar regras de negocio, validacoes e padronizacoes 
	 * relacionadas a insercao de um novo Colaborador 
	 * Regras de negocio validadas:
	 * tamanho do nome do usuario, tamanho do codigo do usuario, tamanho do email do usuario,
	 * se o email tem '@' ou '.', tamanho da senha
	 * @author Techbot Solutions
	 * @param ColaboradorBO recebe objetos do tipo Colaborador e Usuario
	 * @return uma String com a quantidade de registros inseridos ou o erro ocorrido
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static String novoColaborador(Colaborador colaborador) throws Exception{

		if(colaborador.getCodigoUsuario()<1 || colaborador.getCodigoUsuario() > 99999) {
			return "C�digo inv�lido";
		}

		if(colaborador.getNome().isEmpty() || colaborador.getNome().length()>60) {
			return "Nome inv�lido";
		}

		if(colaborador.getEmail().isEmpty() || colaborador.getEmail().length()>60) {
			return "Email inv�lido";
		}

		if(colaborador.getEmail().indexOf("@")<0 || colaborador.getEmail().indexOf(".")<0) {
			return "Email invalido";
		}

		if(colaborador.getSenha().length()<8 || colaborador.getSenha().length()>20) {
			return "Senha inv�lida";
		}

		colaborador.setNome(colaborador.getNome().toUpperCase());
		colaborador.setEmail(colaborador.getEmail().toUpperCase());
		colaborador.setSenha(colaborador.getSenha().toUpperCase());
		colaborador.setNivelAcesso(colaborador.getNivelAcesso().toUpperCase());
		colaborador.setDepartamento(colaborador.getDepartamento().toUpperCase());

		UsuarioDAO userdao	= new UsuarioDAO();
		ColaboradorDAO dao = new ColaboradorDAO();

		Colaborador resultado = dao.consultarPorCodigo(colaborador.getCodigoUsuario());

		if(resultado.getCodigoUsuario()>0) {
			dao.fechar();
			return "Usu�rio j� existe";
		}

		userdao.cadastrar(colaborador);
		dao.cadastrar(colaborador);
		String retorno = dao.cadastrar(colaborador) + "registro inserido";

		dao.fechar();
		return retorno;
	}

	/**
	 * Metodo para verificar regras de negocio, validacoes e padronizacoes 
	 * relacionadas a edicao de um antigo Colaborador 
	 * Regras de negocio validadas:
	 * tamanho do nome do usuario, tamanho do codigo do usuario, tamanho do email do usuario,
	 * se o email tem '@' ou '.', tamanho da senha
	 * @author Techbot Solutions
	 * @param ColaboradorBO recebe objetos do tipo Colaborador e Usuario
	 * @return uma String com a quantidade de registros inseridos ou o erro ocorrido
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static String edicaoColaborador(Colaborador colaborador) throws Exception{

		if(colaborador.getCodigoUsuario()<1 || colaborador.getCodigoUsuario() > 99999) {
			return "C�digo inv�lido";
		}

		if(colaborador.getNome().isEmpty() || colaborador.getNome().length()>60) {
			return "Nome inv�lido";
		}

		if(colaborador.getEmail().isEmpty() || colaborador.getEmail().length()>60) {
			return "Email inv�lido";
		}

		if(colaborador.getEmail().indexOf("@")<0 || colaborador.getEmail().indexOf(".")<0) {
			return "Email invalido";
		}

		if(colaborador.getSenha().length()<8 || colaborador.getSenha().length()>20) {
			return "Senha inv�lida";
		}

		colaborador.setNome(colaborador.getNome().toUpperCase());
		colaborador.setEmail(colaborador.getEmail().toUpperCase());
		colaborador.setSenha(colaborador.getSenha().toUpperCase());
		colaborador.setNivelAcesso(colaborador.getNivelAcesso().toUpperCase());
		colaborador.setDepartamento(colaborador.getDepartamento().toUpperCase());

		UsuarioDAO userdao = new UsuarioDAO();
		ColaboradorDAO dao = new ColaboradorDAO();

		Colaborador resultado = dao.consultarPorCodigo(colaborador.getCodigoUsuario());

		if(resultado.getCodigoUsuario()>0) {
			dao.fechar();
			return "Usu�rio j� existe";
		}

		userdao.editar(colaborador);
		dao.editar(colaborador);
		String retorno =  dao.editar(colaborador) + "registro inserido";

		dao.fechar();
		return retorno;
	}

	/**
	 * Metodo para validar e fazer pesquisa de codigo de usuario 
	 * relacionadas a consulta de um codigo de usuario 
	 * Regras de negocio validadas:
	 * existencia do codigo no banco de dados
	 * @author Techbot Solutions
	 * @param ColaboradorBO recebe objetos do tipo Colaborador
	 * @return resultado da consulta ou objeto colaborador
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static Colaborador consultaColaboradorPorCodigo(int codigo) throws Exception{

		if(codigo < 1 || codigo > 99999) {
			return new Colaborador();
		}

		ColaboradorDAO dao = new ColaboradorDAO();

		Colaborador retorno = dao.consultarPorCodigo(codigo);

		dao.fechar();
		return retorno;
	}

	/**
	 * Metodo para validar e fazer pesquisa de nome de usuario 
	 * relacionadas a consulta de um nome de usuario 
	 * Regras de negocio validadas:
	 * validade do nome dentro do banco de dados e padronizacao para upperCase
	 * @author Techbot Solutions
	 * @param ColaboradorBO recebe objetos do tipo Colaborador
	 * @return resultado da consulta ou objeto listaColaborador
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static List<Colaborador> consultaColaboradorPorNome(String nome) throws Exception{

		List<Colaborador> listaColaborador = new ArrayList<Colaborador>();

		if(nome.isEmpty() || nome.length()> 60) {
			return listaColaborador;
		}

		nome = nome.toUpperCase();

		ColaboradorDAO dao = new ColaboradorDAO();

		listaColaborador = dao.consultarPorNome(nome);

		dao.fechar();
		return listaColaborador;
	}

	/**
	 * Metodo para remover um usuario do banco de dados 
	 * Regras de negocio validadas:
	 * validade do codigo
	 * @author Techbot Solutions
	 * @param ColaboradorBO recebe objetos do tipo Colaborador e Usuario
	 * @return uma String com a quantidade de registros inseridos ou o erro ocorrido
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static String remocaoColaborador(int Colaborador) throws Exception{

		if(Colaborador < 1 || Colaborador > 99999) {
			return "C�digo inv�lido";
		}

		UsuarioDAO userdao = new UsuarioDAO();
		ColaboradorDAO dao = new ColaboradorDAO();

		userdao.remover(Colaborador);
		dao.remover(Colaborador);
		String retorno = dao.remover(Colaborador) + "registro removido";

		dao.fechar();
		return retorno;
	}
}