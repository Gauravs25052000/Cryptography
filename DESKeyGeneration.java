import java.util.*;

/** This class Generated two 8-bit subkeys from 10-bit input key **/
class KeyGeneration
{
  private int[] key = new int[10];
  private int[] k1 = new int[8];
  private int[] k2 = new int[8];
  private boolean flag = false;
  
  KeyGeneration()
  {
    
  }
  
  void GenerateKeys(String inputkey )
  {
    int[] key = new int[10];
  
  
  char c1;
  String ts ;
  
  try
  {
  for(int i=0;i<10;i++)
    {
       c1 = inputkey.charAt(i);
       ts = Character.toString(c1);
       key[i] = Integer.parseInt(ts);
     
     if(key[i] !=0 && key[i]!=1)
     {
    Print.msg("\n .. Invalid Key ..");
    System.exit(0);
    return ;
     }
    }
  }
  catch(Exception e)
  {
    Print.msg("\n .. Invalid Key .. ");
    System.exit(0);
    return ;
    
  }
    this.key = key;
    
    Print.msg("Input Key : ");
      Print.array(this.key,10);
      Print.msg("\n");
    
      permutationP10();
    
      Print.msg("After Permutation(P10) Key : ");
      Print.array(this.key,10);
      Print.msg("\n");
    
      leftshiftLS1();
    
      Print.msg("After LeftShift LS-1 Key : ");
      Print.array(this.key,10);
      Print.msg("\n");
    
    
      this.k1 = permutationP8();

     Print.msg("Subkey K1 Generated : ");
     Print.array(this.k1,8);
     Print.msg("\n");
    
      leftshiftLS2();
  
     Print.msg("After LeftShift LS-2 Key : ");
     Print.array(this.key,10);
   Print.msg("\n");
    
     this.k2 = permutationP8();
     Print.msg("Subkey K2 Generated : ");
     Print.array(this.k2,8);
     Print.msg("\n"); 
    
    flag = true;

}
  
  /** Perform permutation P10 on 10-bit key 
  P10(k1, k2, k3, k4, k5, k6, k7, k8, k9, k10) = (k3, k5, k2, k7, k4, k10, k1, k9, k8, k6)
  **/
   
  private void permutationP10()
  {
    int[] temp = new int[10];
    
    temp[0] = key[2];
    temp[1] = key[4];
    temp[2] = key[1];
    temp[3] = key[6];
    temp[4] = key[3];
    temp[5] = key[9];
    temp[6] = key[0];
    temp[7] = key[8];
    temp[8] = key[7];
    temp[9] = key[5];
    
    
    key = temp;
        
  }
  
  /** Performs a circular left shift (LS-1), or rotation, separately on the first
five bits and the second five bits. **/

  private void leftshiftLS1()
  {
    int[] temp = new int[10];
    
    temp[0] = key[1];
    temp[1] = key[2];
    temp[2] = key[3];
    temp[3] = key[4];
    temp[4] = key[0];
    
    temp[5] = key[6];
    temp[6] = key[7];
    temp[7] = key[8];
    temp[8] = key[9];
    temp[9] = key[5];
    
    key = temp;
    
  }
  
  /** apply Permutaion P8, which picks out and permutes 8 of the 10 bits according to the following 
  rule: P8[ 6 3 7 4 8 5 10 9 ] , 8-bit subkey is returned **/
  private int[] permutationP8()
  {
    int[] temp = new int[8];
    
    temp[0] = key[5];
    temp[1] = key[2];
    temp[2] = key[6];
    temp[3] = key[3];
    temp[4] = key[7];
    temp[5] = key[4];
    temp[6] = key[9];
    temp[7] = key[8];
    
    return temp;
        
  }
  
  
  private void leftshiftLS2()
  {
    int[] temp = new int[10];
    
    temp[0] = key[2];
    temp[1] = key[3];
    temp[2] = key[4];
    temp[3] = key[0];
    temp[4] = key[1];
    
    temp[5] = key[7];
    temp[6] = key[8];
    temp[7] = key[9];
    temp[8] = key[5];
    temp[9] = key[6];
    
    key = temp;
    
  }


public int[] getK1()
{
  if(!flag)
    {
      Print.msg("\nError Occured: Keys are not generated yet ");
      return null;
    }
    return k1;
}

public int[] getK2()
{
  if(!flag)
    {
      Print.msg("\nError Occured: Keys are not generated yet ");
      return null;
    }
    return k2;
}  

}

class DESKeyGeneration
 {
  public static void main(String[] args)
  {
    
    KeyGeneration KG = new KeyGeneration();
    Encryption enc  = new Encryption();
    Scanner sc = new Scanner(System.in);
    
  String pt ;
  String key;
  int[] ct = new int[8];
    
    try
    {  
  //Ex Input : 1010000010       
    System.out.print("Enter 10-bit Key : ");
    key = sc.next();
    
  
  System.out.println(" \n ");
  
  Print.msg("\n Key Generation ...\n");
  Print.msg("\n---------------------------------------\n");
  KG.GenerateKeys(key);
  
  
  Print.msg("\n---------------------------------------\n");    
    }
    catch(InputMismatchException e)
    {
      System.out.println("-- Error Occured : Invalid Input ");
    }
    catch(Exception e)
    {
      System.out.println("-- Error Occured : "+e);
    }
    
  }
}
  class Print
{
  /** Prints array to console  **/
  static void array(int[] arr,int len)
  {
    System.out.print(" - ");
   
    for(int i=0;i<len;i++)
    {
      System.out.print(arr[i] + " ");
    }
  }
  
  static void msg(String msg)
  {
    System.out.print(msg);
  }
}




