import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class InterfaceGraphique {
	
	/**
	 * Gère les changements de ruche
	 * Le type est pour l'instant String, à voir plus tard.
	 */
	private static void changerDeRuche(String ruche) {
		System.out.println("Ruche " + ruche + " sélectionnée");
	}

	/*
	 * Crée l'application graphique et gère les interruptions 
	 */
	 private static void createAndShowGUI() {
	        //Create and set up the window.
		    JFrame frame = new JFrame("Abeillix2000");
		    frame.setMinimumSize(new Dimension(320, 320));
		    frame.setLocation(new Point(320, 320));
		    frame.getContentPane().setSize(new Dimension(320, 320));
		    frame.getContentPane().setPreferredSize(new Dimension(320, 320));
		    
		    // Rajouter ici les ruches en les récupérants depuis une BD
		    String[] ruches = {"Ruche1", "Ruche2", "Ruche3"};
		    JComboBox comboBox = new JComboBox(ruches);
		    comboBox.addItemListener(new ItemListener() {		 
		    	public void itemStateChanged(ItemEvent e) {
		    		changerDeRuche((String) e.getItem().toString());
		    	}
		    });
		    frame.getContentPane().add(comboBox, BorderLayout.NORTH);
		    
		    JButton btnNewButton = new JButton("Mettre à jour");
		    btnNewButton.addMouseListener(new MouseAdapter() {
		    	@Override
		    	public void mouseClicked(MouseEvent e) {
		    		System.out.println("Mis à jour");
		    	}
		    });
		    btnNewButton.setLocation(new Point(0, 15));
		    btnNewButton.setVerticalAlignment(SwingConstants.TOP);
		    frame.getContentPane().add(btnNewButton, BorderLayout.SOUTH);
		    
		    // Exit, à rajouter à la fin
	        JMenuBar menuBar = new JMenuBar();
	        frame.setJMenuBar(menuBar);
	        
	        JMenu mnView = new JMenu("File");
	        menuBar.add(mnView);
	        
	        JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
	        mnView.add(mntmNewMenuItem);
	        
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
