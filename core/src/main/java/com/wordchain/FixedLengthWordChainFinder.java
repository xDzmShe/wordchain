package com.wordchain;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

@Component
public class FixedLengthWordChainFinder implements WordChainFinder {

	private final String PATH_TO_DICTIONARY = "wordlist.txt";

	/**
	 * Finds word chain from startWord to endWord
	 * 
	 * @param startWord
	 *            First word in the chain
	 * @param endWord
	 *            Last word in the chain
	 */
	public List<String> findShortestWordChainBetweenTwoWords(final String startWord, final String endWord) {
		if (startWord.equals(endWord)) {
			System.out.println("Both words are identical");
			return null;
		}

		List<String> dictionary = loadListOfWordsWithGivenLengthFromFile(startWord.length());

		Map<String, String> mapWithLinkToPreviousWordInChain = new HashMap<>();
		boolean isPathFound = false;

		Queue<String> queue = new LinkedList<String>();
		queue.add(startWord);

		// build a tree of words that different from each other only by one character
		// store path from bottom of the tree to the root in
		// mapWithPathToPreviousWordInChain
		while (!queue.isEmpty() && isPathFound == false) {
			String currentWord = queue.remove();
			List<String> listOfNextWords = getListOfWordsThatDifferByOneCharacterOnly(currentWord, dictionary);

			for (String word : listOfNextWords) {

				if (word.equals(endWord)) {
					mapWithLinkToPreviousWordInChain.put(word, currentWord);
					isPathFound = true;
					break;
				}

				if (!mapWithLinkToPreviousWordInChain.containsKey(word)) {
					mapWithLinkToPreviousWordInChain.put(word, currentWord);
					queue.add(word);
				}
			}

		}

		if (isPathFound) {
			return createWordChainBasedOnGivenMap(mapWithLinkToPreviousWordInChain, startWord, endWord);
		} else {
			return null;
		}
	}

	/**
	 * Loads words with length wordLength from file
	 * 
	 * @param wordLength
	 *            Length of words that should be load from the dictionary
	 */
	List<String> loadListOfWordsWithGivenLengthFromFile(final int wordLength) {
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
	 * Finds list of words that differs from the given word by one character
	 * 
	 * @param word
	 *            Word to compare
	 * @param dictionary
	 *            Dictionary where to find words that differ by one character
	 */
	List<String> getListOfWordsThatDifferByOneCharacterOnly(final String word, List<String> dictionary) {
		List<String> nextWords = new ArrayList<>();
		for (String currWord : dictionary) {
			if (areWordsDifferByOneCharacterOnly(word, currWord)) {
				nextWords.add(currWord);
			}
		}
		return nextWords;
	}

	/**
	 * Compares two words and returns true if that words differ by one character
	 * only
	 * 
	 * @param word1
	 *            First word to compare
	 * @param word2
	 *            Second word to compare
	 */
	boolean areWordsDifferByOneCharacterOnly(final String word1, final String word2) {
		int diff = 0;
		for (int i = 0; i < word1.length(); i++) {
			if (word1.charAt(i) != word2.charAt(i)) {
				diff++;

				if (diff > 1) {
					break;
				}
			}
		}
		return diff == 1 ? true : false;
	}

	/**
	 * Creates path from startWord to endWord based on
	 * mapWithLinkToPreviousWordInChain
	 * 
	 * @param mapWithLinkToPreviousWordInChain
	 *            Contains map with word pairs from bottom of the tree to the root
	 * @param startWord
	 *            First word in the chain
	 * @param endWord
	 *            Last word in the chain
	 */
	List<String> createWordChainBasedOnGivenMap(final Map<String, String> mapWithLinkToPreviousWordInChain,
			final String startWord, final String endWord) {
		List<String> path = new LinkedList<>();
		String word = endWord;
		while (!word.equals(startWord)) {
			path.add(0, word);
			word = mapWithLinkToPreviousWordInChain.get(word);
		}

		path.add(0, startWord);
		return path;
	}
}
