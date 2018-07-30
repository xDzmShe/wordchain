package com.wordchain;

public class WordChainApplication {

	public static void main(String[] args) {

		// validate command line arguments
		if (!validateCommandLineArguments(args)) {
			System.exit(1);
		}

		// find word chain
		// print result
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
