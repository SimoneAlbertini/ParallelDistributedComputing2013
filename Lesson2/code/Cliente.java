public class Cliente implements Runnable 
{
	private Bancone<Pane> banco;
	private String nome;
	private int numAcquisti;

	public Cliente(String nome, int numAcquisti, Bancone<Pane> b) 
	{
		this.nome = nome;
		this.numAcquisti = numAcquisti;
		this.banco = b;
	}

	@Override
	public void run() 
	{
		double pesoTotale = 0;
		for (int i = 0; i < numAcquisti; i++)
			try 
			{
				Pane p = banco.get();
				pesoTotale += p.getPeso();
				// System.out.println(nome + ": comprati " + p.getPeso() + " Kg di pane");
			} catch (InterruptedException e) {}

		pesoTotale = (double)Math.round(pesoTotale * 100) / 100;
		System.out.println(nome + ": in totale comprati " + pesoTotale + " Kg di pane");
	}

}
