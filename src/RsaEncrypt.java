import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

import org.bouncycastle.asn1.cms.EncryptedContentInfo;

public class RsaEncrypt {
	
	public static String encrypt(String text,String pubKey, String modulus ) throws UnsupportedEncodingException{
		 String rtext =  new StringBuilder(text).reverse().toString();  
			byte[] temp=rtext.getBytes("utf-8");
				String hexString = byte2HexStr(temp);
			//	System.out.println(hexString);
				BigInteger b1 = new BigInteger(hexString,16);
				Integer b2 = Integer.parseInt(pubKey,16);
				BigInteger b3 = new BigInteger(modulus,16);
				BigInteger b4 = b1.pow(b2); 
				BigInteger b5 = b4.mod(b3);
				return b5.toString(16);
			//	System.out.println(num);
	}
	
	
	
	
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		String str="ฮารว";
	//	byte[] temp=str.getBytes("utf-8");
		//printHexString(temp);  
		encrypt("C47POzXdpiyaZ2Z8", "010001", "00e0b509f6259df8642dbc35662901477df22677ec152b5ff68ace615bb7b725152b3ab17a876aea8a5aa76d2e417629ec4ee341f56135fccf695280104e0312ecbda92557c93870114af6c9d05c4f7f0c3685b7a46bee255932575cce10b424d813cfe4875d3e82047b97ddef52741d546b8e289dc6935b3ece0462db0a22b8e7");
	
		
		
		
	}
	
	public static String byte2HexStr(byte[] b) {  
	    String hs = "";  
	    String stmp = "";  
	    for (int n = 0; n < b.length; n++) {  
	        stmp = (Integer.toHexString(b[n] & 0XFF));  
	        if (stmp.length() == 1)  
	            hs = hs + "0" + stmp;  
	        else  
	            hs = hs + stmp;  
	        // if (n<b.length-1) hs=hs+":";  
	    }  
	    return hs.toUpperCase();  
	}  
	  
}
