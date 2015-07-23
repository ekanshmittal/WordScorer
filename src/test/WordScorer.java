package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class WordScorer {
	Map<Integer, String> scoreVsWord;

	public WordScorer() {
		scoreVsWord = new HashMap<Integer, String>();
	}

	public void sortWords(String fileName) {
		File file = new File(fileName);
		String word;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			while ((word = reader.readLine()) != null) {
				word = word.trim();
				int score = calculateScore(word);
				insertToMap(score, word);
			}
			reader.close();
		} catch (Exception e) {
			System.out.println("Unable to open Input file");
		}
	}

	public void writeSortedWordsToFile(String fileName) {
		File file = new File(fileName);
		try {
			PrintWriter writer = new PrintWriter(file, "UTF-8");
			Set<Integer> scoresSet = scoreVsWord.keySet();
			ArrayList<Integer> scoresList = new ArrayList<Integer>(scoresSet);
			Collections.sort(scoresList);
			Iterator<Integer> iterator = scoresList.iterator();
			while (iterator.hasNext()) {
				Integer score = iterator.next();
				writer.write((score * -1) + " " + scoreVsWord.get(score) + "\n");
			}
			writer.close();
		} catch (Exception e) {
			System.out.println("Error in writing output to " + fileName);
		}
		System.out.println("Output written to file");
	}

	private Integer calculateScore(String word) {
		char[] characterArray = word.toCharArray();
		int score = 0;
		for (char character : characterArray) {
			score += (Character.toUpperCase(character) + 1) - 'A';
		}
		return score * -1;
	}

	private void insertToMap(Integer score, String word) {
		if (scoreVsWord.containsKey(score)) {
			word = scoreVsWord.get(score) + " " + word;
		}
		scoreVsWord.put(score, word);
	}

}
