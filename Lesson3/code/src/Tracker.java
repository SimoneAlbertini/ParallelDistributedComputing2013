import java.util.ArrayList;


public class Tracker 
{
	private int alive;
	private ArrayList<Trail> solutions;

	public Tracker()
	{
		alive = 0;
		solutions = new ArrayList<Trail>();
	}
	
	public synchronized Trail getTrail(int n)
	{
		if (n >= solutions.size())
			return solutions.get(solutions.size()-1);
		
		return solutions.get(n);
	}

	public synchronized void registerSearcher()
	{
		alive++;
	}

	public synchronized void searcherFinished()
	{
		alive--;
		if(alive == 0) notify();
	}
	
	public synchronized int numberOfSolutions()
	{
		return solutions.size();
	}

	public synchronized void trailFound(Trail t)
	{
		alive--;
		solutions.add(t);
		
		if(alive == 0) notify();
	}

	public synchronized int waitAllFinished()
	{
		while(alive != 0)
		try
		{
			wait();
		}
		catch(InterruptedException e) {}
		
		return solutions.size();
	}
}
