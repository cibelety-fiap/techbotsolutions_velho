package br.com.fiap.speventos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.speventos.beans.Evento;
import br.com.fiap.speventos.conexao.Conexao;

public class EventoDAO {
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;

	public EventoDAO() throws Exception {
		con = new Conexao().conectar();
	}

	public int cadastrar(Evento evento) throws Exception {
		stmt = con.prepareStatement("INSERT INTO T_SGE_EVENTO "
				+ "(CD_EVENTO, DS_LINK_IMAGEM, NM_EVENTO, DS_TIPO_EVENTO, DS_SUBTIPO_EVENTO, DS_EVENTO, DS_CONTATO_MAIS_INFO) VALUES (?,?,?,?,?,?,?)");
		stmt.setInt(1, evento.getCodigoEvento());
		stmt.setString(2, evento.getLinkImagem());
		stmt.setString(3, evento.getNomeEvento());
		stmt.setString(4, evento.getTipoEvento());
		stmt.setString(5, evento.getSubtipoEvento());
		stmt.setString(6, evento.getDescricaoEvento());
		stmt.setString(7, evento.getContatoMaisInfo());

		return stmt.executeUpdate();
	}

	public Evento consultar(int codigoEvento) throws Exception {
		stmt = con.prepareStatement("SELECT * FROM T_SGE_EVENTO " 
				+ "WHERE CD_EVENTO=?");

		stmt.setInt(1, codigoEvento);

		rs = stmt.executeQuery();
		
		if (rs.next()) {
			return new Evento(
					rs.getInt("CD_EVENTO"), 
					rs.getString("DS_LINK_IMAGEM"), 
					rs.getString("NM_EVENTO"),
					rs.getString("DS_TIPO_EVENTO"), 
					rs.getString("DS_SUBTIPO_EVENTO"), 
					rs.getString("DS_EVENTO"),
					rs.getString("DS_CONTATO_MAIS_INFO"));
		} else {
			return new Evento();
		}

	}

	public List<Evento> consultarPorNomeEvento(String nomeEvento) throws Exception {
		List<Evento> listaEvento = new ArrayList<Evento>();
		stmt = con.prepareStatement("SELECT * FROM T_SGE_EVENTO " 
				+ "WHERE NM_EVENTO LIKE ?");

		stmt.setString(1, "%" + nomeEvento + "%");

		rs = stmt.executeQuery();

		while (rs.next()) {
			listaEvento.add(new Evento(
					rs.getInt("CD_EVENTO"), 
					rs.getString("DS_LINK_IMAGEM"),
					rs.getString("NM_EVENTO"), 
					rs.getString("DS_TIPO_EVENTO"), 
					rs.getString("DS_SUBTIPO_EVENTO"),
					rs.getString("DS_EVENTO"), 
					rs.getString("DS_CONTATO_MAIS_INFO")));
		}
		return listaEvento;
	}
	
	public List<Evento> consultarPorUsuario(int codUsuario) throws Exception {
		List<Evento> listaEvento = new ArrayList<Evento>();
		stmt = con.prepareStatement("SELECT * FROM T_SGE_ATUALIZACAO_EVENTO INNER JOIN "
				+ "T_SGE_EVENTO ON "
				+ "(T_SGE_ATUALIZACAO_EVENTO.CD_EVENTO=T_SGE_EVENTO.CD_EVENTO) "
				+ "WHERE T_SGE_ATUALIZACAO_EVENTO.CD_USUARIO=?");

		stmt.setInt(1, codUsuario);

		rs = stmt.executeQuery();

		while (rs.next()) {
			listaEvento.add(new Evento(
					rs.getInt("CD_EVENTO"), 
					rs.getString("DS_LINK_IMAGEM"),
					rs.getString("NM_EVENTO"), 
					rs.getString("DS_TIPO_EVENTO"), 
					rs.getString("DS_SUBTIPO_EVENTO"),
					rs.getString("DS_EVENTO"), 
					rs.getString("DS_CONTATO_MAIS_INFO")));
		}
		return listaEvento;
	}

	public int editar(Evento evento) throws Exception {
		stmt = con.prepareStatement("UPDATE T_SGE_EVENTO "
				+ "SET CD_EVENTO=?, DS_LINK_IMAGEM=?, NM_EVENTO=?, DS_TIPO_EVENTO=?, DS_SUBTIPO_EVENTO=?, DS_EVENTO=?, DS_CONTATO_MAIS_INFO=? "
				+ "WHERE CD_EVENTO=?");
		stmt.setInt(1, evento.getCodigoEvento());
		stmt.setString(2, evento.getLinkImagem());
		stmt.setString(3, evento.getNomeEvento());
		stmt.setString(4, evento.getTipoEvento());
		stmt.setString(5, evento.getSubtipoEvento());
		stmt.setString(6, evento.getDescricaoEvento());
		stmt.setString(7, evento.getContatoMaisInfo());
		
		return stmt.executeUpdate();
	}

	public int remover(int codigoEvento) throws Exception {
		stmt = con.prepareStatement("DELETE FROM T_SGE_EVENTO " + 
				"WHERE CD_EVENTO=?");

		stmt.setInt(1, codigoEvento);

		return stmt.executeUpdate();
	}

	public void fechar() throws Exception {
		con.close();
	}
}