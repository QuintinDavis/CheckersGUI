import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
   A component that shows a scene composed of shapes.
 */
public class CheckerBoard extends JPanel
{
	protected final static int SQUARES_1D = 8;

	private GameSquares squares;

	private Player player;
	private GamePiece selectedPiece = null;

	public CheckerBoard()
	{
		super ();
		player = player.RED;//Red plays first
		setLayout(new GridLayout (8,8,0,0));

		squares = new GameSquares();

		int numPieceSpots = 0;

		for (int r = 0; r < SQUARES_1D; r++)
			for (int c = 0; c < SQUARES_1D; c++)
			{
				if ((r + c) % 2 == 1)
					squares.setSquare (new GameSquare (r, c, new Color(229, 180, 130)));
				else
				{
					squares.setSquare (new GameSquare (r, c, new Color(140, 70, 0)));

					if (numPieceSpots <= 11)
						squares.getSquare(r, c).setPiece (new WhiteGamePiece (r, c));
					
					if (numPieceSpots >= 20)
						squares.getSquare(r, c).setPiece (new RedGamePiece (r, c));

					numPieceSpots++;

					final GameSquare sq = squares.getSquare(r, c);
					sq.addMouseListener (new GameSquareMouseListener (sq));
				}

				add (squares.getSquare(r, c));
			}
	}

	private void changePlayer() {
		player = Player.switchPlayer (player);
	}
	
	public void reset(){
		player = player.RED;//Red plays first
		int numPieceSpots = 0;
		for (int r = 0; r < SQUARES_1D; r++){
			for (int c = 0; c < SQUARES_1D; c++){
				squares.getSquare(r, c).setPiece(null);
				if((r + c) % 2 != 1){
					if (numPieceSpots <= 11)
						squares.getSquare(r, c).setPiece (new WhiteGamePiece (r, c));

					if (numPieceSpots >= 20)
						squares.getSquare(r, c).setPiece (new RedGamePiece (r, c));

					numPieceSpots++;
				}
			}
		}
		repaint();
	}

	private void checkVictory(){
		boolean redRemains = false;
		boolean whiteRemains = false;
		for (int r = 0; r < SQUARES_1D; r++){
			for (int c = 0; c < SQUARES_1D; c++){
				if(squares.getSquare(r,c).getPiece()!=null){
					if(squares.getSquare(r, c).getPiece().player==player.RED){
						redRemains = true;
					}
					if(squares.getSquare(r, c).getPiece().player==player.WHITE){
						whiteRemains = true;
					}	
				}
			}
		}
		if(redRemains==false||whiteRemains==false){
			String result = "";
			if(redRemains==false){result="White Wins! ";}
			if(whiteRemains==false){result="Red Wins! ";}
			Object[] options = {"No, close game","Yes, new game"};
			int choice = JOptionPane.showOptionDialog(this,
				    result+"Would you to start a new game?",
				    "Game Over",
				    JOptionPane.YES_NO_OPTION,
				    JOptionPane.QUESTION_MESSAGE,
				    null,
				    options,
				    options[0]);
			if(choice==1){
				reset();
			}
			else if(choice==0){
				System.exit(0);
			}
		}
	}
	
	public class GameSquareMouseListener extends MouseAdapter
	{
		GameSquare sq;

		public GameSquareMouseListener (GameSquare sq) {
			this.sq = sq;
		}
		
		public void mousePressed (MouseEvent event)
		{
			Point mousePoint = event.getPoint();

			if (sq.contains (mousePoint))
			{
				if (selectedPiece == null)
				{
					if (sq.getPiece() != null)
					{
						selectedPiece = sq.getPiece();
						if(selectedPiece.player!=player){
							selectedPiece = null;
							return;
						}
						sq.getPiece().setPieceSelected (true);
					}
				}
				else
				{
					Position pos = sq.getPosition();
					GameSquare selSq = squares.getSquare (selectedPiece.getPosition());

					if (selectedPiece.getPosition().equals (sq.getPosition()))
					{
						selectedPiece.setPieceSelected(false);
						selectedPiece = null;
					}
					else if (selectedPiece.validMove (pos, squares))
					{
						selectedPiece.move (pos, squares);// move selected piece
						if(pos.r==7&&selectedPiece.player==Player.WHITE){
							selectedPiece = new WhiteKingGamePiece (pos);
						}
						if(pos.r==0&&selectedPiece.player==Player.RED){
							selectedPiece = new RedKingGamePiece (pos);
						}
						sq.setPiece (selectedPiece);			// new square containing selected piece
						
						selSq.setPiece (null);// square that used to contain selected piece
						changePlayer();
						checkVictory();
						selectedPiece.setPieceSelected(false);	// deselect
						selectedPiece = null;					//  "    "
						
					}
				}
			}
			
			repaint();
		}
	}
}
