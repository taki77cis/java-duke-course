import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;


public class BabyBirths {
   
  
  public void printNames(){
   
    FileResource fr=new FileResource();
       for (CSVRecord rec:fr.getCSVParser(false)){
           int numBorn=Integer.parseInt(rec.get(2));
           if(numBorn <= 100){
           System.out.println("Name "+ rec.get(0)+" Gender"+rec.get(1)+" Num Born "+rec.get(2));
         }
     }
  }
  
   //_______________________  _______________________________________________________________________________
  
    public void totalBirths(FileResource fr){
    int totalBirths=0;
    int totalBoys=0;
    int totalGirls=0;
    int boys=0;
    int girls=0;
     for (CSVRecord rec:fr.getCSVParser(false)){
          int numBorn=Integer.parseInt(rec.get(2));
          totalBirths +=numBorn;
          if (rec.get(1).equals("M")){
              totalBoys +=numBorn;
              boys ++;
            }
            else{
              totalGirls +=numBorn;
              girls ++;
            }
      }
            System.out.println("total Birth  "+totalBirths);
            System.out.println("total Boys  "+totalBoys +"  number of boys :"+ boys);
            System.out.println("total Girls  "+totalGirls+"  number of girls :"+girls);
  }
  //_______________________  _______________________________________________________________________________
  
  public void testTotalBirths(){
    FileResource fr= new FileResource();
    totalBirths(fr); 
   System.out.println("the Rank of the name is  "+getRank("Drew","M",fr));
   System.out.println("the name of the rank is  "+getName(450,"M",fr));
    System.out.println(getRankMoreThan("Drew","M",fr));
    
    //System.out.println(yearChangingName("Susan","F",fr));
    
    getAverageRank("Susan","F");
    
      
  }
//_______________________  _______________________________________________________________________________
    public int getRank(String name,String gender,FileResource fr){
        int rank=-1;
        int i=0;
        
        String tableName=null;
      for (CSVRecord rec:fr.getCSVParser(false)){
          String tableGender=rec.get(1);
          if (tableGender.equals(gender)){
              i++; 
              tableName=rec.get(0);
              if (tableName.equals(name)){
              rank=i; 
              }
          }
      }
      return rank;
  }
  //_______________________  _______________________________________________________________________________
   public String getName(int rank,String gender,FileResource fr){
        String name="no name";
        int i=0;
       
      for (CSVRecord rec:fr.getCSVParser(false)){
          String tableGender=rec.get(1);
          if (tableGender.equals(gender)){
              i++;
               if (i==rank){
                  name=rec.get(0);
               }
            }
      }
       
      return name;
  }
   //_______________________  _______________________________________________________________________________

  public void rankInAllYears(){
    int max=0;
    int currVal=0;
    String fileName=null;
    DirectoryResource dr = new DirectoryResource();
    for(File f : dr.selectedFiles()) {
      FileResource fr= new FileResource(f);
      
      currVal=getRank("Genevieve","F",fr);
      System.out.println(currVal + "  " + f.getName());
      if (currVal >= max){
       max=currVal;
       fileName=f.getName();
      }
    }
    System.out.println(fileName);
  }
   //_______________________  _______________________________________________________________________________
  
  public int getRankMoreThan(String name,String gender,FileResource fr){
        String Tname="no name";
        int numBorn=0;
      for (CSVRecord rec:fr.getCSVParser(false)){
          String tableGender=rec.get(1);
          if (tableGender.equals(gender)){
              if (!(name.equals(rec.get(0)))){
                numBorn +=Integer.parseInt(rec.get(2));
            }else{
            break;}
              
          }
      }
       return numBorn;
   }
    //_______________________  _______________________________________________________________________________
  
   public void getAverageRank(String name ,String genedr){
     int sumRank=0;
     int currVal=0;
     int i=0;
     double totalAv;
     String fileName=null;
     DirectoryResource dr = new DirectoryResource();
     for(File f : dr.selectedFiles()) {
      FileResource fr= new FileResource(f);
      
      currVal=getRank(name,genedr,fr);
      sumRank +=currVal;
       i++;
      }
      totalAv=(double)sumRank/(double)i;
      System.out.println("the average rank is :"+totalAv);
     }
     
   /*  public String yearChangingName(String name,String genedr,String year){
         String Fname="No Name";
         int Frank=0;
         Frank=getRank(name,genedr,year);
         Fname=getName(Frank,genedr,year);
         return Fname;
        }
        */
       
     public FileResource getfilePath(String year){
         String fname="us_babynames_by_year/yob"+year+".csv";
         FileResource fr=new FileResource(fname);
        return fr;  
     }
      
 }


  
