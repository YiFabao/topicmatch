package so.xunta.servlet;

import javax.servlet.http.HttpServletRequest;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import so.xunta.websocket.WSEvent;
public class WebSocket extends WebSocketServlet{

	private static final long serialVersionUID = -2486881781707620221L;
	private static final String USER_ID = "userId";

	@Override
	protected StreamInbound createWebSocketInbound(String arg0,HttpServletRequest request) {
		return new WSEvent(Integer.parseInt(request.getParameter(USER_ID)));
	}
}
