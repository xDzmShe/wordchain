package com.wordchain;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FixedLengthWordChainFinder implements WordChainFinder {

	private final String PATH_TO_DICTIONARY = "wordlist.txt";

	public List<String> findChain(final String startWord, final String endWord) {
		List<String> wordList = loadDictionary(startWord.length());
		// findPath
		return null;
	}

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
}
