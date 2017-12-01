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
import java.net.*;

public class driver{
	
	static BufferedReader stdin;
	static FileInputStream inFile;
	static FileOutputStream outFile;
	static String eORd;
	static int count;
	static byte[] salt;
	static String sKey;
	
	public driver(){
		
	}	

	public static void main(String[] args){
		
		try{

			stdin = new BufferedReader(new InputStreamReader(System.in));
			byte[] salt = {(byte)0xc7, (byte)0x73, (byte)0x21, (byte)0x8c, (byte)0x7e, (byte)0xc8, (byte)0xee, (byte)0x99};
			count = 20;
			
			char[] cKey = new char[8];

			cKey = sKey.toCharArray();
			
			PBEKeySpec keyspec = new PBEKeySpec(cKey, salt, count);

			SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keyspec);
			
			DesEncrypter des = new DesEncrypter(key);

			System.out.println("Please enter key");

			sKey = stdin.readLine();
						
			while(true){

				System.out.print("Enter string to encrypt: ");

				String tmpS = stdin.readLine();
				if(tmpS.compareTo("quit") == 0)
					break;
				
				String inputE = des.encrypt(tmpS);
				//System.out.println("Encrypted string: " + inputE);
				
				Socket client = new Socket(args[0], 5001);
				PrintWriter dOut = new PrintWriter(client.getOutputStream(),true);
				//System.out.println("Sending data to server");
				dOut.println(inputE);
				dOut.flush();
				//System.out.println("Done Sending");
				BufferedReader bReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
				tmpS = bReader.readLine();
				System.out.print("\nDecrypted string: ");
				System.out.println(des.decrypt(tmpS));				
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
		catch(NumberFormatException e){
			System.out.println(e.getMessage());
		}
	}
}
