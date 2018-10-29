import edu.duke.*;

public class CaesarCipher {
    
    private String alphabet;
    private String shiftedAlphabet;
    
    public CaesarCipher(int key){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key)+
        alphabet.substring(0,key);
    }
    
    public String encrypt(String input) {
       
        StringBuilder encrypted = new StringBuilder(input);
             
        for(int i = 0; i < encrypted.length(); i++) {
           
            char currChar = encrypted.charAt(i);
           
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            
            if(idx != -1){
                
                char newChar = shiftedAlphabet.charAt(idx);
               
                encrypted.setCharAt(i, newChar);
            }
            
        }
        
        return encrypted.toString();
    }
    public void testCaesar() {
        int key=15;
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipher CC=new CaesarCipher(key);
        
        String encrypted = CC.encrypt(message);
        System.out.println(encrypted);
        
         
        CaesarCipher CC2=new CaesarCipher(26-key);
        String decrypted = CC2.encrypt(encrypted);
        System.out.println(decrypted);
          
    }
}

