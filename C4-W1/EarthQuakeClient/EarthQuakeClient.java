import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    private double magMin;
    
        
    //////////////////////////////////////////////////////////////////////////////////////
     private ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData,
			int howMany) {
	
	ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        
        for(int j=0; j < howMany; j++) {
            int largeIndex = 0;
            double maxMagnitude = 0.0;
            for(int k=1; k < copy.size(); k++){
                QuakeEntry qe = copy.get(k);
                
                if (qe.getMagnitude() > maxMagnitude){
                    maxMagnitude = qe.getMagnitude(); 
                    largeIndex=k;
                }
            }
            ret.add(copy.get(largeIndex));
            copy.remove(largeIndex);
        }
        return ret;
	}
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
   public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany){
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        
        for(int j=0; j < howMany; j++) {
            int minIndex = 0;
            for(int k=1; k < copy.size(); k++){
                QuakeEntry quake = copy.get(k);
                Location loc = quake.getLocation();
                if (loc.distanceTo(current) < 
                    copy.get(minIndex).getLocation().distanceTo(current)){
                    minIndex = k;   
                }
            }
            ret.add(copy.get(minIndex));
            copy.remove(minIndex);
        }
        return ret;
   }
    //////////////////////////////////////////////////////////////////////////////////////
    public void findClosestQuakes(){
       EarthQuakeParser parser = new EarthQuakeParser();
		String source = "data/nov20quakedatasmall.atom";
		//String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
		ArrayList<QuakeEntry> list  = parser.read(source);
		System.out.println("read data for " + list.size());
		
		Location jakarta  = new Location(-6.211, 106.845);

		ArrayList<QuakeEntry> close = getClosest(list, jakarta, 10);
		for(int k=0; k < close.size(); k++){
			QuakeEntry entry = close.get(k);
			double distanceInMeters = jakarta.distanceTo(entry.getLocation());
			System.out.printf("%4.2f\t %s\n", distanceInMeters/1000, entry);
		}
		System.out.println("number found: " + close.size());
   }
    //////////////////////////////////////////////////////////////////////////////////////
    
    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData,Filter f){
     ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
                   
            for(QuakeEntry qe: quakeData){
               if (qe.getMagnitude() > magMin) 
                answer.add(qe);
                }
        return answer;
    }
    //////////////////////////////////////////////////////////////////////////////////////
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
                   
          for(QuakeEntry qe: quakeData){
               if (qe.getMagnitude() > magMin) 
                answer.add(qe);
                }
        return answer;
    }
    //////////////////////////////////////////////////////////////////////////////////////
    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe: quakeData ){
           Location loc = qe.getLocation();
           if (loc.distanceTo(from) < distMax){
            answer.add(qe);
            }
          }
        return answer;
    }
    //////////////////////////////////////////////////////////////////////////////////////
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }
    //////////////////////////////////////////////////////////////////////////////////////
    public void bigQuakes() {
        double strong=5.0;
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for "+ list.size() +" quakes");
        
        ArrayList<QuakeEntry>  listBig = filterByMagnitude(list,strong);
        System.out.println("quakes more than "+strong+" is " +listBig.size());
       for (QuakeEntry qe : listBig) {
	 System.out.println(qe);
	}      

    }
    //////////////////////////////////////////////////////////////////////////////////////
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, 
			double minDepth, double maxDepth) {
		ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
		for (QuakeEntry qe: quakeData) {
		   
			if (qe.getDepth() > minDepth && qe.getDepth() < maxDepth) {
				answer.add(qe);
			}
		}
		return answer;
	}
     /////............./////////////////////////////////////////////////////////////////////////////////
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
			String where, String phrase){
		ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
		int phrLength=phrase.length();
		for (QuakeEntry qe: quakeData) {
		    
		   String info = qe.getInfo();
		   if(where=="start"&& info.startsWith(phrase))
		   answer.add(qe);
		   
		  else if(where=="end"&& info.endsWith(phrase))
		   answer.add(qe);
		   
		   else if(where=="any"&& info.indexOf(phrase)!= -1)
		   answer.add(qe);
		  
		}
		
		return answer;
	} 
    //////////////////////////////////////////////////////////////////////////////////////
    public void quakesByPhrase(){
		EarthQuakeParser parser = new EarthQuakeParser();
		String source = "data/nov20quakedata.atom";
		ArrayList<QuakeEntry> list  = parser.read(source);
		
		ArrayList<QuakeEntry> resalt=filterByPhrase(list,"any","Creek");
		for (QuakeEntry qe: resalt) {
		   // System.out.println(qe);
		    
		  }
		  System.out.println("found "+resalt.size());
	}
    //////////////////////////////////////////////////////////////////////////////////////
    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        	ArrayList<QuakeEntry> list  = parser.read(source);
		System.out.println("read data for "+list.size()+" quakes");
		
		double minDepth = -4000.0; 
		double maxDepth = -2000.0;
		System.out.println("Find quakes with depth between " + minDepth + " and " + maxDepth);

		ArrayList<QuakeEntry> listDeep = filterByDepth(list, minDepth, maxDepth);
		for (QuakeEntry qe : listDeep) {
			System.out.println(qe);
		}

		System.out.println("Found " + listDeep.size() + " quakes that match that criteria\n");
	}

    //////////////////////////////////////////////////////////////////////////////////////
    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
       
        Location city =  new Location(38.17, -118.82);
        ArrayList<QuakeEntry>  NewLoc = filterByDistanceFrom(list,1000*1000,city);
       
        for (QuakeEntry qe : NewLoc) System.out.println(qe);
	System.out.println("Found "+NewLoc.size()+" quakes that match that criteria");

       }
    //////////////////////////////////////////////////////////////////////////////////////
     public void findLargestQuuake (){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> result = getLargest(list,50);
        for (QuakeEntry qe : result) {
	  System.out.println(qe);
		}

     }
    //////////////////////////////////////////////////////////////////////////////////////
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
