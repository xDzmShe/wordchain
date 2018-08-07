package com.wordchain;

import java.util.List;

public interface WordChainFinder {
	public List<String> findShortestWordChainBetweenTwoWords(String startWord, String endWord);
}