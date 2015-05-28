package so.xunta.websocket;

import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.websocket.WsOutbound;

public class UserSockets {
	private UserSockets(){
	
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
		WsOutbound ws = this.getUserSocketByUserId(userId);
		int count=0;
		while(true){
			if(ws!=null){
				System.out.println("ws不为空");
				break;
			}else{
				System.out.println("ws为空"+getSocketSize()+ " ==>"+user_socket_map);
				ws = this.getUserSocketByUserId(userId);
			}
			try {
				Thread.sleep(200);
				count++;
				if(count>100){
					break;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return ws;
	}
	
	public int getSocketSize(){
		return user_socket_map.size();
	}
	
	
}
