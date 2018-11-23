public class MagnitudeFilter implements Filter {
    private double maxMag;
    private double minMag;
    
    public MagnitudeFilter(double min,double max){
      maxMag=max;
      minMag=min;
    }
    
    
    public boolean test(QuakeEntry qe){
        return (( maxMag >= qe.getMagnitude() ) && (qe.getMagnitude() >= minMag ));
    }
}
