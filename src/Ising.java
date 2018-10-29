import com.orsoncharts.Chart3D;
import com.orsoncharts.Chart3DFactory;
import com.orsoncharts.Colors;
import com.orsoncharts.data.xyz.XYZDataset;
import com.orsoncharts.data.xyz.XYZSeries;
import com.orsoncharts.data.xyz.XYZSeriesCollection;
import com.orsoncharts.graphics3d.Dimension3D;
import com.orsoncharts.graphics3d.ViewPoint3D;
import com.orsoncharts.label.StandardXYZLabelGenerator;
import com.orsoncharts.plot.XYZPlot;
import com.orsoncharts.renderer.xyz.ScatterXYZRenderer;

public class Ising {
	
	private static Lattice reticoloIsing;
	private int size;
	
	public Ising(int size){
		reticoloIsing=new Lattice(size);
		this.size=size;
	}
	
	public Lattice getReticolo() {
		return reticoloIsing;
	}
	
	public Chart3D createChart(XYZDataset<String> dataset) {
		Chart3D chart = Chart3DFactory.createScatterChart("Ising Model Simulation", 
	                    "", dataset, "X coordinate", "Y coordinate","Flip frequency");
        XYZPlot plot = (XYZPlot) chart.getPlot();
        plot.setDimensions(new Dimension3D(10.0, 10.0, 10.0));
        plot.setLegendLabelGenerator(new StandardXYZLabelGenerator(
                StandardXYZLabelGenerator.COUNT_TEMPLATE));
       
        ScatterXYZRenderer renderer = (ScatterXYZRenderer) plot.getRenderer();
        renderer.setSize(0.15);
        renderer.setColors(Colors.createIntenseColors());
        chart.setViewPoint(ViewPoint3D.createAboveLeftViewPoint(40)); 
        return chart;
	}
	 
	 public XYZDataset<String> createDataset() {        
    	XYZSeries<String> up = initializeSeries("UP");
        XYZSeries<String> down = initializeSeries("DOWN");
        XYZSeriesCollection<String> dataset = new XYZSeriesCollection<String>();
        dataset.add(up);
        dataset.add(down);
        return dataset;
	 }

	 private XYZSeries<String> initializeSeries(String name) {
	    XYZSeries<String> s = new XYZSeries<String>(name);
	    double x=0,y=0,z=0;
	    if(name=="UP") {
	    	for (int i=0;i<size;i++) {
	    		for(int j=0;j<size;j++) {
	        		if (((reticoloIsing.getSito(i,j)).getS())>0) {
	        			x=(reticoloIsing.getSito(i,j)).getX();
	        			y=(reticoloIsing.getSito(i,j)).getY();
	        			z=(reticoloIsing.getSito(i,j)).getZ();
	        			s.add(x,y,z);
	        		}
        		}
	    	}
	     }
	        
	        if (name=="DOWN") {
	        	for (int i=0;i<size;i++) {
	        		for(int j=0;j<size;j++) {
	        		if (((reticoloIsing.getSito(i,j)).getS())<0) {
	        			x=(reticoloIsing.getSito(i,j)).getX();
	        			y=(reticoloIsing.getSito(i,j)).getY();
	        			z=(reticoloIsing.getSito(i,j)).getZ();
	        			s.add(x,y,z);
	        		}
	        	}
        	} 	
        }	 
        return s;
 	}
}