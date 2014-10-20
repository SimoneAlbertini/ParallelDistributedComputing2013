
public class Sezione implements Runnable 
{
	Scrutinio scrutinio;
	Ministero ministero;
	
	public Sezione(Scrutinio scrutinio, Ministero min)
	{
		this.scrutinio = scrutinio;
		ministero = min;
	}

	@Override
	public void run() 
	{
		for (String party : scrutinio.keySet()) 
		{
			int voti = scrutinio.get(party);
			ministero.submit(party, voti);
			sleep(500);
		}
	}
	
	
	
	private void sleep(long ms)
	{
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {}
		
	}

}
