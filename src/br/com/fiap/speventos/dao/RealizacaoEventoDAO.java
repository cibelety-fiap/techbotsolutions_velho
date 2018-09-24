package br.com.fiap.speventos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.speventos.beans.Evento_cibele;
import br.com.fiap.speventos.beans.Local;
import br.com.fiap.speventos.beans.RealizacaoEvento;
import br.com.fiap.speventos.conexao.Conexao;

/**
 * Classe para manipular a tabela T_SGE_REALIZACAO_EVENTO 
 * Possui métodos para: cadastrar, consultarPorCodigo, consultarPorNomeEvento, editar, remover
 * @author Techbot Solutions
 * @version 1.0
 * @since 1.0
 * @see RealizacaoEventoBO
 * @see RealizacaoEvento
 */

public class RealizacaoEventoDAO {

	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;

	/**
	 * Método construtor que estabelece a comunicação com o banco de dados
	 * @author Techbot Solutions
	 * @param não possui parâmetros
	 * @return não há retorno
	 * @throws Exception - Chamada da exceção checked SQLException
	 */
	public RealizacaoEventoDAO() throws Exception {
		con = new Conexao().conectar();
	}

	/**
	 * Método para adicionar um registro na tabela T_DDD_CLIENTE
	 * @author Techbot Solutions
	 * @param realizacaoEvento recebe um objeto do tipo RealizacaoEvento (Beans)
	 * @return um int com a quantidade de registros inseridos
	 * @throws Exception Chamada da exceção Exception (completar??)
	 */
	public int cadastrar(RealizacaoEvento realizacaoEvento) throws Exception {
		stmt = con.prepareStatement("INSERT INTO T_SGE_REALIZACAO_EVENTO "
				+ "(CD_REALIZ_EVENTO, CD_EVENTO, CD_LOCAL, DT_HR_INICIO, DT_HR_TERMINO) " 
				+ "VALUES (?,?,?,TO_DATE(?,\'DD/MM/YYYY HH24:MI\'), TO_DATE(?,\'DD/MM/YYYY HH24:MI\'))");

		stmt.setInt(1, realizacaoEvento.getCodigoRealizacaoEvento());
		stmt.setInt(2, realizacaoEvento.getEvento().getCodigoEvento());
		stmt.setInt(3, realizacaoEvento.getLocal().getCodigoLocal());
		stmt.setString(4, realizacaoEvento.getDataHoraInicio());
		stmt.setString(5, realizacaoEvento.getDataHoraTermino());

		

		return stmt.executeUpdate();
	}

	public RealizacaoEvento consultarPorCodigo(int codRealizEvento) throws Exception {
		stmt = con.prepareStatement("SELECT T_SGE_REALIZACAO_EVENTO.CD_REALIZ_EVENTO, "
				+ "T_SGE_REALIZACAO_EVENTO.CD_EVENTO, T_SGE_REALIZACAO_EVENTO.CD_LOCAL, "
				+ "TO_CHAR(T_SGE_REALIZACAO_EVENTO.DT_HR_INICIO,'DD/MM/YYYY HH24:MI') \"DT_HR_INICIO\", "
				+ "TO_CHAR(T_SGE_REALIZACAO_EVENTO.DT_HR_TERMINO,'DD/MM/YYYY HH24:MI') \"DT_HR_TERMINO\", "
				+ "T_SGE_EVENTO.DS_LINK_IMAGEM, T_SGE_EVENTO.NM_EVENTO, T_SGE_EVENTO.DS_TIPO_EVENTO, "
				+ "T_SGE_EVENTO.DS_SUBTIPO_EVENTO, T_SGE_EVENTO.DS_EVENTO, " 
				+ "T_SGE_EVENTO.DS_CONTATO_MAIS_INFO, T_SGE_LOCAL.NM_LOCAL, T_SGE_LOCAL.DS_ENDERECO "
				+ "FROM T_SGE_REALIZACAO_EVENTO " + "INNER JOIN T_SGE_EVENTO ON "
				+ "(T_SGE_REALIZACAO_EVENTO.CD_EVENTO=T_SGE_EVENTO.CD_EVENTO) " + "INNER JOIN T_SGE_LOCAL ON "
				+ "(T_SGE_REALIZACAO_EVENTO.CD_LOCAL=T_SGE_LOCAL.CD_LOCAL) " + "WHERE CD_REALIZ_EVENTO=?");

		stmt.setInt(1, codRealizEvento);

		rs = stmt.executeQuery();

		if (rs.next()) {
			return new RealizacaoEvento(rs.getInt("CD_REALIZ_EVENTO"),
					new Evento_cibele(rs.getInt("CD_EVENTO"), rs.getString("DS_LINK_IMAGEM"), rs.getString("NM_EVENTO"),
							rs.getString("DS_TIPO_EVENTO"), rs.getString("DS_SUBTIPO_EVENTO"),
							rs.getString("DS_EVENTO"), rs.getString("DS_CONTATO_MAIS_INFO")),
					new Local(rs.getInt("CD_LOCAL"), rs.getString("NM_LOCAL"), rs.getString("DS_ENDERECO")),
					rs.getString("DT_HR_INICIO"), rs.getString("DT_HR_TERMINO"));
		} else {
			return new RealizacaoEvento();
		}
	}

