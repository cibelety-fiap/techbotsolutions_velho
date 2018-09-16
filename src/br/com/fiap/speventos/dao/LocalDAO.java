package br.com.fiap.speventos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.speventos.beans.Local;
import br.com.fiap.speventos.conexao.Conexao;

public class LocalDAO {

	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;

	public LocalDAO() throws Exception {
		con = new Conexao().conectar();
	}

	public int cadastrar(Local local) throws Exception {

		stmt = con.prepareStatement("INSERT INTO T_SGE_LOCAL " + 
				"(CD_LOCAL, NM_LOCAL, DS_ENDERECO) VALUES (?, ?, ?)");

		stmt.setInt(1, local.getCodigoLocal());
		stmt.setString(2, local.getNomeLocal());
		stmt.setString(3, local.getEnderecoLocal());

		return stmt.executeUpdate();

	}
	
	public Local consultarPorCodigo(int codLocal) throws Exception {
		
		stmt = con.prepareStatement("SELECT CD_LOCAL, NM_LOCAL, DS_ENDERECO FROM T_SGE_LOCAL "
				+ "WHERE CD_LOCAL=?");
		stmt.setInt(1, codLocal);
		
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			return new Local(
				rs.getInt("CD_LOCAL"),
				rs.getString("NM_LOCAL"),
				rs.getString("DS_ENDERECO")
			);
		}
		
		return new Local();
		
	}
	
	public List<Local> consultarPorNome(String nomeLocal) throws Exception {
		
		List<Local> listaLocal = new ArrayList<Local>();
		
		stmt = con.prepareStatement("SELECT CD_LOCAL, NM_LOCAL, DS_ENDERECO FROM T_SGE_LOCAL "
				+ "WHERE NM_LOCAL LIKE ?");
		stmt.setString(1, "%" + nomeLocal + "%");
		
		rs = stmt.executeQuery();
		
		while(rs.next()) {
			listaLocal.add(
				new Local(
					rs.getInt("CD_LOCAL"),
					rs.getString("NM_LOCAL"),
					rs.getString("DS_ENDERECO")
				)
			);
		}
		
		return listaLocal;
	}
	
	public int editar(Local local) throws Exception {
		stmt = con.prepareStatement("UPDATE T_SGE_LOCAL "
				+ "SET CD_LOCAL=?, NM_LOCAL=?, DS_ENDERECO=? "
				+ "WHERE CD_LOCAL=?");
		
		stmt.setInt(1, local.getCodigoLocal());
		stmt.setString(2, local.getNomeLocal());
		stmt.setString(3,  local.getEnderecoLocal());
		stmt.setInt(4, local.getCodigoLocal());
		
		return stmt.executeUpdate();
	}
	
	public int remover(int codLocal) throws Exception {
		stmt = con.prepareStatement("DELETE FROM T_SGE_LOCAL "
				+ "WHERE CD_LOCAL=?");
		stmt.setInt(1, codLocal);
		
		return stmt.executeUpdate();
	}

	public void fechar() throws Exception{
		con.close();
	}
}
