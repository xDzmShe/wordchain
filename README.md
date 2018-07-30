# Word Chain Puzzle

This program solves problem described here: http://codekata.com/kata/kata19-word-chains/

**Main task:** build a chain of words, starting with one particular word and ending with another.

The dictionary was taken from here: http://codekata.com/data/wordlist.txt

[Breadth-first search](https://en.wikipedia.org/wiki/Breadth-first_search) alghoritm is used to find the shortest word chain.

The project consist of 2 maven modules:
  - CLI application (core)
  - Spring web service with an endpoint (rest-api)

# HowTo
**CLI:**
Main method is in WordChainApplication class. Run application with exactly two command-line arguments (two words): <start_word> and <end_word>

**Spring Boot:**
Run Spring Boot application with embedded Tomcat. Use the following format to get a word chain (use web browser or application like Postman to send HTTP Get request): http://127.0.0.1:8080/wordchain?start_word=cat&end_word=dog, where *cat* is <start_word> and *dog* is <end_word>
