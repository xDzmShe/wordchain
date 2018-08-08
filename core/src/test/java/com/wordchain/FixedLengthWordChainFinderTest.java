package com.wordchain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

public class FixedLengthWordChainFinderTest {

	private final List<String> DICTIONARY = Arrays.asList("111", "112", "121", "211", "221", "222", "223", "232", "322",
			"332", "333", "334", "343", "433", "443", "444");

	private final int WORD_LENGTH = 3;

	private final Map<String, String> MAP_WITH_LINK_TO_PREVIOUS_WORD = new HashMap<String, String>();

	{
		MAP_WITH_LINK_TO_PREVIOUS_WORD.put("112", "111");
		MAP_WITH_LINK_TO_PREVIOUS_WORD.put("121", "111");
		MAP_WITH_LINK_TO_PREVIOUS_WORD.put("211", "111");
		MAP_WITH_LINK_TO_PREVIOUS_WORD.put("111", "112");
		MAP_WITH_LINK_TO_PREVIOUS_WORD.put("221", "121");
		MAP_WITH_LINK_TO_PREVIOUS_WORD.put("222", "221");
	}

	@Spy
	FixedLengthWordChainFinder wordChainMock = spy(FixedLengthWordChainFinder.class);

	@Test
	public void testFindShortestWordChainBetweenTwoWords_SameStartAndEndWords() {
		Mockito.doReturn(DICTIONARY).when(wordChainMock).loadListOfWordsWithGivenLengthFromFile(WORD_LENGTH);
		assertEquals(wordChainMock.findShortestWordChainBetweenTwoWords("111", "111"), null);
	}

	@Test
	public void testFindShortestWordChainBetweenTwoWords_Successful_ShortChain() {
		when(wordChainMock.loadListOfWordsWithGivenLengthFromFile(WORD_LENGTH)).thenReturn(DICTIONARY);
		assertEquals(wordChainMock.findShortestWordChainBetweenTwoWords("111", "222"),
				Arrays.asList("111", "121", "221", "222"));
	}

	@Test
	public void testFindShortestWordChainBetweenTwoWords_Unsuccessful() {
		when(wordChainMock.loadListOfWordsWithGivenLengthFromFile(WORD_LENGTH)).thenReturn(DICTIONARY);
		assertEquals(wordChainMock.findShortestWordChainBetweenTwoWords("111", "777"), null);
	}

	@Test
	public void testCreateWordChainBasedOnGivenMap() {
		assertEquals(wordChainMock.createWordChainBasedOnGivenMap(MAP_WITH_LINK_TO_PREVIOUS_WORD, "111", "222"),
				Arrays.asList("111", "121", "221", "222"));
	}

	@Test
	public void testAreWordsDifferByOneCharacterOnly_OneDifference_True() {
		assertTrue(wordChainMock.areWordsDifferByOneCharacterOnly("111", "112"));
	}

	@Test
	public void testAreWordsDifferByOneCharacterOnly_TwoDifferences_False() {
		assertFalse(wordChainMock.areWordsDifferByOneCharacterOnly("111", "212"));
	}

	@Test
	public void testGetListOfWordsThatDifferByOneCharacterOnly_wordsFound() {
		assertEquals(wordChainMock.getListOfWordsThatDifferByOneCharacterOnly("111", DICTIONARY),
				Arrays.asList("112", "121", "211"));
	}

	@Test
	public void testGetListOfWordsThatDifferByOneCharacterOnly_wordsNotFound() {
		assertEquals(wordChainMock.getListOfWordsThatDifferByOneCharacterOnly("777", DICTIONARY),
				new ArrayList<String>());
	}
}
