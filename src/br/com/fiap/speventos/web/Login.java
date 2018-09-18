package br.com.fiap.speventos.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.speventos.beans.Usuario2;
import br.com.fiap.speventos.bo.UsuarioBO2;
import br.com.fiap.speventos.excecao.Excecao;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String email = request.getParameter("email");
			String senha = request.getParameter("senha");

			UsuarioBO2 bo = new UsuarioBO2();
			Usuario2 usuario = bo.login(email, senha);

			request.setAttribute(usuario.getEmail(), "email");
			request.setAttribute(usuario.getNome(), "nome");

			RequestDispatcher dispatcher = request.getRequestDispatcher("loginResultado.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(Excecao.tratarExcecao(e));
		}

	}

}
