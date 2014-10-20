
public class Matrix<T> 
{
	private int rows, cols;
	private T[][] data;

	@SuppressWarnings("unchecked")
	public Matrix(int r, int c)
	{
		rows = r;
		cols = c;
		
		data = (T[][]) new Object[r][c];
	}
	
	public T get(int r, int c)
	{
		return data[r][c];
	}
	
	public void set(int r, int c, T value)
	{
		data[r][c] = value;
	}
	
	public int rows()
	{
		return rows;
	}

	public int cols()
	{
		return cols;
	}
	
}
