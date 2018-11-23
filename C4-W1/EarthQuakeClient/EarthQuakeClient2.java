import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {

    
	   
       public void testMatchAllFilter2 (){
           int count=0;
          EarthQuakeParser parser = new EarthQuakeParser(); 
	  String source = "data/nov20quakedata.atom";
	  ArrayList<QuakeEntry> list  = parser.read(source);         
	  System.out.println("read data for "+list.size()+" quakes");
	  
	   MatchAllFilter maf= new MatchAllFilter();
	   Location loc = new Location(55.7308, 9.1153);
	  Filter dep = new DepthFilter(-12000.0  ,-10000.0); 
	  Filter mag = new MagnitudeFilter(0.0, 5.0);
	  Filter phr = new PhraseFilter("any","e");
	  Filter des = new DistanceFilter(3000000 ,loc);
	  
	  //maf.addFilter(mag);
	  //maf.addFilter(phr);
	  //maf.addFilter(des);
	  maf.addFilter(dep);
	  
	  for(QuakeEntry qe: list){
            
            if(maf.test(qe)) {
                System.out.println("Numbor of Quakes  "+qe);
                count++;
                }
      }
       System.out.println("Number of Quakes is  "+count);
    }
}

/// 20   24   358   616

/// 127   157  19    364   58   5.10    japan  74    15    187    17
