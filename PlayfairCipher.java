import java.util.*; 
public class PlayfairCipher 
{
private static char[][] mat = new char[5][5]; 
private static final String str = "ABCDEFGHIKLMNOPQRSTUVWXYZ"; 
public static void main(String[] args)  
{ 
Scanner sc = new Scanner(System.in); 
System.out.println("Enter the key for the cipher:"); 
String key = sc.nextLine().toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I"); matrix(key); 
System.out.println("Enter text to encrypt or decrypt (letters only):"); String txt = sc.nextLine().toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I"); System.out.println("Choose an operation:\n1. Encryption:\n2. Decryption:\n"); int ch = sc.nextInt(); 
switch (ch) { 
case 1: 
String enctxt = encryption(txt); 
System.out.println("Encrypted Text: " + enctxt); 
break; 
case 2: 
String dectxt = decryption(txt); 
System.out.println("Decrypted Text: " + dectxt); 
break; 
default: 
System.out.println("Invalid choice."); 
break; 
}
} 
private static void matrix(String key) { 
boolean[] usedch = new boolean[26]; int r = 0, c = 0; 
for (char letter : key.toCharArray())  { 
if (!usedch[letter - 'A'])  
 { 
mat[r][c] = letter; 
usedch[letter - 'A'] = true; 
c++; 
if (c == 5) 
 { 
c = 0; 
r++; 
} 
} 
} 
for (char letter : str.toCharArray())  { 
if (!usedch[letter - 'A']) { 
mat[r][c] = letter; 
usedch[letter - 'A'] = true; 
c++; 
if (c == 5) 
 {
c = 0; 
r++; 
} 
} 
} 
} 
private static String encryption(String ptext)  
{ 
ptext = formatInstr(ptext); 
StringBuilder enctxt = new StringBuilder(); 
for (int i = 0; i < ptext.length(); i += 2)  
 { 
char fchar = ptext.charAt(i); 
char schar = ptext.charAt(i + 1); 
enctxt.append(applyCipherRules(fchar, schar, true)); } 
return enctxt.toString(); 
} 
private static String decryption(String ctext) 
{ 
StringBuilder dectxt = new StringBuilder(); 
for (int i = 0; i < ctext.length(); i += 2) { 
char fchar = ctext.charAt(i); 
char schar = ctext.charAt(i + 1); 
dectxt.append(applyCipherRules(fchar, schar, false)); }
return dectxt.toString(); 
} 
private static String applyCipherRules(char fchar, char schar, boolean encrypt)  { 
int[] pfirst = getposmatrix(fchar); 
int[] psecond = getposmatrix(schar); 
if (pfirst[0] == psecond[0]) 
{ 
return "" + mat[pfirst[0]][(pfirst[1] + (encrypt ? 1 : 4)) % 5] + 
mat[psecond[0]][(psecond[1] + (encrypt ? 1 : 4)) % 5]; 
}  
else if (pfirst[1] == psecond[1]) 
{  
return "" + mat[(pfirst[0] + (encrypt ? 1 : 4)) % 5][pfirst[1]] + 
mat[(psecond[0] + (encrypt ? 1 : 4)) % 5][psecond[1]]; 
}  
 else 
 {  
return "" + mat[pfirst[0]][psecond[1]] + mat[psecond[0]][pfirst[1]]; } 
} 
private static int[] getposmatrix(char letter) 
{ 
for (int i = 0; i < 5; i++)  
 { 
for (int j = 0; j < 5; j++) 
{ 
if (mat[i][j] == letter) 
{ 
return new int[]{i, j}; 
} 
} 
} 
return null; 
} 
private static String formatInstr(String instr) 
{ 
StringBuilder ftext = new StringBuilder(); 
for (int i = 0; i < instr.length(); i++)  
 { 
char curr = instr.charAt(i); 
if (i < instr.length() - 1 && curr == instr.charAt(i + 1)) { 
ftext.append(curr).append('X'); 
}  
else 
{ 
ftext.append(curr); 
} 
} 
if (ftext.length() % 2 != 0) { 
ftext.append('X'); 
}
return ftext.toString(); 
} 
}
