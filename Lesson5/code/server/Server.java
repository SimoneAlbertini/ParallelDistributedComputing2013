package server;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server 
{
	public static final int PORT = 6667;
	
	private static ConnectedUsers connectedUSers = new ConnectedUsers();

	public static void main(String[] args) throws IOException 
	{
		ServerSocket ssocket = new ServerSocket(PORT);
		System.out.println("Started: " + ssocket);
		
		try
		{
			while(true)
			{
					Socket socket = ssocket.accept();
					
					BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
					
					String username = reader.readLine().toLowerCase();
					System.out.print("** Incoming request: " + username);
					
					if(connectedUSers.canBeRegistered(username))
					{
						connectedUSers.registerUser(username, socket);
						writer.println("OK");
						new ServeClient(username, connectedUSers).start();
						System.out.println(" - Connection Accepted: " + socket);
					}
					else
					{
						writer.println("Invalid username (not valid or in use)");
						System.out.println(" - Rejected");
					}
			}
		}
		finally
		{
			ssocket.close();
		}
	}

}
