package security;

import java.io.Serializable;
import java.security.MessageDigest;
import security.AESUtil;
import security.Base64;

public class Digester implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 7604462765133806317L;
	private static final String ALGORITHM = "SHA";
    private static final String ALGORITHM_512 = "SHA-512";

    
	public static final String encriptSaltAES(String password, String salt){
        try{
               AESUtil aes = new AESUtil();
               aes.setKey("AAAD8B4C00E13F53B8E716BCB5F4D1B9");
               aes.setIvKey("FA0E3C9B4FA2C7AA370FCA9B5A916408");
               aes.afterPropertiesSet();
               
               //Criptografar - requesito melhoria seguran�a Cor�ia
               return aes.encrypt(password + salt); //descomentar para iniciar criptografia
              // return password; //comentar para iniciar criptografia
        } catch (Exception e) {
               throw new RuntimeException(e);
        }
  }
	
	public static final String encriptAES(String password){
        try{
               AESUtil aes = new AESUtil();
               aes.setKey("AAAD8B4C00E13F53B8E716BCB5F4D1B9");
               aes.setIvKey("FA0E3C9B4FA2C7AA370FCA9B5A916408");
               aes.afterPropertiesSet();
               
               return aes.encrypt(password);
              
        } catch (Exception e) {
               throw new RuntimeException(e);
        }
  }
	
	public static final String decryptAES(String password){
        try{
               AESUtil aes = new AESUtil();
               aes.setKey("AAAD8B4C00E13F53B8E716BCB5F4D1B9");
               aes.setIvKey("FA0E3C9B4FA2C7AA370FCA9B5A916408");
               aes.afterPropertiesSet();
               
               return aes.decrypt(password);
              
        } catch (Exception e) {
               throw new RuntimeException(e);
        }
  }
	
    /**
     * Digere um texto com o algoritmo SHA e codifica o resultado
     * na base 64.
     *
     * @param text Texo a digerir.
     * @return Texto digerido.
     */
    public static final String digest( String text ) {
        try {
            MessageDigest md = MessageDigest.getInstance( ALGORITHM );
            md.update( text.getBytes() );
            byte encodedText[] = md.digest();

            return Base64.encode( encodedText );
        }
        catch ( Exception e ) {
            throw new RuntimeException( e );
        }
    }
    
    public static final String digest512( String text ) {
    	try {
            MessageDigest md = MessageDigest.getInstance( ALGORITHM_512 );
            md.update( text.getBytes() );
            byte encodedText[] = md.digest();

            return Base64.encode( encodedText );
        }
        catch ( Exception e ) {
            throw new RuntimeException( e );
        }
    }

    /**
     * Digere um texto com o algoritmo informado e codifica o resultado
     * na base 64.
     *
     * @param text Texo a digerir.
     * @param algorithm Algoritmo para criptografia.
     * @return Texto digerido.
     */
    public static final String digest( String text, String algorithm ) {
        try {
            MessageDigest md = MessageDigest.getInstance( algorithm );
            md.update( text.getBytes() );
            byte encodedText[] = md.digest();

            return Base64.encode( encodedText );
        }
        catch ( Exception e ) {
            throw new RuntimeException( e );
        }
    }

}