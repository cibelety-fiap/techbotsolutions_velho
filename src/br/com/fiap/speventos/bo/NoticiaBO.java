package br.com.fiap.speventos.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.speventos.beans.Noticia;
import br.com.fiap.speventos.dao.NoticiaDAO;

public class NoticiaBO {

	public static String novaNoticia(Noticia noticia) throws Exception {

		if (noticia.getCodigoNoticia() < 1 || noticia.getCodigoNoticia() > 99999) {
			return "Código da noticia inválido";
		}
		if (noticia.getNomeNoticia().isEmpty() || noticia.getNomeNoticia().length() > 80) {
			return "Nome da noticia inválido";
		}
		if (noticia.getCategoriaNoticia().isEmpty() || noticia.getCategoriaNoticia().length() > 30) {
			return "Categoria da noticia inválida";
		}
		// Validar DATA??
		if (noticia.getNoticia().isEmpty() || noticia.getNoticia().length() > 2000) {
			return "Descrição da noticia inválida";
		}

		NoticiaDAO dao = new NoticiaDAO();

		Noticia noticiaCodRepetido = dao.consultarPorCodigo(noticia.getCodigoNoticia());
		if (noticiaCodRepetido.getCodigoNoticia() > 0) {
			return "Notícia já existe";
		}

		noticia.setNomeNoticia(noticia.getNomeNoticia().toUpperCase());
		noticia.setCategoriaNoticia(noticia.getCategoriaNoticia().toUpperCase());
		noticia.setNoticia(noticia.getNoticia().toUpperCase());
		
		int retorno = dao.cadastrar(noticia);
		dao.fechar();

		return retorno + "registro cadastrado";

	}

	public Noticia consultaNoticiaPorCodigo(int codNoticia) throws Exception {

		if (codNoticia < 1 || codNoticia > 99999) {
			return new Noticia();
		}

		NoticiaDAO dao = new NoticiaDAO();

		Noticia retorno = dao.consultarPorCodigo(codNoticia);
		dao.fechar();

		return retorno;
	}

	public List<Noticia> consultaNoticiaPorNome(String nomeNoticia) throws Exception {
		
		List<Noticia> listaNoticia = new ArrayList<Noticia>();
		
		if (nomeNoticia.isEmpty() || nomeNoticia.length() > 80 ) {
			return listaNoticia;
		}
		
		nomeNoticia = nomeNoticia.toUpperCase();

		NoticiaDAO dao = new NoticiaDAO();
		listaNoticia = dao.consultarPorNome(nomeNoticia);

		dao.fechar();

		return listaNoticia;

	}

	public String edicaoNoticia(Noticia noticia) throws Exception {

		if (noticia.getCodigoNoticia() < 1 || noticia.getCodigoNoticia() > 99999) {
			return "Codigo da noticia invalido";
		}
		if (noticia.getNomeNoticia().isEmpty() || noticia.getNomeNoticia().length() > 80) {
			return "Nome da noticia invalido";
		}
		if (noticia.getCategoriaNoticia().isEmpty() || noticia.getCategoriaNoticia().length() > 30) {
			return "Categoria da noticia invalida";
		}
		// Validar DATA??
		if (noticia.getNoticia().isEmpty() || noticia.getNoticia().length() > 2000) {
			return "Descricao da noticia invalida";
		}

		noticia.setNomeNoticia(noticia.getNomeNoticia().toUpperCase());
		noticia.setCategoriaNoticia(noticia.getCategoriaNoticia().toUpperCase());
		noticia.setNoticia(noticia.getNoticia().toUpperCase());

		NoticiaDAO dao = new NoticiaDAO();

		int retorno = dao.editar(noticia);
		dao.fechar();

		return retorno + "registro editado";
	}

	public String remocaoNoticia(int codNoticia) throws Exception {

		if (codNoticia < 1 || codNoticia > 99999) {
			return "Codigo invalido";
		}

		NoticiaDAO dao = new NoticiaDAO();

		int retorno = dao.remover(codNoticia);
		dao.fechar();

		return retorno + "registro removido";
	}

}
