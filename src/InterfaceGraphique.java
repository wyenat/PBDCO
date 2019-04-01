import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.awt.Dimension;
import java.awt.Point;

public class InterfaceGraphique {
	
	 private static void createAndShowGUI() {
	        //Create and set up the window.
		    JFrame frame = new JFrame("Abeillix2000");
		    frame.setMinimumSize(new Dimension(320, 320));
		    frame.setLocation(new Point(320, 320));
		    frame.getContentPane().setSize(new Dimension(320, 320));
		    frame.getContentPane().setPreferredSize(new Dimension(320, 320));
	        
	        JMenuBar menuBar = new JMenuBar();
	        frame.setJMenuBar(menuBar);
	        
	        JMenu mnView = new JMenu("Ruche");
	        menuBar.add(mnView);
	        
	        JCheckBoxMenuItem chckbxmntmNewCheckItem = new JCheckBoxMenuItem("Show id");
	        mnView.add(chckbxmntmNewCheckItem);
	        
	        frame.setVisible(true);

	    }
	
	 public static void main(String[] args){
		    //Schedule a job for the event-dispatching thread:
	        //creating and showing this application's GUI.
	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                createAndShowGUI();
	            }
	        });
		  }       
}
