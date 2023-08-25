package gameutil;

import java.util.ArrayList;
import java.util.Collections;

public class RandomUniqueNumbers {
	
	public static ArrayList<Integer> generate(int size, int n, int numAvoid) {
		ArrayList<Integer> allNumbers = new ArrayList<Integer>(size - 1);
		for (int i = 0; i < size; i++)
			if (i != numAvoid)
				allNumbers.add(i);
		
		Collections.shuffle(allNumbers);
		
		ArrayList<Integer> randomNumbers = new ArrayList<Integer>(n);
		for (int i = 0; i < n; i++)
			randomNumbers.add(allNumbers.get(i));
		
		return randomNumbers;
	}
}
