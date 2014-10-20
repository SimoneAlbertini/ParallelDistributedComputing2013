package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server 
{

	public static void main(String[] args) throws IOException 
	{
		Results results = new Results();
		ServerSocket ss = new ServerSocket(ServerInterface.PORT);
		System.out.println("Server started");
		
		try
		{
			int count = 0;
			while(true)
			{
				Socket socket = ss.accept();
				System.out.println("Connection accepted - " + socket);
				new ServeClient("Client " + count, socket, results).start();
				count++;
			}
		}
		finally
		{
			System.out.println("Shutting down");
			ss.close();
		}
	}

}
