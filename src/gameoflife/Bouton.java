package gameoflife;

import java.awt.*;
import javax.swing.JButton;

public class Bouton extends JButton{


	private final Color LIVE = Color.blue;
	private final Color DEAD = Color.white;

	private Cell cell;

	public Bouton() {
		setBackground(Color.white);
		cell = null;
	}


	public void addCell(Cell cell) { this.cell = cell; }
	public Cell getCell()          { return cell; }

	public void changeCell() {
		if ( cell.isAlive() ) {
			cell.setAlive( false );
			setBackground(DEAD);
		} else {
			cell.setAlive( true );
			setBackground(LIVE);
		}
	}

	public void reset() {
		cell.setAlive(false);
		this.setBackground(DEAD);
	}

	public void refresh() {
		if ( cell.isAlive() ) this.setBackground(LIVE);
		else                  this.setBackground(DEAD);
	}	
}
