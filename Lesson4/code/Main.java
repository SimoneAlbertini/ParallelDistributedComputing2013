import java.util.ArrayList;
import java.util.List;


public class Main 
{

	public static void main(String[] args) throws InterruptedException 
	{
		Points points = new Points(30);
		Croupier croupy = new Croupier(5);
		
		List<Player> players = new ArrayList<Player>();
		players.add(new Player("Augusto Pinochet", croupy, points));
		players.add(new Player("Robert Mugabe", croupy, points));
		players.add(new Player("Kim Jong-il", croupy, points));
		players.add(new Player("Adolf Hitler", croupy, points));
		players.add(new Player("Francisco Franco", croupy, points));
		
		String winner = null;
		do
		{
			List<Thread> threads = makeThreads(players);
			
			for (Thread t : threads)
				t.start();
			
			for (Thread t : threads) 
				t.join();
			
			Thread.sleep(2000);
			winner = points.isThereAWinner();
			
		} while(winner.isEmpty());
		
		System.out.println();
		System.out.println("The winner is " + winner + " with " + points.get(winner) + " points!");
	}
	
	
	private static List<Thread> makeThreads(List<Player> players)
	{
		List<Thread> threads = new ArrayList<Thread>();
		for (Player player : players) 
			threads.add(new Thread(player));
		return threads;
	}
}
