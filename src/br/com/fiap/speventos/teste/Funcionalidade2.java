package br.com.fiap.speventos.teste;

import br.com.fiap.speventos.beans.RealizacaoEvento;
import br.com.fiap.speventos.bo.RealizacaoEventoBO;
import br.com.fiap.speventos.excecao.Excecao;
import br.com.fiap.speventos.view.Magica;

public class Funcionalidade2 {
	
	public static void main(String[] args) {

		try {
			
			int codRealizEvento = Magica.inteiro("Codigo da realizacao evento");
			RealizacaoEvento realizEvento = RealizacaoEventoBO.consultaEventoPorCodigo(codRealizEvento);
			
			
			if(realizEvento.getCodigoRealizacaoEvento() > 0) {
				String[] dataHoraInicio = realizEvento.getDataHoraInicio().split(" ");
				String[] dataHoraTermino = realizEvento.getDataHoraTermino().split(" ");
				
				System.out.println("Cod realizacao do evento: " + realizEvento.getCodigoRealizacaoEvento() + "\n"
					+ "Cod. do evento: " + realizEvento.getEvento().getCodigoEvento() + "\n"
					+ "Nome do evento: " + realizEvento.getEvento().getNomeEvento() + "\n"
					+ "Link da imagem: " + realizEvento.getEvento().getLinkImagem() + "\n"
					+ "Cod. do local: " + realizEvento.getLocal().getCodigoLocal() + "\n"
					+ "Nome do local: " + realizEvento.getLocal().getNomeLocal() + "\n"
					+ "Endereço do local: " + realizEvento.getLocal().getEnderecoLocal() + "\n"
					+ "Data inicio: " + dataHoraInicio[0] + "\n" 
					+ "Hora inicio: " + dataHoraInicio[1] + "\n"
					+ "Data termino: " + dataHoraTermino[0] + "\n" 
					+ "Hora termino: " + dataHoraTermino[1] + "\n");
			} else {
				System.out.println("Realizacao de Evento nao encontrada");
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(Excecao.tratarExcecao(e));
		}
	}
}
