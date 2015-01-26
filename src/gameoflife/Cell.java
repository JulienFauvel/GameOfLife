package gameoflife;

/**
 * Cellule pouvant etre soit vivante ou non
 */
public class Cell {
	
	private boolean isAlive;
	
	//Nombre de voisins autours de la cellule
	private int nbVoisin;


	public Cell() {
		isAlive = false;
		nbVoisin = 0;
	}

	
	public boolean isAlive () { 
		return isAlive;
	}
	
	public int getNbVoisin () {
		return this.nbVoisin;
	}
	
	public void resetVoisin() {
		nbVoisin = 0;
	}
	
	public void addVoisin  () { 
		nbVoisin++;
	}
	
	public void setAlive( boolean b ) {
		isAlive = b;
	}

}
