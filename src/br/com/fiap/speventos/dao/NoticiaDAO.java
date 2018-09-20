package br.com.fiap.speventos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.speventos.beans.Noticia;
import br.com.fiap.speventos.conexao.Conexao;

public class NoticiaDAO {

		private Connection con;
		private PreparedStatement stmt;
		private ResultSet rs;

		public NoticiaDAO() throws Exception {
			con = new Conexao().conectar();
		}
		
		public int cadastrar(Noticia noticia)throws Exception{
			stmt = con.prepareStatement("INSERT INTO T_SGE_NOTICIA " + 
					"(CD_NOTICIA, NM_NOTICIA, DS_CATEGORIA_NOTICIA, DT_HR_NOTICIA, DS_NOTICIA) VALUES (?, ?, ?, ?, ?)");

			stmt.setInt(1, noticia.getCodigoNoticia());
			stmt.setString(2, noticia.getNomeNoticia());
			stmt.setString(3, noticia.getCategoriaNoticia());
			stmt.setString(4, noticia.getDataHoraNoticia());
			stmt.setString(5, noticia.getNoticia());
			

			return stmt.executeUpdate();
			
		}
		
		public Noticia consultarPorCodigo(int codNoticia) throws Exception{
			stmt = con.prepareStatement("SELECT CD_NOTICIA, NM_NOTICIA, DS_CATEGORIA_NOTICIA, DT_HR_NOTICIA, DS_NOTICIA FROM T_SGE_NOTICIA "
					+ "WHERE CD_NOTICIA=?");
			
			stmt.setInt(1, codNoticia);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				return new Noticia(
					rs.getInt("CD_NOTICIA"),
					rs.getString("NM_NOTICIA"),
					rs.getString("DS_CATEGORIA_NOTICIA"),
					rs.getString("DT_HR_NOTICIA"),
					rs.getString("DS_NOTICIA")
				);
			}
			return new Noticia();
			
			
		}
		
		public List<Noticia> consultarPorNome(String nomeNoticia) throws Exception {
			
			List<Noticia> listaNoticia = new ArrayList<Noticia>();
			
			stmt = con.prepareStatement("SELECT CD_NOTICIA, NM_NOTICIA, DS_CATEGORIA_NOTICIA, DT_HR_NOTICIA, DS_NOTICIA FROM T_SGE_NOTICIA "
					+ "WHERE NM_NOTICIA LIKE ?");
			stmt.setString(1, "%" + nomeNoticia + "%");
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				listaNoticia.add(
					new Noticia(
						rs.getInt("CD_NOTICIA"),
						rs.getString("NM_NOTICIA"),
						rs.getString("DS_CATEGORIA_NOTICIA"),
						rs.getString("DT_HR_NOTICIA"),
						rs.getString("DS_NOTICIA")
					)
				);
			}
			
			return listaNoticia;
		}
		
		public int editar(Noticia noticia) throws Exception {
			stmt = con.prepareStatement("UPDATE T_SGE_NOTICIA "
					+ "SET CD_NOTICIA=?, NM_NOTICIA=?, DS_CATEGORIA_NOTICIA=?, DT_HR_NOTICIA=?, DS_NOTICIA=? "
					+ "WHERE CD_NOTICIA=?");
			
			stmt.setInt(1, noticia.getCodigoNoticia());
			stmt.setString(2, noticia.getNomeNoticia());
			stmt.setString(3,  noticia.getCategoriaNoticia());
			stmt.setString(4, noticia.getDataHoraNoticia());
			stmt.setString(5, noticia.getNoticia());
			stmt.setInt(6, noticia.getCodigoNoticia());
			
			return stmt.executeUpdate();
		}
		
		public int remover(int codNoticia)throws Exception{
			stmt = con.prepareStatement("DELETE FROM T_SGE_NOTICIA"
					+ "WHERE CD_NOTICIA=?");
			
			return stmt.executeUpdate();
		}
		
		public void fechar() throws Exception{
			con.close();
		}		

}
