package utils;
import java.math.BigInteger;
import java.security.MessageDigest;
/**
 * Classe pour pour hacher des chaines de caractères via l'algorithme sha1
 */
public class Sha1 {
    private String sha1;
    /**
    * Hache une chaîne de caractère passée en argument via l'algorithme sha1 
    * @param tohash Chaîne de caractère à hacher avec l'algorithme sha1
    */
   public Sha1(String tohash){
    //Trouve sur : http://oliviertech.com/java/generate-SHA1-hash-from-a-String/
      

      String hash = "";
      
      // With the java libraries
      try {
          MessageDigest digest = MessageDigest.getInstance("SHA-1");
          digest.reset();
          digest.update(tohash.getBytes("utf8"));
          hash = String.format("%040x", new BigInteger(1, digest.digest()));
      } catch (Exception e){
          e.printStackTrace();
      }
    sha1 = hash;
   }

   /**
    * Getter pour sha1
    * @return chaîne de caractère contenue dans sha1
    */
   public String getSha1(){
       return sha1;
   }
}
