package it.polito.tdp.spellchecker.model;

public class RichWord {
	String word;
	boolean isCorrect;
	/**
	 * @param word singola parola della frase da controllare
	 * @param isCorrect boolean per capire se la parola è corretta o meno
	 * 
	 */
	public RichWord(String word,boolean isCorrect) {
		super();
		this.word = word;
		this.isCorrect = isCorrect;
	}
	/**
	 * @return the word
	 */
	public String getWord() {
		return word;
	}
	/**
	 * @return the isCorrect
	 */
	public boolean isCorrect() {
		return isCorrect;
	}
	
	
	
}
