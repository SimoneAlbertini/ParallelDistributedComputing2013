package Server;
import java.io.IOException;
import java.util.Map;


public interface ServerInterface 
{
	public static final int PORT = 6666;
	
	public void addResults(String party, int votes);
	public String winner() throws IOException;
	public Map<String, Integer> results() throws IOException; 
	public void close() throws IOException;
}
