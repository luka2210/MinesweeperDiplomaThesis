package gameutil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class RandomUniqueNumbers {
	
	public static ArrayList<Integer> generate(int size, int n, ArrayList<Integer> numAvoid) {
		ArrayList<Integer> allNumbers = new ArrayList<Integer>();
		for (int i = 0; i < size; i++)
			if (!numAvoid.contains(i))
				allNumbers.add(i);
		
		Collections.shuffle(allNumbers);
		
		ArrayList<Integer> randomNumbers = new ArrayList<Integer>(n);
		Random random = new Random();
		for (int i = 0; i < n; i++) {
			int index = random.nextInt(allNumbers.size());
			randomNumbers.add(allNumbers.get(index));
			allNumbers.remove(index);
		}
		
		return randomNumbers;
	}
}
