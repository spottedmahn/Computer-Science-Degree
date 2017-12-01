/***************************************************************

** Michael DePouw

** COt4932

** Assignment Number: 1

** Date:3-08-04

***************************************************************/

/****************Program Description***************************

**
** Please see Program1.doc
**

**************************************************************/

import java.io.*;
import javax.crypto.spec.*;
import javax.crypto.*;
import java.security.*;
import java.security.spec.*;

public class driver{
	
	static BufferedReader stdin;
	static FileInputStream inFile;
	static FileOutputStream outFile;
	static String eORd;
	static int count;
	static byte[] salt;

	public driver(){

	}	

	public static void main(String[] args){
		
		try{

			stdin = new BufferedReader(new InputStreamReader(System.in));
			byte[] salt = {(byte)0xc7, (byte)0x73, (byte)0x21, (byte)0x8c, (byte)0x7e, (byte)0xc8, (byte)0xee, (byte)0x99};
			count = 20;

			inFile = new FileInputStream(args[0]);
			outFile = new FileOutputStream(args[1]);
			eORd = args[2];
			
			System.out.print("Enter Key: ");
			
			boolean done = false;
			String sKey = new String();
			
			while(!done){

				sKey = stdin.readLine();

				if(sKey.length() == 8){
					done = true;
				}
			}
			
			System.out.println("");
			
			char[] cKey = new char[8];

			cKey = sKey.toCharArray();
			
			PBEKeySpec keyspec = new PBEKeySpec(cKey, salt, count);

			SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keyspec);
			
			DesEncrypter des = new DesEncrypter(key);
			
			if(eORd.startsWith("en")){
				
				des.encrypt(inFile, outFile);
			}
			else{
				
				des.decrypt(inFile, outFile);
			}
				
			System.out.println("Done, have a good day");
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
		catch(NoSuchAlgorithmException e){
			System.out.println(e.getMessage());
		}
		catch(InvalidKeySpecException e){
			System.out.println(e.getMessage());
		}
	}
}
