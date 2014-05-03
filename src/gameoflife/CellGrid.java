package gameoflife;


public class CellGrid {

	private final int nbCol;
	private final int nbLig;
	private Cell[][] tabCell;

	public CellGrid( int col, int lig) {
		nbCol = col;
		nbLig = lig;
		tabCell = new Cell[nbCol][nbLig];

		for ( int i=0; i<nbLig; i++ ) {
			for (int j=0; j<nbCol; j++) {
				tabCell[j][i] = new Cell();
			}
		}
	}

	public Cell getCell( int c, int l) { return tabCell[c][l]; }


	public void suivant() {



		for ( int i=0; i<nbLig; i++) {
			for (int j=0; j<nbCol; j++) {

				tabCell[j][i].resetVoisin();
				try {
					if ( tabCell[j+1][i+1].isAlive() ) tabCell[j][i].addVoisin();
					if ( tabCell[j-1][i-1].isAlive() ) tabCell[j][i].addVoisin();
					if ( tabCell[j+1][i-1].isAlive() ) tabCell[j][i].addVoisin();
					if ( tabCell[j-1][i+1].isAlive() ) tabCell[j][i].addVoisin();
					if ( tabCell[j-1][i].isAlive()   ) tabCell[j][i].addVoisin();
					if ( tabCell[j+1][i].isAlive()   ) tabCell[j][i].addVoisin();
					if ( tabCell[j][i+1].isAlive()   ) tabCell[j][i].addVoisin();
					if ( tabCell[j][i-1].isAlive()   ) tabCell[j][i].addVoisin();

				} catch ( IndexOutOfBoundsException a ){}
			  }
		}

		Cell[][] nextTab = tabCell;
		for ( int i=0; i<nbLig; i++) {
			for (int j=0; j<nbCol; j++) {
				if ( !tabCell[j][i].isAlive() && tabCell[j][i].getNbVoisin() == 3 )
					nextTab[j][i].setAlive(true);
				else if ( tabCell[j][i].isAlive() && ( tabCell[j][i].getNbVoisin() < 2 || tabCell[j][i].getNbVoisin() > 3 ) )
					nextTab[j][i].setAlive(false);
			}
	   }

		tabCell = nextTab;
	}
}
