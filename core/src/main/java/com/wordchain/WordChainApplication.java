package com.wordchain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class WordChainApplication implements CommandLineRunner {

	@Autowired
	private WordChainFinder wordChainFinder;

	public static void main(String[] args) {
		SpringApplication.run(WordChainApplication.class, args);
	}

	@Override
	public void run(String... args) {

		// validate command line arguments
		if (!areCommandLineArgumentsValid(args)) {
			System.exit(1);
		}
		List<String> wordChain = wordChainFinder.findShortestWordChainBetweenTwoWords(args[0], args[1]);
		System.out.println(wordChain != null ? wordChain : "Cannot find solution");
	}

	boolean areCommandLineArgumentsValid(String[] args) {
		if (args.length != 2) {
			System.out.println("Usage: WordChainApplication <start_word> <end_word>");
			System.err.println("Wrong number of parameters. Expected 2, received " + args.length);
			return false;
		}

		if (args[0].length() != args[1].length()) {
			System.err.println("Both words must be the same length");
			return false;
		}

		return true;
	}
}
