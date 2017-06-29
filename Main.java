package janken;

import java.util.ArrayList;

public class Main {

	// System.out.println が長いので省略したメソッドを用意
	public static <T> void p(T val) {
		System.out.println(val);
	}
	
	// TODO: 追加課題まだ
	public static void main(String[] args) {
		Player player1 = new Player("プレイヤー1");
		Player player2 = new Player("プレイヤー2");
		Judge  kawashi = new Judge();
		kawashi.startJanken(player1, player2);
	}

}
