package com.company;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        var path = "C:\\Users\\miste\\IdeaProjects\\SysProgLab1\\src\\text.txt";

        WordRecognizer wr = new WordRecognizer(path);
        var result = new HashSet<String>(wr.getRecognizedWords());
        for (var w : result){
            System.out.println(w);
        }
    }
}
