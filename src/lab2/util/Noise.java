package lab2.util;

import java.io.IOException;
import java.util.Random;

/**
 * Created by Jessica.
 */
public class Noise {

	public String applyNoise(String text) throws IOException {
		String alfabeto = "abcdefghijklmnopqrstuvxwyzABCDEFGHIJKLMNOPQRSTUVXWYZ";
		int tam = alfabeto.length();

		Random r = new Random();

		int i = text.length() / 5;
		StringBuilder strBuilder = new StringBuilder(text);
		while (i > 0) {
			strBuilder.insert(r.nextInt(text.length() - 1), alfabeto.charAt(r.nextInt(tam)));
			i--;
		}

		System.out.println(strBuilder.toString());

		return strBuilder.toString();
	}
}
