

import java.util.*;

public class DifferentSorters {
    
    
    public int getLargesrDepth(ArrayList<QuakeEntry> quakes, int from) {
        int maxIdx = from;
        for (int i = from +1; i < quakes.size(); i++) {
            if (quakes.get(i).getDepth() > quakes.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }
    
    public void sortByLargestDepth() {
          
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataDec6sample2.atom";
        ArrayList<QuakeEntry> in  = parser.read(source);
        
        for(int i = 0; i < 50; i++) {
           
            int maxIdx = getLargesrDepth(in, i);
            /* swap the ith quake with the minIdxth quake */
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(maxIdx);
            in.set(i, qmin);
            in.set(maxIdx, qi);
        }
        print(in);
    }
    /////////////////////////////////////////////////////////////////////////
    public void sortWithCompareTo() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
       
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list);
        print(list);
        
        System.out.println("\n"+list.get(600));
    }    

    public void sortByMagnitude() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list, new MagnitudeComparator());
        print(list);
       int quakeNumber = 10;
       System.out.println("Print quake entry in position " + quakeNumber);
       System.out.println(list.get(quakeNumber));
    }

    public void sortByDistance() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        // Location is Durham, NC
        Location where = new Location(35.9886, -78.9072);
        Collections.sort(list, new DistanceComparator(where));
        print(list);
    }
    
    
    public void sortTitleAndDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        
        ArrayList<QuakeEntry> list  = parser.read(source);
        
        int i=0;
        Collections.sort(list, new TitleAndDepthComparator());
        print(list);
    }
    
    
    public void sortByLastWordInTitleThenByMagnitude() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        
        ArrayList<QuakeEntry> list  = parser.read(source);

        Collections.sort(list, new TitleLastAndMagnitudeComparator());

        System.out.println(list.get(500));
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
}
