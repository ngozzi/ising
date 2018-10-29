public class Lattice {

	private Sito [][] lattice;
	private int size;
	
	public Lattice(int size) {
		this.size=size;
		lattice= new Sito[size][size];
		java.util.Random r = new java.util.Random(System.currentTimeMillis());
		for (int i=0;i<size;i++) {
			for(int j=0;j<size;j++){
			  double x=i;
			  double y=j;
			  double z=0;
			  int s;
			if(r.nextDouble()<0.5) {
				s=1;
			} else {
				s=-1;
			}
			lattice[i][j]= new Sito(x,y,z,s);		
		  }
	   }
	}
	
	public Sito getSito(int l,int c){
		return lattice[l][c];
	}
	
	public int getSize() {
		return size;
	}
}
