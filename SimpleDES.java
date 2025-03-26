import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
public class SimpleDES
{
public static void main(String[] args) throws Exception
{
KeyGenerator keyGen = KeyGenerator.getInstance("DES");
SecretKey secretKey = keyGen.generateKey();
  String plaintext = "HELLO WORLD";
Cipher cipher = Cipher.getInstance("DES");
cipher.init(Cipher.ENCRYPT_MODE,secretKey);
String encryptedText =
Base64.getEncoder().encodeToString(cipher.doFinal(plaintext.getBytes()));
cipher.init(Cipher.DECRYPT_MODE, secretKey);
String decryptedText = new
String(cipher.doFinal(Base64.getDecoder().decode(encryptedText)));
System.out.println("Original: " + plaintext);
System.out.println("Encrypted: " + encryptedText);
System.out.println("Decrypted: " + decryptedText);
}
}
