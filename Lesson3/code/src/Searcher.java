
public class Searcher extends Thread
{
	
	private SearchableMaze maze;
	private MazePosition currentPosition;
	private MazePosition previousPosition;
	private Trail trail;
	private Tracker tracker;


	public Searcher(SearchableMaze maze, MazePosition from, Trail following, Tracker tr)
	{
		super("Searcher");
		this.maze = maze;
		this.currentPosition = from;
		this.tracker = tr;
		this.trail = following.clone();
		
		tracker.registerSearcher();
	}
	
	
	public void run()
	{
		int count;
		
		do
		{
			count = -1;
			MazePosition[] moves = trail.mark(currentPosition);
			
			for(int i=0; i < moves.length; i++)
			{
				if(!moves[i].equals(previousPosition)) // non torna in dietro
				{
					if(!trail.visited(moves[i])) // evita cicli
					{
						if(count == -1)
						{
							count = i;
						}
						else
						{
							new Searcher(maze, moves[i], trail, tracker).start();
						}
					}
				}
			}
			
			if(count != -1)
			{
				previousPosition = currentPosition;
				currentPosition = moves[count];
			}
			
		} while(count != -1);
		
		if(maze.atExit(currentPosition))
		{
			tracker.trailFound(trail);
		}
		else
		{
			tracker.searcherFinished();
		}
	} 
}