	public List<RealizacaoEvento> consultarPorNomeEvento(String nomeEvento) throws Exception {
		List<RealizacaoEvento> listaRealizEvento = new ArrayList<RealizacaoEvento>();
		stmt = con.prepareStatement("SELECT T_SGE_REALIZACAO_EVENTO.CD_REALIZ_EVENTO, "
								+ "T_SGE_REALIZACAO_EVENTO.CD_EVENTO, T_SGE_REALIZACAO_EVENTO.CD_LOCAL, " 
								+ "TO_CHAR(T_SGE_REALIZACAO_EVENTO.DT_HR_INICIO,'DD/MM/YYYY HH24:MI') \"DT_HR_INICIO\", "  
								+ "TO_CHAR(T_SGE_REALIZACAO_EVENTO.DT_HR_TERMINO,'DD/MM/YYYY HH24:MI') \"DT_HR_TERMINO\", "  
								+ "T_SGE_EVENTO.DS_LINK_IMAGEM, T_SGE_EVENTO.NM_EVENTO, T_SGE_EVENTO.DS_TIPO_EVENTO, " 
								+ "T_SGE_EVENTO.DS_SUBTIPO_EVENTO, T_SGE_EVENTO.DS_EVENTO, " 
								+ "T_SGE_EVENTO.DS_CONTATO_MAIS_INFO, T_SGE_LOCAL.NM_LOCAL, T_SGE_LOCAL.DS_ENDERECO "
				+ "FROM T_SGE_REALIZACAO_EVENTO " 
				+ "INNER JOIN T_SGE_EVENTO ON "
				+ "(T_SGE_REALIZACAO_EVENTO.CD_EVENTO=T_SGE_EVENTO.CD_EVENTO) " 
				+ "INNER JOIN T_SGE_LOCAL ON "
				+ "(T_SGE_REALIZACAO_EVENTO.CD_LOCAL=T_SGE_LOCAL.CD_LOCAL) " 
				+ "WHERE T_SGE_EVENTO.NM_EVENTO LIKE ?");

		stmt.setString(1, "%" + nomeEvento + "%");

		rs = stmt.executeQuery();

		while (rs.next()) {
			listaRealizEvento.add(new RealizacaoEvento(rs.getInt("CD_REALIZ_EVENTO"),
					new Evento_cibele(rs.getInt("CD_EVENTO"), rs.getString("DS_LINK_IMAGEM"), rs.getString("NM_EVENTO"),
							rs.getString("DS_TIPO_EVENTO"), rs.getString("DS_SUBTIPO_EVENTO"),
							rs.getString("DS_EVENTO"), rs.getString("DS_CONTATO_MAIS_INFO")),
					new Local(rs.getInt("CD_LOCAL"), rs.getString("NM_LOCAL"), rs.getString("DS_ENDERECO")),
					rs.getString("DT_HR_INICIO"), rs.getString("DT_HR_TERMINO")));
		}

		return listaRealizEvento;
	}


	public int editar(RealizacaoEvento realizacaoEvento) throws Exception {
		//REVER QUERY
		stmt = con.prepareStatement("UPDATE T_SGE_REALIZACAO_EVENTO "
				+ "SET CD_REALIZ_EVENTO=?, CD_EVENTO=?, CD_LOCAL=?, "
				+ "DT_HR_INICIO =?, DT_HR_TERMINO=? " 
				+ "WHERE CD_REALIZ_EVENTO = ?");

		stmt.setInt(1, realizacaoEvento.getCodigoRealizacaoEvento());
		stmt.setInt(2, realizacaoEvento.getEvento().getCodigoEvento());
		stmt.setInt(3, realizacaoEvento.getLocal().getCodigoLocal());
		stmt.setString(4, realizacaoEvento.getDataHoraInicio());
		stmt.setString(5, realizacaoEvento.getDataHoraTermino());
		stmt.setInt(6, realizacaoEvento.getCodigoRealizacaoEvento());

		return stmt.executeUpdate();
	}

	public int remover(int codRealizEvento) throws Exception {

		stmt = con.prepareStatement("DELETE FROM T_SGE_REALIZACAO_EVENTO " 
				+ "WHERE CD_REALIZ_EVENTO=?");
		stmt.setInt(1, codRealizEvento);

		return stmt.executeUpdate();
	}
	
	public void fechar() throws Exception{
		con.close();
	}
}
