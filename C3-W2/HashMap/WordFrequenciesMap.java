import edu.duke.*;
import java.util.*;

public class WordFrequenciesMap {
	
	
	public void countWordsMap(String word){
		 FileResource fr = new FileResource();
		 HashMap<String,Integer> map = new HashMap<String,Integer>();
                 map.clear();
		for(String w : fr.words()){
			w = w.toLowerCase();
			if (!map.containsKey(w)){
				map.put(w,1);
			}
			else {
				map.put(w,map.get(w)+1);
				
			}
		}
		int total = 0;
		for(String w : map.keySet()){
		  int value = map.get(w);
		  //System.out.println(value+"\t"+w);
		  total += value;
		  if (w.equals(word)){
			 System.out.println(word +"  ocure "+ map.get(w));
				}
		}
		System.out.println("total count: "+total+" different = "+map.keySet().size());
	}
	
	public void tester(){
		String filename = "sea";
		
		countWordsMap(filename);
		
	}
	
}
