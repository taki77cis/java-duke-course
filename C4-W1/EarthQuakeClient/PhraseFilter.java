

public class PhraseFilter implements Filter{
   private String where;
   private String phrase;
   
   public PhraseFilter(String whr,String phr){
      where=whr;
      phrase=phr;
   }
    
   public boolean test(QuakeEntry qe){
        String info = qe.getInfo();
		  if(where=="start"&& info.startsWith(phrase)) return true;
		   	   
		  else if(where=="end"&& info.endsWith(phrase)) return true;
		   	   
		  else if(where=="any"&& info.indexOf(phrase)!= -1)return true;
	return false;	   
   }
  
}
