import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;


public class WhiteGamePiece extends GamePiece
{
	public WhiteGamePiece (int row, int col) {
		super (row, col, Player.WHITE);
	}

	public WhiteGamePiece (Position pos) {
		this (pos.r, pos.c);
	}
	
	protected boolean validNonJump (int dr, int dc, GameSquares squares)
	{
		if ((dr == 1) && ((dc == 1) || (dc == -1)))
		{
			if (squares.getSquare(pos.r + dr, pos.c + dc).getPiece() == null)
				return true;
		}
		return false;
	}
	
	protected boolean validJump (int dr, int dc, GameSquares squares)
	{
		if ((dr == 2) && ((dc == 2) || (dc == -2)))
		{
			if ((squares.getSquare(pos.r + dr, pos.c + dc).getPiece() == null)
					&& 	(squares.getSquare(pos.r + dr/2, pos.c + dc/2).getPiece() != null)
					&& 	(squares.getSquare(pos.r + dr/2, pos.c + dc/2).getPiece().getPlayer() == Player.getOpponent(player)))
				return true;
		}
		
		return false;
	}
	
	public void drawPiece (Graphics2D g2, int x, int y, int width, int height)
	{
		Ellipse2D.Double outline = new Ellipse2D.Double (x + width * DIST_FROM_EDGE + LINE_WIDTH / 2,
														 y + height * DIST_FROM_EDGE + LINE_WIDTH / 2,
														 width * (1 - 2 * DIST_FROM_EDGE) - LINE_WIDTH,
														 height * (1 - 2 * DIST_FROM_EDGE) - LINE_WIDTH);

		g2.setColor (Color.WHITE);

		BasicStroke stroke = new BasicStroke (LINE_WIDTH);
		g2.setStroke(stroke);
		g2.fill (outline);

		if (selected)
		{
			g2.setColor (Color.GREEN);
			g2.draw (outline);
		}
		
		

	}
}
