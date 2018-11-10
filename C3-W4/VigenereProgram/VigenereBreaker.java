import java.util.*;
import edu.duke.*;
import java.io.File;

public class VigenereBreaker {
    ///////////////////////////////////////////////////////////////////////////////////
    public String sliceString(String message, int whichSlice, int totalSlices) {
       StringBuilder sd= new StringBuilder();
       for(int j=whichSlice; j < message.length(); j =j + totalSlices){
          sd.append(message.charAt(j));
       }
       return sd.toString();
    }
    /////////////////////////////////////////////////////////////////////////////////
    public void printLineNumb(String txt,int no){
        int pos=0;
        int indx=0;
        int newpos=0;
        for (int i=1;i <= no ;i++){
          indx=txt.indexOf("\n",pos);
          newpos=pos;
          pos=indx+2;
                }
         System.out.println();
         System.out.println(txt.substring(newpos,indx));
    }
    /////////////////////////////////////////////////////////////////////////////////
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc=new CaesarCracker(mostCommon);
        for (int i=0; i < klength;i++){
          String s=sliceString(encrypted,i,klength);
          key[i]=cc.getKey(s);
        }
        return key;
    }
     //////////////////////////////////////////////////////////////////////////////////
     public HashSet<String> readDictionary(FileResource fr2){
         HashSet<String> h = new HashSet<String>();
        for(String line : fr2.lines()){
           String Lin = line.toLowerCase();
           h.add(Lin);
          
       }
        return h;   
     }
     ///////////////////////////////////////////////////////////////////////////////////
     public char mostCommonCharIn(HashSet<String> words){
         HashMap<Character,Integer> map = new HashMap<Character,Integer>();   
         char maxChar='0';
        for(String wrd : words){
            
           String LoWrd=wrd.toLowerCase();
           
             for (char c: LoWrd.toCharArray()){
              
               if (!map.containsKey(c)){
               map.put(c,1);
               }
              else {
               map.put(c,map.get(c)+1);
              } 
            }
         }
        int max=0;
        for(char c: map.keySet()) {
          if(map.get(c)>=max){
              max=map.get(c);
              maxChar=c;
            }  
        }
        //System.out.println(maxChar);
        return maxChar;
     }
     ///////////////////////////////////////////////////////////////////////////////////
      public void breakForAllLanguages(String encrypted, HashMap<String, HashSet<String>> mapLang){
        int max = 0;
        String realLang=null;
        String s=null;
        HashSet<String> temp = new HashSet<String>() ;
        for (String language: mapLang.keySet()){
            s = breakForLanguage(encrypted, mapLang.get(language));
            
            int i = countWords(s, mapLang.get(language));
            System.out.println("  "+language+"   "+mostCommonCharIn(mapLang.get(language))+"    "+i);
            System.out.println();
            if (i > max){
                max = i;
                realLang = language;
                temp = mapLang.get(language);
            }
        }
          System.out.println("maching words is "+ max);
           s=breakForLanguage(encrypted,temp);
           //System.out.println(s);
           printLineNumb(s,1);
           System.out.println(realLang);  
    }
      ///////////////////////////////////////////////////////////////////////////////////
      public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        int[] key =new int[100];
        int max = 0;
        String str=null;
        char c = mostCommonCharIn(dictionary);
        int keyLen=0;
        for (int i = 1; i <= 100; i++){
            key = tryKeyLength(encrypted, i, c);
            VigenereCipher vc = new VigenereCipher(key);
            str = vc.decrypt(encrypted);
            int a = countWords(str, dictionary);
            if (a > max){
                max = a;
                keyLen=i;
            }
        }
         System.out.print("key length "+ keyLen );
         
       
            
            key = tryKeyLength(encrypted, keyLen, c);
            VigenereCipher vc = new VigenereCipher(key);
            str = vc.decrypt(encrypted);
            return str; 

        }
    
     
     ///////////////////////////////////////////////////////////////////////////////////
      public int countWords(String message,HashSet dictionary){
          String[] sa = message.split("\\W+");
        int i = 0;
        for (String s: sa){
            String slower = s.toLowerCase();
            if (dictionary.contains(slower)){
                i++;
            }
         }
        return i;
     }
       ///////////////////////////////////////////////////////////////////////////////// 
      public void breakVigenere () {
        
        
        FileResource fr= new FileResource("messages/secretmessage2.txt");
        String txt=fr.asString();
         
        DirectoryResource dr = new DirectoryResource();
        HashMap<String, HashSet<String>> map = new HashMap<String, HashSet<String>>();
         
        for (File f: dr.selectedFiles()){
            FileResource fr2 = new FileResource(f);
                                                   
            map.put(f.getName(), readDictionary(fr2));
            //System.out.println(f.getName());
        }
        System.out.println();
        breakForAllLanguages(txt,map);
    }
        
       
 }
    

