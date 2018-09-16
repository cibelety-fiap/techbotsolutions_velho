package br.com.fiap.speventos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.speventos.beans.Evento;
import br.com.fiap.speventos.beans.Local;
import br.com.fiap.speventos.beans.RealizacaoEvento;
import br.com.fiap.speventos.conexao.Conexao;

/**
 * Esta classe manipula a tabela T_SGE_REALIZACAO_EVENTO 
 * possui metodos para: cadastrar, consultarPorCodigo, consultarPorNomeEvento, editar, remover
 * @author Techbot Solutions
 * @version 1.0
 * @since 1.0
 * @see RealizacaoEventoBO
 * @see RealizacaoEventoBeans
 */

/**
 * Neste m�todo construtor, estabelecemos a comunica��o com o banco
 * @author Cibele Takaoka Yamamoto
 * @param n�o possui par�metros
 * @return n�o h� retorno
 * @throws Exception Chamada da exce��o checked SQLException
 */

public class RealizacaoEventoDAO {

	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;

	public RealizacaoEventoDAO() throws Exception {
		con = new Conexao().conectar();
	}

	
	/**
	 * Este m�todo respons�vel por adicionar uma linha na tabela T_DDD_CLIENTE
	 * @param cli Recebe um objeto do tipo Cliente Beans
	 * @return Uma string com a mensagem de confirma��o
	 * @throws Exception Chamada da exce��o checked
	 * @author Cibele Takaoka Yamamoto
	 */
	public String cadastrar(RealizacaoEvento realizacaoEvento) throws Exception {
		stmt = con.prepareStatement("INSERT INTO T_SGE_REALIZACAO_EVENTO "
				+ "(CD_REALIZ_EVENTO, CD_EVENTO, CD_LOCAL, DT_HR_INICIO, DT_HR_TERMINO " + "VALUES (?,?,?,?,?)");

		stmt.setInt(1, realizacaoEvento.getCodigoRealizacaoEvento());
		stmt.setInt(2, realizacaoEvento.getEvento().getCodigoEvento());
		stmt.setInt(3, realizacaoEvento.getLocal().getCodigoLocal());
		stmt.setDate(4, realizacaoEvento.getDataHoraInicio());
		stmt.setDate(5, realizacaoEvento.getDataHoraTermino());

		stmt.executeUpdate();

		return "Cadastrado com sucesso!";
	}

	public RealizacaoEvento consultarPorCodigo(int codRealizEvento) throws Exception {
		stmt = con.prepareStatement("SELECT * FROM T_SGE_REALIZACAO_EVENTO " + "INNER JOIN T_SGE_EVENTO ON "
				+ "(T_SGE_REALIZACAO_EVENTO.CD_EVENTO=T_SGE_EVENTO.CD_EVENTO) " + "INNER JOIN T_SGE_LOCAL ON "
				+ "(T_SGE_REALIZACAO_EVENTO.CD_LOCAL=T_SGE_LOCAL.CD_LOCAL) " + "WHERE CD_REALIZACAO_EVENTO=?");

		stmt.setInt(1, codRealizEvento);

		rs = stmt.executeQuery();

		if (rs.next()) {
			return new RealizacaoEvento(rs.getInt("CD_REALIZ_EVENTO"),
					new Evento(rs.getInt("CD_EVENTO"), rs.getString("DS_LINK_IMAGEM"), rs.getString("NM_EVENTO"),
							rs.getString("DS_TIPO_EVENTO"), rs.getString("DS_SUBTIPO_EVENTO"),
							rs.getString("DS_EVENTO"), rs.getString("DS_CONTATO_MAIS_INFO")),
					new Local(rs.getInt("CD_LOCAL"), rs.getString("NM_LOCAL"), rs.getString("DS_ENDERECO")),
					rs.getDate("DT_HR_INICIO"), rs.getDate("DT_HR_TERMINO"));
		} else {
			return new RealizacaoEvento();
		}
	}

	public List<RealizacaoEvento> consultarPorNomeEvento(String nomeEvento) throws Exception {
		List<RealizacaoEvento> listaRealizEvento = new ArrayList<RealizacaoEvento>();
		stmt = con.prepareStatement("SELECT * FROM T_SGE_REALIZACAO_EVENTO " 
				+ "INNER JOIN T_SGE_EVENTO ON "
				+ "(T_SGE_REALIZACAO_EVENTO.CD_EVENTO=T_SGE_EVENTO.CD_EVENTO) " 
				+ "INNER JOIN T_SGE_LOCAL ON "
				+ "(T_SGE_REALIZACAO_EVENTO.CD_LOCAL=T_SGE_LOCAL.CD_LOCAL) " 
				+ "WHERE NM_EVENTO LIKE ?");

		stmt.setString(1, "%" + nomeEvento + "%");

		rs = stmt.executeQuery();

		while (rs.next()) {

			listaRealizEvento.add(new RealizacaoEvento(rs.getInt("CD_REALIZ_EVENTO"),
					new Evento(rs.getInt("CD_EVENTO"), rs.getString("DS_LINK_IMAGEM"), rs.getString("NM_EVENTO"),
							rs.getString("DS_TIPO_EVENTO"), rs.getString("DS_SUBTIPO_EVENTO"),
							rs.getString("DS_EVENTO"), rs.getString("DS_CONTATO_MAIS_INFO")),
					new Local(rs.getInt("CD_LOCAL"), rs.getString("NM_LOCAL"), rs.getString("DS_ENDERECO")),
					rs.getDate("DT_HR_INICIO"), rs.getDate("DT_HR_TERMINO")));
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
		stmt.setDate(4, realizacaoEvento.getDataHoraInicio());
		stmt.setDate(5, realizacaoEvento.getDataHoraTermino());
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
