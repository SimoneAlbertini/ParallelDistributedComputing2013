package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

public class Watcher extends Thread 
{
	private boolean stop;
	private Socket socket;
	
	public Watcher(Socket s)
	{
		this.socket = s;
		stop = false;
	}

	public void stopThread()
	{
		stop = true;
	}
	
	@Override
	public void run() 
	{
		try
		{
			BufferedReader readSock = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while(!stop)
			{
					String line = readSock.readLine();
					if(line == null)
						break;
					System.out.println("\r" + line);
					System.out.print("> ");
			}
		}
		catch(SocketException e)
		{
			// socket has been closed
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
	
}
