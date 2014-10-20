
public class MazePosition 
{
	private int row;
	private int col;

	public MazePosition(int r, int c)
	{
		row = r;
		col = c;
	}
	
	public int getRow()
	{
		return row;
	}
	
	public void setRow(int r)
	{
		row = r;
	}
	
	public int getCol()
	{
		return col;
	}
	
	public void setCol(int c)
	{
		col = c;
	}
	
	public boolean equals(MazePosition other) 
	{
		if (this == other)
			return true;
		if (other == null)
			return false;
		
		return col == other.col && row == other.row;
	}

	@Override
	public String toString() 
	{
		return "("+row+", "+col+")";
	}
	
}
