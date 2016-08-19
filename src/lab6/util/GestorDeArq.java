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

	public static final String DIR_DOS_MODELOS_PT = "res/lab6/models/pt/";
	public static final String DIR_DOS_MODELOS_EN = "res/lab6/models/en/";
	
	public static final String DIR_DOS_CORPORA = "res/lab6/corpora/";
	
	public static InputStream lerArquivo(String nomeDoArquivo) {
		try {
			return new FileInputStream(new File(nomeDoArquivo));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
