import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

public class GameSquare extends JComponent
{
	private int x;
	private int y;

	private Position pos = null;
	private Color squareColor;

	private GamePiece piece = null;

	public GameSquare (int row, int col, Color squareColor)
	{
		super ();

		pos = new Position (row, col);
		this.squareColor = squareColor;
	}

	public GameSquare (Position pos, Color squareColor) {
		this (pos.r, pos.c, squareColor);
	}

	public Position getPosition () {
		return pos;
	}

	public void setPiece (GamePiece p) {
		piece = p;
	}

	public GamePiece getPiece () {
		return piece;
	}

	public void paintComponent (Graphics g)
	{
		super.paintComponents (g);

		Graphics2D g2 = (Graphics2D) g;

		int height = getHeight();
		int width = getWidth();

		Rectangle2D.Double bkgnd = new Rectangle2D.Double (x, y, width, height);
		g2.setColor (squareColor);
		g2.fill (bkgnd);

		if (piece != null)
			piece.drawPiece (g2, x, y, width, height);
	}   
}
