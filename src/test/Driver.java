package test;

public class Driver {
	public static void main(String[] args) {
		WordScorer wordScorer=new WordScorer();
		wordScorer.sortWords("C:\\Users\\test\\Desktop\\Team13\\sowpods.txt");
		wordScorer.writeSortedWordsToFile("C:\\Users\\test\\Desktop\\sorted_words.txt");
	}
}
