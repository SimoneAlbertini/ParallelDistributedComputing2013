package Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import Server.ServerInterface;


public class ProxyServer implements ServerInterface 
{

	private Socket socket;
	private BufferedReader reader;
	private PrintWriter writer;

	public ProxyServer(String addr) throws IOException
	{
		System.out.print("Connecting to the server... ");
		socket = new Socket(addr, ServerInterface.PORT);
		System.out.println("Done!");
		
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
	}

	@Override
	public void addResults(String party, int votes) 
	{
		writer.println("<add> " + party + " " + votes);
	}

	@Override
	public String winner() throws IOException 
	{
		writer.println("<winner>");
		return reader.readLine();
	}

	@Override
	public Map<String, Integer> results() throws IOException 
	{
		writer.println("<results>");
		String line = reader.readLine();
		String[] tok = line.split("\\s+");
		
		Map<String, Integer> results = new HashMap<String, Integer>();
		
		for(int i=0; i < tok.length; i += 2)
		{
			results.put(tok[i], Integer.parseInt(tok[i+1]));
		}
		
		return results;
	}

	@Override
	public void close() 
	{
		writer.println("<close>");
	}

}
