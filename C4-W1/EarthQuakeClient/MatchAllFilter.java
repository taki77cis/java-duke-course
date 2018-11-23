import java.util.*;
import edu.duke.*;

 public class MatchAllFilter implements Filter{
    
        private ArrayList<Filter> filters = new ArrayList<Filter>();
	
	
	public void addFilter(Filter f) {
		filters.add(f);
	}
	
	
	public boolean test(QuakeEntry qe) {
		
		for (Filter f : filters) {
		  if (!f.test(qe)) return false;
		}
		return true;
	}
}
