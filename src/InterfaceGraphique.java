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
	

	static int i = 0;
	/**
	 * Gère les changements de ruche
	 * Le type est pour l'instant String, à voir plus tard.
	 */
	private static void changerDeRuche(String ruche) {
		if ((i++)%2 == 1) {
			System.out.println("Ruche : " + ruche + " sélectionnée");
		}
	}
	
	/**
	 * Met à jour les données prises dans la BD
	 */
	private static void miseAJour() {
		//TODO
		System.out.println("Mis à jour !");
	}

	/**
	 * Permet d'enregistrer une intervention
	 */
	private static void enregistrerUneIntervention() {
		//TODO
		System.out.println("Intervention enregistrée");
	}
	
	/**
	 * 
	 */
	static int j = 0;
	private static void selectionCadre(String cadre) {
		if ((j++)%2 == 1) {
			System.out.println("Cadre: " + cadre + " sélectionné");
		}
	}
	
	/**
	 * Crée l'application graphique et gère les interruptions 
	 */
	 private static void createAndShowGUI() {
	        //Create and set up the window.
		    JFrame frame = new JFrame("Abeillix2000");
		    frame.setMinimumSize(new Dimension(320, 320));
		    frame.setLocation(new Point(320, 320));
		    frame.getContentPane().setSize(new Dimension(450, 400));
		    frame.getContentPane().setPreferredSize(new Dimension(450, 400));
		    
		    // Rajouter ici les ruches en les récupérants depuis une BD
		    String[] ruches = {"Ruche1", "Ruche2", "Ruche3"};
		    JComboBox comboBox = new JComboBox(ruches);
		    comboBox.addItemListener(new ItemListener() {		 
		    	public void itemStateChanged(ItemEvent e) {
		    		changerDeRuche((String) e.getItem().toString());
		    	}
		    });
		    frame.getContentPane().add(comboBox, BorderLayout.NORTH);
		    
		    
		    
		    // BOUTONS
		    JSplitPane splitPane = new JSplitPane();
		    frame.getContentPane().add(splitPane, BorderLayout.SOUTH);
		    
		    JButton button = new JButton("Mettre à jour");
		    button.addMouseListener(new MouseAdapter() {
		    	@Override
		    	public void mouseClicked(MouseEvent e) {
		    		miseAJour();
		    	}
		    });
		    button.setVerticalAlignment(SwingConstants.TOP);
		    button.setLocation(new Point(0, 15));
		    splitPane.setLeftComponent(button);
		    
		    JButton btnEnregistrerUneInformation = new JButton("Enregistrer une intervention");
		    btnEnregistrerUneInformation.addMouseListener(new MouseAdapter() {
		    	@Override
		    	public void mouseClicked(MouseEvent e) {
		    		enregistrerUneIntervention();
		    	}
		    });
		    splitPane.setRightComponent(btnEnregistrerUneInformation);
		    // FIN BOUTONS
		    
		    
		    
		    // Informations
		    JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		    frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		    
		    JPanel panelRuche = new JPanel();
		    tabbedPane.addTab("Ruche", null, panelRuche, null);
		    String[] infoRuche = {"numéro", "nombre de hausses"}; 
		    JList listRuche = new JList(infoRuche);
		    panelRuche.add(listRuche);
		    
		    
		    JPanel panelEssaim = new JPanel();
		    tabbedPane.addTab("Essaim", null, panelEssaim, null);
		    String[] infoEssaim = {"Température", "Couleur de la reine", "Race", "Âge"}; 
		    JList listEssaim = new JList(infoEssaim);
		    panelEssaim.add(listEssaim);
		    
		    JPanel panelHausses = new JPanel();
		    tabbedPane.addTab("Hausses", null, panelHausses, null);
		    String[] selectCadre = {"Cadre 1", "Cadre 2", "Cadre 3"};
		    JComboBox comboBoxCadres = new JComboBox(selectCadre);
		    comboBoxCadres.addItemListener(new ItemListener() {
		    	public void itemStateChanged(ItemEvent e) {
		    		selectionCadre((String) e.getItem().toString());
		    	}
		    });
		    panelHausses.add(comboBoxCadres);
		    String[] infoHausses = {"Numéro de la hausse", "Matière", "Couleur", "Poids total"}; 
		    JList listHausses = new JList(infoHausses);
		    panelHausses.add(listHausses);
		    
		    // fin informations
		    
		    
		    
		    // MENU
	        JMenuBar menuBar = new JMenuBar();
	        frame.setJMenuBar(menuBar);
	        JMenu mnView = new JMenu("File");
	        menuBar.add(mnView);
	        JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
	        mnView.add(mntmNewMenuItem);
	        // FIN MENU
	        
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
