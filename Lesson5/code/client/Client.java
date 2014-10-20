package client;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import server.Server;


public class Client 
{
	public static final String ADDRESS = "localhost";

	public static void main(String[] args) throws IOException 
	{
		Socket socket = new Socket(ADDRESS, Server.PORT);
		Watcher watcher = new Watcher(socket);
		try
		{
			System.out.println("Socket: " + socket);
			PrintWriter writeSock = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			BufferedReader readSock = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedReader commandLine = new BufferedReader(new InputStreamReader(System.in));
			
			// registration
			System.out.print("** Enter your username **\n> ");
			String username = commandLine.readLine();
			writeSock.println(username);
			String line = readSock.readLine();
			
			// se connessione accettata
			if(line.equals("OK"))
			{
				System.out.println("** Accepted - joining chat **");
				watcher.start();
				
				do
				{
					System.out.print("> ");
					line = commandLine.readLine();
					writeSock.println(line);
				} 
				while(!line.equals("END"));
			}
			else
			{
				System.out.println("Connection Refused: " + line);
			}
		}
		finally
		{
			System.out.println("Closing");
			watcher.stopThread();
			socket.close();
		}
	}

}
