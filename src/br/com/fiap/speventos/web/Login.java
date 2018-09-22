package br.com.fiap.speventos.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.speventos.beans.Colaborador;
import br.com.fiap.speventos.beans.Usuario;
import br.com.fiap.speventos.bo.ColaboradorBO;
import br.com.fiap.speventos.bo.UsuarioBO;
import br.com.fiap.speventos.excecao.Excecao;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//APAGAR
			PrintWriter writer = response.getWriter();
			
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");

			Usuario usuario = UsuarioBO.login(email, senha);

			if (usuario.getEmail() == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("loginErro.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("usuario", usuario);					
				HttpSession session = request.getSession();
				System.out.println(session.getId());
				
				Colaborador colaborador = ColaboradorBO.consultaColaboradorPorCodigo(usuario.getCodigoUsuario());	
				
				if (colaborador.getCodigoUsuario() > 0) {		
					RequestDispatcher dispatcher = request.getRequestDispatcher("tela_colaborador.jsp");
					dispatcher.forward(request, response);
					
				} else {
					

					RequestDispatcher dispatcher = request.getRequestDispatcher("tela_pessoa.jsp");
					dispatcher.forward(request, response);	
					writer.println(session.getId());
					
				} 
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(Excecao.tratarExcecao(e));
		}
	}
}
