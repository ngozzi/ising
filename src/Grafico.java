import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;


import com.orsoncharts.Chart3D;
import com.orsoncharts.Chart3DPanel;

public class Grafico {
	private boolean running;
	private JFrame finestraPrincipale;
	private Chart3DPanel chartPanel;
	private JScrollBar temperaturaBar;
		  
	public Grafico(Ising plot) {
		 this.running= false;
		 JLabel temperaturaLabel= new JLabel("Temperatura = 2.27");
	     this.temperaturaBar= new JScrollBar (JScrollBar.HORIZONTAL,227,1,1,1000);
	     temperaturaBar.setPreferredSize(new Dimension(100,15));
	     temperaturaBar.setBlockIncrement(1); 
	     temperaturaBar.addAdjustmentListener(new AdjustmentListener() {
             public void adjustmentValueChanged(AdjustmentEvent e) {
                 temperaturaLabel.setText("Temperatura = " + temperaturaBar.getValue()/100.0);
             }
         });
       
		  
		 this.finestraPrincipale = new JFrame("Ising model");
	     finestraPrincipale.setSize(1000,1000);
		 JPanel controlPanel= new JPanel ();
		 Chart3D chart = plot.createChart(plot.createDataset());
		 this.chartPanel = new Chart3DPanel(chart);
     	 finestraPrincipale.add(chartPanel,BorderLayout.CENTER);
			
			
		 finestraPrincipale.addWindowListener(new WindowAdapter() {
		 @Override
		 public void windowClosing(WindowEvent e) {
			 super.windowClosing(e);
			 System.exit(0);
			 }
		 });
			
		 JButton start=new JButton("Start");
		 start.addMouseListener(new MouseAdapter(){
		 @Override
		 public void mousePressed(MouseEvent e) {
			 running = !(running);
			 if (running){
				 start.setText("Pause");
				 }
			 else  {
				 start.setText("Resume");
		            	  
	        	 finestraPrincipale.remove(chartPanel);
			     Chart3D chart = plot.createChart(plot.createDataset());
			     chartPanel = new Chart3DPanel(chart);
			     finestraPrincipale.add(chartPanel);}
			 }
		 });
		 
		 controlPanel.add(temperaturaLabel);
	     controlPanel.add(temperaturaBar);
		 controlPanel.add(start);
		
		 finestraPrincipale.add(controlPanel, BorderLayout.NORTH);
		 finestraPrincipale.setVisible(true);  
	 }
	 
	 public boolean getState() {
		 return running;
	 }
	 
	 public double getTemperatura() {
		 return temperaturaBar.getValue();
	 }
	 
	 public JFrame getFrame() {
		 return finestraPrincipale;
	 }
	 
	 public Chart3DPanel getChartPanel() {
		 return chartPanel;
	 }	 
}
	 
	

