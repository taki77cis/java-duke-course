
import java.util.*;

public class Tester
{
       
    public void testLogAnalyzer() {
        LogAnalyzer logAn = new LogAnalyzer(); 
        logAn.readFile("weblog2_log"); 
        logAn.printAll();
    }
}
