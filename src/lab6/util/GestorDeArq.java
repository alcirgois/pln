package lab6.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author Emanuel
 *
 */
public class GestorDeArq {
	public static InputStream lerArquivo(String nomeDoArquivo) {
		try {
			return new FileInputStream(new File(nomeDoArquivo));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
