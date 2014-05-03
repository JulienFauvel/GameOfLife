package gameoflife;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


public class Fenetre extends JFrame implements ActionListener, Runnable {

	private static final long serialVersionUID = 1L;

	private CellGrid cg;

	private Bouton[][] tabBouton;

	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JButton b4;
	private JButton vPl;
	private JButton vMo;

	private final int NB_COL = 25;
	private final int NB_LIG = 25;

	private boolean actif = false;
	private Thread t = new Thread();

	private int vitesse = 750;

	public Fenetre() {
		setTitle("Game Of Life");
		setSize(new Dimension(600,500));
		setLocationRelativeTo(null);

		init();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setVisible(true);
	}

	/**
	 * Initialise la grille de cellule ainsi que le tableau de bouton
	 */
	private void init() {
		
		JPanel p1 = new JPanel();
		p1.setLayout( new GridLayout(NB_LIG , NB_COL , 0 , 0));
		p1.setBackground(new Color(0x999999));

		JPanel p2 = new JPanel();

		add(p1);
		add(p2, BorderLayout.SOUTH);

		cg = new CellGrid(NB_COL, NB_LIG);

		this.tabBouton = new Bouton[NB_COL][NB_LIG];

		for ( int i=0; i<this.NB_LIG; i++) {
			for ( int j=0; j<this.NB_COL; j++) {
				tabBouton[j][i] = new Bouton();
				tabBouton[j][i].addCell( cg.getCell(j, i) );
				tabBouton[j][i].addActionListener(this);
				p1.add(tabBouton[j][i]);
			}
		}

		b1 = new JButton("GO");
		b1.addActionListener(this);
		p2.add(b1);

		b2 = new JButton("Stop");
		b2.addActionListener(this);
		b2.setEnabled(false);
		p2.add(b2);

		b3 = new JButton("Suivant");
		b3.addActionListener(this);
		p2.add(b3);

		vMo = new JButton("Accelérer");
		vMo.addActionListener(this);
		vMo.setEnabled(false);
		p2.add(vMo);

		vPl = new JButton("Ralentir");
		vPl.addActionListener(this);
		vPl.setEnabled(false);
		p2.add(vPl);
		
		b4 = new JButton("Reset");
		b4.addActionListener(this);
		p2.add(b4);
	}

	/**
	 * Rafraichi l'affichage des boutons correspondant aux cellules
	 */
	private void refresh() {
		for ( int i=0; i<this.NB_LIG; i++) {
			for ( int j=0; j<this.NB_COL; j++)
				tabBouton[j][i].refresh();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ( e.getSource() == b1 ) {
			if ( !actif ) {
				actif = true;
				t = new Thread(this);
				t.start();
				b1.setEnabled(false);
				b2.setEnabled(true);
				b3.setEnabled(false);
				vPl.setEnabled(true);
				vMo.setEnabled(true);
			}
		}

		else if( e.getSource() == b2) {
			actif = false;
			b1.setEnabled(true);
			b2.setEnabled(false);
			b3.setEnabled(true);
			vPl.setEnabled(false);
			vMo.setEnabled(false);
		}

		else if( e.getSource() == b3) {
			if ( !t.isAlive() ) {
				cg.suivant();
				this.refresh();
			}
		}

		else if( e.getSource() == b4) {
			for ( int i=0; i<this.NB_LIG; i++) {
				for ( int j=0; j<this.NB_COL; j++)
					tabBouton[j][i].reset();
			}
		}


		else if ( e.getSource() == vPl || e.getSource() == vMo) {
			if ( e.getSource() == vPl  && vitesse < 1550 ) vitesse += 100;
			if ( e.getSource() == vMo  && vitesse >   50 ) vitesse -= 100; 
		}

		else {
			for ( int i=0; i<this.NB_LIG; i++) {
				for ( int j=0; j<this.NB_COL; j++)
					if( e.getSource() == tabBouton[j][i] )
						tabBouton[j][i].changeCell();
			}
		}  
	}

	@Override
	public void run() {
		while( actif ) {
			cg.suivant();
			this.refresh();
			try { Thread.sleep(vitesse); } catch(InterruptedException ex){} 
		}
	}

}
