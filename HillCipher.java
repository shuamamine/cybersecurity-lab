import java.util.Scanner;
class HillCipher {
    static String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    int[][] generateKeyMatrix(String key) 
	   {
        int length = key.length();
        int size = (int) Math.ceil(Math.sqrt(length));
        key = key + str.substring(0, size * size - length);
        char[][] keyMatrix = new char[size][size];
        int index = 0;
       for (int i = 0; i < size; i++)
       {
            for (int j = 0; j < size; j++)
             {
                keyMatrix[i][j] = key.charAt(index++);
            }
        }
          int[][] keyindices = new int[size][size];
          for (int i = 0; i < size; i++) {
              for (int j = 0; j < size; j++) {
                  keyindices[i][j] = str.indexOf(keyMatrix[i][j]);
              }
          }
          return keyindices;
  }
  int[][] txtmatrix(String str1, int size, int start)
   {
          int txtmat[][] = new int[size][1];
          int index = start;
          for (int i = 0; i < size; i++) {
              txtmat[i][0] = str.indexOf(str1.charAt(index++));
          }
          return txtmat;
  }
  String encryption(String key, String ptext)
  {
          int length = key.length();
          int size = (int) Math.ceil(Math.sqrt(length));
          String enctxt = "";
          int keyMatrix[][] = generateKeyMatrix(key);
          for (int i = 0; i < ptext.length(); i += size)
         {
              int txtmat[][] = txtmatrix(ptext, size, i);
              int resMatrix[][] = new int[size][1];          
              for (int j = 0; j < size; j++) 
          {
                  resMatrix[j][0] = 0;
                  for (int k = 0; k < size; k++) {
                      resMatrix[j][0] += keyMatrix[j][k] * txtmat[k][0];
                  }
                  resMatrix[j][0] %= 26;
              }  
              for (int j = 0; j < size; j++) 
          {
                  enctxt += str.charAt(resMatrix[j][0]);
              }
          }
          return enctxt;
    }
  int modInverse(int num, int modulo)
  {
          num = num % modulo;
          for (int x = 1; x < modulo; x++) {
              if ((num * x) % modulo == 1)
                  return x;
          }
          return -1;
  }
  int[][] computeInverseKeyMatrix(int[][] mat, int size)
  {
          int det = (mat[0][0] * mat[1][1]) - (mat[0][1] * mat[1][0]);
          det = ((det % 26) + 26) % 26;
          int detinv = modInverse(det, 26);
          if (detinv == -1) 
          {
              throw new ArithmeticException("Matrix is not invertible");
          }
          int[][] adjgmat = {{ mat[1][1], -mat[0][1] }, { -mat[1][0], mat[0][0] }};
          int[][] invmat = new int[size][size];
          for (int i = 0; i < size; i++) {
              for (int j = 0; j < size; j++) {
                  invmat[i][j] = (adjgmat[i][j] * detinv) % 26;
                  if (invmat[i][j] < 0) {
                      invmat[i][j] += 26;
                  }
              }
          }
          return invmat;
      }
  String decryption(String key, String ciphertxt)
   {
          int length = key.length();
          int size = (int) Math.ceil(Math.sqrt(length));
          int inverseKeyMatrix[][] = computeInverseKeyMatrix(generateKeyMatrix(key), size);
          String dectxt = "";
          for (int i = 0; i < ciphertxt.length(); i += size) {
              int txtmat[][] = txtmatrix(ciphertxt, size, i);
              int resMatrix[][] = new int[size][1];
              
              for (int j = 0; j < size; j++) {
                  resMatrix[j][0] = 0;
                  for (int k = 0; k < size; k++) {
                      resMatrix[j][0] += inverseKeyMatrix[j][k] * txtmat[k][0];
                  }
                  resMatrix[j][0] %= 26;
                  if (resMatrix[j][0] < 0) {
                      resMatrix[j][0] += 26;
                  }
              }
              
              for (int j = 0; j < size; j++) {
                  dectxt += str.charAt(resMatrix[j][0]);
              }
          }
          return dectxt;
   }
  }
  class Main
  {
  public static void main(String args[])
   {
          Scanner sc = new Scanner(System.in);
          System.out.println("Enter secret key: ");
          String key = sc.nextLine();
          System.out.println("Enter text: ");
          String inputtxt = sc.nextLine();
          System.out.println("1. Encryption\n 2. Decrypt\n");
          System.out.println("Enter your choice: ");
          int ch = sc.nextInt();
          HillCipher ob = new HillCipher();
          String outputtxt = "";
          switch(ch) 
          {
              case 1:
                  outputtxt = ob.encryption(key, inputtxt);
                  System.out.println("Encrypted Text: " + outputtxt);
                  break;
              case 2:
                  outputtxt = ob.decryption(key, inputtxt);
                  System.out.println("Decrypted Text: " + outputtxt);
                  break;
              default:
                  System.out.println("Invalid choice!");
                  break;
          }
      }
  }
    
