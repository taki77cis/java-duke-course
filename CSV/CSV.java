import edu.duke.*;
import org.apache.commons.csv.*;
import java.text.DecimalFormat;

public class CSV{
    String msg="NOT FOUND";

    
public String countryInfo(CSVParser parser, String country){
    for(CSVRecord record: parser){
        String Rcountry=record.get("Country");
        if(Rcountry.contains(country)){
            msg ="";
            String exports= record.get("Exports");
            String value= record.get("Value (dollars)");
            System.out.println(country + ":" + exports +","+ value);
        }
    }
    return msg;
} 

public void tester(){
    FileResource fr= new FileResource();
    CSVParser parser= fr.getCSVParser();
    
    System.out.println("countryInfo");
    System.out.println(countryInfo(parser,"Nauru"));
    
    parser = fr.getCSVParser(); //reset parser
    
    System.out.println("listExportersTwoProducts");
    System.out.println(listExportersTwoProducts(parser,"cotton","flowers"));
    
    parser = fr.getCSVParser(); //reset parser
    
    System.out.println("numberOfExporters");
    System.out.println(numberOfExporters(parser,"cocoa"));
    
    parser = fr.getCSVParser(); //reset parser
    
    System.out.println("bigExporters");
    System.out.println(bigExporters(parser));
}

public String listExportersTwoProducts(CSVParser parser,String exportItem1,String exportItem2){
    for(CSVRecord record: parser){
         String Rexports=record.get("Exports");
         if(Rexports.contains(exportItem1)&&Rexports.contains(exportItem2)){
            msg ="";
            System.out.println(record.get("Country"));
         }
    }
  return msg;
  }
  
  public int numberOfExporters(CSVParser parser,String exportItem){
      int count=0;
      for (CSVRecord record:parser){
        String Rexport=record.get("Exports");
        if (Rexport.contains(exportItem)){
            count++;
        }
    }
    return count;
    }
    
    public String bigExporters(CSVParser parser){
      String new_value=null;
      int op=0;
      for (CSVRecord record:parser){
       String Rvalue=record.get("Value (dollars)");
       new_value=Rvalue.substring(1,Rvalue.length());
       if (new_value.length()>15){
       System.out.println((new_value)+"  "+record.get("Country"));
      }
  }
    return "";
}
  
}