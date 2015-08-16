package servlet;

import servlet.utils.NotificationGetPostUtil;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.utils.NotificationGetPostUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class NotificationServlet
 */
@WebServlet("/NotificationServlet")
public class NotificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private ArrayList<String> userList = new ArrayList<String>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotificationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// format input
				InputStreamReader inputReader =new InputStreamReader(request.getInputStream());
				BufferedReader bufferedReader = new BufferedReader(inputReader);
				StringBuffer stringBuffer = new StringBuffer();
				String lineString;
				while ((lineString= bufferedReader.readLine())!=null) {
					stringBuffer.append(lineString);
				}
				System.out.println("input string : "+stringBuffer);
				JSONArray inputArray = JSONArray.fromObject(stringBuffer.toString());
				System.out.println("input JSONObject : "+inputArray);
				
				userList = (ArrayList<String>) JSONArray.toCollection(inputArray);
				
				sendToUsers(userList);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// format input
		InputStreamReader inputReader =new InputStreamReader(request.getInputStream());
		BufferedReader bufferedReader = new BufferedReader(inputReader);
		StringBuffer stringBuffer = new StringBuffer();
		String lineString;
		while ((lineString= bufferedReader.readLine())!=null) {
			stringBuffer.append(lineString);
		}
		System.out.println("input string : "+stringBuffer);
		JSONArray inputArray = JSONArray.fromObject(stringBuffer.toString());
		System.out.println("input JSONObject : "+inputArray);
		
		userList = (ArrayList<String>) JSONArray.toCollection(inputArray);
		
		sendToUsers(userList);
		
		// write response
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("OK");
		out.flush();
		out.close();
	}

	private void sendToUsers(ArrayList<String> users) {
		String u1 = users.get(0);
		System.out.println("sendToUsers : user1 = "+u1);
		if (!u1.equals("")) {
			// 3.��POST��ʽ����
			/*--ƴ��POST�ַ���--*/
			final StringBuilder parameter = new StringBuilder();
			parameter.append("action=send&broadcast=N&username="); // ������������Ҫ���N,�����Y��㲥,ȫ���յ�,���������Ч
			parameter.append(u1);
			parameter.append("&title=FEЭ��ƽ̨&message=");
			parameter.append("POST���磬���");
			parameter.append("&uri=");
			parameter.append(""); // �����͸�iPhone��ʽ����һ��
			/*--End--*/
			 String resp = NotificationGetPostUtil.send("POST", NotificationGetPostUtil.NOTIFICATION_SERVER + "notification_api.do", parameter);
			 System.out.println("response:" + resp);
		}
	}

}
