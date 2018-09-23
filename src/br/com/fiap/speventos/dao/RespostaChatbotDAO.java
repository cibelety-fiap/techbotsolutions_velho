package br.com.fiap.speventos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.speventos.beans.RespostaChatbot;
import br.com.fiap.speventos.conexao.Conexao;

public class RespostaChatbotDAO {

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

	public RespostaChatbotDAO() throws Exception {
		con = new Conexao().conectar();
	}

	public List<RespostaChatbot> consultar(String intencao, String subtipo, String dataHora) throws Exception {
		ArrayList<RespostaChatbot> listaEventos = new ArrayList<RespostaChatbot>();

		int codEvento = 0;
		int codEventoAnterior = 0;
		String nomeEvento = new String();
		String nomeEventoAnterior = new String();
		String linkImagem = new String();
		String linkImagemAnterior = new String();
		String horarioInicio = new String();
		String horariosLocalPorFilme = new String();
		ArrayList<String> listaHorariosLocalPorFilme = new ArrayList<String>();
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
			nomeEvento = rs.getString("NM_EVENTO");
			linkImagem = rs.getString("DS_LINK_IMAGEM");
			horarioInicio = new SimpleDateFormat("HH:mm").format(rs.getTime("DT_HR_INICIO"));
			nomeLocal = rs.getString("NM_LOCAL");
			
			if (codEventoAnterior!=0 && codEvento!=codEventoAnterior) {
				horariosLocalPorFilme = horariosLocalPorFilme + " " + nomeLocalAnterior;
				listaHorariosLocalPorFilme.add(horariosLocalPorFilme);
				RespostaChatbot respChatbot = 
						new RespostaChatbot(nomeEventoAnterior, linkImagemAnterior, listaHorariosLocalPorFilme);
				listaEventos.add(respChatbot);
			} else {
				if ((nomeLocal.equals(nomeLocalAnterior)) && (!nomeLocalAnterior.isEmpty())) {
					horariosLocalPorFilme = horariosLocalPorFilme + " " 
							+ horarioInicio;
				} else {
					horariosLocalPorFilme = horariosLocalPorFilme + " " + nomeLocalAnterior;
					listaHorariosLocalPorFilme.add(horariosLocalPorFilme);
					horariosLocalPorFilme = "";
					horariosLocalPorFilme = horarioInicio;
				}

			}
			codEventoAnterior = codEvento;
			nomeLocalAnterior = nomeLocal;
			nomeEventoAnterior = nomeEvento;
			linkImagemAnterior = linkImagem;
		}
		horariosLocalPorFilme = horariosLocalPorFilme + " " + nomeLocalAnterior;
		listaHorariosLocalPorFilme.add(horariosLocalPorFilme);
		RespostaChatbot respChatbot = 
				new RespostaChatbot(nomeEventoAnterior, linkImagemAnterior, listaHorariosLocalPorFilme);
		listaEventos.add(respChatbot);

		System.out.println(listaEventos.size() + " elementos em listaEventos");

		return listaEventos;
	}
	
//	public String retornarResposta(String nomeIntent, String tipo_filme, String dataHora) throws Exception {
//		
//		List<RespostaChatbot> listaResposta = new ArrayList<RespostaChatbot>();
//		listaResposta = this.consultar(nomeIntent, tipo_filme, dataHora);
//		
//		for (RespostaChatbot respostaTemp : listaResposta) {
//			String linkImagem = respostaTemp.getLinkImagem();
//			String nomeEvento = respostaTemp.getNomeEvento();
//			String resposta = "[\"<img src=\'" + linkImagem +"\' /><br />"
//					+ "<a href=\'" + nomeEvento + "\'>" + nomeEvento + "</a>";
//
//			List<String> listaHorariosLocalPorFilme = respostaTemp.getHorariosLocalPorFilme();
//			for(String horariosPorLocalFilmeTemp : listaHorariosLocalPorFilme) {
//				resposta = resposta + horariosPorLocalFilmeTemp + "<br />";
//			}
//			resposta = resposta + "\"]";
//			
//			System.out.println(resposta);
//			return resposta;
//		}
//	}
}
