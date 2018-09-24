package br.com.fiap.speventos.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.speventos.beans.PessoaJuridica;
import br.com.fiap.speventos.dao.PessoaDAO;
import br.com.fiap.speventos.dao.PessoaJuridicaDAO;
import br.com.fiap.speventos.dao.UsuarioDAO;

/**
 *  Classe para validar e padronizar dados para a tabela T_SGE_PESSOA_JURIDICA
 *  @version 1.0
 *  @since 1.0
 *  @author Techbot Solutions
 *  @see PessoaJuridicaDAO
 *  @see PessoaJuridica
 */
public class PessoaJuridicaBO {

	/**
	 * Metodo para verificar regras de negocio, validacoes e padronizacoes 
	 * relacionadas a insercao de uma nova PessoaJuridica
	 * Regras de negocio validadas:
	 * tamanho do nome do usuario, tamanho do codigo do usuario, tamanho do email do usuario,
	 * se o email tem '@' ou '.', tamanho da senha
	 * @author Techbot Solutions
	 * @param recebe objetos do tipo Usuario, Pessoa, PessoaJuridica
	 * @return uma String com a quantidade de registros inseridos ou o erro ocorrido
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static String novoPessoaJuridica(PessoaJuridica pessoaJuridica) throws Exception {

		if(pessoaJuridica.getCodigoUsuario() <1 || pessoaJuridica.getCodigoUsuario() > 99999) {
			return "Código inválido";
		}

		if(pessoaJuridica.getNome().isEmpty() || pessoaJuridica.getNome().length()>60) {
			return "Nome inválido";
		}

		if(pessoaJuridica.getRazaoSocial().isEmpty() || pessoaJuridica.getRazaoSocial().length()>60) {
			return "Razão Social inválida";
		}

		if(pessoaJuridica.getEmail().isEmpty() || pessoaJuridica.getEmail().length()>60) {
			return "Email inválido";
		}

		if(pessoaJuridica.getEmail().indexOf("@")<0 || pessoaJuridica.getEmail().indexOf(".")<0) {
			return "Email invalido";
		}

		if(pessoaJuridica.getSenha().length()<8 || pessoaJuridica.getSenha().length()>20) {
			return "Senha inválida";
		}

		if(pessoaJuridica.getCnpj()!=12) {
			return "Cnpj inválido";
		}

		if(pessoaJuridica.getCnpjDigito()!=2) {
			return "Cnpj inválido";
		}

		if(pessoaJuridica.getTelefone()>12) {
			return "Numero inválido";
		}

		if(pessoaJuridica.getEndereco().isEmpty() || pessoaJuridica.getEndereco().length()>100) {
			return "Endereço inválido";
		}

		//padronizacao
		pessoaJuridica.setNome(pessoaJuridica.getNome().toUpperCase());
		pessoaJuridica.setEmail(pessoaJuridica.getEmail().toUpperCase());
		pessoaJuridica.setSenha(pessoaJuridica.getSenha().toUpperCase());
		pessoaJuridica.setRazaoSocial((pessoaJuridica.getRazaoSocial().toUpperCase()));
		pessoaJuridica.setEndereco(pessoaJuridica.getEndereco().toUpperCase());

		UsuarioDAO userdao = new UsuarioDAO();
		PessoaDAO pdao = new PessoaDAO();
		PessoaJuridicaDAO pjdao = new PessoaJuridicaDAO();
		 
		PessoaJuridica resultado = pjdao.consultarPorCodigo(pessoaJuridica.getCodigoUsuario());

		if(resultado.getCodigoUsuario()>0) {
			pjdao.fechar();
			return "Usuário já existe";
		}

		userdao.cadastrar(pessoaJuridica);
		pdao.cadastrar(pessoaJuridica);
		pjdao.cadastrar(pessoaJuridica);
		String retorno = pjdao.cadastrar(pessoaJuridica) + "registro inserido";

		pjdao.fechar();
		return retorno + "registro inserido";
	}

	/**
	 * Metodo para validar e fazer pesquisa de codigo de usuario 
	 * relacionadas a consulta de um codigo de usuario 
	 * Regras de negocio validadas:
	 * existencia do codigo no banco de dados
	 * @author Techbot Solutions
	 * @param PessoaFisicaJuridica recebe objetos do tipo PessoaJuridica
	 * @return resultado da consulta ou objeto PessoaJuridica
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static PessoaJuridica consultaPessoaJuridicaPorCodigo(int codigo) throws Exception{

		if(codigo <1 || codigo > 99999) {
			return new PessoaJuridica();
		}
		
		PessoaJuridicaDAO dao = new PessoaJuridicaDAO();

		PessoaJuridica retorno = dao.consultarPorCodigo(codigo);

		dao.fechar();
		return retorno;
	}

	/**
	 * Metodo para validar e fazer pesquisa de nome de usuario 
	 * relacionadas a consulta de um nome de usuario 
	 * Regras de negocio validadas:
	 * validade do nome dentro do banco de dados e padronizacao para upperCase
	 * @author Techbot Solutions
	 * @param PessoaJuridica recebe objetos do tipo PessoaJuridica
	 * @return resultado da consulta ou objeto listaPessoaJuridica
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static List<PessoaJuridica> consultaPessoaJuridicaPorNome(String nome) throws Exception{

		List<PessoaJuridica> listaPessoaJuridica = new ArrayList<PessoaJuridica>();
		
		if(nome.isEmpty() || nome.length()>60) {
			return listaPessoaJuridica;
		}
		
		nome = nome.toUpperCase();
		
		PessoaJuridicaDAO dao = new PessoaJuridicaDAO();

		listaPessoaJuridica = dao.consultarPorNome(nome);

		dao.fechar();
		return listaPessoaJuridica;
	}

	/**
	 * Metodo para verificar regras de negocio, validacoes e padronizacoes 
	 * relacionadas a edicao de um antigo PessoaJuridica
	 * Regras de negocio validadas:
	 * tamanho do nome do usuario, tamanho do codigo do usuario, tamanho do email do usuario,
	 * se o email tem '@' ou '.', tamanho da senha
	 * @author Techbot Solutions
	 * @param PessoaJuridicaBO recebe objetos do tipo PessoaJuridica, Pessoa e Usuario
	 * @return uma String com a quantidade de registros inseridos ou o erro ocorrido
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static String edicaoPessoaJuridica(PessoaJuridica pessoaJuridica) throws Exception{

		if(pessoaJuridica.getCodigoUsuario() <1 || pessoaJuridica.getCodigoUsuario() > 99999) {
			return "Código inválido";
		}

		if(pessoaJuridica.getNome().isEmpty() || pessoaJuridica.getNome().length()>60) {
			return "Nome inválido";
		}

		if(pessoaJuridica.getRazaoSocial().isEmpty() || pessoaJuridica.getRazaoSocial().length()>60) {
			return "Razão Social inválida";
		}

		if(pessoaJuridica.getEmail().isEmpty() || pessoaJuridica.getEmail().length()>60) {
			return "Email inválido";
		}

		if(pessoaJuridica.getEmail().indexOf("@")<0 || pessoaJuridica.getEmail().indexOf(".")<0) {
			return "Email invalido";
		}

		if(pessoaJuridica.getSenha().length()<8 || pessoaJuridica.getSenha().length()>20) {
			return "Senha inválida";
		}

		if(pessoaJuridica.getCnpj()!=12) {
			return "Cnpj inválido";
		}

		if(pessoaJuridica.getCnpjDigito()!=2) {
			return "Cnpj inválido";
		}

		if(pessoaJuridica.getTelefone()>12) {
			return "Numero inválido";
		}

		if(pessoaJuridica.getEndereco().isEmpty() || pessoaJuridica.getEndereco().length()>100) {
			return "Endereço inválido";
		}

		//padronizacao
		pessoaJuridica.setNome(pessoaJuridica.getNome().toUpperCase());
		pessoaJuridica.setEmail(pessoaJuridica.getEmail().toUpperCase());
		pessoaJuridica.setSenha(pessoaJuridica.getSenha().toUpperCase());
		pessoaJuridica.setRazaoSocial((pessoaJuridica.getRazaoSocial().toUpperCase()));
		pessoaJuridica.setEndereco(pessoaJuridica.getEndereco().toUpperCase());

		UsuarioDAO userdao = new UsuarioDAO();
		PessoaDAO pdao = new PessoaDAO();
		PessoaJuridicaDAO pjdao = new PessoaJuridicaDAO();
		
		PessoaJuridica resultado = pjdao.consultarPorCodigo(pessoaJuridica.getCodigoUsuario());

		if(resultado.getCodigoUsuario()>0) {
			pjdao.fechar();
			return "Usuário já existe";
		}
		
		userdao.editar(pessoaJuridica);
		pdao.editar(pessoaJuridica);
		pjdao.editar(pessoaJuridica);

		String retorno =  pjdao.editar(pessoaJuridica) + "registro editado";

		pjdao.fechar();
		return retorno;
	}
	
	/**
	 * Metodo para remover um usuario do banco de dados 
	 * Regras de negocio validadas:
	 * validade do codigo
	 * @author Techbot Solutions
	 * @param PessoaJuridicaBO recebe objetos do tipo PessoaJuridica, Pessoa e Usuario
	 * @return uma String com a quantidade de registros inseridos ou o erro ocorrido
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static String remocaoPessoaJuridica(int pessoaJuridica) throws Exception{
		
		if(pessoaJuridica < 1 || pessoaJuridica > 99999) {
			return "Código inválido";
		}

		UsuarioDAO userdao = new UsuarioDAO();
		PessoaDAO pdao = new PessoaDAO();
		PessoaJuridicaDAO pjdao = new PessoaJuridicaDAO();
		
		userdao.remover(pessoaJuridica);
		pdao.remover(pessoaJuridica);
		pjdao.remover(pessoaJuridica);
		
		String retorno = pjdao.remover(pessoaJuridica) + "registro removido";

		pjdao.fechar();
		return retorno;
	}
}
