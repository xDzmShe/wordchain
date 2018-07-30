package com.wordchain;

import java.util.List;

public interface WordChainFinder {
	public List<String> findChain(String startWord, String endWord);
}