public class Sito {
	
	private double x,y,z;
	private int s;

	public Sito(double x,double y,double z,int s) {
		this.x=x;
		this.y=y;
		this.z=z;
		this.s=s;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getZ() {
		return z;
	}
	
	public int getS() {
		return s;
	}
	
	public void flipS () {
		this.s*=-1;
		this.z++;
	}
	
	public void setZ(double _z) {
		z=_z;
	}
}
