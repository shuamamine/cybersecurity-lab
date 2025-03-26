import java.math.BigInteger;
import java.util.Scanner;
public class RSA
{
public static void main(String[] args)
{
Scanner sc= new Scanner(System.in);
System.out.print("Enter first prime number (p): ");
int p = sc.nextInt();
System.out.print("Enter second prime number (q): ");
int q = sc.nextInt();
int n = p * q;
int phi = (p - 1) * (q - 1);
System.out.print("Enter public key exponent (e): ");
int e = sc.nextInt();
BigInteger E = BigInteger.valueOf(e);
BigInteger PHI = BigInteger.valueOf(phi);
BigInteger d = E.modInverse(PHI);
System.out.print("Enter plaintext (PT): ");
int pt = sc.nextInt();

BigInteger PT = BigInteger.valueOf(pt);
BigInteger N = BigInteger.valueOf(n);
BigInteger CT = PT.modPow(E, N);
BigInteger decryptedPT = CT.modPow(d, N);

8

System.out.println("Public Key: (" + e + ", " + n + ")");
System.out.println("Private Key: (" + d + ", " + n + ")");
System.out.println("Cipher Text (CT): " + CT);
System.out.println("Decrypted Plain Text (PT): " + decryptedPT);
}
}
