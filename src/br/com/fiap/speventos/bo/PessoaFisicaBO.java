package br.com.fiap.speventos.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.speventos.beans.PessoaFisica;
import br.com.fiap.speventos.dao.PessoaDAO;
import br.com.fiap.speventos.dao.PessoaFisicaDAO;
import br.com.fiap.speventos.dao.UsuarioDAO;

/**
 *  Classe para validar e padronizar dados para a tabela T_SGE_PESSOA_FISICA
 *  @version 1.0
 *  @since 1.0
 *  @author Techbot Solutions
 *  @see PessoaFisicaDAO
 *  @see PessoaFisica
 */
public class PessoaFisicaBO {
	
	/**
	 * Metodo para verificar regras de negocio, validacoes e padronizacoes 
	 * relacionadas a insercao de uma nova PessoaFisica 
	 * Regras de negocio validadas:
	 * tamanho do nome do usuario, tamanho do codigo do usuario, tamanho do email do usuario,
	 * se o email tem '@' ou '.', tamanho da senha
	 * @author Techbot Solutions
	 * @param recebe objetos do tipo Usuario, Pessoa, PessoaFisica
	 * @return uma String com a quantidade de registros inseridos ou o erro ocorrido
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static String novoPessoaFisica(PessoaFisica pessoaFisica) throws Exception {

		if(pessoaFisica.getCodigoUsuario() < 1 || pessoaFisica.getCodigoUsuario() > 99999) {
			return "Código inválido";
		}

		if(pessoaFisica.getNome().isEmpty() || pessoaFisica.getNome().length()>60) {
			return "Nome inválido";
		}

		if(pessoaFisica.getEmail().isEmpty() || pessoaFisica.getEmail().length()>60) {
			return "Email inválido";
		}

		if(pessoaFisica.getEmail().indexOf("@")<0 || pessoaFisica.getEmail().indexOf(".")<0) {
			return "Email invalido";
		}

		if(pessoaFisica.getSenha().length()<8 || pessoaFisica.getSenha().length()>20) {
			return "Senha inválida";
		}

		if(pessoaFisica.getTelefone()>12) {
			return "Numero inválido";
		}

		if(pessoaFisica.getEndereco().isEmpty() || pessoaFisica.getEndereco().length()>100) {
			return "Endereço inválido";
		}

		if(pessoaFisica.getCpf()<1 || pessoaFisica.getCpf()>9) {
			return "Cpf inválido";
		}

		if(pessoaFisica.getCpfDigito()!=2) {
			return "Cpf inválido";
		}

		if(!(pessoaFisica.getGenero() == 'M' || pessoaFisica.getGenero() == 'F' || pessoaFisica.getGenero() == 'O')) {
			return "Genero inválido";
		}

		if(pessoaFisica.getRg()!=9) {
			return "Rg inválido";
		}

		if(pessoaFisica.getRgDigito()!=1) {
			return "Rg inválido";
		}

		//padronizacao
		pessoaFisica.setNome(pessoaFisica.getNome().toUpperCase());
		pessoaFisica.setEmail(pessoaFisica.getEmail().toUpperCase());
		pessoaFisica.setSenha(pessoaFisica.getSenha().toUpperCase());
		pessoaFisica.setEndereco(pessoaFisica.getEndereco().toUpperCase());
		pessoaFisica.setGenero(Character.toUpperCase(pessoaFisica.getGenero()));		

		UsuarioDAO userdao = new UsuarioDAO();
		PessoaDAO pdao = new PessoaDAO();
		PessoaFisicaDAO pfdao = new PessoaFisicaDAO();

		PessoaFisica resultado = pfdao.consultarPorCodigo(pessoaFisica.getCodigoUsuario());

		if(resultado.getCodigoUsuario()>0) {
			pfdao.fechar();
			return "Usuário já existe";
		}
		
		userdao.cadastrar(pessoaFisica);
		pdao.cadastrar(pessoaFisica);
		pfdao.cadastrar(pessoaFisica);
		String retorno = pfdao.cadastrar(pessoaFisica) + "registro inserido";
		
		pfdao.fechar();
		return retorno;
	}
	
	/**
	 * Metodo para validar e fazer pesquisa de codigo de usuario 
	 * relacionadas a consulta de um codigo de usuario 
	 * Regras de negocio validadas:
	 * existencia do codigo no banco de dados
	 * @author Techbot Solutions
	 * @param PessoaFisicaBO recebe objetos do tipo PessoaFisica
	 * @return resultado da consulta ou objeto PessoaFisica
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static PessoaFisica consultaPessoaFisicaPorCodigo(int codigo) throws Exception{
		
		if(codigo <1 || codigo > 99999) {
			return new PessoaFisica();
		}

		PessoaFisicaDAO dao = new PessoaFisicaDAO();

		PessoaFisica retorno = dao.consultarPorCodigo(codigo);

		dao.fechar();
		return retorno;
	}
	
	/**
	 * Metodo para validar e fazer pesquisa de nome de usuario 
	 * relacionadas a consulta de um nome de usuario 
	 * Regras de negocio validadas:
	 * validade do nome dentro do banco de dados e padronizacao para upperCase
	 * @author Techbot Solutions
	 * @param PessoaFisicaBO recebe objetos do tipo PessoaFisica
	 * @return resultado da consulta ou objeto listaPessoaFisica
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static List<PessoaFisica> consultaPessoaFisicaPorNome(String nome) throws Exception{
		
		List<PessoaFisica> listaPessoaFisica = new ArrayList<PessoaFisica>();
		
		if(nome.isEmpty() || nome.length()>60) {
			return listaPessoaFisica;
		}

		nome = nome.toUpperCase();
		
		PessoaFisicaDAO dao = new PessoaFisicaDAO();

		listaPessoaFisica = dao.consultarPorNome(nome);

		dao.fechar();
		return listaPessoaFisica;
	}
	
	/**
	 * Metodo para verificar regras de negocio, validacoes e padronizacoes 
	 * relacionadas a edicao de um antigo PessoaFisica 
	 * Regras de negocio validadas:
	 * tamanho do nome do usuario, tamanho do codigo do usuario, tamanho do email do usuario,
	 * se o email tem '@' ou '.', tamanho da senha
	 * @author Techbot Solutions
	 * @param PessoaFisicaBO recebe objetos do tipo PessoaFisica, Pessoa e Usuario
	 * @return uma String com a quantidade de registros inseridos ou o erro ocorrido
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static String edicaoPessoaFisica(PessoaFisica pessoaFisica) throws Exception{

		if(pessoaFisica.getCodigoUsuario() <1 || pessoaFisica.getCodigoUsuario() > 99999) {
			return "Código inválido";
		}

		if(pessoaFisica.getNome().isEmpty() || pessoaFisica.getNome().length()>60) {
			return "Nome inválido";
		}

		if(pessoaFisica.getEmail().isEmpty() || pessoaFisica.getEmail().length()>60) {
			return "Email inválido";
		}

		if(pessoaFisica.getEmail().indexOf("@")<0 || pessoaFisica.getEmail().indexOf(".")<0) {
			return "Email invalido";
		}

		if(pessoaFisica.getSenha().length()<8 || pessoaFisica.getSenha().length()>20) {
			return "Senha inválida";
		}

		if(pessoaFisica.getTelefone()>12) {
			return "Numero inválido";
		}

		if(pessoaFisica.getEndereco().isEmpty() || pessoaFisica.getEndereco().length()>100) {
			return "Endereço inválido";
		}

		if(pessoaFisica.getCpf()<1 || pessoaFisica.getCpf()>9) {
			return "Cpf inválido";
		}

		if(pessoaFisica.getCpfDigito()!=2) {
			return "Cpf inválido";
		}

		if(!(pessoaFisica.getGenero() == 'M' || pessoaFisica.getGenero() == 'F' || pessoaFisica.getGenero() == 'O')) {
			return "Genero inválido";
		}

		if(pessoaFisica.getRg()!=9) {
			return "Rg inválido";
		}

		if(pessoaFisica.getRgDigito()!=1) {
			return "Rg inválido";
		}

		//padronizacao
		pessoaFisica.setNome(pessoaFisica.getNome().toUpperCase());
		pessoaFisica.setEmail(pessoaFisica.getEmail().toUpperCase());
		pessoaFisica.setSenha(pessoaFisica.getSenha().toUpperCase());
		pessoaFisica.setEndereco(pessoaFisica.getEndereco().toUpperCase());
		pessoaFisica.setGenero(Character.toUpperCase(pessoaFisica.getGenero()));

		UsuarioDAO userdao = new UsuarioDAO();
		PessoaDAO pdao = new PessoaDAO();
		PessoaFisicaDAO pfdao = new PessoaFisicaDAO();
		
		PessoaFisica resultado = pfdao.consultarPorCodigo(pessoaFisica.getCodigoUsuario());

		if(resultado.getCodigoUsuario()>0) {
			pfdao.fechar();
			return "Usuário já existe";
		}
		
		userdao.editar(pessoaFisica);
		pdao.editar(pessoaFisica);
		pfdao.editar(pessoaFisica);
		String retorno = pfdao.editar(pessoaFisica) + "registro editado";

		pfdao.fechar();
		return retorno;
	}
	
	/**
	 * Metodo para remover um usuario do banco de dados 
	 * Regras de negocio validadas:
	 * validade do codigo
	 * @author Techbot Solutions
	 * @param PessoaFisicaBO recebe objetos do tipo PessoaFisica, Pessoa e Usuario
	 * @return uma String com a quantidade de registros inseridos ou o erro ocorrido
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static String remocaoPessoaFisica(int pessoaFisica) throws Exception{
		
		if(pessoaFisica < 1 || pessoaFisica > 99999) {
			return "Código inválido";
		}

		UsuarioDAO userdao = new UsuarioDAO();
		PessoaDAO pdao = new PessoaDAO();
		PessoaFisicaDAO pfdao = new PessoaFisicaDAO();
		
		userdao.remover(pessoaFisica);
		pdao.remover(pessoaFisica);
		pfdao.remover(pessoaFisica);
		String retorno = pfdao.remover(pessoaFisica) + "registro removido";

		pfdao.fechar();
		return retorno;
	}

}
