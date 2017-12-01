import java.io.*;
import javax.crypto.*;
import java.security.spec.*;
import javax.crypto.spec.*;

public class DesEncrypter{
	
	Cipher ecipher;
	Cipher dcipher;

	PBEParameterSpec pbeParamSpec;

	DesEncrypter(SecretKey key){
		
		byte[] salt = {(byte)0xc7, (byte)0x73, (byte)0x21, (byte)0x8c, (byte)0x7e, (byte)0xc8, (byte)0xee, (byte)0x99};
		
		int count = 20;
		
		pbeParamSpec = new PBEParameterSpec(salt, count);

		try{
			
			ecipher = Cipher.getInstance("PBEWithMD5AndDES");
			dcipher = Cipher.getInstance("PBEWithMD5AndDES");

			ecipher.init(Cipher.ENCRYPT_MODE, key, pbeParamSpec);
			dcipher.init(Cipher.DECRYPT_MODE, key, pbeParamSpec);
		}
		catch(java.security.InvalidAlgorithmParameterException e){
		}
		catch(javax.crypto.NoSuchPaddingException e){
		}
		catch(java.security.NoSuchAlgorithmException e){
		}
		catch(java.security.InvalidKeyException e){
		}
	}

byte[] buf = new byte[1024];

public void encrypt(InputStream in, OutputStream out){
     try{
	out = new CipherOutputStream(out, ecipher);

	int numRead =0;
	while((numRead = in.read(buf)) >= 0){
		out.write(buf, 0, numRead);
	}

	out.close();
     }  catch(java.io.IOException e){
	//handle the damn errors if you want to!!!!
     }

}


public void decrypt(InputStream in, OutputStream out){
     try{
	in = new CipherInputStream(in, dcipher);

	int numRead = 0;
	while((numRead  = in.read(buf)) >= 0){
		out.write(buf,0,numRead);
	}
	
	out.close();
    }  catch (java.io.IOException e) {
	//I said handle the damn errors
    }
  }
}
