import java.util.ArrayList;
import java.util.List;

public class Main 
{

	public static void main(String[] args) throws InterruptedException 
	{
		/* Screo la lista degli scrutinii, ovvero
		 * l'invieme dei voti raccolti in ogni sezione
		 */
		List<Scrutinio> scrutinii = new ArrayList<Scrutinio>();
		
		Scrutinio scrutinio1 = new Scrutinio(); 
		scrutinio1.put("party1", 3);
		scrutinio1.put("party2", 31);
		scrutinio1.put("party3", 7);
		scrutinio1.put("party4", 12);
		scrutinii.add(scrutinio1);
		
		Scrutinio scrutinio2 = new Scrutinio(); 
		scrutinio2.put("party1", 90);
		scrutinio2.put("party2", 12);
		scrutinio2.put("party3", 76);
		scrutinio2.put("party4", 43);
		scrutinii.add(scrutinio2);
		
		Scrutinio scrutinio3 = new Scrutinio(); 
		scrutinio3.put("party1", 80);
		scrutinio3.put("party2", 78);
		scrutinio3.put("party3", 1);
		scrutinio3.put("party4", 13);
		scrutinii.add(scrutinio3);
		
		/* Questo è il risultato finale che mi aspetto,
		 * Ovvero la somma dei voti di ogni partito
		 * per ogni sezione
		 */
		Scrutinio expected = new Scrutinio();
		expected.put("party1", 173);
		expected.put("party2", 121);
		expected.put("party3", 84);
		expected.put("party4", 68);
		
		/* Voglio ripetere l'esperimento 10 volte:
		 * Ogni volta istanzio dei nuovi thread che
		 * rappresentano le sezioni e li lancio.
		 * Alla fine, una procedura automatica mi
		 * rileva quali risultati sono sbagliati.
		 * 
		 * Se introduco un meccanismo di controllo
		 * d'accesso alla zona critica, ovvero
		 * ministero.submit,
		 * questi errori spariscono.
		 * 
		 * La soluzione è utilizzare una variabile
		 * di lock oppure rendere il metodo synchronized
		 * 
		 */
		int tries = 10;
		for(int i=0; i < tries; i++)
		{
			Ministero ministero = new Ministero();
			List<Thread> threads = makeThreads(scrutinii, ministero);
			
			for (Thread t : threads) {
				t.start();
			}
			
			for (Thread t : threads) {
				t.join();
			}
			
			Scrutinio statistics = ministero.getStatistics();

			System.out.println("Integrity check iteration " + i);
			IntegrityCheck(expected, statistics);
		}
	}
	
	

	private static List<Thread> makeThreads(List<Scrutinio> scrutinii, Ministero ministero)
	{
		List<Thread> out = new ArrayList<Thread>();
		
		for (Scrutinio scrutinio : scrutinii) 
		{
			out.add(new Thread(new Sezione(scrutinio, ministero)));
		}
		
		return out;
	}

	private static void IntegrityCheck(Scrutinio expected, Scrutinio actual)
	{
		for (String key : expected.keySet()) 
		{
			int voti_exp = expected.get(key);
			int voti_actual = actual.get(key);
			if(voti_actual != voti_exp)
			{
				System.out.println("Per il partito " + key + " mi aspetto " + voti_exp + " voti, invece sono " + voti_actual);
			}
		}
	}
	
}
