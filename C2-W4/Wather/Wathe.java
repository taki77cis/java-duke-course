import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class Wathe {
     String timeLowestHum=null;
     
     public double coldestHourInFile(CSVParser parser){
     double lowestTemp=9999.;
    
      for (CSVRecord record : parser){
        String temp=record.get("TemperatureF");
        double curr_temp = Double.parseDouble(temp);
           if (curr_temp != -9999.){
               if (curr_temp < lowestTemp){
                 lowestTemp=curr_temp;
               }
           } 
      }
       return  lowestTemp;
    }
       
    //-----------------------------------------------------------
    public double lowestHumidity(CSVParser parser){
     double lowestHum=9999.;
         
       for (CSVRecord record : parser){
           String hum=record.get("Humidity");
          
        if(!hum.equals("N/A")){
           double currHum = Double.parseDouble(hum);
           if (currHum < lowestHum){
               lowestHum= currHum;
               timeLowestHum=record.get("DateUTC");
           }
        } 
    }
        return lowestHum;
      }
    //-----------------------------------------------------------
     public void testTemp(){
         
       DirectoryResource dr = new DirectoryResource();
       // loop through each file that was selected.
       double minTemp=9999;
       double currTemp=0;
       String NameOfFile=null;
      for(File f : dr.selectedFiles()) {
         // create a FileResource using the current File f that was selected
        
         FileResource fr= new FileResource(f);
         
         // create a CSVParser from the fileResource
         CSVParser parser = fr.getCSVParser();
         // find the coldest hour in a file and print the results. 
         currTemp= coldestHourInFile(parser);
          if (minTemp > currTemp ){ 
              minTemp=currTemp;
              NameOfFile=f.getName();
          }
      }
      System.out.println("the lowest tempreture is : "+minTemp + "  in file " +NameOfFile);    
   }
   //------------------------------------------------------------
   
   public void testHum(){
       double minHum=9999;
       double currHum=0;
    DirectoryResource dr = new DirectoryResource();
    for(File f : dr.selectedFiles()) {
         FileResource fr= new FileResource(f);
         CSVParser parser = fr.getCSVParser();
         currHum= lowestHumidity(parser);
          if(minHum > currHum){
            minHum=currHum;
          }
    } 
      System.out.println("the lowest Humidity is : "+ minHum +"in  "+timeLowestHum);
    }
}
