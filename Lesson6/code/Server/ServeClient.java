package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;


public class ServeClient extends Thread implements ServerInterface 
{

	private Socket socket;
	private BufferedReader reader;
	private PrintWriter writer;
	private Results res;

	public ServeClient(String name, Socket s, Results res) throws IOException
	{
		super(name);
		
		this.socket = s;
		this.res = res;
		
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
	}
	
	@Override
	public void run() 
	{
		try 
		{
			while(!socket.isClosed())
			{
				String line = reader.readLine();
				if (line == null)
					break;
				String[] tok = line.trim().toLowerCase().split("\\s+");
				String cmd = tok[0];
				
				if(cmd.equals("<add>"))
				{
					addResults(tok[1], Integer.parseInt(tok[2]));
				}
				else if(cmd.equals("<winner>"))
				{
					String w = winner();
					writer.println(w);
				}
				else if(cmd.equals("<results>"))
				{
					Map<String, Integer> results = results();
					StringBuilder str = new StringBuilder();
					for (String party : results.keySet()) 
					{
						str.append(party).append(" ")
							.append(results.get(party)).append(" ");
					}
					writer.println(str.toString());
				}
				else if(cmd.equals("<close>"))
				{
					// do nothing
				}
				
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			System.out.println(getName() + " disconnected");
		}

	}

	@Override
	public void addResults(String party, int votes) 
	{
		res.add(party, votes);
	}

	@Override
	public String winner() throws IOException 
	{
		return res.getWinner();
	}

	@Override
	public Map<String, Integer> results() throws IOException 
	{
		return res.getResults();
	}

	@Override
	public void close() throws IOException 
	{
		socket.close();
		System.out.println(getName() + " disconnected");
	}

}
