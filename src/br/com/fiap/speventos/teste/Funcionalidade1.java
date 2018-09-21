package br.com.fiap.speventos.teste;

import br.com.fiap.speventos.beans.Local;
import br.com.fiap.speventos.beans.RealizacaoEvento;
import br.com.fiap.speventos.bo.EventoBO;
import br.com.fiap.speventos.bo.RealizacaoEventoBO;
import br.com.fiap.speventos.excecao.Excecao;
import br.com.fiap.speventos.view.Magica;

public class Funcionalidade1 {

	public static void main(String[] args) {

		try {

//			RealizacaoEvento realizacaoEvento = new RealizacaoEvento(
//						100,
//						new Evento(100, "img/imagem.png", "Nome do filme", "Cinema", "Terror",
//								"A freira é um filme terror que...", "email@email.com"),
//						new Local(100, "Shopping Santa Cruz", "Av. Domingo de Moraes, 1234"),
//						"20/09/2018 16:00",
//						"20/09/2018 18:00"
//					);		

			int codigoRealizEvento = Magica.inteiro("Digite o codigo da realizacao do evento");
			int codEvento = Magica.inteiro("Digite o codigo do Evento");
			RealizacaoEvento realizacaoEvento2 = new RealizacaoEvento(
					codigoRealizEvento,
					EventoBO.consultaEvento(codEvento),
					new Local(
							Magica.inteiro("Codigo do Local"), Magica.texto("Nome Local"), Magica.texto("Endereco do local")), 
					Magica.texto("Digite data/hora inicio do evento"),
					Magica.texto("Digite data/hora termino")
				);
			
			System.out.println(RealizacaoEventoBO.novaRealizacaoEvento(realizacaoEvento2));
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(Excecao.tratarExcecao(e));
		}
	}
}
