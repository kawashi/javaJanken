package janken;

import java.util.ArrayList;
import java.util.stream.Collectors;

// 複数人じゃんけん (未完成)
public class MultiJudge extends Judge {
	
	public void multiJudge(ArrayList<Player> players) {
		final ArrayList<Integer> hands = players
			.stream()
			.map( player -> player.showHand() )
			.collect( Collectors.toCollection(ArrayList::new) );
	}
	
	//　2つの抽出した手を取得する
	public ArrayList<Integer> filterHand(ArrayList<Integer> hands) {
		//　スタック用意
		final ArrayList<Integer> stack = new ArrayList<Integer>();

		for( int hand : hands ) {
			boolean flag = false;
			// 最初の1つ目は必ず add する
			if( stack.size() == 0 ) {
				stack.add(hand);
				continue;
			}
			for( int val : stack ) {
				if ( hand == val ) {
					flag = true;
				}
			}
			// statck の中に一致するものが無かったらstackに入れる
			if ( !flag ) {
				stack.add(hand);
			}
		}
		
		// 要素数が1つ(全員同じ) or 要素数が3つ(全員バラバラ)だった場合はあいこ (true)
		return stack;
	}
	
	// あいこなら true を返す
	public boolean isDraw(ArrayList<Integer> hands) {
		return hands.size() != 2;
	}
	
	// 勝っているユーザの手を返す
	public int judge(int hand1, int hand2) {
		final int judgeTable[][] = {
			{JankenContents.Result.DRAW, JankenContents.Result.LOSE, JankenContents.Result.WIN,},
			{JankenContents.Result.WIN,  JankenContents.Result.DRAW, JankenContents.Result.LOSE,},
			{JankenContents.Result.LOSE, JankenContents.Result.WIN,  JankenContents.Result.DRAW,}
		};
		
		switch(judgeTable[hand2][hand1]) {
		case JankenContents.Result.WIN:
			return hand1;
		case JankenContents.Result.LOSE:
			return hand2;
		default:
			throw new IllegalStateException();
		}
	}
	
	// 勝っているプレイヤーのみをフィルタ
	public ArrayList<Integer> filterWinPlayer(final ArrayList<Player> players, final int winHand) {
		return players
			.stream()
			.map( player -> player.showHand() )
			.filter( hand -> hand == winHand )
			.collect( Collectors.toCollection(ArrayList::new) );
	}
	
	// System.out.println が長いので省略したメソッドを用意
	public static <T> void p(T val) {
		System.out.println(val);
	}

}
