import java.util.*;
class CaesarCipher
{
public static String encryption(String instr, int key)
{
instr = instr.toLowerCase();
String entxt = "";
for (int i = 0; i < instr.length(); i++)
{
char curr = instr.charAt(i);
curr = (char)(((((int)curr - 97) + key) % 26) + 97);
entxt += curr;
}
return entxt;
}
public static String decryption(String instr, int key)
{
int adj;
instr= instr.toLowerCase();
String dectxt = " ";
for (int i = 0; i < instr.length(); i++)
{
char curr = instr.charAt(i);
adj = 0;
int n = ((((int)curr-97)- key) % 26) + 97;
if (n < 97)
n = 122 - 97 + n + 1;
curr = (char)(n);
dectxt += curr;
}

return dectxt;
}
public static void main(String[] args)
{
Scanner sc = new Scanner(System.in);
System.out.println("Enter string: ");
String str = sc.nextLine();
System.out.println("Enter key: ");
int k = sc.nextInt();
System.out.println("Enter choice: \n1 for Encryption:\n2 for Decryption:\n ");
int ch = sc.nextInt();
switch (ch)
{
case 1:
System.out.println("The Encrypted String is: " + encryption(str, k));
break;
case 2:
System.out.println("Decrypted String: " + decryption(str,k));
break;
default:
System.out.println("Invalid Choice");
}
}
}
