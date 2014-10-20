public class Panettiere implements Runnable
{
	private Bancone<Pane> banco;
	private String nome;
	private int numProduzioni;
	
	public Panettiere(String nome, int numProduzioni, Bancone<Pane> b)
	{
		this.numProduzioni = numProduzioni;
		banco = b;
		this.nome = nome;
	}

	@Override
	public void run() 
	{
		double produzioneTotale = 0;
		for(int i=0; i < numProduzioni; i++)
			try 
			{
				Pane p = new Pane(Math.random() * 5 + 0.5);
				banco.put(p);
//					System.out.println(nome + ": messi sul banco " + p.getPeso() + " Kg di pane");
				produzioneTotale += p.getPeso();
			}
			catch(InterruptedException e) {}
		
		produzioneTotale = (double)Math.round(produzioneTotale * 100) / 100;
		System.out.println(nome + ": in totale prodotti " + produzioneTotale + " Kg di pane");
	}
	
}
