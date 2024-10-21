package advent;


import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class dayone1 {
  static Map<String, String> map = new HashMap<>();
  static Map<String, String> map1 = new HashMap<>();


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
  public static int includeNum2(String fileName){
    int total = 0;
    int iterationVal= 0;
    map1.put("one", "1");
    map1.put("two", "2");
    map1.put("three", "3");
    map1.put("four", "4");
    map1.put("five", "5");
    map1.put("six", "6");
    map1.put("seven", "7");
    map1.put("eight", "8");
    map1.put("nine", "9");
    try {
      File file = new File(fileName);
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        iterationVal = getFirst(s)*10+getLast(s);
        total += iterationVal;
        iterationVal = 0;
      }
      return total;
    }
    catch(Exception e){
      System.out.println("error");
      return total;
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
      return total;
    }
  }
  public static int getFirst(String s) {
    int first = -1;
      int value = 0;
      for (String str : map1.keySet()) {
        int idx = s.indexOf(str);
        if (idx != -1) {
          if (first == -1) {
            first = idx;
            value = map1.get(str).charAt(0) - '0';
          } else if (idx < first) {
            first = idx;
            value = map1.get(str).charAt(0) - '0';
          }
        }
      }
      for (String str : map1.values()) {
        int idx = s.indexOf(str);
        if (idx != -1) {
          if (first == -1) {
            first = idx;
            value = str.charAt(0) - '0';
          } else if (idx < first) {
            first = idx;
            value = str.charAt(0) - '0';
          }
        }
      }
    return value;
  }
  public static int getLast(String s) {
    int first = -1;
    int value = 0;
    for (String str : map1.keySet()) {
      int idx = s.lastIndexOf(str);
      if (idx != -1) {
        if (first == -1) {
          first = idx;
          value = map1.get(str).charAt(0) - '0';
        } else if (idx > first) {
          first = idx;
          value = map1.get(str).charAt(0) - '0';
        }
      }
    }
    for (String str : map1.values()) {
      int idx = s.lastIndexOf(str);
      if (idx != -1) {
        if (first == -1) {
          first = idx;
          value = str.charAt(0) - '0';
        } else if (idx > first) {
          first = idx;
          value = str.charAt(0) - '0';
        }
      }
    }
    return value;
  }


}
