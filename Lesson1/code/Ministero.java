
public class Ministero 
{
	private Scrutinio totale_voti;
	
	public Ministero()
	{
		totale_voti = new Scrutinio();
	}
	
	Scrutinio getStatistics()
	{
		return totale_voti;
	}
	
	/* In questo metodo si ha la sezione critica.
	 * Soluzione per risolvere la race condition
	 * qui presente:
	 * (1) uso della parola chiave synchronized
	 * (2) definire variabile di lock.
	 * Provatele entrambe. Per qualunque dubbio
	 * ci sono le slide delle lezioni.
	 */
	public void submit(String party, int voti)
	{
		if(!totale_voti.containsKey(party))
		{
			totale_voti.put(party, voti);
		}
		else
		{
			int old_voti = totale_voti.get(party);
			totale_voti.put(party, old_voti + voti);
		}
	}
	
	
	public void printStatistics()
	{
		System.out.println("Risultati del ministero");
		for (String key : totale_voti.keySet()) 
		{
			int voti = totale_voti.get(key);
			System.out.println(key + " ha ottenuto " + voti + " voti");
		}
	}
	
}
