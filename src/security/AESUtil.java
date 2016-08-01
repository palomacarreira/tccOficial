package security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil{// implements //InitializingBean, Encrypter {
	
	/**
	 * 
	 */
	public static final long serialVersionUID = 4999790062065211606L;
	
	public String key;
	public String ivKey;
	public Cipher encrypter;
	public Cipher decrypter;
	
	/* (non-Javadoc)
	 * @see br.com.lg.clube.util.Encrypter#encrypt(java.lang.String)
	 */
	public String encrypt(String message) throws Exception {
		if (message == null) return "";
		
		byte[] encrypted = encrypter.doFinal(message.getBytes());
		return asHex(encrypted);
	}
	
	/* (non-Javadoc)
	 * @see br.com.lg.clube.util.Encrypter#encrypt(java.lang.String)
	 */
	public String encrypt(byte[] message) throws Exception {
		if (message == null) return "";
		
		byte[] encrypted = encrypter.doFinal(message);
		return asHex(encrypted);
	}

	public String decrypt(String encrypted) throws Exception {
		if (encrypted == null) return "";
		
		byte[] original = decrypter.doFinal(hexToBytes(encrypted));
		return new String(original);
	}
	
	
	public void afterPropertiesSet() throws Exception {
		IvParameterSpec ivSpec = new IvParameterSpec(hexToBytes(ivKey));		
		byte[] key = hexToBytes(this.key);
		
	    SecretKeySpec keyspec = new SecretKeySpec(key, "AES");
	   
	    encrypter = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    encrypter.init(Cipher.ENCRYPT_MODE, keyspec, ivSpec);
	    
	    decrypter = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    decrypter.init(Cipher.DECRYPT_MODE, keyspec, ivSpec);
	}
	
	
	public String asHex (byte buf[]) {
      StringBuffer strbuf = new StringBuffer(buf.length * 2);
      int i;

      for (i = 0; i < buf.length; i++) {
       if (((int) buf[i] & 0xff) < 0x10)
	    strbuf.append("0");

       strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
      }

      return strbuf.toString();
     }
	
	

	public byte[] hexToBytes(char[] hex) {
      int length = hex.length / 2;
      byte[] raw = new byte[length];
      for (int i = 0; i < length; i++) {
        int high = Character.digit(hex[i * 2], 16);
        int low = Character.digit(hex[i * 2 + 1], 16);
        int value = (high << 4) | low;
        if (value > 127)
          value -= 256;
        raw[i] = (byte) value;
      }
      return raw;
    }

	public byte[] hexToBytes(String hex) {
      return hexToBytes(hex.toCharArray());
    }
	
	public static void main(String[] args) {
		
		String senha = "testecrip@teste";
		//String idUsr = "106090";
		try{
			   
			   System.out.println(Digester.encriptAES(senha));
			   System.out.println(Digester.decryptAES("3395e95cf399f0758876db77b839e876"));
			   
		//System.out.println(new Long(233).longValue() == 233);
			   //new DShopServiceImpl().getTrakingPedidoNew(null);
		}catch (Exception ex){
			   ex.printStackTrace();
		}
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	public void setIvKey(String ivKey) {
		this.ivKey = ivKey;
	}
	
}
