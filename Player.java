package janken;

import java.util.Random;

public class Player {
	private String name;
	private int winCount;
	
	public Player(String name) {
		super();
		this.name = name;
		this.winCount = 0;
	}
	
	public int showHand() {
		final int HAND_MAX = 3;
		return new Random().nextInt(HAND_MAX);
	}
	
	public void notifyResult(int result) {
		if ( result == JankenContents.Result.WIN ) {
			winCount = winCount + 1;
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getWinCount() {
		return this.winCount;
	}
	
	
}
