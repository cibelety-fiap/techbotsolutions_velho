package br.com.fiap.speventos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.speventos.beans.RespostaChatbot;
import br.com.fiap.speventos.conexao.Conexao;

public class ChatDAO {

	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;

	/**
	 * Neste metodo construtor, estabelecemos a comunicao com o banco
	 * 
	 * @author Cibele Takaoka Yamamoto
	 * @param nao
	 *            possui parametros
	 * @return nao ha retorno
	 * @throws Exception
	 *             Chamada da excecao checked SQLException
	 */

	public ChatDAO() throws Exception {
		con = new Conexao().conectar();
	}

	public List<RespostaChatbot> consultar(String intencao, String subtipo, String dataHora) throws Exception {
		List<RespostaChatbot> listaEventos = new ArrayList<RespostaChatbot>();

		int codEvento = 0;
		int codEventoAnterior = 0;
		String nomeEvento = new String();
		String linkImagem = new String();
		ArrayList<String> horariosPorLocal = new ArrayList<String>();
		ArrayList<ArrayList<String>> horariosLocalPorFilme = new ArrayList<ArrayList<String>>();
		String nomeLocal = new String();
		String nomeLocalAnterior = new String();

		stmt = con.prepareStatement("SELECT T_SGE_EVENTO.CD_EVENTO, T_SGE_EVENTO.NM_EVENTO, T_SGE_EVENTO.DS_LINK_IMAGEM, "
						+ "T_SGE_REALIZACAO_EVENTO.DT_HR_INICIO, "
						+ "T_SGE_LOCAL.NM_LOCAL FROM T_SGE_REALIZACAO_EVENTO INNER JOIN T_SGE_EVENTO ON "
						+ "(T_SGE_REALIZACAO_EVENTO.CD_EVENTO=T_SGE_EVENTO.CD_EVENTO) INNER JOIN T_SGE_LOCAL ON "
						+ "(T_SGE_REALIZACAO_EVENTO.CD_LOCAL=T_SGE_LOCAL.CD_LOCAL) "
						+ "WHERE T_SGE_EVENTO.DS_TIPO_EVENTO=? " + "AND T_SGE_EVENTO.DS_SUBTIPO_EVENTO=? "
						+ "AND DT_HR_INICIO BETWEEN (TO_DATE(?,'YYYY-MM-DD HH24:MI:SS') - interval '1' hour) "
						+ "AND (TO_DATE(?,'YYYY-MM-DD HH24:MI:SS') + interval '2' hour) "
						+ "ORDER BY T_SGE_EVENTO.CD_EVENTO, T_SGE_LOCAL.NM_LOCAL, T_SGE_REALIZACAO_EVENTO.DT_HR_INICIO");

		stmt.setString(1, intencao);
		stmt.setString(2, subtipo);
		stmt.setString(3, dataHora);
		stmt.setString(4, dataHora);
		rs = stmt.executeQuery();
		//apagar
/*		
		while (rs.next()) {
			int codEvento2 = rs.getInt("CD_EVENTO");
			String nomeEvento2 = rs.getString("NM_EVENTO");
			String linkImagem2 = rs.getString("DS_LINK_IMAGEM");
			Date horarioInicio2 = rs.getTime("DT_HR_INICIO");
			String dataFormatada = new  SimpleDateFormat("HH:mm").format(horarioInicio2);
			String nomeLocal2 = rs.getString("NM_LOCAL");
			System.out.println(codEvento2 + " " + nomeEvento2 + " " + linkImagem2 + " " +
					  nomeLocal2 + " " + dataFormatada);
		}
*/
		// TEMOS PROBLEMAS AQUI!

		while (rs.next()) {
			codEvento = rs.getInt("CD_EVENTO");
			nomeLocal = rs.getString("NM_LOCAL");
			if (codEvento != codEventoAnterior) {
				if (codEventoAnterior != 0) {
					listaEventos.add(new RespostaChatbot(nomeEvento, linkImagem, horariosLocalPorFilme));
				}
				nomeEvento = rs.getString("NM_EVENTO");
				linkImagem = rs.getString("DS_LINK_IMAGEM");
				horariosPorLocal.add(new  SimpleDateFormat("HH:mm").format(rs.getTime("DT_HR_INICIO")));
			} else {
				if (nomeLocal == nomeLocalAnterior) {
					horariosPorLocal.add(new  SimpleDateFormat("HH:mm").format(rs.getTime("DT_HR_INICIO")));
				} else {
					horariosPorLocal.add(nomeLocalAnterior);
					horariosLocalPorFilme.add(horariosPorLocal);
					horariosPorLocal.clear();
					horariosPorLocal.add(new  SimpleDateFormat("HH:mm").format(rs.getTime("DT_HR_INICIO")));
				}

			}
			codEventoAnterior = codEvento;
			nomeLocalAnterior = nomeLocal;
			System.out.println(codEvento + " " + nomeEvento + " " + linkImagem + " " +
					horariosPorLocal + " " + nomeLocal);
		}
		listaEventos.add(new RespostaChatbot(nomeEvento, linkImagem, horariosLocalPorFilme));

//APAGAR
		System.out.println(listaEventos.size() + " elementos em listaEventos");
/*		
		for (RespostaChatbot evento: listaEventos ) {
			System.out.println(evento.getNomeEvento());
			System.out.println(evento.getLinkImagem());
			System.out.println(evento.getHorariosLocalPorFilme());
		}
*/
		return listaEventos;
	}
}
