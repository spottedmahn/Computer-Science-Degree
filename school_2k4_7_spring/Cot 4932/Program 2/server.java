import java.net.*;
import java.io.*;
import javax.crypto.spec.*;
import javax.crypto.*;
import java.security.*;
import java.security.spec.*;

public class server{
	
	static String sKey = "abcdefgh";
	
	public server(){
	
	}
	public static void main(String[] args){
		
		try{
			ServerSocket socket = new ServerSocket(5001);
			
			byte[] salt = {(byte)0xc7, (byte)0x73, (byte)0x21, (byte)0x8c, (byte)0x7e, (byte)0xc8, (byte)0xee, (byte)0x99};
			int count = 20;
			
			char[] cKey = new char[8];
	
			cKey = sKey.toCharArray();
			
			PBEKeySpec keyspec = new PBEKeySpec(cKey, salt, count);
	
			SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keyspec);
			
			DesEncrypter des = new DesEncrypter(key);
				
			while(true){
					
				Socket newSocket = socket.accept();
				//System.out.println("Accepted: " + newSocket.getInetAddress());
				BufferedReader bReader = new BufferedReader(new InputStreamReader(newSocket.getInputStream()));
				//System.out.println("Socket accepted");
				String tmpS = bReader.readLine();
				//System.out.println("Encrypted : " + tmpS);
				
				String dString = des.decrypt(tmpS);
				System.out.println("Decrypted : " + dString);
				PrintWriter client = new PrintWriter(newSocket.getOutputStream());
				client.println(des.encrypt(dString));
				client.flush();
			}
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