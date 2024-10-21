package advent;


import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class dayone1 {
  static Map<String, String> map = new HashMap<>();


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
  public static int includingNumbers(String fileName){
    int total = 0;
    boolean firstNum = false;
    int first =0;
    int last = 0;
    map.put("one", "o1e");
    map.put("two", "t2o");
    map.put("three", "t3e");
    map.put("four", "f4r");
    map.put("five", "f5e");
    map.put("six", "s6x");
    map.put("seven", "s7n");
    map.put("eight", "e8t");
    map.put("nine", "n9e");
    try {
      File file=new File(fileName);
      Scanner sc=new Scanner(file);
      while(sc.hasNextLine()){
        String s = sc.nextLine();
        for (String s1 : map.keySet()) {
          s = s.replaceAll(s1, map.get(s1));
        }
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
  public static String getFirst(String s) {
    int first = 0;
    while (first != -1) {
      first = -1;
      int value = 0;
      String removal = "";
      for (String str : map.keySet()) {
        int idx = s.indexOf(str);
        if (idx != -1) {
          if (first == -1) {
            first = idx;
            value = map.get(str).charAt(0) - '0';
            removal = str;
          } else if (idx < first) {
            first = idx;
            value = map.get(str).charAt(0) - '0';
            removal = str;
          }
        }
      }
      if (first != -1)
        s = s.substring(0, first) + value + s.substring(first + removal.length());
    }
    return s;
  }

}
