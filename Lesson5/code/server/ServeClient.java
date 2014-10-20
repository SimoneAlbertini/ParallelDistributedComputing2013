package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServeClient extends Thread 
{
	private String username;
	private ConnectedUsers connected;
	

	public ServeClient(String username, ConnectedUsers connected) 
	{
		this.connected = connected;
		this.username = username;
	}

	@Override
	public void run() 
	{
		try
		{
			Socket socket = connected.getSocket(username);
			try
			{
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				while(true)
				{
					String str = reader.readLine();
					if(str == null || str.equals("END"))
						break;
					str = username + "> " + str;
					System.out.println(str);
					connected.echoMessageFrom(username, str);
				}
			}
			finally
			{
				System.out.println(username + " disconnected");
				connected.removeUser(username);
				socket.close();
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
}
