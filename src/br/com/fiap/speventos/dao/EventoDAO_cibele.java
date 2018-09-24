package br.com.fiap.speventos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.speventos.beans.Evento;
import br.com.fiap.speventos.beans.Evento_cibele;
import br.com.fiap.speventos.conexao.Conexao;

/**
 * Classe para manipular a tabela T_SGE_EVENTO
 * Possui metodos para: cadastrar, consultarPorCodigo, consultarPorNomeEvento, 
 * consultarPorUsuario, editar, remover
 * @author Techbot Solutions
 * @version 1.0
 * @since 1.0
 * @see Evento
 * @see EventoBO
 *
 */
public class EventoDAO_cibele {
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
	public EventoDAO_cibele() throws Exception {
		con = new Conexao().conectar();
	}

	/**
	  * Metodo para adicionar um registro na tabela T_SGE_EVENTO
	  * @author Techbot Solutions
	  * @param evento recebe um objeto do tipo Evento (Beans)
	  * @return um int com a quantidade de registros inseridos
	  * @throws Exception - Chamada da excecao Exception
	  */
	public int cadastrar(Evento_cibele evento) throws Exception {
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

	/**
	  * Metodo para consultar por codigo do evento um registro na tabela T_SGE_EVENTO
	  * @author Techbot Solutions
	  * @param codigoEvento recebe um objeto do tipo int
	  * @return um objeto Evento
	  * @throws Exception - Chamada da excecao Exception
	  */
	public Evento_cibele consultar(int codigoEvento) throws Exception {
		stmt = con.prepareStatement("SELECT * FROM T_SGE_EVENTO " 
				+ "WHERE CD_EVENTO=?");

		stmt.setInt(1, codigoEvento);

		rs = stmt.executeQuery();
		
		if (rs.next()) {
			return new Evento_cibele(
					rs.getInt("CD_EVENTO"), 
					rs.getString("DS_LINK_IMAGEM"), 
					rs.getString("NM_EVENTO"),
					rs.getString("DS_TIPO_EVENTO"), 
					rs.getString("DS_SUBTIPO_EVENTO"), 
					rs.getString("DS_EVENTO"),
					rs.getString("DS_CONTATO_MAIS_INFO"));
		} else {
			return new Evento_cibele();
		}

	}

	/**
	  * Metodo para consultar por nome um registro na tabela T_SGE_EVENTO
	  * @author Techbot Solutions
	  * @param nomeEvento recebe um objeto do tipo String
	  * @return uma lista com objetos do tipo Evento
	  * @throws Exception - Chamada da excecao Exception
	  */
	public List<Evento_cibele> consultarPorNomeEvento(String nomeEvento) throws Exception {
		List<Evento_cibele> listaEvento = new ArrayList<Evento_cibele>();
		stmt = con.prepareStatement("SELECT * FROM T_SGE_EVENTO " 
				+ "WHERE NM_EVENTO LIKE ?");

		stmt.setString(1, "%" + nomeEvento + "%");

		rs = stmt.executeQuery();

		while (rs.next()) {
			listaEvento.add(new Evento_cibele(
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
	
	public List<Evento_cibele> consultarPorUsuario(int codUsuario) throws Exception {
		List<Evento_cibele> listaEvento = new ArrayList<Evento_cibele>();
		stmt = con.prepareStatement("SELECT * FROM T_SGE_ATUALIZACAO_EVENTO INNER JOIN "
				+ "T_SGE_EVENTO ON "
				+ "(T_SGE_ATUALIZACAO_EVENTO.CD_EVENTO=T_SGE_EVENTO.CD_EVENTO) "
				+ "WHERE T_SGE_ATUALIZACAO_EVENTO.CD_USUARIO=?");

		stmt.setInt(1, codUsuario);

		rs = stmt.executeQuery();

		while (rs.next()) {
			listaEvento.add(new Evento_cibele(
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
	/**
	  * Metodo para editar um registro na tabela T_SGE_EVENTO
	  * @author Techbot Solutions
	  * @param evento recebe um objeto do tipo Evento
	  * @return um int com a quantidade de registros editados
	  * @throws Exception - Chamada da excecao Exception
	  */
	public int editar(Evento_cibele evento) throws Exception {
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
	/**
	  * Metodo para remover um registro na tabela T_SGE_EVENTO
	  * @author Techbot Solutions
	  * @param codigoEvento recebe um objeto do tipo int
	  * @return um int com o numero do item removido
	  * @throws Exception - Chamada da excecao Exception
	  */
	public int remover(int codigoEvento) throws Exception {
		stmt = con.prepareStatement("DELETE FROM T_SGE_EVENTO " + 
				"WHERE CD_EVENTO=?");

		stmt.setInt(1, codigoEvento);

		return stmt.executeUpdate();
	}

	/**
	  * Método construtor que fecha a comunicacao com o banco de dados
	  * @author Techbot Solutions
	  * @param nao possui parametros
	  * @return nao ha retorno
	  * @throws Exception - Chamada da excecao checked SQLException
	  */
	public void fechar() throws Exception {
		con.close();
	}
}