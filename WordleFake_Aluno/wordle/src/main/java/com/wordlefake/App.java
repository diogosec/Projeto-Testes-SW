package com.wordlefake;

import java.io.*;
import java.text.Normalizer;
import java.util.regex.Pattern;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.nio.file.Files;
import java.io.File;

public class App  {
	private ArrayList<String> words;
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		ArrayList<String> words = new ArrayList<String>();
		String user_word, chosen_word, aux_word;
		int tamanho_palavra, quantidade_tentativas, contador_jogadas = 0;

		// pega o tamanho da palavra
		do {
			System.out.println("Insira o tamanho da palavra (entre 2 e 23 letras)");
			tamanho_palavra = keyboard.nextInt();
		} while (tamanho_palavra < 2 || tamanho_palavra > 23);

		// tentativas
		do {
			System.out.println("Insira a quantidade de tentativas (entre 1 e 6)");
			quantidade_tentativas = keyboard.nextInt();
		} while (quantidade_tentativas < 1 || quantidade_tentativas > 6);
    
		// jogo real
		aux_word = (SelectFile(tamanho_palavra));
    	chosen_word = removeAccents(aux_word);

		System.out.println("LEGENDAS:\nX = NÃO CONSTA\nA = CONSTA, LOCAL ERRADO\nV = CONSTA, LOCAL CERTO\n");
    
		while (contador_jogadas < quantidade_tentativas) {
			System.out.println("Tentativa " + (contador_jogadas + 1) + ":");

			do {
				user_word = removeAccents(keyboard.next().trim().toUpperCase());
			} while (!(ExistePalavra(user_word)) || (!TamanhoCerto(tamanho_palavra, user_word)));

			// um if antes caso o usuario acerte de primeira
			if (user_word.contentEquals(chosen_word)) {
				System.out.println("Voce venceu!");
				break;
			} else {
				WordsEquals(user_word, chosen_word, tamanho_palavra);
			}

			contador_jogadas++;
		}
		if (contador_jogadas == quantidade_tentativas)
			System.out.println("Que pena, suas tentativas acabaram e você não acertou! mais sorte na próxima!");
      System.out.println("A palavra correta é: "+aux_word);
	}

	public static String SorteioPalavra(String txt) {
		List<String> wordsList = new ArrayList<String>();

		try {
			File wordFile = new File(txt);
			wordsList = Files.readAllLines(wordFile.toPath());
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		Random random = new Random();
		String wordOfTheDay = wordsList.get(random.nextInt(wordsList.size()));
		return wordOfTheDay.trim().toUpperCase();
	}

	public static String SelectFile(int tamanho_palavra) {
		String palavra;
		String a = "TXT//palavras";
		String b = "letras";
		String c = ".txt";

		palavra = SorteioPalavra(a + tamanho_palavra + b + c);
    //aux_word = (a + tamanho_palavra + b + c);
		return palavra;
	}

	public static String removeAccents(String value) {
		String normalizer = Normalizer.normalize(value, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(normalizer).replaceAll("");
	}

	public static void WordsEquals(String userw, String gamew, int t) {
		List<String> wordleWordsList = Arrays.asList(gamew.split(""));
		List<String> userwordlist = Arrays.asList(userw.split(""));
		String resp = "";

		for (int i = 0; i < t; i++) {
			if (wordleWordsList.contains(userwordlist.get(i))) {
				if (wordleWordsList.get(i).equals(userwordlist.get(i))) {
					resp = resp.concat("V");
				} else {
					resp = resp.concat("A");
				}
			} else {
				resp = resp.concat("X");
			}
		}
		System.out.println(resp);
	}

  //tratamento de exceção: verificação de tamanho
	public static boolean TamanhoCerto(int t, String w) {
		if (w.length() == t)
			return true;
		else {
			System.out.println("Palavra com tamanho diferente do escolhido! Insira uma palavra com " + t + " letras");
			return false;
		}
	}

  //tratamento de exceção: palavra desconhecida
	public static boolean ExistePalavra(String w) {
		System.out.println(w);
		List<String> wordsList = new ArrayList<String>();
		try {
			File wordFile = new File("TXT//br-utf8.txt"/*aux_word*/);
			wordsList = Files.readAllLines(wordFile.toPath());
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		for (String a : wordsList) {
			if (w.equals(removeAccents(a.toUpperCase())))
				return true;
		}
		System.out.println("Essa palavra não consta no nosso banco de palavras! tente outra vez!");
		return false;
	}
}