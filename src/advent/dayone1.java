package advent;


import java.io.File;
import java.util.Scanner;
public class dayone1 {

  public static int stringGather(String fileName){
    int total = 0;
    boolean firstNum = false;
    int first =0;
    int last = 0;
    try {
      File file=new File(fileName);
      Scanner sc=new Scanner(file);
      while(sc.hasNextLine()){
        String s = sc.nextLine();
        for (int i = 0; i < s.length(); i++) {
          int value = s.charAt(i) - '0';
          if(value >=0 && value <=9){
            if(!firstNum){
              first = value;
              firstNum = true;
            }
            last = value;
          }

        }
        first = first*10 + last;
        total += first;
        first =0;
        last = 0;
        firstNum = false;
      }
      return total;
    }
    catch(Exception e){
      System.out.println("error");
      return -1;
    }
  }
}
