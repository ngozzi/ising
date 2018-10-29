public class Metropolis {
	
	private int rigaSitoFlip;
	private int colonnaSitoFlip;
	private Lattice reticolo;
	private int size;
	private Grafico grafico;
	
	public Metropolis (Ising ising,Grafico grafico)  {
		this.grafico=grafico;
        this.reticolo=ising.getReticolo();
		this.size=reticolo.getSize();
	}
	
	public void run() {
		
		while(!(grafico.getState())){
			double temperatura=grafico.getTemperatura()/100D;
			rigaSitoFlip=(int) Math.floor(Math.random()*size);
			colonnaSitoFlip= (int) Math.floor(Math.random()*size);
			if(deltaE(reticolo,reticolo.getSito(rigaSitoFlip,colonnaSitoFlip),rigaSitoFlip,colonnaSitoFlip)<=0) {
				reticolo.getSito(rigaSitoFlip,colonnaSitoFlip).flipS();
			} else if(Math.random()<Math.exp(-deltaE(reticolo,reticolo.getSito(rigaSitoFlip,colonnaSitoFlip),
				rigaSitoFlip,colonnaSitoFlip)/temperatura)) 
			{
				reticolo.getSito(rigaSitoFlip,colonnaSitoFlip).flipS();
			}
			normalizeZ();
			}
		}
		
	public void normalizeZ(){
		double zMax=reticolo.getSito(0, 0).getZ();
		int i=0;
		int j=0;
		//ricerca z massima 
		while(i<size){
			while(j<size){
				if(reticolo.getSito(i, j).getZ()>zMax) {
					zMax=reticolo.getSito(i, j).getZ();
				}
				j++;
			}
			j=0;
			i++;
		}
		i=0;
		j=0;
		while (i<size) {
			while(j<size) {
				reticolo.getSito(i, j).setZ(reticolo.getSito(i,j).getZ()/zMax);
				j++;
			}
			j=0;
			i++;
		}	
	}
	

	public double deltaE(Lattice reticolo,Sito s,int rigaSito,int colonnaSito) {
		
		int leftS, rightS, topS, bottomS; 
		
        if (rigaSito == 0) 
        	leftS =   reticolo.getSito(size-1, colonnaSito).getS(); 
          else
        	  leftS = reticolo.getSito(rigaSito-1, colonnaSito).getS();                           
        
        if (rigaSito == size-1)
        	rightS = reticolo.getSito(0, colonnaSito).getS();                
          else
        	  rightS = reticolo.getSito(rigaSito+1, colonnaSito).getS();     
        
        if (colonnaSito == 0)
        	topS = reticolo.getSito(rigaSito, size-1).getS();  
          else 
        	  topS = reticolo.getSito(rigaSito, colonnaSito-1).getS();  
        
        if (colonnaSito == size-1) 
        	bottomS =  reticolo.getSito(rigaSito, 0).getS(); 
          else 
        	  bottomS = reticolo.getSito(rigaSito, colonnaSito+1).getS(); 
        
        
        return 2.0 *reticolo.getSito(rigaSito, colonnaSito).getS()* (leftS + rightS + topS + bottomS);
	}
		
		
	public int getRigaSitoFlip () {
		return rigaSitoFlip;
	}
	
	public int getColonnaSitoFlip() {
		return colonnaSitoFlip;
	}
}
