
public class Main 
{

	public static void main(String[] args) 
	{
		/* Correggendo gli esercizi svolti ho notato che
		 * ci sono in generale grosse lacune nel capire cosa sia
		 * un tipo generico.
		 * Consiglio di riguardarvelo.
		 * 
		 * Guardate la definizione di Bancone.
		 * T in questo caso è istanziato col tipo Pane
		 * (che è una classe)
		 */
		
		/* Se il buffer ha dimensione troppo grande
		 * si ha che non si riempie mai.
		 * Lo imposto piccolo per far vedere come poi
		 * i totali di ciascun produttore e consumatore
		 * non coincidano (ma coincidono i totali di
		 * tutti i produttori con quelli di tutti i 
		 * consumatori).
		 */
		Bancone<Pane> banco = new Bancone<Pane>(3);
		
		final int numCicli = 10;
		
		Panettiere p1 = new Panettiere("prod 1", numCicli, banco);
		Panettiere p2 = new Panettiere("prod 2", numCicli, banco);
		
		Cliente c1 = new Cliente("cons 1", numCicli, banco);
		Cliente c2 = new Cliente("cons 2", numCicli, banco);
		
		new Thread(p1).start();
		new Thread(p2).start();
		
		new Thread(c1).start();
		new Thread(c2).start();
	}

}
