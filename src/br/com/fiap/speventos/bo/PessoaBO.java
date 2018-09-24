package br.com.fiap.speventos.bo;

import br.com.fiap.speventos.beans.Pessoa;
import br.com.fiap.speventos.dao.PessoaDAO;

public class PessoaBO {

	public static String novoPessoa(Pessoa pessoa) throws Exception{

		if(pessoa.getCodigoUsuario()<1 || pessoa.getCodigoUsuario() > 99999) {
			return "C�digo inv�lido";
		}

		if(pessoa.getNome().isEmpty() || pessoa.getNome().length()>60) {
			return "Nome inv�lido";
		}

		if(pessoa.getEmail().isEmpty() || pessoa.getEmail().length()>60) {
			return "Email inv�lido";
		}
		
		if(pessoa.getEmail().indexOf("@")<0 || pessoa.getEmail().indexOf(".")<0) {
			return "Email invalido";
		}

		if(pessoa.getSenha().length()<8 || pessoa.getSenha().length()>20) {
			return "Senha inv�lida";
		}

		pessoa.setNome(pessoa.getNome().toUpperCase());
		pessoa.setEmail(pessoa.getEmail().toUpperCase());
		pessoa.setSenha(pessoa.getSenha().toUpperCase());
		pessoa.setEndereco(pessoa.getEndereco().toUpperCase());

		PessoaDAO dao = new PessoaDAO();
		
		String retorno = dao.cadastrar(pessoa) + "registro inserido";

		dao.fechar();
		return retorno;
	}
	
	public static String edicaoPessoa(Pessoa pessoa) throws Exception{

		if(pessoa.getCodigoUsuario()<1 || pessoa.getCodigoUsuario() > 99999) {
			return "C�digo inv�lido";
		}

		if(pessoa.getNome().isEmpty() || pessoa.getNome().length()>60) {
			return "Nome inv�lido";
		}

		if(pessoa.getEmail().isEmpty() || pessoa.getEmail().length()>60) {
			return "Email inv�lido";
		}

		if(pessoa.getEmail().indexOf("@")<0 || pessoa.getEmail().indexOf(".")<0) {
			return "Email invalido";
		}

		if(pessoa.getSenha().length()<8 || pessoa.getSenha().length()>20) {
			return "Senha inv�lida";
		}

		if(pessoa.getTelefone()>12) {
			return "Numero inv�lido";
		}

		if(pessoa.getEndereco().isEmpty() || pessoa.getEndereco().length()>100) {
			return "Endere�o inv�lido";
		}

		//padronizacao
		pessoa.setNome(pessoa.getNome().toUpperCase());
		pessoa.setEmail(pessoa.getEmail().toUpperCase());
		pessoa.setSenha(pessoa.getSenha().toUpperCase());
		pessoa.setEndereco(pessoa.getEndereco().toUpperCase());

		PessoaDAO dao = new PessoaDAO();

		String retorno =  dao.editar(pessoa) + "registro editado";

		dao.fechar();
		return retorno;
	}

	public static String remocaoPessoa(int pessoa) throws Exception{

		PessoaDAO dao = new PessoaDAO();

		//fazer if para comparacao do codigo, se <1 o codigo � invalido

		String retorno = dao.remover(pessoa) + "registro removido";

		dao.fechar();
		return retorno;
	}
}
