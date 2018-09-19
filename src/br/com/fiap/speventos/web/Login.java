package br.com.fiap.speventos.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.speventos.beans.Usuario;
import br.com.fiap.speventos.bo.UsuarioBO;
import br.com.fiap.speventos.excecao.Excecao;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String email = request.getParameter("email");
			String senha = request.getParameter("senha");

			UsuarioBO bo = new UsuarioBO();
			Usuario usuario = bo.login(email, senha);

			if (usuario.getEmail() == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("loginErro.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("usuario", usuario);
			
				// if(usuario.instanceOf(UsuarioPF) {				
					RequestDispatcher dispatcher = request.getRequestDispatcher("loginColaborador.jsp");
				// } else {
				//	RequestDispatcher dispatcher = request.getRequestDispatcher("loginUsuario.jsp");	
				// }
					dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(Excecao.tratarExcecao(e));
		}
	}
}
