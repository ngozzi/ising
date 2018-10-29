public class Simulazione {
	public static void main (String [] args)  {
		Ising ising= new Ising(50);
		Grafico grafico= new Grafico(ising);
		Metropolis simulation= new Metropolis(ising, grafico);
	
		while (true) {
			simulation.run();
		}
	}
}