package so.xunta.websocket;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.websocket.WsOutbound;

import so.xunta.utils.Console;

public class WSSessionConnectControl {
	private WSSessionConnectControl(){};
	
	private static WSMessageControl instance = new WSMessageControl();
	
	public static  WSMessageControl getInstance(){
		return instance;
	}
	
	static boolean exist = true;
	
	private static Map<Integer, WsOutbound> sessionConnectControl = new HashMap<Integer, WsOutbound>();

	/*
	 * 将 WebSocket "会话对象" 添加到会话管理器; 已解决: 1.
	 * 同一用户多窗口消息同步,将窗口ID映射到用户ID,通过用户ID获取该用户对应的窗口ID列表
	 */
	public static void addUserSessionConnect(int user_id, WsOutbound outbound) {
		Console.print(user_id + ":  开启一个窗口连接客户端,将用户添加到会话连接管理器中 ");
		sessionConnectControl.put(user_id, outbound);
			if(exist){
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						while(true){
							System.out.println("连接数："+sessionConnectControl.size());
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}).start();
				exist =false;
			}
			
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
		for(int i:sessionConnectControl.keySet())
		{
			System.out.println("连接id:"+i);
		}
		return sessionConnectControl.get(user_id);
	}
	
	public static void CloseSession (int user_id){
		//测试，执行该方法后，客户端的console.log会打印出 code: 200 ，否则该方法有Bug,
		try {
			ByteBuffer byteBuffer = ByteBuffer.wrap("服务器主动中断连接".getBytes("UTF-8"));
			sessionConnectControl.get(user_id).close(20, byteBuffer);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Map<Integer, WsOutbound> getSessionConnectControl() {
		return sessionConnectControl;
	}

	public static void setSessionConnectControl(Map<Integer, WsOutbound> sessionConnectControl) {
		WSSessionConnectControl.sessionConnectControl = sessionConnectControl;
	}
	
	
}