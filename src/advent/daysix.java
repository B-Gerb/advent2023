package advent;

import java.io.File;
import java.util.Scanner;

public class daysix {
  public static int timeSpent(String fileName){
    try (Scanner sc = new Scanner(new File(fileName)){
      return -1;
    }
    catch(Exception e){
      System.out.printf(e);
      return -1;
    }
  }
}
