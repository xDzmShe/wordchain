package com.wordchain;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.Mock;

public class FixedLengthWordChainFinderTest {

	final List<String> DICTIONARY = Arrays.asList("111", "112", "121", "211", "221", "222", "223", "232", "322", "332",
			"333", "334", "343", "433", "443", "444");

	class WordChainMock extends FixedLengthWordChainFinder {
	}

	@Mock
	WordChainMock wordChainMock = mock(WordChainMock.class);

	@Test
	public void testFindChain_SameStartAndEndWords() {
		when(wordChainMock.loadDictionary()).thenReturn(DICTIONARY);
		when(wordChainMock.findChain("111", "111")).thenCallRealMethod();
		assertEquals(wordChainMock.findChain("111", "111"), null);
	}

	@Test
	public void testFindChain_Successful_SortChain() {
		when(wordChainMock.loadDictionary()).thenReturn(DICTIONARY);
		when(wordChainMock.findChain("111", "222")).thenCallRealMethod();
		assertEquals(wordChainMock.findChain("111", "222"), Arrays.asList("111", "121", "221", "222"));
	}

	@Test
	public void testFindChain_Unsuccessful() {
		when(wordChainMock.loadDictionary()).thenReturn(DICTIONARY);
		when(wordChainMock.findChain("111", "777")).thenCallRealMethod();
		assertEquals(wordChainMock.findChain("111", "777"), null);
	}

}
