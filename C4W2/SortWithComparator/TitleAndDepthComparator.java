
import java.util.*;


public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
  
        public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        return qe1.getInfo().compareTo(qe2.getInfo());
    }
    
}
