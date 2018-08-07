package com.wordchain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wordchain.WordChainFinder;

@RestController
@RequestMapping("/wordchain")
public class WordChainController {

	@Autowired
	private WordChainFinder wordChainPuzzle;

	@GetMapping
	public List<String> findWordChain(@RequestParam(value = "start_word", required = true) String startWord,
			@RequestParam(value = "end_word", required = true) String endWord) {

		return wordChainPuzzle.findShortestWordChainBetweenTwoWords(startWord, endWord);
	}
}
