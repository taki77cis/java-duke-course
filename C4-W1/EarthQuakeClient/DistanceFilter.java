
public class DistanceFilter implements Filter {
 private double distMax;
    private Location current;
    
    public DistanceFilter(double max,Location loc){
      distMax=max;
      current=loc;
    }
    
    public boolean test(QuakeEntry qe){
        return qe.getLocation().distanceTo(current)<= distMax;
    }
}
