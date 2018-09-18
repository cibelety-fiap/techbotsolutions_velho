package br.com.fiap.speventos.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;

import com.google.gson.Gson;
import com.ibm.watson.developer_cloud.conversation.v1.Conversation;
import com.ibm.watson.developer_cloud.conversation.v1.model.Context; //Precisei importar
import com.ibm.watson.developer_cloud.conversation.v1.model.InputData;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

import br.com.fiap.speventos.beans.RespostaChatbot;
import br.com.fiap.speventos.chat.Chat;
import br.com.fiap.speventos.dao.ChatDAO;

@WebServlet(urlPatterns = "/chat")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 6093948662123015033L;

	private String dia = new String();
	private String horario = new String();
	private String nomeEntity = new String();
	private String nomeIntent = new String();
	private String tipo_filme = new String();
	private Context contexto = null;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String message = req.getParameter("question");
		if (message.isEmpty())  this.contexto = null;
		MessageResponse response = this.conversationAPI(message, contexto);

		Chat chat = new Chat();
		chat.addUserMessage(message);
		for (String text : response.getOutput().getText()) {
			chat.addBotMessage(text);
		}
		
		contexto = response.getContext();
		resp.setContentType("application/json");

		try {
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
				} else if (nomeEntity.equals("sys-time")) {
					horario = jsonArrayEntities.getJSONObject(0).getString("value");
					System.out.println(horario);
//					String teste = new Gson().toJson(response.getOutput().getText());
					/*
					String teste = "[\"<img src=\'img/incriveis2.png\' /><br />" 
										+ "<a href=\'teste.html\'>Os Incriveis</a>\"]";					
					System.out.println(teste);
					resp.getWriter().write(teste);
					return;
					*/
				}
				if(!nomeIntent.isEmpty() && !tipo_filme.isEmpty() && !dia.isEmpty() && !horario.isEmpty()) {
					System.out.println(nomeIntent + " " + tipo_filme + " " + dia + " " + horario);
					nomeIntent = "";
					tipo_filme = "";
					dia = "";
					horario = "";
					//Intent: CINEMA Tipo de filme: ANIMACAO Dia: 2018-09-19 Horário: 15:00:00
					ChatDAO dao = new ChatDAO();
					
					List<RespostaChatbot> respostas = dao.consultar("Cinema", "ANIMACAO", "2018-09-04 20:00:00");
					for(RespostaChatbot resposta : respostas) {
						System.out.println(resposta.getAll());
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.getWriter().write(new Gson().toJson(response.getOutput().getText()));
	}

	private MessageResponse conversationAPI(String input, Context context) { // Agora é do tipo Context
		Conversation service = new Conversation("2018-09-10");
		service.setUsernameAndPassword("10208ce1-5e86-4c70-80ba-ab7e6ce81ab8", "oH2zLDGISFUJ");
		/*
		 * InputData inputData = new InputData.Builder().text(input).build();
		 * MessageRequest newMessage = new MessageRequest();
		 * newMessage.setInput(inputData); String workspaceId =
		 * "b4247604-967d-424b-a1ab-a224b831dddb"; // id jah alterado MessageOptions
		 * options = new MessageOptions.Builder(workspaceId).input(inputData).build();
		 * MessageResponse response = service.message(options).execute(); return
		 * response;
		 */

		String workspaceId = "b4247604-967d-424b-a1ab-a224b831dddb"; // id jah alterado

		MessageOptions newMessageOptions = new MessageOptions.Builder().workspaceId(workspaceId)
				.input(new InputData.Builder(input).build()).context(context) // Esse metodo eh chamado antes do build,
																				// assim consegue incluir o context no
																				// obj
				.build();
		MessageResponse response = service.message(newMessageOptions).execute();
		// System.out.println(newMessageOptions);
		return response;
	}
}
