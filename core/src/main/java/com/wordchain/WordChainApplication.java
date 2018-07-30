package com.wordchain;

import java.util.List;

public class WordChainApplication {

	private static WordChainFinder wordChainFinder = new FixedLengthWordChainFinder();
	
	public static void main(String[] args) {

		// validate command line arguments
		if (!validateCommandLineArguments(args)) {
			System.exit(1);
		}

		List<String> wordChain = wordChainFinder.findChain(args[0], args[1]);
		System.out.println(wordChain != null ? wordChain : "Cannot find solution");
	}

	static boolean validateCommandLineArguments(String[] args) {
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
