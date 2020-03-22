package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Dictionary {
	Set<String> dizionario = new HashSet<>();

	/**
	 * permette di caricare in memoria il dizionario della lingua desiderata e di
	 * salvare le singole parole in una lista
	 * 
	 * @param language
	 */
	
	public void loadDictionary(String language) {
		dizionario.clear();
		
		if (language.compareTo("English") == 0) {
			try {
				FileReader fr = new FileReader("src/main/resources/English.txt");
				BufferedReader br = new BufferedReader( fr);
				String word;
				while ((word = br.readLine()) != null) {
					// aggiungere parola alla struttura dati
					dizionario.add(word);
				}
				br.close();
			} catch (IOException e) {
				System.out.println("Errore nella lettura del file English.txt");
			}
		} else if (language.compareTo("Italian") == 0) {
			try {
				FileReader fr = new FileReader("src/main/resources/Italian.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while ((word = br.readLine()) != null) {
					// aggiungere parola alla struttura dati
					dizionario.add(word);
				}
				br.close();
			} catch (IOException e) {
				System.out.println("Errore nella lettura del file Italian.txt");
			}
		}
	}

	public List<RichWord> spellCheckText(List<String> inputTextList) {
		List<RichWord> out = new LinkedList<>();
		for (String s : inputTextList) {
			if (s != null) {
				if (dizionario.contains(s)) {
					out.add(new RichWord(s, true));
				} else {
					out.add(new RichWord(s, false));
					;
				}
			}
		}
		return out;
	}

	public int getNumeroErrori(List<RichWord> errori) {
		int err = 0;
		for (RichWord r : errori) {
			if (r != null) {
				if (r.isCorrect() == false) {
					err++;
				}
			}
		}
		return err;
	}

	public String getParoleErrate(List<RichWord> errate) {
		String risultato = "";
		for (RichWord r : errate) {
			if (r != null) {
				if (r.isCorrect() == false) {
					risultato += r.getWord() + "\n";
				}
			}
		}

		return risultato.trim();
	}
}
