
import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
        for (int i=0; i< in.size(); i++) {
           if (checkInSortedOrder(in)){
               System.out.println(i+"  passes ");
               break;
            }
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi =   in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }     
    }
    
    public int getLargesrDepth(ArrayList<QuakeEntry> quakes, int from) {
        int maxIdx = from;
        for (int i = from +1; i < quakes.size(); i++) {
            if (quakes.get(i).getDepth() > quakes.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
        //////// for 50 =  -20021.00   for 70 = -31430.00
        
        ///////
        for(int i = 0; i < 70; i++) {
            
            int maxIdx = getLargesrDepth(in, i);
            /* swap the ith quake with the minIdxth quake */
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(maxIdx);
            in.set(i, qmin);
            in.set(maxIdx, qi);
        }
    }
   
///////////////////////////////////Magnitude///////////////////////////////////////
   public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakeData){
       double a=0;
       double b=0;
     for(int i = 0; i < quakeData.size()-1 ; i++) { 
          a=quakeData.get(i).getMagnitude();
          b=quakeData.get(i+1).getMagnitude();
         // System.out.println(a+"  "+b);
         if (a > b) {
            return false;
          }
      } 
      return true; 
    }

    
    
   public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in){
       
       for(int i = 1; i < in.size() ; i++) { 
           
           if (checkInSortedOrder(in)){
               System.out.println(i-1+"  passes ");
               break;
            }
           onePassBubbleSort(in,i);               
       } 
       
    }
    
    
   public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData,int numSorted){
        double a=0;
        double b=0;
        int lengh=quakeData.size()-numSorted;
    
         for (int k=0; k < lengh ; k++){
             a=quakeData.get(k).getMagnitude();
             b=quakeData.get(k+1).getMagnitude();         
            
            if (a > b){
             QuakeEntry Qe1 = quakeData.get( k );
             QuakeEntry Qe2 = quakeData.get(k+1);
             quakeData.set(k,  Qe2);
             quakeData.set(k+1,Qe1);
           }
        }
     
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
         for (int i=0; i < in.size();i++) 
    		onePassBubbleSort(in, i);
    }
         
     
     
    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByLargestDepth (list);
        //MagnitudeBubbleSort(list);
       
        //print(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        print(list);
    }
    
    
    public void print(ArrayList<QuakeEntry> list){
        int i=0;
        System.out.println("-----------------------------------------------------");
        for (QuakeEntry qe: list) {
            System.out.println(i+"   "+qe);
            i++;
        } 
        System.out.println("-----------------------------------------------------");
    }
    
    /*
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
		
	}*/
}
