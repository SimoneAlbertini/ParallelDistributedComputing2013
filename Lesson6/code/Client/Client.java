package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import Server.ServerInterface;


public class Client 
{
	/*
	 * add: increment
	 * winner: add n 
	 * results: to 0
	 * close: bye bye 
	 */
	
	public static void main(String[] args) throws IOException
	{
		ServerInterface server = null;
		try
		{
			server = new ProxyServer("localhost");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String line;
			do
			{
				System.out.print("> ");
				line = in.readLine();
				if(line == null)
					break;
				process(server, line.trim().toLowerCase());
			} 
			while(!line.equals("close"));
			
		}
		finally
		{
			System.out.println("Closing... ");
			server.close();
		}
	}

	private static void process(ServerInterface server, String line)
			throws IOException 
	{
		String[] tok = line.split("\\s+");
		String cmd = tok[0];
		
		if(cmd.equals("add"))
		{
			server.addResults(tok[1], Integer.parseInt(tok[2]));
		}
		else if(cmd.equals("winner"))
		{
			String w = server.winner();
			System.out.println("* " + w + " is winning");
		}
		else if(cmd.equals("results"))
		{
			Map<String, Integer> results = server.results();
			for (String party : results.keySet()) 
			{
				System.out.println("* " + party + " -- " + results.get(party));
			}
		}
	}
	
}
