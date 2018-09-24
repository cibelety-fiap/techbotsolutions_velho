package br.com.fiap.speventos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.speventos.beans.PessoaFisica;
import br.com.fiap.speventos.conexao.Conexao;

/**
 * Classe para manipular a tabela T_SGE_PESSOA_FISICA
 * Possui metodos para: cadastrar, consultarPorCodigo, consultarPorCodigoEvento, editar, remover
 * @author Techbot Solutions
 * @version 1.0
 * @since 1.0
 * @see PessoaFisica
 * @see PessoaFisicaBO
 * @see Usuario
 * @see Pessoa
 *
 */
public class PessoaFisicaDAO {

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
	public PessoaFisicaDAO() throws Exception {
		con = new Conexao().conectar();
	}

	/**
	  * Metodo para adicionar um registro na tabela T_SGE_PESSOA_FISICA
	  * @author Techbot Solutions
	  * @param pessoaFisica recebe um objeto do tipo PessoaFisica (Beans)
	  * @return um int com a quantidade de registros inseridos
	  * @throws Exception - Chamada da excecao Exception
	  */
	public String cadastrar(PessoaFisica pf) throws Exception {

		stmt = con.prepareStatement("INSERT INTO T_SGE_PESSOA_FISICA "
				+ "(NR_CPF, NR_CPF_DIGITO, NR_RG,"
				+ "NR_RG_DIGITO, DS_GENERO, DT_NASCIMENTO)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

		stmt.setString(1, "NR_CPF");
		stmt.setString(2, "NR_CPF_DIGITO");
		stmt.setString(3, "NR_RG");
		stmt.setString(4, "NR_RG_DIGITO");
		stmt.setString(5, "DS_GENERO");
		stmt.setString(6, "DT_NASCIMENTO");

		return stmt.executeQuery() + "Cadastro Realizado";
	}

	/**
	  * Metodo para consultar por codigo de usuario um registro na tabela T_SGE_USUARIO, T_SGE_PESSOA, T_SGE_PESSOA_FISICA
	  * @author Techbot Solutions
	  * @param codigoUsuario recebe um objeto do tipo int
	  * @return um objeto PessoaFisica
	  * @throws Exception - Chamada da excecao Exception
	  */
	public PessoaFisica consultarPorCodigo(int codigo) throws Exception {

		stmt = con.prepareStatement(
				"SELECT * FROM T_SGE_USUARIO "
						+ "INNER JOIN T_SGE_PESSOA ON " 
						+ "(T_SGE_USUARIO.CD_USUARIO=T_SGE_PESSOA.CD_USUARIO) " 
						+ "INNER JOIN T_SGE_PESSOA_FISICA ON "
						+ "(T_SGE_PESSOA.CD_USUARIO=T_SGE_PESSOA_FISICA.CD_USUARIO) " 
						+ "WHERE T_SGE_PESSOA_FISICA.CD_USUARIO = ?");

		stmt.setInt(1, codigo);
		rs = stmt.executeQuery();

		if(rs.next()) {
			return new PessoaFisica(
					rs.getInt("CD_USUARIO"),
					rs.getString("DS_EMAIL"),
					rs.getString("DS_SENHA"),
					rs.getString("NM_USUARIO"),
					rs.getLong("NR_TELEFONE"),
					rs.getString("DS_ENDERECO"),
					rs.getLong("NR_CPF"),
					rs.getInt("NR_CPF_DIGITO"),
					rs.getInt("NR_RG"),
					rs.getString("NR_RG_DIGITO").charAt(0),
					rs.getString("DS_GENERO").charAt(0),
					rs.getString("DT_NASCIMENTO")
					);

		} else {
			return new PessoaFisica();
		}
	}

	/**
	  * Metodo para consultar por codigo de usuario registros na tabela 
	  * T_SGE_USUARIO, T_SGE_PESSOA, T_SGE_PESSOA_FISICA
	  * @author Techbot Solutions
	  * @param codigoUsuario recebe um objeto do tipo int
	  * @return uma lista com objetos do tipo PessoaFisica
	  * @throws Exception - Chamada da excecao Exception
	  */
	public List<PessoaFisica> consultarPorNome(String nome) throws Exception {

		List<PessoaFisica> lista = new ArrayList<PessoaFisica>();

		stmt = con.prepareStatement("SELECT * FROM T_SGE_USUARIO "
				+ "INNER JOIN T_SGE_PESSOA ON " 
				+ "(T_SGE_USUARIO.CD_USUARIO=T_SGE_PESSOA.CD_USUARIO) " 
				+ "INNER JOIN T_SGE_PESSOA_FISICA ON "
				+ "(T_SGE_PESSOA.CD_USUARIO=T_SGE_PESSOA_FISICA.CD_USUARIO) " 
				+ "WHERE T_SGE_PESSOA_FISICA.NM_USUARIO LIKE ?");

		stmt.setString(1, "%" + nome + "%");

		while (rs.next()) {
			lista.add(new PessoaFisica(
					rs.getInt("CD_USUARIO"),
					rs.getString("DS_EMAIL"),
					rs.getString("DS_SENHA"),
					rs.getString("NM_USUARIO"),
					rs.getLong("NR_TELEFONE"),
					rs.getString("DS_ENDERECO"),
					rs.getLong("NR_CPF"),
					rs.getInt("NR_CPF_DIGITO"),
					rs.getInt("NR_RG"),
					rs.getString("NR_RG_DIGITO").charAt(0),
					rs.getString("DS_GENERO").charAt(0),
					rs.getString("DT_NASCIMENTO")
					));
		}
		return lista;
	}

	/**
	 * Metodo para editar um registro na tabela T_SGE_PESSOA_FISICA
	 * @author Techbot Solutions
	 * @param pessoaFisica recebe um objeto do tipo PessoaFisica
	 * @return um int com a quantidade de registros editados
	 * @throws Exception - Chamada da excecao Exception
	 */
	public int editar(PessoaFisica pessoaFisica) throws Exception {

		stmt = con.prepareStatement(
				"UPDATE T_SGE_PESSOA_FISICA SET NR_CPF=?, NR_CPF_DIGITO=?, NR_RG=?, NR_RG_DIGITO,"
						+ " DS_GENERO=?, DT_NASCIMENTO=? "
						+ "WHERE T_SGE_PESSOA_FISICA.CD_USUARIO=?");

		stmt.setLong(1, pessoaFisica.getCpf());
		stmt.setInt(2, pessoaFisica.getCpfDigito());
		stmt.setInt(3, pessoaFisica.getRg());
		stmt.setString(4, String.valueOf(pessoaFisica.getRgDigito()));
		stmt.setString(5, String.valueOf(pessoaFisica.getGenero()));
		stmt.setString(6, pessoaFisica.getDataNascimento());

		return stmt.executeUpdate();
	}

	/**
	 * Metodo para remover um registro na tabela T_SGE_PESSOA_FISICA
	 * @author Techbot Solutions
	 * @param codigoUsuario recebe um objeto do tipo int
	 * @return um int com o numero de itens removidos
	 * @throws Exception - Chamada da excecao Exception
	 */
	public int remover(int cd_usuario) throws Exception {
		stmt = con.prepareStatement("DELETE FROM T_SGE_PESSOA_FISICA "
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
