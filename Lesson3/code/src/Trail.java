import java.util.ArrayList;


public class Trail 
{
	private SearchableMaze maze;
	private ArrayList<MazePosition> positions;

	public Trail(SearchableMaze maze)
	{
		this.maze = maze;
		positions = new ArrayList<MazePosition>();
	}
	
	public MazePosition[] getPointsInTrail()
	{
		return positions.toArray(new MazePosition[0]);
	}
	
	public Trail clone()
	{
		Trail newTrail = new Trail(maze);
		
		for (MazePosition pos : positions) 
			newTrail.positions.add(new MazePosition(pos.getRow(), pos.getCol()));
		
		return newTrail;
	}

	public boolean visited(MazePosition pos)
	{
		if(pos == null)
			return false;
		
		for (MazePosition p : positions) 
			if(p.getRow() == pos.getRow() && p.getCol() == pos.getCol())
				return true;
		
		return false;
	}
	
	public MazePosition[] mark(MazePosition pos)
	{
		positions.add(pos);
		return maze.next(pos);
	}

	@Override
	public String toString() 
	{
		StringBuilder b = new StringBuilder();
		for (MazePosition pos : positions)
			b.append(pos.toString()).append(" ");
		return b.toString();
	}

	
	
}
