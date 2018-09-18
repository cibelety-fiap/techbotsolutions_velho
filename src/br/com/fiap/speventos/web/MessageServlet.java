package br.com.fiap.speventos.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.google.gson.Gson;
import com.ibm.watson.developer_cloud.conversation.v1.Conversation;
import com.ibm.watson.developer_cloud.conversation.v1.model.Context; //Precisei importar
import com.ibm.watson.developer_cloud.conversation.v1.model.InputData;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

import br.com.fiap.speventos.beans.RespostaChatbot;
import br.com.fiap.speventos.chat.Chat;
import br.com.fiap.speventos.dao.ChatDAO;
import br.com.fiap.speventos.excecao.Excecao;

@WebServlet(urlPatterns = "/chat")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 6093948662123015033L;

	private String dia = new String();
	private String horario = new String();
	private String nomeEntity = new String();
	private String nomeIntent = new String();
	private String tipo_filme = new String();
	private String respostaParaChatbot = new String();
	private Context contexto = null;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String message = req.getParameter("question");
		if (message.isEmpty()) {
			contexto = null;
			nomeIntent = "";
			tipo_filme = "";
			dia = "";
			horario = "";
		}
		MessageResponse response = this.conversationAPI(message, contexto);

		Chat chat = new Chat();
		chat.addUserMessage(message);
		for (String text : response.getOutput().getText()) {
			chat.addBotMessage(text);
		}
		contexto = response.getContext();
		resp.setContentType("application/json");
		
		JSONArray jsonArrayIntents = new JSONArray(response.getIntents());
		System.out.println(jsonArrayIntents);

		JSONArray jsonArrayEntities = new JSONArray(response.getEntities());
		System.out.println(jsonArrayEntities);

		if (!jsonArrayIntents.isEmpty()) {
			nomeIntent = jsonArrayIntents.getJSONObject(0).getString("intent").toUpperCase();
		}
		
		if (!jsonArrayEntities.isEmpty()) {
			nomeEntity = jsonArrayEntities.getJSONObject(0).getString("entity");
			if (nomeEntity.equals("tipo-filme")) {
				tipo_filme = jsonArrayEntities.getJSONObject(0).getString("value").toUpperCase();
			} 
			if (nomeEntity.equals("sys-date")) {
				dia = jsonArrayEntities.getJSONObject(0).getString("value");
				System.out.println(dia);
			} 
			if (nomeEntity.equals("sys-time")) {
				horario = jsonArrayEntities.getJSONObject(0).getString("value");
				System.out.println(horario);
			}
		}

		if (nomeIntent.equals("CINEMA") && !tipo_filme.isEmpty() && !dia.isEmpty() && !horario.isEmpty()) {

			try {
				ChatDAO dao = new ChatDAO();
				
				System.out.println(nomeIntent + " " + tipo_filme + " " + dia + " " + horario);
				List<RespostaChatbot> respostas = dao.consultar(nomeIntent, tipo_filme, dia + " " + horario);
				for (RespostaChatbot resposta : respostas) {
					System.out.println(resposta.getAll());
				}
				respostaParaChatbot = "[\"<img src=\'img/incriveis2.png\' /><br />"
						+ "<a href=\'teste.html\'>Os Incriveis</a>\"]";
			} catch (Exception e) {
				// e.printStackTrace();
				System.out.println(Excecao.tratarExcecao(e));
			} finally {
				nomeIntent = "";
				tipo_filme = "";
				dia = "";
				horario = "";
			}
		} else {
			respostaParaChatbot = new Gson().toJson(response.getOutput().getText());
		}

		resp.getWriter().write(respostaParaChatbot);

	}

	private MessageResponse conversationAPI(String input, Context context) {

		Conversation service = new Conversation("2018-09-10");
		service.setUsernameAndPassword("10208ce1-5e86-4c70-80ba-ab7e6ce81ab8", "oH2zLDGISFUJ");
		String workspaceId = "b4247604-967d-424b-a1ab-a224b831dddb";

		MessageOptions newMessageOptions = new MessageOptions.Builder().workspaceId(workspaceId)
				.input(new InputData.Builder(input).build()).context(context).build();
		//Imprimir para visualizar o que esta sendo enviado para o chatbot tratar e responder
		//System.out.println(newMessageOptions);
		
		MessageResponse response = service.message(newMessageOptions).execute();

		return response;
	}
}
