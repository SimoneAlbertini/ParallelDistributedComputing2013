
public class Bancone<T>
{
	private T buffer[];
	private int first;
	private int last;
	private int numberInBuffer;
	private int size;
	
	@SuppressWarnings("unchecked")
	public Bancone(int spazio)
	{
		size = spazio;
		buffer = (T[]) new Object[size];
		last = 0;
		first = 0;
	}
	
	public synchronized void put(T item) throws InterruptedException
	{
		while(numberInBuffer == size)
		{
			wait();
		}
		
		last = (last+1) % size;
		numberInBuffer++;
		buffer[last] = item;
		notifyAll();
	}
	
	public synchronized T get() throws InterruptedException
	{
		while(numberInBuffer == 0)
		{
			wait();
		}
		
		first = (first + 1) % size;
		numberInBuffer--;
		notifyAll();
		return buffer[first];
	}
	
}
