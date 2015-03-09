package so.xunta.websocket;

import java.util.HashMap;
import java.util.Map;
import org.apache.catalina.websocket.WsOutbound;

import so.xunta.utils.Console;

public class WSSessionConnectControl {
	private static Map<Integer, WsOutbound> sessionConnectControl = new HashMap<Integer, WsOutbound>();

	/*
	 * 将 WebSocket "会话对象" 添加到会话管理器; 已解决: 1.
	 * 同一用户多窗口消息同步,将窗口ID映射到用户ID,通过用户ID获取该用户对应的窗口ID列表
	 */
	public static void addUserSessionConnect(int user_id, WsOutbound outbound) {
		Console.print(user_id + ":  开启一个窗口连接客户端,将用户添加到会话连接管理器中 ");
		sessionConnectControl.put(user_id, outbound);
	}

	/*
	 * 将 WebSocket "会话对象" 从会话管理器中删除;
	 */
	public static void deleteUserSessionConnect(int user_id) {
		sessionConnectControl.remove(user_id);
		Console.print(user_id + ":  与服务器连接中断");
	}

	public static WsOutbound getWindowConnect(int user_id) {
		System.out.println("服务器LOG   WSSessionConnectControl  ：  43行 执行前 ");
		return sessionConnectControl.get(user_id);
	}
}