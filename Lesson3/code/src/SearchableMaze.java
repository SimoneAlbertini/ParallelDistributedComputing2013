import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class SearchableMaze 
{
	private static final int WALL = 0;
	private static final int PATH = 1;
	
	private Matrix<Integer> maze;
	private MazePosition entrance;
	private MazePosition exit;

	private Matrix<Integer> readFile(String fileName) throws IOException 
	{
		File file = new File(fileName);
				
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String line = br.readLine();
		int rows = Integer.parseInt(line.split("\\s+")[0]);
		int cols = Integer.parseInt(line.split("\\s+")[1]);
		
		Matrix<Integer> mat = new Matrix<Integer>(rows, cols);
		
		line = br.readLine();
		int row = 0;
		while(line != null)
		{
			for(int col=0; col < cols; col++)
			{
				final char s = line.charAt(col);
				if(s == 'x')
				{
					mat.set(row, col, WALL);
				}
				else if(s == '-')
				{
					mat.set(row, col, PATH);
				}
			}
			
			row++;
			line = br.readLine();
		}
			
		br.close();
		return mat;
	}
	
	private MazePosition findEntrance(Matrix<Integer> maze)
	{
		MazePosition entrance = null;
		for(int r=0; r < maze.rows(); r++)
			if(maze.get(r, 0) == PATH)
			{
				entrance = new MazePosition(r, 0);
				break;
			}
		return entrance;
	}
	
	private MazePosition findExit(Matrix<Integer> maze)
	{
		MazePosition exit = null;
		for(int r=0; r < maze.rows(); r++)
			if(maze.get(r, maze.cols()-1) == PATH)
			{
				exit = new MazePosition(r, maze.cols()-1);
				break;
			}
		return exit;
	}
	
	public SearchableMaze(String filename) throws IOException 
	{
		maze = readFile(filename);
		entrance = findEntrance(maze);
		exit = findExit(maze);
	}
	
	
	public MazePosition entrance()
	{
		return entrance;
	}
	
	
	public boolean atExit(MazePosition pos)
	{
		return pos.equals(exit);
	}
	
	
	private boolean positionIsValid(MazePosition pos)
	{
		return pos.getRow() >= 0 && pos.getRow() < maze.rows() && pos.getCol() >= 0 && pos.getCol() < maze.cols(); 
	}
	
	private boolean isValidPathPosition(MazePosition pos)
	{
		return positionIsValid(pos) && maze.get(pos.getRow(), pos.getCol()) == PATH;
	}
	
	public MazePosition[] next(MazePosition current)
	{
		ArrayList<MazePosition> positions = new ArrayList<MazePosition>();
		
		// 4-vicinanza
		MazePosition pos = new MazePosition(current.getRow() - 1, current.getCol());
		if(isValidPathPosition(pos)) positions.add(pos);
		
		pos = new MazePosition(current.getRow(), current.getCol() - 1);
		if(isValidPathPosition(pos)) positions.add(pos);
		
		pos = new MazePosition(current.getRow(), current.getCol() + 1);
		if(isValidPathPosition(pos)) positions.add(pos);
		
		pos = new MazePosition(current.getRow() + 1, current.getCol());
		if(isValidPathPosition(pos)) positions.add(pos);
		
		return positions.toArray(new MazePosition[0]);
	}
	
	public int getRows() { return maze.rows(); }
	public int getCols() { return maze.cols(); }
}


