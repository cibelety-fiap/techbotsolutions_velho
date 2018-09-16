package br.com.fiap.speventos.web;

import java.io.IOException;

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

import br.com.fiap.speventos.chat.Chat;

@WebServlet(urlPatterns = "/chat")
public class MessageServlet extends HttpServlet {

	private static final long serialVersionUID = 6093948662123015033L;
	private String dia = new String();
	private String horario = new String();
	private String tipoInfo = new String();
	private Context contexto = null; //Não é mais um Map
	
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
			JSONArray jsonArray = new JSONArray(response.getEntities());
			if (!jsonArray.isEmpty()) {
				tipoInfo = jsonArray.getJSONObject(0).getString("entity");

				if (tipoInfo.equals("sys-date")) {
					dia = jsonArray.getJSONObject(0).getString("value");
					System.out.println(dia);
				} else if (tipoInfo.equals("sys-time")) {
					horario = jsonArray.getJSONObject(0).getString("value");
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
				if(!dia.isEmpty() && !horario.isEmpty()) {
					System.out.println("Dia: " + dia + " Horário: " + horario);
				}
			}

		} catch (

		JSONException e) {
			e.printStackTrace();
		}

		resp.getWriter().write(new Gson().toJson(response.getOutput().getText()));
	}

	private MessageResponse conversationAPI(String input, Context context) { //Agora é do tipo Context
		Conversation service = new Conversation("2018-09-10");
		service.setUsernameAndPassword("10208ce1-5e86-4c70-80ba-ab7e6ce81ab8", "oH2zLDGISFUJ");
/*
		InputData inputData = new InputData.Builder().text(input).build();
		MessageRequest newMessage = new MessageRequest();
		newMessage.setInput(inputData);
		String workspaceId = "b4247604-967d-424b-a1ab-a224b831dddb"; // id jah alterado
		MessageOptions options = new MessageOptions.Builder(workspaceId).input(inputData).build();
		MessageResponse response = service.message(options).execute();
		return response;
*/
		
		String workspaceId = "b4247604-967d-424b-a1ab-a224b831dddb"; // id jah alterado
	    
	    MessageOptions newMessageOptions = new MessageOptions.Builder()
	    		  .workspaceId(workspaceId)
	    		  .input(new InputData.Builder(input).build())
	    		  .context(context) //Esse metodo eh chamado antes do build, assim consegue incluir o context no obj
	    		  .build();
	    MessageResponse response = service.message(newMessageOptions).execute();
	    System.out.println(newMessageOptions);
		return response;	 
	}
}
