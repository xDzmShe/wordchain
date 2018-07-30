package com.wordchain;

import java.util.List;

public class FixedLengthWordChainFinder implements WordChainFinder {

	private final String PATH_TO_DICTIONARY = "wordlist.txt";

	public List<String> findChain(final String startWord, final String endWord) {
		List<String> wordList = loadDictionary();
		// findPath
		return null;
	}
	
	List<String> loadDictionary() {
		List<String> wordList = null;
		return wordList;
	}
}
