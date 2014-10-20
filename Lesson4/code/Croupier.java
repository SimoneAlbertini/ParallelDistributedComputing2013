public class Croupier {
	
	private int num_thread;
	private int arrived;
	private boolean release;
	
	public Croupier(int number) 
	{
		num_thread = number;
		arrived = 0;
		release = false;
	}
	
	public synchronized int arrived() 
	{
		return arrived;
	}
	
	public int capacity() 
	{
		return num_thread ;
	}
	
	public synchronized void waitAtBarrier() throws InterruptedException 
	{
		while (release)
			wait() ;
		
		try 
		{
			arrived++;
			while(arrived != num_thread && !release)
				wait();
			if(arrived == num_thread)
			{
				release = true;
				System.out.println("All " + num_thread + " Players played");
				notifyAll();
			}
		} 
		finally
		{
			arrived--;
			if(arrived == 0)
			{
				release = false;
				notifyAll();
			}
		} 
	}

	
}