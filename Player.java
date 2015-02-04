
public enum Player {
	EMPTY, RED, WHITE;
	
	public static Player switchPlayer (Player p)
	{
		if (p == Player.RED)
			return Player.WHITE;
		else if (p == Player.WHITE)
			return Player.RED;
		else
			return Player.EMPTY;
	}
	
	public static Player getOpponent (Player p) {
		return switchPlayer (p);
	}
}
