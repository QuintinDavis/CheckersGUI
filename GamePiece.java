import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;


public abstract class GamePiece implements PlayableGamePiece
{
	protected final static double DIST_FROM_EDGE = 0.1;
	protected final static int LINE_WIDTH = 5;

	protected boolean selected = false;
	protected Position pos = null;
	protected Player player = Player.EMPTY;

	public GamePiece (int row, int col, Player p) {
		pos = new Position (row, col);
		player = p;
	}

	public GamePiece (Position pos, Player p) {
		this (pos.r, pos.c, p);
	}

	public Position getPosition () {
		return pos;
	}

	public void setPosition (Position pos) {
		pos = new Position (pos);
	}
	
	public Player getPlayer () {
		return player;
	}

	public void setPieceSelected (boolean b)
	{
		if ((b == true) && (player != Player.EMPTY))
			selected = true;
		else
			selected = false;
	}

	public boolean isPieceSelected() {
		return selected;
	}

	public boolean validMove (int targetRow, int targetCol, GameSquares squares) {
		return validMove (new Position (targetRow, targetCol), squares);
	}
	
	public boolean validMove (Position targetPos, GameSquares squares)
	{
		Position dp = targetPos.offset (pos);
		int dr = dp.r;
		int dc = dp.c;
		
		if (validNonJump (dr, dc, squares))
			return true;
		else if (validJump (dr, dc, squares))
			return true;
				
		return false;
	}
	
	protected abstract boolean validNonJump (int dr, int dc, GameSquares squares);	
	
	protected abstract boolean validJump (int dr, int dc, GameSquares squares);
	
	public void move (Position newPos, GameSquares squares)
	{
		Position dp = newPos.offset (pos);
		int dr = dp.r;
		int dc = dp.c;
		
		if (validNonJump (dr, dc, squares)){
			pos = new Position (newPos);
		}
		else if (validJump (dr, dc, squares))
		{
			squares.getSquare (pos.r + dr/2, pos.c + dc/2).setPiece (null);
			pos = new Position (newPos);
		}
	}
	
	public abstract void drawPiece (Graphics2D g2, int x, int y, int width, int height);
}
