package com.wordchain;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FixedLengthWordChainFinder implements WordChainFinder {

	private final String PATH_TO_DICTIONARY = "wordlist.txt";

	/**
	 * Find word chain from startWord to endWord
	 * 
	 * @param startWord
	 *            First word in the chain
	 * @param endWord
	 *            Last word in the chain
	 * 
	 */
	public List<String> findChain(final String startWord, final String endWord) {
		if (startWord.equals(endWord)) {
			System.out.println("Both words are identical");
			return null;
		}

		Map<String, String> listOfPrevious = new HashMap<>();
		boolean isPathFound = false;

		// build tree of words that different from each other only by one character
		// store path from bottom of the tree to the root in listOfPrevious

		if (isPathFound) {
			return getPath(listOfPrevious, startWord, endWord);
		} else {
			return null;
		}
	}

	/**
	 * Loads words from file
	 * 
	 * @param wordLength
	 *            Length of words that should be load from the dictionary
	 */
	List<String> loadDictionary(final int wordLength) {
		List<String> wordList = null;

		try (Stream<String> stream = Files
				.lines(Paths.get(getClass().getClassLoader().getResource(PATH_TO_DICTIONARY).toURI()))) {

			wordList = stream.filter(s -> s.length() == wordLength).collect(Collectors.toList());

		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		return wordList;

	}

	/**
	 * Get path from startWord to endWord based on listOfPrevious
	 * 
	 * @param listOfPrevious
	 *            Contains map with word pairs from bottom of the tree to the root
	 * @param startWord
	 *            First word in the chain
	 * @param endWord
	 *            Last word in the chain
	 * 
	 */
	List<String> getPath(final Map<String, String> listOfPrevious, final String startWord, final String endWord) {
		return null;
	}
}
