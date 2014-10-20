import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Main 
{
	
	private static String drawPath(Trail t, SearchableMaze maze)
	{
		StringBuilder str = new StringBuilder();
		
		Matrix<String> image = new Matrix<String>(maze.getRows(), maze.getCols());
		for(int r=0; r < image.rows(); r++)
			for(int c=0; c < image.cols(); c++)
				image.set(r, c, "x");
		
		MazePosition[] points = t.getPointsInTrail();
		for(int i=0; i < points.length; i++)
			image.set(points[i].getRow(), points[i].getCol(), "-");
		
		for(int r=0; r < image.rows(); r++)
		{
			for(int c=0; c < image.cols(); c++)
				str.append(image.get(r, c));
			str.append("\n");
		}
		
		return str.toString();
	}
	
	private static void saveToFile(Tracker tr, SearchableMaze maze, String filename) throws IOException
	{
		File file = new File(filename);
		StringBuilder str = new StringBuilder();
		
		int n = tr.numberOfSolutions();
		for(int i=0; i < n; i++)
		{
			Trail t = tr.getTrail(i);
			str.append("Trail ").append(i+1).append(": ").append(t.toString()).append("\n");
			str.append(drawPath(t, maze)).append("\n");
		}
		
		if(file.exists()) file.delete();
		file.createNewFile();
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(str.toString());
		writer.close();
	}
	
	public static void main(String[] args) throws IOException 
	{	
		Tracker tr = new Tracker();
		
		SearchableMaze maze = new SearchableMaze("maze2.txt");
		
		Trail t = new Trail(maze);
		
		Searcher s = new Searcher(maze, maze.entrance(), t, tr);
		
		s.start();
		
		int numberOfSolutions = tr.waitAllFinished();
		
		for(int i=0; i < numberOfSolutions; i++)
		{
			Trail trail = tr.getTrail(i);
			System.out.println("Trail " + (i+1) + ": " + trail);
			System.out.println(drawPath(trail, maze));
		}
		
		saveToFile(tr, maze, "solutions.txt");
	}
	
}
