package com.wordchain;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.mockito.Mock;

public class FixedLengthWordChainFinderTest {

	private final List<String> DICTIONARY = Arrays.asList("1111", "1112", "111", "112", "121", "211", "221", "222",
			"223", "232", "322", "332", "333", "334", "343", "433", "443", "444");

	private final int WORD_LENGTH = 3;

	private final Map<String, String> LIST_OF_PREVIOUS = new HashMap<String, String>();

	{
		LIST_OF_PREVIOUS.put("112", "111");
		LIST_OF_PREVIOUS.put("121", "111");
		LIST_OF_PREVIOUS.put("211", "111");
		LIST_OF_PREVIOUS.put("111", "112");
		LIST_OF_PREVIOUS.put("221", "121");
		LIST_OF_PREVIOUS.put("222", "221");
	}

	class WordChainMock extends FixedLengthWordChainFinder {
	}

	@Mock
	WordChainMock wordChainMock = mock(WordChainMock.class);

	@Test
	public void testFindChain_SameStartAndEndWords() {
		when(wordChainMock.loadDictionary(WORD_LENGTH)).thenReturn(DICTIONARY);
		when(wordChainMock.findChain("111", "111")).thenCallRealMethod();
		assertEquals(wordChainMock.findChain("111", "111"), null);
	}

	@Test
	public void testFindChain_Successful_SortChain() {
		when(wordChainMock.loadDictionary(WORD_LENGTH)).thenReturn(DICTIONARY);
		when(wordChainMock.findChain("111", "222")).thenCallRealMethod();
		assertEquals(wordChainMock.findChain("111", "222"), Arrays.asList("111", "121", "221", "222"));
	}

	@Test
	public void testFindChain_Unsuccessful() {
		when(wordChainMock.loadDictionary(WORD_LENGTH)).thenReturn(DICTIONARY);
		when(wordChainMock.findChain("111", "777")).thenCallRealMethod();
		assertEquals(wordChainMock.findChain("111", "777"), null);
	}

	@Test
	public void testGetPath() {
		assertEquals(wordChainMock.getPath(LIST_OF_PREVIOUS, "111", "222"), Arrays.asList("111", "121", "221", "222"));
	}
}
