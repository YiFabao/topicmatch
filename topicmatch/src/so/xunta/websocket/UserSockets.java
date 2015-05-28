package so.xunta.websocket;

import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.websocket.WsOutbound;

public class UserSockets {
	private UserSockets(){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true){
						System.out.println("socket连接数："+UserSockets.getInstance().getSocketSize());
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}	
				}
			}).start();
		}
	
	Map<Integer,WsOutbound> user_socket_map = new HashMap<Integer,WsOutbound>();
	
	private static UserSockets instance = new UserSockets();
	
	public static UserSockets getInstance(){
		return instance;
	}
	
	//添加socket
	public void addUserSocket(int userId,WsOutbound ws){
		user_socket_map.put(userId,ws);
	}
	
	//移除socket
	public void removeUserSocketByUserId(int userId)
	{
		user_socket_map.remove(userId);
	}
	
	//获取socket
	public WsOutbound getUserSocketByUserId(int userId)
	{
		return user_socket_map.get(userId);
	}
	
	public int getSocketSize(){
		return user_socket_map.size();
	}
	
	
}
