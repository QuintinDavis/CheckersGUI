import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;


public class RedKingGamePiece extends RedGamePiece{

	public RedKingGamePiece(int row, int col) {
		super(row, col);
	}
	
	public RedKingGamePiece (Position pos) {
		this (pos.r, pos.c);
	}

	protected boolean validNonJump (int dr, int dc, GameSquares squares)
	{
		if ((dr == 1) && ((dc == 1) || (dc == -1))||(dr == -1) && ((dc == 1) || (dc == -1)))
		{
			if (squares.getSquare(pos.r + dr, pos.c + dc).getPiece() == null)
				return true;
		}
		
		return false;
	}
	
	protected boolean validJump (int dr, int dc, GameSquares squares)
	{
		if ((dr == 2) && ((dc == 2) || (dc == -2))||(dr == -2) && ((dc == 2) || (dc == -2)))
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
		
		Point2D.Double k1 = //points for crown
				new Point2D.Double(x + width/2 - width*.15, y + height/2-height*.1);
		Point2D.Double k2 =
				new Point2D.Double(x + width/2 - width*.15, y + height/2+height*.1);
		Line2D.Double K1 = new Line2D.Double(k1, k2);
		Point2D.Double k3 =
				new Point2D.Double(x + width/2 + width*.15, y + height/2-height*.1);
		Point2D.Double k4 =
				new Point2D.Double(x + width/2 + width*.15, y + height/2+height*.1);
		Line2D.Double K2 = new Line2D.Double(k3, k4);
		Line2D.Double K3 = new Line2D.Double(k2, k4);
		Point2D.Double k5 =
				new Point2D.Double(x + width/2 + width*.075, y + height/2);
		Point2D.Double k6 =
				new Point2D.Double(x + width/2 - width*.075, y + height/2);
		Point2D.Double k7 =
				new Point2D.Double(x + width/2, y + height/2-height*.1);
		Line2D.Double K4 = new Line2D.Double(k1, k6);
		Line2D.Double K5 = new Line2D.Double(k3, k5);
		Line2D.Double K6 = new Line2D.Double(k5, k7);
		Line2D.Double K7 = new Line2D.Double(k6, k7);
		
		g2.setColor (Color.RED);
		BasicStroke stroke = new BasicStroke (LINE_WIDTH);
		g2.setStroke(stroke);
		g2.fill (outline);

		if (selected)
		{
			g2.setColor (Color.GREEN);
			g2.draw (outline);
		}
		BasicStroke stroke2 = new BasicStroke (1);
		g2.setStroke(stroke2);
		g2.setColor(Color.BLACK);
		g2.draw(K1); g2.draw(K2); g2.draw(K3);
		g2.draw(K4); g2.draw(K5); g2.draw(K6); g2.draw(K7);

	}
}
