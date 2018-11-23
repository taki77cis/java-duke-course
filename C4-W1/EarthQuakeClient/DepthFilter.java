
public class DepthFilter implements Filter {
    private double maxMag;
    private double minMag;
    
    public DepthFilter(double min,double max){
      maxMag=max;
      minMag=min;
    }
    
    public boolean test(QuakeEntry qe){
        return (( maxMag > qe.getDepth() ) && (qe.getDepth() > minMag ));
    }
}
