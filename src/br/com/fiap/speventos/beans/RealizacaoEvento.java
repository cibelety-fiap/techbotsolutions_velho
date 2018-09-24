package br.com.fiap.speventos.beans;

import java.sql.Date;

public class RealizacaoEvento {

	private int codigoRealizacaoEvento;
	private Evento evento;
	private Local local;
	private Date dataHoraInicio;
	private Date dataHoraTermino;

	public RealizacaoEvento() {
	}

	public RealizacaoEvento(int codigoRealizacaoEvento, Evento evento, Local local, Date dataHoraInicio,
			Date dataHoraTermino) {
		this.codigoRealizacaoEvento = codigoRealizacaoEvento;
		this.evento = evento;
		this.local = local;
		this.dataHoraInicio = dataHoraInicio;
		this.dataHoraTermino = dataHoraTermino;
	}

	public int getCodigoRealizacaoEvento() {
		return codigoRealizacaoEvento;
	}

	public void setCodigoRealizacaoEvento(int codigoRealizacaoEvento) {
		this.codigoRealizacaoEvento = codigoRealizacaoEvento;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public Date getDataHoraInicio() {
		return dataHoraInicio;
	}

	public void setDataHoraInicio(Date dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public Date getDataHoraTermino() {
		return dataHoraTermino;
	}

	public void setDataHoraTermino(Date dataHoraTermino) {
		this.dataHoraTermino = dataHoraTermino;
	}

}