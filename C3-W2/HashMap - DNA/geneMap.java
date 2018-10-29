import edu.duke.*;
import java.util.*;

public class geneMap {
    private HashMap<String,Integer> map;
   
    public geneMap(){
       map = new HashMap<String,Integer>();
    }
    
    public void countWordsMap(String txt){
        String part="";
        int endloop=0;
        int len=txt.length()-1;///i dont know whay it gave me length incremat by 1
        System.out.println(txt);       
        endloop=len-(len % 3);
        
            for (int i=0 ;i <= endloop-1;i=i+3){
               part= txt.substring(i,i+3);
              //System.out.println("....  "+ part);
               
               if (!map.containsKey(part)){
		  map.put(part,1);
		}
		else {
		  map.put(part,map.get(part)+1);
		}
            }
            int k=0;
            for(String w : map.keySet()){
                int value = map.get(w);
                k++;
                System.out.println(value+"\t"+w);
		}	
		System.out.println("number of uniuqe codon is :"+k);
	}
     
    public void frame(String txt,int frm){
      map.clear();
      if (frm==1){
       txt = txt.substring(1);
      }
      else if (frm==2){
       txt = txt.substring(2);
      }
      countWordsMap(txt);
    }
 
    public void tester(){
       
        FileResource fr= new FileResource("dnaMystery2.txt");
        String txt=fr.asString(); 
           
        frame(txt,2);
              
    }
    
}
