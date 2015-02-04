import java.awt.*;
import java.awt.geom.*;

/**
   Defines the interface and common functionality of a TicTacToe game piece.
*/
public interface PlayableGamePiece
{
	public boolean validMove (int dr, int dc, GameSquares squares);
	public boolean validMove (Position dp, GameSquares squares);
	
	public void setPieceSelected (boolean b);
	public boolean isPieceSelected ();
}
