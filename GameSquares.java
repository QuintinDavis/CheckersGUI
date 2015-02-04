
public class GameSquares
{
	private GameSquare[][] squares;

	public GameSquares () {
		squares = new GameSquare[CheckerBoard.SQUARES_1D][CheckerBoard.SQUARES_1D];
	}
	
	public GameSquare getSquare (int row, int col) {
		if (validPosition (row, col))
			return squares[row][col];
		else
			return null;
	}
	
	public void setSquare (GameSquare sq)
	{
		Position pos = sq.getPosition();
		
		if (validPosition (pos))
			squares[pos.r][pos.c]= sq;
	}
	
	public GameSquare getSquare (Position pos) {
		return getSquare (pos.r, pos.c);
	}
	
	protected boolean validPosition (int row, int col)
	{
		if ( row < 0)
			return false;
		else if (row >= CheckerBoard.SQUARES_1D)
			return false;
			
		if (col < 0)
			return false;
		else if (col >= CheckerBoard.SQUARES_1D)
			return false;
		
		return true;
	}
	
	protected boolean validPosition (Position pos) {
		return validPosition (pos.r, pos.c);
	}

}
