package server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ConnectedUsers 
{
	private Map<String, Socket> users = new HashMap<String, Socket>();
	
	public boolean canBeRegistered(String username)
	{
		if(username == null || username.isEmpty())
			return false;
		
		if(users.containsKey(username))
			return false;
		
		return true;
	}
	
	public void registerUser(String username, Socket s)
	{
		if(canBeRegistered(username))
			users.put(username, s);
	}
	
	public void removeUser(String username)
	{
		users.remove(username);
	}
	
	public Socket getSocket(String username)
	{
		return users.get(username);
	}
	
	public void echoMessageFrom(String username, String message) throws IOException
	{
		for (String us : users.keySet())
		{
			if(us.equals(username))
				continue;
			Socket socket = users.get(us);
			
			PrintWriter writeSock = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			writeSock.println(message);
		}
	}
	
}
