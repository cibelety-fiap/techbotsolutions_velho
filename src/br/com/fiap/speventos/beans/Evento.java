package br.com.fiap.speventos.beans;

public class Evento implements Comparable<Evento> {

	private int codigoEvento;
	private String linkImagem;
	private String nomeEvento;
	private String tipoEvento;
	private String subtipoEvento;
	private String descricaoEvento;
	private String contatoInfo;
	
	
	public int compareTo(Evento outro) {
		return this.nomeEvento.compareTo(outro.nomeEvento);
	}

	public Evento() {
		super();
	}

	public Evento(int codigoEvento, String linkImagem, String nomeEvento, String tipoEvento, String subtipoEvento,
			String descricaoEvento, String contatoInfo) {
		super();
		setCodigoEvento(codigoEvento);
		setLinkImagem(linkImagem);
		setNomeEvento(nomeEvento);
		setTipoEvento(tipoEvento);
		setSubtipoEvento(subtipoEvento);
		setDescricaoEvento(descricaoEvento);
		setContatoInfo(contatoInfo);
	}

	public String getAll() {
		return codigoEvento + "\n" + linkImagem + "\n" + nomeEvento + "\n" + tipoEvento + "\n" + subtipoEvento + "\n"
				+ descricaoEvento + "\n" + contatoInfo;
	}

	public void setAll(int codigoEvento, String linkImagem, String nomeEvento, String tipoEvento, String subtipoEvento,
			String descricaoEvento, String contatoInfo) {
		setCodigoEvento(codigoEvento);
		setLinkImagem(linkImagem);
		setNomeEvento(nomeEvento);
		setTipoEvento(tipoEvento);
		setSubtipoEvento(subtipoEvento);
		setDescricaoEvento(descricaoEvento);
		setContatoInfo(contatoInfo);
	}

	public int getCodigoEvento() {
		return codigoEvento;
	}

	public void setCodigoEvento(int codigoEvento) {
		this.codigoEvento = codigoEvento;
	}

	public String getLinkImagem() {
		return linkImagem;
	}

	public void setLinkImagem(String linkImagem) {
		this.linkImagem = linkImagem;
	}

	public String getNomeEvento() {
		return nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}

	public String getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public String getSubtipoEvento() {
		return subtipoEvento;
	}

	public void setSubtipoEvento(String subtipoEvento) {
		this.subtipoEvento = subtipoEvento;
	}

	public String getDescricaoEvento() {
		return descricaoEvento;
	}

	public void setDescricaoEvento(String descricaoEvento) {
		this.descricaoEvento = descricaoEvento;
	}

	public String getContatoInfo() {
		return contatoInfo;
	}

	public void setContatoInfo(String contatoInfo) {
		this.contatoInfo = contatoInfo;
	}

}
