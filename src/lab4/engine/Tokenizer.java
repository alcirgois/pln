package lab4.engine;

import lab4.model.Token;

import java.util.*;

/**
 * Created by ericm on 08-Jul-16.
 */
public class Tokenizer {

    public List<Token> textToTokens(String text){
        if(text==null || text.isEmpty())
            throw new RuntimeException("No lines to analyze");
        List<Token> tokens = new ArrayList<Token>();
        HashMap<String,Long> hashMap = getHashMap(text);
        hashMap.forEach((k,v) -> {if(!k.equals(""))tokens.add(new Token(k,v));});
        Comparator<Token> byCounter = (t1, t2) -> Long.compare(t1.getCounter(), t2.getCounter());
        tokens.sort(byCounter);
        Collections.reverse(tokens);
        printTokens(tokens);
        return tokens;
    }

    public HashMap<String,Long> getHashMap(String text) {
        HashMap<String,Long> hashMap = new HashMap<String, Long>();
        getHashMap(text,hashMap);
        return hashMap;
    }

    public void getHashMap(String text, HashMap<String,Long> hashMap) {
        text = text.toLowerCase().replaceAll("\\("," ")
                .replaceAll("\\)"," ")
                .replaceAll("\\d+", " ")
                .replaceAll("!"," ")
                .replaceAll("\\."," ")
                .replaceAll(","," ")
                .replaceAll("\\?"," ")
                .replaceAll(":"," ")
                .replaceAll(";"," ")
                .replaceAll("\""," ")
                .replaceAll("=", " ")
                .replaceAll("\\+", " ")
                .replaceAll("\\s-", " ")
                .replaceAll("-\\s", " ")
                .replaceAll("\\S*[\\\\/]\\S*", " ")
                .replaceAll("\\s'"," ")
                .replaceAll("'\\s"," ")
                .replaceAll("\\s[^A-Za-z·‡‚„ÈÍÌÛÙı˙Á¡¿¬√…Õ”‘’⁄«]+", " ")
                .replaceAll("[^A-Za-z·‡‚„ÈÍÌÛÙı˙Á¡¿¬√…Õ”‘’⁄«]+\\s", " ")
                .replaceAll("\\s\\S{0,2}\\s", " ")
                .replaceAll("\\S{23,}", " ")
                ;

        String[] words = text.split("\\s+");
        for(String word: words){
            if(hashMap.containsKey(word)){
                long counter = hashMap.get(word);
                counter++;
                hashMap.put(word,counter);
            } else {
                hashMap.put(word, (long) 1);
            }
        }
    }

    public void printTokens(List<Token> tokens){
        System.out.println("*****************************************");
        System.out.println("Tokens");
        tokens.forEach(token -> {
            System.out.println(token.toString());
        });
        System.out.println("*****************************************");
    }


}
