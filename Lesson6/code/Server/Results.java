package Server;

import java.util.HashMap;
import java.util.Map;

public class Results
{
	private Map<String, Integer> results;
	
	public Results()
	{
		results = new HashMap<String, Integer>();
	}
	
	public synchronized void add(String party, int votes)
	{
		if(results.containsKey(party))
			votes += results.get(party);
		
		results.put(party, votes);
	}
	
	public synchronized Map<String, Integer> getResults()
	{
		return results;
	}
	
	public synchronized String getWinner()
	{
		String winner = "";
		int votes = Integer.MIN_VALUE;
		for (String party : results.keySet()) 
		{
			int v = results.get(party);
			if(v > votes)
			{
				votes = v;
				winner = party;
			}
		}
		
		return winner;
	}
}
