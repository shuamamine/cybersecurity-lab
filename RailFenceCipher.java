public class RailFenceCipher
{
public static String encrypt(String text, int rails)
{
text=text.toUpperCase();
if (rails <= 1)
return text;
StringBuilder[] rail = new StringBuilder[rails];
for (int i = 0; i < rails; i++)
rail[i] = new StringBuilder(); int dir = 1, row = 0;
for (char c : text.toCharArray()) {
rail[row].append(c);
if (row == 0)
dir = 1;
else if (row == rails - 1)
dir = -1;
row += dir;
}
StringBuilder result = new StringBuilder();
for (StringBuilder sb : rail)
result.append(sb);
return result.toString();
}
public static String decrypt(String text, int rails)
  {
text=text.toUpperCase();
if (rails <= 1)
return text;
int len = text.length();
char[] decrypted = new char[len];
int[] pos = new int[rails];
int dir = 1, row = 0;
for (int i = 0; i < len; i++) {
pos[row]++;
if (row == 0)
dir = 1;
else if (row == rails - 1)
dir = -1;
row += dir;
}
int index = 0;
StringBuilder[] rail = new StringBuilder[rails];
for (int i = 0; i < rails; i++) {
rail[i] = new StringBuilder(text.substring(index, index + pos[i]));
index += pos[i];
}
row = 0; dir = 1;
for (int i = 0; i < len; i++) {
decrypted[i] = rail[row].charAt(0);
rail[row].deleteCharAt(0);
if (row == 0)
dir = 1;
else if (row == rails - 1)
dir = -1;
row += dir;
}
return new String(decrypted);
}
public static void main(String[] args)
{
String message = "hello from the other side";
int rails = 3;
String encrypted = encrypt(message.replaceAll(" ", ""), rails);
System.out.println("Encrypted: " + encrypted);
String decrypted = decrypt(encrypted, rails);
System.out.println("Decrypted: " + decrypted);
}
}
