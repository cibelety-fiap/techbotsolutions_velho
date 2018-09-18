package br.com.fiap.speventos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.fiap.speventos.beans.Usuario2;
import br.com.fiap.speventos.conexao.Conexao;

public class UsuarioDAO2 {
	
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	public UsuarioDAO2() throws Exception {
		con = new Conexao().conectar();
	}
	
	public Usuario2 logar(String email, String senha) throws Exception {
		stmt = con.prepareStatement("SELECT DS_EMAIL, DS_SENHA, NM_PESSOA FROM T_SGE_USUARIO "
				+ "WHERE DS_EMAIL=? AND DS_SENHA=?");
		
		stmt.setString(1, email);
		stmt.setString(2, senha);
		
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			return new Usuario2(rs.getString("DS_EMAIL"),
					rs.getString("DS_SENHA"),
					rs.getString("NM_PESSOA")
					);
		}
		
		return new Usuario2();
	}
	
	public int fechar() throws Exception {
		con.close();
		return 1; 
	}

}
