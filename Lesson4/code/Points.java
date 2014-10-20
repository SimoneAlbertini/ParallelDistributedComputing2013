import java.util.HashMap;
import java.util.Map;


public class Points 
{
	private Map<String, Integer> points;
	private int max_points;
	
	public Points(int max_points)
	{
		points = new HashMap<String, Integer>();
		this.max_points = max_points;
	}
	
	public synchronized int addPointsTo(String player_name, int p)
	{
		int current_points = 0;
		if(points.containsKey(player_name))
			current_points = points.get(player_name);
		
		current_points += p;
		
		points.put(player_name, current_points);
		return current_points;
	}
	
	public synchronized int get(String player_name)
	{
		if(!points.containsKey(player_name))
			return -1;
		
		return points.get(player_name);
	}
	
	public synchronized String isThereAWinner()
	{
		String winner = "";
		int winner_p = -1;
		for (String player : points.keySet()) 
		{
			int p = points.get(player);
			if(p >= max_points && p > winner_p)
			{
				winner = player;
				winner_p = p;
			}
		}
		
		return winner;
	}
	
}
