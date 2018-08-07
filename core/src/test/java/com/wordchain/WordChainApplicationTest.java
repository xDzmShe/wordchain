package com.wordchain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class WordChainApplicationTest {

	private WordChainApplication wordChainApplication = new WordChainApplication();

	/**
	 * Verify the method returns false in case only 1 command-line arguments present
	 */
	@Test
	public void testValidateCommandLineArguments_OneArg_False() {
		boolean result;

		String[] oneArg = { "one" };
		result = wordChainApplication.areCommandLineArgumentsValid(oneArg);
		assertFalse("Should return false, because only one command-line argument", result);
	}

	/**
	 * Verify the method returns true in case 2 command-line arguments present with
	 * the same length
	 */
	@Test
	public void testValidateCommandLineArguments_TwoArgsSameLength_True() {
		boolean result;
		String[] twoArgs = { "one", "two" };
		result = wordChainApplication.areCommandLineArgumentsValid(twoArgs);
		assertTrue("Should return true, because there are exact two command-line arguments with the same length",
				result);
	}

	/**
	 * Verify the method returns false in case 2 command-line arguments present with
	 * different length
	 */
	@Test
	public void testValidateCommandLineArguments_TwoArgsDifferentLength_False() {
		boolean result;
		String[] twoArgsDifferentLength = { "short", "long" };
		result = wordChainApplication.areCommandLineArgumentsValid(twoArgsDifferentLength);
		assertFalse("Should return false in case command-line arguments have different length", result);
	}

	/**
	 * Verify the method returns false in case 3 command-line arguments present
	 */
	@Test
	public void testValidateCommandLineArguments_ThreeArgs_False() {
		boolean result;
		String[] threeArgs = { "one", "two", "three" };
		result = wordChainApplication.areCommandLineArgumentsValid(threeArgs);
		assertFalse("Should return false, because more than two command-line arguments", result);
	}
}
