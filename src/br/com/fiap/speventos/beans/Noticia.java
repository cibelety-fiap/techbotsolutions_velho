package br.com.fiap.speventos.beans;

public class Noticia {
	private int codigoNoticia;
	private String nomeNoticia;
	private String categoriaNoticia;
	private String dataHoraNoticia;
	private String noticia;
	
	public Noticia() {
		super();
	}
	public Noticia(int codigoNoticia, String nomeNoticia, String categoriaNoticia, String dataHoraNoticia,
			String noticia) {
		this.codigoNoticia = codigoNoticia;
		this.nomeNoticia = nomeNoticia;
		this.categoriaNoticia = categoriaNoticia;
		this.dataHoraNoticia = dataHoraNoticia;
		this.noticia = noticia;
	}
	
	public int getCodigoNoticia() {
		return codigoNoticia;
	}
	public void setCodigoNoticia(int codigoNoticia) {
		this.codigoNoticia = codigoNoticia;
	}
	public String getNomeNoticia() {
		return nomeNoticia;
	}
	public void setNomeNoticia(String nomeNoticia) {
		this.nomeNoticia = nomeNoticia;
	}
	public String getCategoriaNoticia() {
		return categoriaNoticia;
	}
	public void setCategoriaNoticia(String categoriaNoticia) {
		this.categoriaNoticia = categoriaNoticia;
	}
	public String getDataHoraNoticia() {
		return dataHoraNoticia;
	}
	public void setDataHoraNoticia(String dataHoraNoticia) {
		this.dataHoraNoticia = dataHoraNoticia;
	}
	public String getNoticia() {
		return noticia;
	}
	public void setNoticia(String noticia) {
		this.noticia = noticia;
	}
	
	

}
