import java.util.*;
import java.io.File;
import edu.duke.*;

public class WordsInFiles {
    HashMap<String, ArrayList<String>> map;
    
    
    public WordsInFiles() {
        map = new HashMap<String, ArrayList<String>>();
    }
        
    private void addsWordsFromFile(File f) {
        String filename = f.getName();
        
        FileResource fr = new FileResource(f);
        for (String word : fr.words()) {
             if (map.containsKey(word)) {
                ArrayList<String> arr = map.get(word);
                if (!arr.contains(filename)) {
                    arr.add(filename);
                }
            } else {
                ArrayList<String> arr = new ArrayList<String>();
                arr.add(filename);
                map.put(word, arr);
            }
        }
    }
    
    private void buildWordFileMap() {
        map.clear();
        DirectoryResource dir = new DirectoryResource();
        for (File f : dir.selectedFiles()) {
            addsWordsFromFile(f);
        }
    }
    
    private int maxNumber() {
        int max = 0; 
       
        for (String word : map.keySet()) {
            int count = map.get(word).size();
            if (count > max) {
                max = count;
            }
        }
        return max; 
    }
    
    
    private ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> words = new ArrayList<String>();
        for (String word : map.keySet()) {
            int appearances = map.get(word).size();
            if (appearances == number) {
                words.add(word);
            }
        }
        return words;
    }
    
    private void printFilesln(String word) {
        ArrayList<String> filenames = map.get(word);
        for (int i = 0; i < filenames.size(); i++) {
            System.out.println(filenames.get(i));
        }
    }
    
    public void tester() {
        buildWordFileMap();
        int max = maxNumber();
        System.out.println("Maximum number of files any word appears in: " + max);
        System.out.println("All the words that are in " + max + " files:");
        ArrayList<String> words = wordsInNumFiles(max);
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);      
        }
        
         
        System.out.println("Count of words that appear in " + max + " files: " + words.size());
        System.out.println("To answer the quiz: ");
        words = wordsInNumFiles(4);
        System.out.println("Words in 4 files: " + words.size());
        System.out.println("sea: ");
        printFilesln("sea");
    }
}