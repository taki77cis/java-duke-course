import edu.duke.*;

public class CaesarCipher {
    
    public String encrypt(String input,int key1,int key2) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String FirstshiftedAlphabet  = alphabet.substring(key1)+alphabet.substring(0,key1);
        String SecondshiftedAlphabet = alphabet.substring(key2)+alphabet.substring(0,key2);
        StringBuilder encrypted = new StringBuilder(input);
             
        for(int i = 0; i < encrypted.length(); i++) {
           
            char currChar = encrypted.charAt(i);
           
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            
            if(idx != -1){
                if(i % 2 == 0){
                char newChar1 = FirstshiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar1);
                }
                else{ 
                  char newChar2 = SecondshiftedAlphabet.charAt(idx);
                  encrypted.setCharAt(i, newChar2);
                }
          }
        }
        return encrypted.toString();
      }
    
            
    public void testCaesar() {
        //int key1=14;
        //int key2=24;
       // FileResource fr = new FileResource();
       // String message = fr.asString();
        
        String encrypted="vvwveigy wetycxp. Xyi wprvvxc fvxnivr xyid yej sivr e ovc xf klv jytgvwj zr gfrkmeyrpcc vvjfvdmek xyi ";
        // String decrypted = encrypt(encrypted,26-17,26-4);
         // System.out.println(decrypted) ;
        for(int j=1;j<=26;j++){
            for(int k=17;k<=17;k++){
             String decrypted = encrypt(encrypted,26-j,26-k);
                        
              System.out.println(j +"   "+ k +"    "+ decrypted) ;
            }
        } 
    }
}

