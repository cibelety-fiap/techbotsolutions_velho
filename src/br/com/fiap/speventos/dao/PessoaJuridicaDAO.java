package br.com.fiap.speventos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.speventos.beans.PessoaJuridica;
import br.com.fiap.speventos.conexao.Conexao;

/**
 * Classe para manipular a tabela T_SGE_PESSOA_JURIDICA
 * Possui metodos para: cadastrar, consultarPorCodigo, consultarPorCodigoEvento, editar, remover
 * @author Techbot Solutions
 * @version 1.0
 * @since 1.0
 * @see PessoaJuridica
 * @see PessoaJuridicaBO
 * @see Usuario
 * @see Pessoa
 *
 */
public class PessoaJuridicaDAO {

	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;

	/**
	  * Metodo construtor que estabelece a comunicacao com o banco de dados
	  * @author Techbot Solutions
	  * @param nao possui parametros
	  * @return nao ha retorno
	  * @throws Exception - Chamada da excecao Exception
	  */
	public PessoaJuridicaDAO() throws Exception {
		con = new Conexao().conectar();
	}

	/**
	  * Metodo para adicionar um registro na tabela T_SGE_PESSOA_JURIDICA
	  * @author Techbot Solutions
	  * @param pessoaJuridica recebe um objeto do tipo PessoaJuridica (Beans)
	  * @return um int com a quantidade de registros inseridos
	  * @throws Exception - Chamada da excecao Exception
	  */
	public String cadastrar(PessoaJuridica pj) throws Exception {

		stmt = con.prepareStatement("INSERT INTO T_SGE_PESSOA_JURIDICA "
				+ "(NM_RAZAO_SOCIAL, NR_CNPJ, NR_CNPJ_DIGITO)"
				+ " VALUES (?, ?, ?)");

		stmt.setString(1, "NM_RAZAO_SOCIAL");
		stmt.setString(2, "NR_CNPJ");
		stmt.setString(3, "NR_CNPJ_DIGITO");

		stmt.executeQuery();

		return "Cadastro Realizado";
	}

	/**
	  * Metodo para consultar por codigo de usuario um registro na tabela 
	  * T_SGE_USUARIO, T_SGE_PESSOA, T_SGE_PESSOA_JURIDICA
	  * @author Techbot Solutions
	  * @param codigoUsuario recebe um objeto do tipo int
	  * @return um objeto PessoaJuridica
	  * @throws Exception - Chamada da excecao Exception
	  */
	public PessoaJuridica consultarPorCodigo(int codigo) throws Exception {

		stmt = con.prepareStatement(
				"SELECT * FROM T_SGE_USUARIO"
						+ " INNER JOIN T_SGE_PESSOA ON "
						+ "(T_SGE_USUARIO.CD_USUARIO=T_SGE_PESSOA.CD_USUARIO)"
						+ " INNER JOIN T_SGE_PESSOA_JURIDICA ON "
						+ "(T_SGE_PESSOA.CD_USUARIO=T_SGE_PESSOA_JURIDICA.CD_USUARIO"
						+ " WHERE T_SGE_PESSOA_JURIDICA.CD_USUARIO = ?");

		stmt.setInt(1, codigo);
		rs = stmt.executeQuery();

		if(rs.next()) {
			return new PessoaJuridica(
					rs.getInt("CD_USUARIO"),
					rs.getString("DS_EMAIL"),
					rs.getString("DS_SENHA"),
					rs.getString("NM_USUARIO"),
					rs.getLong("NR_TELEFONE"),
					rs.getString("DS_ENDERECO"),
					rs.getString("NM_RAZAO_SOCIAL"),
					rs.getLong("NR_CNPJ"),
					rs.getInt("NR_CNPJ_DIGITO"));

		} else {
			return new PessoaJuridica();
		}
	}

	/**
	  * Metodo para consultar por codigo de usuario registros na tabela T_SGE_USUARIO, T_SGE_PESSOA, T_SGE_PESSOA_JURIDICA
	  * @author Techbot Solutions
	  * @param codigoUsuario recebe um objeto do tipo int
	  * @return uma lista com objetos do tipo PessoaJuridica
	  * @throws Exception - Chamada da excecao Exception
	  */
	public List<PessoaJuridica> consultarPorNome(String nome) throws Exception {

		List<PessoaJuridica> lista = new ArrayList<PessoaJuridica>();

		stmt = con.prepareStatement("SELECT * FROM T_SGE_USUARIO"
				+ " INNER JOIN T_SGE_PESSOA ON "
				+ "(T_SGE_USUARIO.CD_USUARIO=T_SGE_PESSOA.CD_USUARIO)"
				+ " INNER JOIN T_SGE_PESSOA_JURIDICA ON "
				+ "(T_SGE_PESSOA.CD_USUARIO=T_SGE_PESSOA_JURIDICA.CD_USUARIO"
				+ " WHERE T_SGE_PESSOA_JURIDICA.NM_USUARIO LIKE ?");

		stmt.setString(1, "%" + nome + "%");

		while (rs.next()) {
			lista.add(new PessoaJuridica(
					rs.getInt("CD_USUARIO"),
					rs.getString("DS_EMAIL"),
					rs.getString("DS_SENHA"),
					rs.getString("NM_USUARIO"),
					rs.getLong("NR_TELEFONE"),
					rs.getString("DS_ENDERECO"),
					rs.getString("NM_RAZAO_SOCIAL"),
					rs.getLong("NR_CNPJ"),
					rs.getInt("NR_CNPJ_DIGITO")));
		}
		return lista;
	}

	/**
	 * Metodo para editar um registro na tabela T_SGE_PESSOA_JURIDICA
	 * @author Techbot Solutions
	 * @param pessoaJuridica recebe um objeto do tipo PessoaJuridica
	 * @return um int com a quantidade de registros editados
	 * @throws Exception - Chamada da excecao Exception
	 */
	public int editar(PessoaJuridica pessoaJuridica) throws Exception {

		stmt = con.prepareStatement(
				"UPDATE T_SGE_USUARIO SET NM_RAZAO_SOCIAL=?, NR_CNPJ=?, NR_CNPJ_DIGITO=? "
				+ "WHERE T_SGE_PESSOA_FISICA.CD_USUARIO=?");


		stmt.setString(1, pessoaJuridica.getRazaoSocial());
		stmt.setLong(2, pessoaJuridica.getCnpj());
		stmt.setInt(3, pessoaJuridica.getCnpjDigito());

		return stmt.executeUpdate();
	}
	
	/**
	 * Metodo para remover um registro na tabela T_SGE_PESSOA_JURIDICA
	 * @author Techbot Solutions
	 * @param codigoUsuario recebe um objeto do tipo int
	 * @return um int com o numero de itens removidos
	 * @throws Exception - Chamada da excecao Exception
	 */
	public int remover(int cd_usuario) throws Exception {
		stmt = con.prepareStatement("DELETE FROM T_SGE_PESSOA_JURIDICA "
				+ "WHERE T_SGE_PESSOA_FISICA.CD_USUARIO = ?");
		stmt.setInt(1, cd_usuario);

		return stmt.executeUpdate();
	}

	/**
	  * Metodo que fecha a comunicacao com o banco de dados
	  * @author Techbot Solutions
	  * @param nao possui parametros
	  * @return nao ha retorno
	  * @throws Exception - Chamada da excecao Exception
	  */
	
	public void fechar() throws Exception {
		con.close();
	}
}
