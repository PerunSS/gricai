package gameName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameName {

	static List<Character>letters = new ArrayList<Character>();
	public static void main(String[] args) {
		letters.add('r');
		letters.add('c');
		letters.add('t');
		letters.add('b');
		letters.add('c');
		letters.add('g');
		letters.add('l');
		letters.add('v');
		letters.add('o');
		letters.add('e');
		letters.add('u');
		for(int i=0;i<10;i++){
		Collections.shuffle(letters);
		System.out.println(letters);
		}
	}
}
