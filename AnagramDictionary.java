package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();

    public HashSet wordSet = new HashSet();
    public ArrayList<String> wordList = new ArrayList<String>();
    public HashMap<String, ArrayList> lettersToWord = new HashMap<String, ArrayList>();


    public AnagramDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        String line;
        while ((line = in.readLine()) != null) {
            String word = line.trim();
            wordList.add(word);
            wordSet.add(word);
        }
       /* for(String str:wordList)
        {
            String key=sortLetters(str);
            ArrayList value=getAnagrams(str);
            lettersToWord.put(key,value);
*/
        /*    if(lettersToWord.containsKey(key))
            {
                  //  ArrayList repeaValue=lettersToWord.get(key);
                    value=lettersToWord.get(key);
                    value.add(str);
                    lettersToWord.put(key,value);

            }
            else
            {   value.add(str);
                lettersToWord.put(key,value);

            }
        }*/
    }

    public String sortLetters(String unsorted) {
        char sorted[] = unsorted.toCharArray();
        Arrays.sort(sorted);
        return sorted.toString();
    }

    public boolean isGoodWord(String word, String base) {
        if (wordSet.contains(word) && (!word.contains(base)))
            return true;
        return false;
    }

    public ArrayList<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();
        for (String str : wordList) {
            if (sortLetters(str).equals(sortLetters(targetWord)))
                result.add(str);
        }
        return result;
    }

    public ArrayList<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<String> anagrams = getAnagrams(word);
        int len = word.length();
        for (char c = 'a'; c <= 'z'; c++) {
            for (String str : anagrams) {
                for (int i = 0; i <= len; i++) {
                    String wordWithExtraLetter = str.substring(0, i) + c + str.substring(i);
                    if (wordList.contains(wordWithExtraLetter)) {
                        result.add(wordWithExtraLetter);
                    }
                }

            }

        }


        return result;
    }

    public String pickGoodStarterWord() {
        return "stop";
    }

    public void addToHashMap(String input) {
        String sorted = sortLetters(input);
        if (lettersToWord.containsKey(sorted)) {
            ArrayList value = lettersToWord.get(sorted);
            value.add(input);
            lettersToWord.put(sorted, value);

        } else {
            ArrayList value = new ArrayList();
            value.add(input);
            lettersToWord.put(sorted, value);
        }
    }
}
