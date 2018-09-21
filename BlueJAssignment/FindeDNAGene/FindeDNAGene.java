import edu.duke.*;
import java.io.*;

public class FindeDNAGene{
    
    
    public int findStopCodon(String dnaStr,int startIndex,String stopCodon ){
    int currIndex = dnaStr.indexOf(stopCodon,startIndex+3);
    while (currIndex != -1){
        int diff = currIndex - startIndex + 3 ;
        if(diff % 3 == 0){ 
        return currIndex; 
        }
        else{
         currIndex=dnaStr.indexOf(stopCodon,currIndex+3);
        }
    }
    return dnaStr.length();
   }
  public String findGene(String dna ,int where){
      int startIndex = dna.indexOf("ATG",where);
      if (startIndex == -1){
          return "";
      }
      int taaIndex = findStopCodon(dna,startIndex,"TAA");
      int tagIndex = findStopCodon(dna,startIndex,"TAG");
      int tgaIndex = findStopCodon(dna,startIndex,"TGA");
      //System.out.println(taaIndex+" "+tagIndex+" "+tgaIndex);
      int minIndex = 0;
        if (taaIndex == -1 ||(tgaIndex != -1 && tgaIndex < taaIndex)){
           minIndex=tgaIndex;
        }
        else {
            minIndex = taaIndex;
        }
        if (minIndex == -1 ||(tagIndex != -1 && tagIndex < minIndex)){
            minIndex = tagIndex;
        }
        if (minIndex == -1){
            return "";
        }
        return dna.substring(startIndex,minIndex+3);
  }           
  
  
  public StorageResource getAllGenes (String dna){
       StorageResource geneList = new StorageResource();
      int startIndex = 0;
      while(true){
        String currentGene=findGene(dna,startIndex);
        if(currentGene.isEmpty()){
            break;
        }
        geneList.add(currentGene);
       //System.out.println(currentGene);
       startIndex=dna.indexOf(currentGene,startIndex)+currentGene.length();
     }
     return geneList;
   }    
   
   public void testOn(){
       //String dna = "AAATGCCCTAACTAGATTAAGAAACC";
       FileResource dna = new FileResource("data/dna.txt");
       StorageResource genes = getAllGenes(dna.asString());
       int max=0; 
       int i=0; 
       int j=0;
       int k=0;
       int sum=0;
     for (String g: genes.data()){
       int temp =g.length();
       if (temp > 60){
           k++;
        }
       i++;
       
       if(temp > max){
        j=i;
        max= temp;
        }
       System.out.println(g);
       if (findcgRatio(g)> 0.35){sum++;}
    }
    System.out.println();
    System.out.println("the number of gene is : "+ i);
    System.out.println("the number of max gene is : "+ j);
    System.out.println("the max gene is : "+ max);
    System.out.println("number of Gene larger than 60 :"+ k);
    System.out.println("number of cgRatio Gene larger 0.35 :"+sum);
    //System.out.println (mystery(dna.asString()));
            
    }

    
  public double findcgRatio(String g) {
    int no=0;
    int len=0;
     
      for(int i=0;i< g.length();i++){
        len++;
        String kk=g.substring(i,i+1);
        
        if(kk.equals("C")||kk.equals("G")){
         no++;
        }
      }
           
   return (double)no/len;
}
    


   
   public String mystery(String dna) {
  int pos = dna.indexOf("T");
  int count = 0;
  int startPos = 0;
  String newDna = "";
  if (pos == -1) {
    return dna;
  }
  while (count < 3) {
    count += 1;
    newDna = newDna + dna.substring(startPos,pos);
    startPos = pos+1;
    pos = dna.indexOf("T", startPos);
    if (pos == -1) {
      break;
    }
  }
  newDna = newDna + dna.substring(startPos);
  return newDna;
}
}