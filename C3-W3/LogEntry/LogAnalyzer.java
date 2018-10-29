
import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
      public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
       }      
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for(String line: fr.lines()){
           records.add(WebLogParser.parseEntry(line));
          }
     }
     
        public int countUniqueIPs(){
         ArrayList<String> unique = new ArrayList<String>();
         for (LogEntry le : records) {
           String ip = le.getIpAddress();
            if (!unique.contains(ip)) {
    	     unique.add(ip);
    	     }
    	  }
    	 return unique.size();
      }
      
      public int countUniqueIPsInRange(int low ,int high){
          ArrayList<String> unique = new ArrayList<String>(); 
         for (LogEntry le : records) {
    		 String ip = le.getIpAddress();
    		 if (!unique.contains(ip)) {
    			 int stat = le.getStatusCode();
    			 if (stat>=low && stat<=high) unique.add(ip);
    		 }
    	 }
    	 return unique.size();
      }
      
      
      public HashMap<String, Integer> countVisitsPerIP() {
    	 HashMap<String, Integer> map = new HashMap<String, Integer>();
    	 
    	 for (LogEntry le : records) {
    		 String ip = le.getIpAddress();
    		 if (!map.keySet().contains(ip)) {
    		    map.put(ip, 1); //System.out.println(ip);
    		  }
    		 else{
    		    map.put(ip, map.get(ip)+1);
    		  }
    		  
    	 }
    	 return map;
    	 
     }
      
     
      public HashMap<String, Integer> uniqueIPVisitsOnDay(String someday){
          
         HashMap<String, Integer> map2 = new HashMap<String, Integer>();
         
         for (LogEntry le : records) {
           String ip = le.getIpAddress();
           String dat=le.getAccessTime().toString();
            if (dat.indexOf(someday) != -1){
                         
             if (!map2.keySet().contains(ip)) {
                 map2.put(ip,1);
                }
    	     else{
    	         map2.put(ip, map2.get(ip)+1);
    	       }
           }
    	 }
    	 
       	 return map2;
        }
        
        public void printAllHigherThanNum(int num) {
     	System.out.printf("Status more than ",num);
    	 for (LogEntry le : records) {
    		 int status = le.getStatusCode();
    		 if (status > num) System.out.println(le);
    	 }
    	 
     }
     
    public void printHashMap(HashMap<String, Integer> map){
         for (String  count : map.keySet()){
             System.out.println(map.get(count)+"  "+count);
            }
    }
    
     public void getMaxIP(HashMap<String, Integer> map){
         int max =0;
         int val=0;
         String mostvisit=null;
         for (String  count : map.keySet()){
            val= map.get(count);
            if (val > max){
             max=val;
             mostvisit = count;        
           } 
       }
        System.out.println("the most visit ip is "+ mostvisit +" with "+max +" times");
      }
      
      
      public HashMap<String, ArrayList<String>> iPsForDays() {
    	 HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
    	 
    	 for (LogEntry le : records) {
    		String day = getDay(le);
    		String ip = le.getIpAddress();
    		 
    		if (!map.containsKey(day)) {  
    			 ArrayList<String> list = new ArrayList<String>();
    			 list.add(ip);
    			 map.put(day, list);
    		}
    		else {
    			if (!map.get(day).contains(ip)){
    			map.get(day).add(ip);
                       }
    		}
    	 }
    	 return map;
     }
     
      private String getDay(LogEntry le) {
    	 String date = le.getAccessTime().toString();
    	 // Assuming that day is in the same place
    	 return date.substring(4, 10);
     }
    
     public void printAll() {
          HashMap<String, Integer> map = new HashMap<String, Integer>();
          
          for (LogEntry le : records) {
            // System.out.println(le);
         }
         System.out.println("the no.of ALL Unique IPs  " + countUniqueIPs());
         System.out.println("the no.of Unique IPs In Range  "+  countUniqueIPsInRange(200,299));
         
         //printAllHigherThanNum(400);
         
         
         map=uniqueIPVisitsOnDay("Sep 27");
         System.out.println("uniqueIP Visits OnDay  "+ map.size());
         getMaxIP(map);
         System.out.println("//////////////////////////////////////////////");
         
         //map=iPsForDays();
         //getMaxIP(map);
         
          System.out.println("//////////////////////////////////////////////");
          
         map=countVisitsPerIP();
         System.out.println("uniqueIP Visits Per IP  "+ map.size());
         getMaxIP(map);
        
        
    }
}
