package janken;

public class Judge {
	public void judgeJanken(Player player1, Player player2) {
		final int player1Result = this.judge(player1.showHand(), player2.showHand());
		final int player2Result = this.flipResult(player1Result);
		player1.notifyResult(player1Result);
		player2.notifyResult(player2Result);
		this.callWinner(player1.getName(), player2.getName(), player1Result);
	}
	
	public void startJanken(Player player1, Player player2) {
		final int BATTLE_TIMES = 3;
		for( int i=0 ; i<BATTLE_TIMES ; i++ ) {
			this.judgeJanken(player1, player2);
		}
		
		// 総合勝ち数が多いプレイヤーを表示
		if ( player1.getWinCount() > player2.getWinCount() ) {
			System.out.println(player1.getName() + "の勝ち");
		} else if (player1.getWinCount() < player2.getWinCount() ) {
			System.out.println(player2.getName() + "の勝ち");
		} else {
			System.out.println("引き分け");
		}
	}
	
	// 1番目のユーザの勝敗を返す
	private int judge(int hand1, int hand2) {
		final int judgeTable[][] = {
			{JankenContents.Result.DRAW, JankenContents.Result.LOSE, JankenContents.Result.WIN,},
			{JankenContents.Result.WIN,  JankenContents.Result.DRAW, JankenContents.Result.LOSE,},
			{JankenContents.Result.LOSE, JankenContents.Result.WIN,  JankenContents.Result.DRAW,}
		};
		
		System.out.println("プレイヤー1の手: " + handToString(hand1));
		System.out.println("プレイヤー2の手: " + handToString(hand2));
		
		return judgeTable[hand2][hand1];
	}
	
	// 1番目のユーザの勝敗から2番目のユーザの勝敗を返す
	private int flipResult(int result){
		switch(result) {
		case JankenContents.Result.WIN:
			return JankenContents.Result.LOSE;
		case JankenContents.Result.LOSE:
			return JankenContents.Result.WIN;
		default:
			return JankenContents.Result.DRAW;
		}
	}
	
	// 整数のhandを文字列に変換する
	private String handToString(int hand) {
		switch(hand) {
		case JankenContents.Hand.GU:
			return "グー";
		case JankenContents.Hand.CHOKI:
			return "チョキ";
		case JankenContents.Hand.PA:
			return "パー";
		default:
			return "該当無し";
		}
	}
	
	// 勝者を表示
	private void callWinner(String player1Name, String player2Name, int player1Result) {
		switch(player1Result) {
		case JankenContents.Result.WIN:
			System.out.println("勝者は、" + player1Name + " です。");
			break;
		case JankenContents.Result.LOSE:
			System.out.println("勝者は、" + player2Name + " です。");
			break;
		default:
			System.out.println("あいこでした。");
			break;
		}
		System.out.println();
	}
}
