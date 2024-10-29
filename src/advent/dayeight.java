package advent;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class dayeight {
  public static long camelFind(String fileName){
    try(Scanner sc = new Scanner(new File(fileName))){
      long total = 0;
      String directions = sc.nextLine();
      Map<String, Pair> values = new HashMap<>();
      while(sc.hasNextLine()){
        String s = sc.nextLine();
        if(s.length() != 0) {
          String key = s.split(" ")[0];
          s = s.split("\\(")[1];
          s = s.replaceAll("\\s", "");
          s = s.substring(0,s.length()-1);
          Pair value  = new Pair(s.split(",")[0], s.split(",")[1]);
          values.put(key, value);


        }
      }
      String start = "AAA";
      int index = 0;
      while(!start.equals("ZZZ")){
        boolean left = directions.charAt(index++%directions.length()) == 'L';
        if(left){
          start = values.get(start).left;
          total++;
        }
        else{
          start = values.get(start).right;
          total++;
        }
      }

      return total;
    }
    catch(Exception e){
      System.out.println(e);
      return -1;
    }
  }
  public static long camelEnding(String fileName){
    try(Scanner sc = new Scanner(new File(fileName))){
      long total = 0;
      String directions = sc.nextLine();
      Map<String, Pair> values = new HashMap<>();
      List<String> pointers = new ArrayList<>();
      while(sc.hasNextLine()){
        String s = sc.nextLine();
        if(s.length() != 0) {
          String key = s.split(" ")[0];
          if(key.charAt(key.length()-1) == 'A'){
            pointers.add(key);
          }
          s = s.split("\\(")[1];
          s = s.replaceAll("\\s", "");
          s = s.substring(0,s.length()-1);
          Pair value  = new Pair(s.split(",")[0], s.split(",")[1]);
          values.put(key, value);


        }
      }
      List<Long> longValues = new ArrayList<>();

      for(int i=0; i<pointers.size(); ++i){
        boolean allTrue = false;
        total = 0;
        int index = 0;
        while(!allTrue){
        allTrue = true;
        boolean left = directions.charAt(index++%directions.length()) == 'L';

          String checker;
          if(left){
             checker = values.get(pointers.get(i)).left;

          }
          else{
             checker = values.get(pointers.get(i)).right;

          }

          allTrue = checker.charAt(checker.length()-1) == 'Z' && allTrue;
          pointers.set(i, checker);
          total++;

        }
        longValues.add(total);





      }
      long divider = 1;
      for(int i =0; i<longValues.size(); i++){
        long v1 = longValues.get(i);
        divider = (divider*v1/gcd(v1,divider));

      }
      return divider;


    }
    catch(Exception e){
      System.out.println(e);
      return -1;
    }
  }
  public static long gcd(long left, long right){
    if(left < right){
      long t = left;
      left = right;
      right = t;
    }
    while(right!= 0) {
      long t = right;
      right = left % right;
       left = t;
    }
    return left;
  }

  public static class Pair{
    public String left;
    public String right;
    public Pair(String left, String right){
      this.left = left;
      this.right = right;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Pair pair = (Pair) o;
      return Objects.equals(left, pair.left) && Objects.equals(right, pair.right);
    }

    @Override
    public int hashCode() {
      return Objects.hash(left, right);
    }

    @Override
    public String toString() {
      return "pair{" +
              "left='" + left + '\'' +
              ", right='" + right + '\'' +
              '}';
    }
  }
}
