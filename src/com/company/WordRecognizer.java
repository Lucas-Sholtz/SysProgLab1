package com.company;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class WordRecognizer {
    private String _path;
    private char[] _separators = {' ', ':', ';', '!', '@', '"', '#', '№', '$', '\'','%','^','&','?','*','(',')','_','-','=','+'};
    private char[] _vowels = {'e','u','i','o','a'};

    public WordRecognizer(String filePath) {
        _path = filePath;
    }
    public ArrayList<String> getRecognizedWords() {
        var recognizedWords = new ArrayList<String>();

        try (BufferedReader br = new BufferedReader(new FileReader(_path))) {
            String line;
            while ((line = br.readLine()) != null) {
                var words = _splitLineToWords(line);
                recognizedWords.addAll(_getWordsWithOnlyVowels(words));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return recognizedWords;
    }
    private ArrayList<String> _splitLineToWords(String line) {
        var words = new ArrayList<String>();
        line+=" ";
        var word = "";
        for(var c : line.toCharArray()){
            if(_isInContainer(c, _separators)) {
                if (!word.isEmpty()) {
                    words.add(word);
                    word="";
                }
            }
            else {
                word+=c;
            }
        }
        return words;
    }
    private boolean _isInContainer(char c, char[] container){
        for (var s : container){
            if(s==c){
                return true;
            }
        }
        return false;
    }
    private ArrayList<String> _getWordsWithOnlyVowels(ArrayList<String> words){
        var vowelWords = new ArrayList<String>();
        for(var w : words){
            var word = w.length()>30 ? w.substring(0,30) : w;
            if(_checkIfWordContainsOnlyVowels(word)){
                vowelWords.add(word);
            }
        }
        return vowelWords;
    }
    private boolean _checkIfWordContainsOnlyVowels(String s){
        for(var c : s.toCharArray()){
            if(!_isInContainer(c, _vowels)){
                return false;
            }
        }
        return true;
    }
}
