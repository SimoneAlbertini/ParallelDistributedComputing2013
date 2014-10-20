
public class Player implements Runnable
{
	private Croupier croupier;
	private Points points;
	private String name;
	
	public Player(String name, Croupier croupier, Points points)
	{
		if(name == null)
			throw new NullPointerException();

		this.name = name;
		this.croupier = croupier;
		this.points = points;
	}
	
	private int play()
	{
		int p = 0;
		try 
		{
			Thread.sleep((long) (Math.random()*3 + 0.5)*1000);
			p = (int)(Math.random()*10);
		} 
		catch (InterruptedException e) {}
		
		return p;
	}
	
	public void run()
	{
		try
		{
			int p = play();
			points.addPointsTo(name, p);
			System.out.println("<"+name+">" + " ha giocato ottenendo " + p + " punti - Totale: " + points.get(name));
			croupier.waitAtBarrier();
		}
		catch(InterruptedException e) {}
	}
}

